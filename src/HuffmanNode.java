/**
 * Author: Jack Robbins
 * This class represents one Node within the huffman tree. Within this node we store the 
 * frequency of a given character and the character itself
 */
public class HuffmanNode{	
	//ASCII character
	private char c;
	//How often does it occur?
	private int frequency;
	//For tree functionality
	private HuffmanNode leftChild;
	private HuffmanNode rightChild;


	/**
	 * Constructor if we know the frequency and character
	 */
	public HuffmanNode(char c, int frequency){
		this.frequency = frequency;
		this.c = c;
		this.leftChild = this.rightChild = null;
	}

	
	/**
	 * Setter for frequency
	 */
	public void setFrequency(int frequency){
		this.frequency = frequency;
	}


	/**
	 * Setter for c
	 */
	public void setChar(char newChar){
		this.c = newChar;
	}


	/**
	 * Getter for frequency
	 */
	public int getFrequency(){
		return this.frequency;
	}


	/**
	 * Getter for c
	 */
	public char getChar(){
		return this.c;
	}


	/**
	 * Left child setter
	 */
	public void setLeftChild(HuffmanNode leftChild){
		this.leftChild = leftChild;
	}


	/**
	 * Right child setter
	 */
	public void setRightChild(HuffmanNode rightChild){
		this.rightChild = rightChild;
	}


	/**
	 * Left child getter
	 */
	public HuffmanNode getLeft(){
		return this.leftChild;
	}


	/**
	 * Right child getter
	 */
	public HuffmanNode getRight(){
		return this.rightChild;
	}

}
