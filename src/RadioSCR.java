import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

import signal.Complex;
import signal.Noise;
import signal.Signal;
import signalprocessing.SignalProcessor;
import simulator.Simulator;
import writer.DataWriter;
import fetcher.Fetcher;



public class RadioSCR {
	private Fetcher fetcher;
	private String path;
	private Signal signal;

	public RadioSCR(String path) {
		this.path = path;
		this.fetcher = new Fetcher(path);
	}

	public double observe() {
		signal = new Signal();
		fetcher.fetch();
		int length =fetcher.getLength();
		signal.setSamples(fetcher.getSamples().toArray(new Complex[length]));
		double power = SignalProcessor.power(signal);
		return getSNRFromPower(power);
	}
	
	

	private double getSNRFromPower(double signalPower) {
		double noisePower = signalPower -1;
		double snrDB = 10*Math.log10(1/noisePower);
		return snrDB;
	}

	private void writeSignalOnFile(Signal generateSignalWithNoise) {
		DataWriter writer = new DataWriter();	
		writer.write(path, generateSignalWithNoise);
	}
	private Double[] getNoiseEnergies(int numberOfTests, double snr, int samplesLength) {
		Double[] noiseEnergies = new Double[numberOfTests];
		for (int i = 0; i < noiseEnergies.length; i++) {			
			noiseEnergies[i] = SignalProcessor.power(new Noise(snr, samplesLength));
		}
		return noiseEnergies;
	}

	public double determineThreshold(Double[] noiseEnergies, double probabilityFA) throws Exception {
		double threshold = averageEnergy(noiseEnergies);
		threshold += Math.sqrt(2*variance(noiseEnergies)) * invErf(probabilityFA);
		return threshold;
	}

	private double variance(Double[] noiseEnergies) {
		double average2 = Math.pow(this.averageEnergy(noiseEnergies), 2);
		double variance = vqm(noiseEnergies) - average2;
		return variance;
	}

	private double vqm(Double[] noiseEnergies) {
		int length = noiseEnergies.length;
		double probability = 1.0/length;
		double vqm = 0;
		for (int i = 0; i < length; i++) {
			vqm += Math.pow(noiseEnergies[i], 2)*probability;
		}
		return vqm;
	}

	private double averageEnergy(Double[] noiseEnergies) {
		double noiseEnergySum = 0.0;
		for (int i = 0; i < noiseEnergies.length; i++) {
			noiseEnergySum += noiseEnergies[i];
		}
		double averageNoiseEnergy = noiseEnergySum/noiseEnergies.length;
		return  averageNoiseEnergy;
	}

	
	
	public double invErf(double d) throws Exception {
		if (Math.abs(d)>1) {
			throw new Exception ("Allowed values for argument in [-1,1]");
		}
		if (Math.abs(d) == 1) {
			return (d==-1 ? Double.NEGATIVE_INFINITY :
				Double.POSITIVE_INFINITY);
		}
		else {
			if (d==0) {
				return 0;
			}
			BigDecimal bd = new BigDecimal(0, MathContext.UNLIMITED);
			BigDecimal x = new
					BigDecimal(d*Math.sqrt(Math.PI)/2,MathContext.UNLIMITED);
			//System.out.println(x);
			String[] A092676 = {"1", "1", "7", "127", "4369", "34807",
					"20036983", "2280356863", 
					"49020204823", "65967241200001",
					"15773461423793767",
					"655889589032992201",
					"94020690191035873697", "655782249799531714375489",
					"44737200694996264619809969",
					"10129509912509255673830968079", "108026349476762041127839800617281",
					"10954814567103825758202995557819063",
					"61154674195324330125295778531172438727",
					"54441029530574028687402753586278549396607",
					"452015832786609665624579410056180824562551",
					"2551405765475004343830620568825540664310892263",
					"70358041406630998834159902148730577164631303295543",
					"775752883029173334450858052496704319194646607263417",
			"132034545522738294934559794712527229683368402215775110881"};
			String[] A092677 = {"1", "3", "30", "630", "22680", "178200",
					"97297200", "10216206000", 
					"198486288000", "237588086736000",
					"49893498214560000", 
					"1803293578326240000",
					"222759794969712000000","1329207696584271504000000",
					"77094046401887747232000000",
					"14761242414008506896480000000", "132496911908140357902804480000000",
					"11262237512191930421738380800000000",
					"52504551281838779626144331289600000000",
					"38905872499842535702972949485593600000000",
					"268090886133368733415443853598208000000000",
					"1252532276140582782027102181569679872000000000",
					"28520159927721069946757116674341610685440000000000",
					"259078091444256105986928093487086396226560000000000",
			"36256424429074976496234665114956818633529712640000000000"};
			for (int i = 0; i < A092676.length; i++) {                
				BigDecimal num = new BigDecimal(new BigInteger(A092676[i]),50);
				BigDecimal den = new BigDecimal(new BigInteger(A092677[i]),50);
				BigDecimal coeff = num.divide(den, RoundingMode.HALF_UP);
				//System.out.println(coeff);
				BigDecimal xBD = x.pow(i*2+1, MathContext.UNLIMITED);           
				bd = bd.add(xBD.multiply(coeff, MathContext.UNLIMITED));       
			}            
			return bd.doubleValue();            
		}
	}


	public static void main(String[] args) throws Exception {
		RadioSCR radio = new RadioSCR("/Users/Daniel/Desktop/workspace_sdr/HW1/test/mio_output.dat");
		radio.writeSignalOnFile(Simulator.generateSignalWithNoise(1000000, 1.0/250));
		double snr = radio.observe();
		System.out.println("SNR: " + snr);
		Double[] noiseEnergies= radio.getNoiseEnergies(10000, snr, 1000);
		if (noiseEnergies != null)
			System.out.println("ho il vettore");
		double threshold = radio.determineThreshold(noiseEnergies, 1/10000.0);
		System.out.println(threshold);
		
		int numeroDiProve = 100;
		double detectionPercent = radio.compareSignalWithThreshold(threshold, numeroDiProve );
		System.out.println("Detection" + detectionPercent + "%");
	}

	private double compareSignalWithThreshold(double threshold, int numeroDiProve) {
		int numberOfTotalSamples = signal.getSamples().length;
		int numberOfSamples = numberOfTotalSamples/numeroDiProve;
		Double[] powers = new Double[numeroDiProve]; 
		for (int i = 0; i < numeroDiProve; i++) {
			Signal sig = new Signal();
			setSamplesToSignal(sig, signal, numberOfSamples, i);
			double signalPower = SignalProcessor.power(sig);
			powers[i] = signalPower;
		}
		int count = 0;
		for(int i = 0; i < numeroDiProve; i++) {
			if(powers[i] >= threshold) {
				count++;
			}
		}
		return count;
	}

	private void setSamplesToSignal(Signal sig, Signal signal2, int numberOfSamples, int test) {
		sig.setSamples(new Complex[numberOfSamples]);
		for (int i = 0; i < numberOfSamples; i++) {
			sig.setSample(i, signal.getSample(i*test));
		}
	}
}
