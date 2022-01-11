import java.io.*;
import java.util.*;

/** program to find compression ratio using Huffman algorithm
 */
public class Main {

	public static void main(String[] args) throws IOException {

		long start = System.currentTimeMillis();
		FileReader reader = new FileReader(args[0]);
		Hashtable<Character, Integer> characters = new Hashtable<Character, Integer>();
		int originalFileSize = 0, compressedFilzeSize = 0, readChar = reader.read();
		while (readChar != -1) {
			originalFileSize+=8;
			char ch = (char) readChar;
			if (characters.containsKey(ch)) characters.put(ch, characters.get(ch)+1);
			else characters.put(ch, 1);
			readChar = reader.read();			
		}
		reader.close();
		PriorityQueue<Integer> nodes = new PriorityQueue<>();
		for(Map.Entry m:characters.entrySet()) {
			nodes.add((int) m.getValue());
		}
		while (nodes.size() > 1) {
			int x = nodes.poll();
			int y = nodes.poll();
			int f = x + y;
			compressedFilzeSize += f;
			nodes.add(f);
		}
		long end = System.currentTimeMillis();
		System.out.println("Original file length in bits = " + originalFileSize);
		System.out.println("Compressed file length in bits = " + compressedFilzeSize);
		System.out.println("Compression ratio = " + (double) compressedFilzeSize/originalFileSize);
		System.out.println("Elapsed time: " + (end - start) + " milliseconds");
	}

}
