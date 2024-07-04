import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main{
	public static void main(String[] args){
		Map<Character, Integer> frequencies = new HashMap<Character, Integer>();
		String fileName;
		Scanner in = new Scanner(System.in);
		System.out.print("Enter the file you would like to get the table for: ");
		fileName = in.next();	
		in.close();
		File fl = new File(fileName);

		try {
			FileReader fr = new FileReader(fl);
			BufferedReader br = new BufferedReader(fr);
			char[] file = new char[(int)fl.length()];
			br.read(file);
			br.close();

			char c;
			for(int i = 0; i < file.length; i++){
				c = file[i];
				if(frequencies.keySet().contains(c)){
					frequencies.put(c, frequencies.get(c) + 1);
				} else {
					frequencies.put(c, 1);
				}
			}

			in.close();
		} catch (FileNotFoundException FNFE){
			System.out.println(FNFE.getMessage());
		} catch (IOException IOE){
			System.out.println(IOE.getMessage());
		}
	
		char[] characters = new char[frequencies.keySet().toArray().length];
		int[] charFreq = new int[characters.length];

		for(int i = 0; i < characters.length; i++){
			characters[i] = (char)frequencies.keySet().toArray()[i];
			charFreq[i] = frequencies.get(characters[i]);	
		}				

		HuffmanEncoder h = new HuffmanEncoder(characters, charFreq, frequencies);
		h.generateHuffmanCodes();
	}
}
