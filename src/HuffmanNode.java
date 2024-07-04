public class HuffmanNode{
	private int data;
	private char c;

	private HuffmanNode leftChild;
	private HuffmanNode rightChild;

	public HuffmanNode(){}

	public HuffmanNode(char c, int data){
		this.data = data;
		this.c = c;
		this.leftChild = this.rightChild = null;
	}

	public void setData(int newData){
		this.data = newData;
	}

	public void setChar(char newChar){
		this.c = newChar;
	}

	public int getData(){
		return this.data;
	}

	public char getChar(){
		return this.c;
	}

	public void setLeftChild(HuffmanNode leftChild){
		this.leftChild = leftChild;
	}

	public void setRightChild(HuffmanNode rightChild){
		this.rightChild = rightChild;
	}

	public HuffmanNode getLeft(){
		return this.leftChild;
	}

	public HuffmanNode getRight(){
		return this.rightChild;
	}

}
