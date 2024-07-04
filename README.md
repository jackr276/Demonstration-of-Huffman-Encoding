# Demonstration of Huffman Encoding
Author: Jack Robbins

## Introduction
Huffman encoding is an efficient and lossless data compression algorithm that uses statistics, specifically occurrence frequency, to generate variable length codes that are assigned to each character. This allows more frequent characters to be assigned shorter codes and therefore reduce the overall size of the file. The real reduction amount is usually around 45%-50%, but this can vary based on the frequency levels and distribution in a given set of ASCII text. 

## Huffman Encoding Algorithm
The Huffman encoding algorithm works by building a binary tree from the ground up. To run the algorithm, we must first have a list of all characters and their respective frequencies in the given text file. Once we have this, we take each character and its associated frequency and bundle them into an object, and then we place all of these objects into a priority queue. The lower the frequency, the higher the priority and the sooner this object will be dequeued. This allows us to use the queue to build the tree from the ground up, starting with the least frequent characters and eventually working our way up to the most frequent one. More frequent characters are always higher up on the tree. This way, when we parse the tree, more frequent characters end up with shorter codes, achieving the compression that we desire.

Here is the algorithm in full for creating the Huffman tree:
```
Algorithm generateHuffmanTree(Map<Character, Integer> frequencies){

  char[] characters = new char[frequencies.keySet().toArray().length];
  int[] charFreq = new int[characters.length];

  for(int i = 0; i < characters.length; i++){
    characters[i] = (char)frequencies.keySet().toArray()[i];
    charFreq[i] = frequencies.get(characters[i]);	
  }

  PriorityQueue queue = new PriorityQueue<HuffmanNode>(new HuffmanComparator());

  for(int i = 0; i < characters.length; i++){
    queue.add(new HuffmanNode(characters[i], charFreq[i]));
  }

  root = null

  while(queue.size() > 1){
    HuffmanNode left = queue.poll();
    HuffmanNode right = queue.poll();

    HuffmanNode f = new HuffmanNode('$', right.getFrequency() + left.getFrequency());

    f.setLeftChild(left);
    f.setRightChild(right);

    root = f;
    queue.add(f);
		}
}
```
Once the tree has been created, we can then parse the tree to generate the huffman codes for each character like this:
```
Algorithm generateHuffmanCode(HuffmanNode root, String encoding){
  if(root == null){
    return;
  }

  if(root.getLeft() == null && root.getRight() == null){
    return new HuffmanCode(root.getChar(), encoding)
  }

  generateHuffmanCode(root.getLeft(), encoding + "0");
  generateHuffmanCode(root.getRight(), encoding + "1");
}
```
With this little bit of code, we are able to create a an efficient encoding scheme for every character in the text file. The encoding scheme is also non-ambiguous becuase it obeys the prefix property, being that no whole code is the prefix for another code.

