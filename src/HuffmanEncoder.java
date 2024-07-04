import java.util.PriorityQueue;
import java.util.Map;

public class HuffmanEncoder{
	private	PriorityQueue<HuffmanNode> queue;
	private Map<Character, Integer> freqs;
	private HuffmanNode root;
	int totalBits;

	public HuffmanEncoder(char[] characters, int[] frequencies, Map<Character, Integer> freqs){
		this.root = null;
		this.totalBits = 0;
		this.freqs = freqs;
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
		System.out.println("\n=============== Huffman Codes =====================\n");
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

		System.out.println("\nTotal bytes needed: " + totalBits / 8);

		System.out.println("===================================================");
	}


	public void printCode(HuffmanNode root, String byteString){
		if(root == null){
			return;
		}

		if(root.getLeft() == null && root.getRight() == null && root.getData() != '\n'){
			System.out.printf("Character: %c, Encoding: %13s, Bits needed: %2d\n", root.getChar(), byteString, byteString.length());
			totalBits += byteString.length() * freqs.get(root.getChar());
			return;
		}

		printCode(root.getLeft(), byteString + "0");
		printCode(root.getRight(), byteString + "1");
	}
}
