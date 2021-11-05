package io;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
public class Reader {

	FileReader fr;
	
	Reader(String fileName) {
		try {
			fr = new FileReader(fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	Reader(File file) {
		try {
			fr = new FileReader(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	

	public int read() {
		try {
			return fr.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}
}
