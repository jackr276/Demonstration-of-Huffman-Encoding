import java.util.PriorityQueue;

public class HuffmanEncoder{
	private	PriorityQueue<HuffmanNode> queue;
	private HuffmanNode root;


	public HuffmanEncoder(char[] characters, int[] frequencies){
		this.root = null;

		queue = new PriorityQueue<HuffmanNode>(new HuffmanComparator());

		for(int i = 0; i < characters.length; i++){
			if(characters[i] == '\n'){
				continue;
			}

			System.out.println("Character: " + characters[i] + ", frequency: " + frequencies[i]);
			queue.add(new HuffmanNode(characters[i], frequencies[i]));
		}
	}


	public void generateHuffmanCodes(){
		while(this.queue.size() > 1){
			HuffmanNode x = queue.poll();

			HuffmanNode y = queue.poll();

			HuffmanNode f = new HuffmanNode();

			f.setData(x.getData() + y.getData());	
			f.setChar('$');

			f.setLeftChild(x);	
			f.setRightChild(y);	
			
			this.root = f;

			queue.add(f);
		}

		printCode(root, "");
	}


	public void printCode(HuffmanNode root, String byteString){
		if(root == null){
			return;
		}

		if(root.getLeft() == null && root.getRight() == null && root.getData() != '\n'){
			System.out.println(root.getChar() + ":" + byteString + ", Bytes needed: " + byteString.length());
			return;
		}

		printCode(root.getLeft(), byteString + "0");
		printCode(root.getRight(), byteString + "1");
	}
}
