package service.fetcher;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import radio.signal.Complex;

/**
 * Lettore di Segnali da file.
 * @author Daniel
 *
 */
public class SignalFetcher {

	private int countReal;
	private int countImg;
	private int length;
	private List<Complex> samples;
	private String path;

	public SignalFetcher(String path) {
		this.path = path;
		this.countReal=0;
		this.countImg=0;
		this.length=0;
		this.samples = new ArrayList<>();
	}

	/**
	 * Semplice metodo che legge segnali da file
	 */
	public void fetch() throws FileNotFoundException {
		BufferedReader reader = null;

		try {
			String line;
			reader = new BufferedReader(new FileReader(path));
			while ((line = reader.readLine()) != null)  
				fetchLine(line);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * legge numero complesso dalla riga di un file
	 */
	private void fetchLine(String line) {

		Scanner scanner = new Scanner(line);
		scanner.useDelimiter("\t");
		String realString = null;
		String imgString = null;
		if (scanner.hasNext()) {
			realString = scanner.next();
			countReal++;
		}
		if (scanner.hasNext()) {
			imgString = scanner.next();
			countImg++;
		}
		length++;
		Double realTemp = Double.valueOf(realString);
		Double imgTemp = Double.valueOf(imgString);

		Complex sample = new Complex(realTemp, imgTemp);
		samples.add(sample);
		scanner.close();
	}

	public int getLength() {
		return length;
	}

	public int getCountImg() {
		return countImg;
	}

	public int getCountReal() {
		return countReal;
	}

	public List<Complex> getSamples() {
		return samples;
	}

	public void setSamples(List<Complex> samples) {
		this.samples = samples;
	}

	public void setCountImg(int countImg) {
		this.countImg = countImg;
	}
	public void setCountReal(int countReal) {
		this.countReal = countReal;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getPath() {
		return path;
	}
}
