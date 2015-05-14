package service.writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import radio.signal.AbstractSignal;
import radio.signal.Complex;

public class SignalWriter {
	
	public void write(String path, AbstractSignal s) {
		try {
			 
			File file = new File(path);
 
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			Complex[] samples = s.getSamples();
			for (Complex sample: samples) {
				bw.write(sample.toString());
				bw.newLine();
			}
			bw.close();
 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
