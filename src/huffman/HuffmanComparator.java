/**
 * Author: Jack Robbins
 * This class implements the comparator for the priority queue used in the huffman encoder
 */

package huffman;

import java.util.Comparator;

public class HuffmanComparator implements Comparator<HuffmanNode> {
	//We are comparing the frequencies of the two nodes
	public int compare(HuffmanNode first, HuffmanNode second){
		return first.getFrequency() - second.getFrequency();
	}
}
