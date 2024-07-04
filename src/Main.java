/**
 * Author: Jack Robbins
 * This runnner class reads in a given file, parses it character by character and saves
 * the character frequencies into a Map that is passed along to the HuffmanEncoder
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import huffman.HuffmanEncoder;

public class Main{
	/**
	 * AccessPoint method -- generates a frequency map and passes it to the Encoder
	 * @param args not used
	 */
	public static void main(String[] args){
		//We will store our characters and frequencies in a map	
		Map<Character, Integer> frequencies = new HashMap<Character, Integer>();

		//Grab the file from the user
		String fileName;
		Scanner in = new Scanner(System.in);

		System.out.print("Enter the file you would like to get the table for: ");
		fileName = in.next();	
		in.close();

		File fl = new File(fileName);
		char[] file = new char[1];

		try {
			//Grab the entirety of our file
			FileReader fr = new FileReader(fl);
			BufferedReader br = new BufferedReader(fr);
			file = new char[(int)fl.length()];
			br.read(file);
			br.close();
	
			//For each character in the file, count the frequency
			char c;
			for(int i = 0; i < file.length; i++){
				c = file[i];

				//If we have seen the character before
				if(frequencies.keySet().contains(c)){
					frequencies.put(c, frequencies.get(c) + 1);
				} else {
					//If we haven't seen it before
					frequencies.put(c, 1);
				}
			}
			

		//Let the user know of any exceptions and terminate appropriately
		} catch (FileNotFoundException FNFE){
			System.out.println(FNFE.getMessage());
			System.exit(1);
		} catch (IOException IOE){
			System.out.println(IOE.getMessage());
			System.exit(1);
		}

		//Once we have a good map, pass it along to our HuffmanEncoder
		HuffmanEncoder h = new HuffmanEncoder(frequencies);
		h.generateHuffmanCodes();
		System.out.println("Initial File Size: " + file.length + " bytes\n");
		System.out.println("===================================================");
	}
}
