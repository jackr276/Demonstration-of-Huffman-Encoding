import java.util.Comparator;

public class HuffmanComparator implements Comparator<HuffmanNode> {
	public int compare(HuffmanNode first, HuffmanNode second){
		return first.getData() - second.getData();
	}
}
