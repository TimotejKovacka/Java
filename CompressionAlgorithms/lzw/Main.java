import java.io.*;
import java.util.*;

/** program to find compression ratio using LZW compression
 */
public class Main {

	/**
	 * @param args
	 * @throws IOException
	 */
	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		long start = System.currentTimeMillis();
		FileReader reader = new FileReader(args[0]);
		Trie dictionary = new Trie(8);
		for(int i = 0; i <= 127; i++) {  
			dictionary.insert("" +(char) i);
		}
		int originalFileSize = 0, compressedFilzeSize = 0, readChar = reader.read();
		String s = "";
		while (readChar != -1) {
			originalFileSize += 8;
			char ch = (char) readChar;
			if (dictionary.search(s + ch)) {
				s+= ch;
			} else {
				compressedFilzeSize += dictionary.getCodewordLength();
				dictionary.insert(s+ch);
				s=Character.toString(ch);
			}
			readChar = reader.read();
		}
		reader.close();
		compressedFilzeSize += dictionary.getCodewordLength();
		long end = System.currentTimeMillis();
		System.out.println("Original file length in bits = " + originalFileSize);
		System.out.println("Compressed file length in bits = " + compressedFilzeSize);
		System.out.println("Compression ratio = " + (float) compressedFilzeSize/originalFileSize);
		System.out.println("Elapsed time: " + (end - start) + " milliseconds");
	}

}
