package com.kalyansury;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.stream.Stream;

/**
 * Bigram - Identifies and count bigrams from a file
 * 
 * <p/>
 * Ignores new line characters in the file
 * <p/>
 * Assumes that there are no special characters
 * <p/>
 * File read is not streamed, hence not suitable for huge files
 * <p/>
 * 
 * @author Kalyan Sury
 *
 */
public class Bigram {

	public HashMap<String, Long> histogram = new HashMap<String, Long>();

	public static void main(String[] args) {
		if (args == null || args.length == 0) {
			System.out.println("File name needed as argument");
		} else {
			Bigram bigram = new Bigram();
			bigram.iterator(bigram.readFile(args[0]));
			System.out.println(bigram.getHistogram());
		}
	}

	public String getHistogram() {
		return histogram.toString();
	}

	// Iterator method to iterate on the data from the file
	public int iterator(String fileData) {
		String[] values = fileData.split(" ");
		for (int i = 0; i < values.length - 1; i++) {
			count(values[i] + " " + values[i + 1]);
		}
		return histogram.size();
	}

	// Records the count of bigrams in lower case (converted)
	public long count(String bigram) {
		bigram = bigram.toLowerCase();
		Long iCount = histogram.get(bigram);
		iCount = (iCount != null) ? ++iCount : new Long(1);
		histogram.put(bigram, iCount);
		return iCount.longValue();
	}

	// Simple file reader to concatenate the lines and send it as one string
	public String readFile(String fileName) {
		Path path = null;
		try {
			path = Paths.get(getClass().getClassLoader().getResource(fileName).toURI());
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (path == null)
			return null;

		StringBuilder data = new StringBuilder();
		Stream<String> lines = null;
		try {
			lines = Files.lines(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lines.forEach(line -> data.append(line).append(" "));
		lines.close();

		return data.toString();
	}
}