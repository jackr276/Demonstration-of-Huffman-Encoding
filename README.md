# Demonstration of Huffman Encoding
Author: [Jack Robbins](https://www.github.com/jackr276)

## Introduction
Huffman encoding is an efficient and lossless data compression algorithm that uses statistics, specifically occurrence frequency, to generate variable length codes that are assigned to each character. This allows more frequent characters to be assigned shorter codes and therefore reduce the overall size of the file. The real reduction amount is usually around 45%-50%, but this can vary based on the frequency levels and distribution in a given set of ASCII text. 

## Huffman Encoding Algorithm
The Huffman encoding algorithm works by building a binary tree from the ground up. To run the algorithm, we must first have a list of all characters and their respective frequencies in the given text file. Once we have this, we take each character and its associated frequency and bundle them into an object, and then we place all of these objects into a priority queue. The lower the frequency, the higher the priority and the sooner this object will be dequeued. This allows us to use the queue to build the tree from the ground up, starting with the least frequent characters and eventually working our way up to the most frequent one. More frequent characters are always higher up on the tree. This way, when we parse the tree, more frequent characters end up with shorter codes, achieving the compression that we desire.

Here is the algorithm in full for creating the Huffman tree:
```java
Algorithm generateHuffmanTree(HuffmanNode root, Map<Character, Integer> frequencies){

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

  root = null;

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
```java
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

## Running the demonstration
This repository contains only a simple demonstration of Huffman encoding. The [source code](https://github.com/jackr276/Demonstration-of-Huffman-Encoding/tree/main/src) itself is well documented, so I will not go in depth into it here.   
   
To run the source code, first download everything in this repository into a local folder. Then navigate to that folder in your terminal and run the following commands. Sample output is also shown below:
```console
example@bash: ~/Demonstration-of-Huffman-Encoding $ chmod +x run.sh
example@bash: ~/Demonstration-of-Huffman-Encoding $ ./run.sh

Compiling:

./src/Main.java
./src/huffman/HuffmanNode.java
./src/huffman/HuffmanComparator.java
./src/huffman/HuffmanEncoder.java

Using Java Version: 
openjdk 21.0.3 2024-04-16
OpenJDK Runtime Environment (build 21.0.3+9-Ubuntu-1ubuntu122.04.1)
OpenJDK 64-Bit Server VM (build 21.0.3+9-Ubuntu-1ubuntu122.04.1, mixed mode, sharing)

Compilation Success!
Attempting to Run

Enter the file you would like to get the table for: ../Resources/Constitution.txt

================ Character Frequencies ================

Character: , frequency: 18
Character: —, frequency: 9
Character:  , frequency: 4377
Character: ", frequency: 10
Character: ', frequency: 1
Character: (, frequency: 5
Character: ), frequency: 5
Character: ,, frequency: 381
Character: -, frequency: 3
Character: ., frequency: 144
Character: 0, frequency: 1
Character: 1, frequency: 5
Character: 2, frequency: 4
Character: 3, frequency: 4
Character: 4, frequency: 3
Character: 5, frequency: 1
Character: 6, frequency: 1
Character: 7, frequency: 1
Character: 8, frequency: 1
Character: 9, frequency: 1
Character: :, frequency: 8
Character: ;, frequency: 64
Character: A, frequency: 80
Character: B, frequency: 27
Character: C, frequency: 159
Character: D, frequency: 33
Character: E, frequency: 46
Character: F, frequency: 10
Character: G, frequency: 7
Character: H, frequency: 34
Character: I, frequency: 53
Character: J, frequency: 23
Character: K, frequency: 1
Character: L, frequency: 68
Character: M, frequency: 53
Character: N, frequency: 45
Character: O, frequency: 48
Character: P, frequency: 133
Character: Q, frequency: 4
Character: R, frequency: 62
Character: S, frequency: 218
Character: T, frequency: 115
Character: U, frequency: 63
Character: V, frequency: 33
Character: W, frequency: 22
Character: Y, frequency: 24
Character: a, frequency: 1516
Character: b, frequency: 334
Character: c, frequency: 534
Character: d, frequency: 693
Character: e, frequency: 2922
Character: f, frequency: 594
Character: g, frequency: 255
Character: h, frequency: 1195
Character: i, frequency: 1339
Character: j, frequency: 26
Character: k, frequency: 31
Character: l, frequency: 824
Character: m, frequency: 362
Character: n, frequency: 1498
Character: o, frequency: 1507
Character: p, frequency: 267
Character: q, frequency: 25
Character: r, frequency: 1178
Character: s, frequency: 1344
Character: t, frequency: 2082
Character: u, frequency: 475
Character: v, frequency: 219
Character: w, frequency: 200
Character: x, frequency: 64
Character: y, frequency: 264
Character: z, frequency: 16

=============== Huffman Codes =====================

Character: h, Encoding:                 0000, Bits needed:  4
Character: f, Encoding:                00010, Bits needed:  5
Character: ., Encoding:              0001100, Bits needed:  7
Character: H, Encoding:            000110100, Bits needed:  9
Character: , Encoding:           0001101010, Bits needed: 10
Character: 3, Encoding:         000110101100, Bits needed: 12
Character: (, Encoding:         000110101101, Bits needed: 12
Character: ", Encoding:          00011010111, Bits needed: 11
Character: A, Encoding:             00011011, Bits needed:  8
Character: b, Encoding:               000111, Bits needed:  6
Character: i, Encoding:                 0010, Bits needed:  4
Character: s, Encoding:                 0011, Bits needed:  4
Character: d, Encoding:                01000, Bits needed:  5
Character: C, Encoding:              0100100, Bits needed:  7
Character: F, Encoding:          01001010000, Bits needed: 11
Character: 1, Encoding:         010010100010, Bits needed: 12
Character: ), Encoding:         010010100011, Bits needed: 12
Character: W, Encoding:           0100101001, Bits needed: 10
Character: N, Encoding:            010010101, Bits needed:  9
Character: E, Encoding:            010010110, Bits needed:  9
Character: J, Encoding:           0100101110, Bits needed: 10
Character: Y, Encoding:           0100101111, Bits needed: 10
Character: m, Encoding:               010011, Bits needed:  6
Character: n, Encoding:                 0101, Bits needed:  4
Character: e, Encoding:                  011, Bits needed:  3
Character: o, Encoding:                 1000, Bits needed:  4
Character: a, Encoding:                 1001, Bits needed:  4
Character: ,, Encoding:               101000, Bits needed:  6
Character: w, Encoding:              1010010, Bits needed:  7
Character: O, Encoding:            101001100, Bits needed:  9
Character: q, Encoding:           1010011010, Bits needed: 10
Character: j, Encoding:           1010011011, Bits needed: 10
Character: M, Encoding:            101001110, Bits needed:  9
Character: I, Encoding:            101001111, Bits needed:  9
Character: l, Encoding:                10101, Bits needed:  5
Character: S, Encoding:              1011000, Bits needed:  7
Character: v, Encoding:              1011001, Bits needed:  7
Character: u, Encoding:               101101, Bits needed:  6
Character: T, Encoding:             10111000, Bits needed:  8
Character: B, Encoding:           1011100100, Bits needed: 10
Character: -, Encoding:        1011100101000, Bits needed: 13
Character: 4, Encoding:        1011100101001, Bits needed: 13
Character: G, Encoding:         101110010101, Bits needed: 12
Character: z, Encoding:          10111001011, Bits needed: 11
Character: R, Encoding:            101110011, Bits needed:  9
Character: U, Encoding:            101110100, Bits needed:  9
Character: x, Encoding:            101110101, Bits needed:  9
Character: ;, Encoding:            101110110, Bits needed:  9
Character: k, Encoding:           1011101110, Bits needed: 10
Character: V, Encoding:           1011101111, Bits needed: 10
Character: g, Encoding:              1011110, Bits needed:  7
Character: y, Encoding:              1011111, Bits needed:  7
Character: t, Encoding:                 1100, Bits needed:  4
Character: P, Encoding:             11010000, Bits needed:  8
Character: D, Encoding:           1101000100, Bits needed: 10
Character: Q, Encoding:        1101000101000, Bits needed: 13
Character: ', Encoding:      110100010100100, Bits needed: 15
Character: 0, Encoding:      110100010100101, Bits needed: 15
Character: 5, Encoding:      110100010100110, Bits needed: 15
Character: 6, Encoding:      110100010100111, Bits needed: 15
Character: :, Encoding:         110100010101, Bits needed: 12
Character: K, Encoding:      110100010110000, Bits needed: 15
Character: 7, Encoding:      110100010110001, Bits needed: 15
Character: 8, Encoding:      110100010110010, Bits needed: 15
Character: 9, Encoding:      110100010110011, Bits needed: 15
Character: 2, Encoding:        1101000101101, Bits needed: 13
Character: —, Encoding:         110100010111, Bits needed: 12
Character: L, Encoding:            110100011, Bits needed:  9
Character: p, Encoding:              1101001, Bits needed:  7
Character: c, Encoding:               110101, Bits needed:  6
Character: r, Encoding:                11011, Bits needed:  5
Character:  , Encoding:                  111, Bits needed:  3

Huffman Encoded File Size: 14590 bytes
Initial File Size: 26375 bytes

===================================================

Running Sucessful! Exit code: 0

example@bash: ~/Demonstration-of-Huffman-Encoding $
```

