package binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {
	
	/****
	 * Internal class of a binary tree node
	 */
	private class Node {
		int value;
		Node leftChild;
		Node rightChild;
		
		public Node(int value) {
			this.value = value;
		}
		
		/***
		 * Add a new value to node. 
		 * If new value is less than current node, recursively add to left child.
		 * If new value is greater than current node, recursively add to right child.
		 *  
		 * @param newValue
		 */
		public void add(int newValue) {
			if (newValue <= this.value) {
				if (leftChild != null) {
					leftChild.add(newValue);
				} else {
					leftChild = new Node(newValue);
				}
			} else {
				if (rightChild != null) {
					rightChild.add(newValue);
				} else {
					rightChild = new Node(newValue);
				}				
			}
		}
		
		/***
		 * Get a complete path from valueToFind to root node.
		 * If valueToFind is less than current node, recursively get path from leftChild.
		 * If valueToFind is greater than current node, recursively get path from rightChild.
		 * 
		 * @param valueToFind
		 * @return List<Integer> containing path from valueToFind to root if such path exists; null otherwise.
		 */
		public List<Integer> getPath(int valueToFind) {
			if (valueToFind < this.value) {
				if (leftChild != null) {
					List<Integer> path = leftChild.getPath(valueToFind);
					if (path != null) {
						path.add(this.value);
					} 
					return path;
				} else {
					return null;
				}
				
			} else if (valueToFind > this.value) {
				if (rightChild != null) {
					List<Integer> path = rightChild.getPath(valueToFind);
					if (path != null) {
						path.add(this.value);
					}
					return path;
				} else {
					return null;
				}				
			} else {
				ArrayList<Integer> path = new ArrayList<Integer>();
				path.add(this.value);
				return path;
			}
		}
	}

	private Node tree = null;
	
	public void buildTree(int numNodes, int[] allValues) {
		if (allValues.length > 0) {
			tree = new Node(allValues[0]);
			for (int i=1; i < numNodes; i++) {
				tree.add(allValues[i]);
			}
		}
	}
	
	/***
	 * Find distance between node A and node B.
	 * First find the path from A to root, then find the path from B to root.
	 * Next, find the first common node between A and B.
	 * Finally, sum the distance between A to common node and B to common node.    
	 *  
	 * @param A
	 * @param B
	 * @return integer value representing the shortest distance between A and B if such path exists; -1 otherwise.
	 */
	private int findDistance(int A, int B) {
		int result = -1;
		
		if (tree != null) {
			List<Integer> pathToA = tree.getPath(A);		
			List<Integer> pathToB = tree.getPath(B);
			
			if (pathToA != null && pathToB != null) {
				int aToCommonNodeIndex = -1;
				int bToCommonNodexIndex = -1;
				for (int i=0; i<pathToA.size(); i++) {
					for (int j=0; j<pathToB.size(); j++) {
						if (pathToA.get(i) == pathToB.get(j)) {
							aToCommonNodeIndex = i;
							bToCommonNodexIndex = j;
							break;
						}
					}
					if (aToCommonNodeIndex >= 0 && bToCommonNodexIndex >= 0) {
						result = aToCommonNodeIndex + bToCommonNodexIndex;
						break;
					}
				}
			}
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int A = sc.nextInt();
		int B = sc.nextInt();
		int numNodes = sc.nextInt();
		int[] allValues = new int[numNodes];
		for (int i = 0; i < numNodes; i++) {
			allValues[i] = sc.nextInt();
		}
		sc.close();

		Solution s = new Solution();
		s.buildTree(numNodes, allValues);
		int distance = s.findDistance(A, B);
		
		if (distance < 0) {
			System.out.println("Not found");
		} else {
			System.out.println(distance);
		}
	}
	
}
