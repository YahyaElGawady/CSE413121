package cse41321.homework;

import cse41321.containers.BinaryTree;
import cse41321.containers.BinaryTree.Node;

public class Homework6 {

	/**
	 * gets the root and input it into private countleaves function
	 * @param tree
	 * @return the number of leaves
	 */
	public static int countLeaves(BinaryTree tree) {
		Node node = tree.getRoot();
		return countLeaves(node);
	}
	/**
	 * Counts the leaves 
	 * @param node the node that is being inputted
	 * @return the total number of leaves
	 */
	private static int countLeaves(Node node) {
		if (node == null)		//Returns 0 if node is null
            return 0;
        if (node.isLeaf())		//Return 1 if the node is a leaf to get added into counted
            return 1;
        else
            return countLeaves(node.getLeft()) + countLeaves(node.getRight());		//adds all the leaves together
	}
	/**
	 * gets the root and input it into private countNonLeaves function
	 * @param tree
	 * @return the number of nonleaves
	 */

	public static int countNonLeaves(BinaryTree tree){
		Node node = tree.getRoot();
		return countNonLeaves(node);

	}
	/**
	 * Counts the non leaves 
	 * @param node the node that is being inputted
	 * @return the total number of non leaves
	 */
	private static int countNonLeaves(Node node) {
		if (node == null || node.isLeaf()) //if its a leaf or null add zero
            return 0;
        else
            return countNonLeaves(node.getLeft()) + countNonLeaves(node.getRight()) + 1;	// add One to non leaves	
	}
	/**
	 * Finds the height of the tree
	 * @param tree the tree in question
	 * @return returns the height
	 */
	public static int getHeight(BinaryTree tree) {
		Node root = tree.getRoot();
		return getHeight(root);
	}
	/**
	 * Finds the height of the tree based of node using recursion
	 * @param node
	 * @return
	 */
	public static int getHeight(Node node) {
		//Returns the base case when the node is null
		if(node == null)
			return 0;
		return Math.max(getHeight(node.getLeft()), getHeight(node.getRight())) + 1;
		}
	/**
	 * Prints the pre order
	 * @param tree inputed tree
	 */
	public static void printPreOrder(BinaryTree tree) {
		Node root = tree.getRoot();
		printPreOrder(root);
		System.out.println();
	}
	/**
	 * Prints the pre order
	 * @param node the node
	 */
	private static void printPreOrder(Node node) {
		//the return when the node is null : Base Case
		if (node == null) {
			return; }
		System.out.printf("%s ", node.getData());
		//Prints and recurses in pre order
		printPreOrder(node.getLeft());
		printPreOrder(node.getRight());
		}
	/**
	 * Prints the in order
	 * @param tree inputed tree
	 */
	public static void printInOrder(BinaryTree tree) {
		Node root = tree.getRoot();
		printInOrder(root);
		System.out.println();
	}
	/**
	 * Prints the in order
	 * @param node the node
	 */
    public static void printInOrder(Node root) {
        if(root !=  null) {
        	printInOrder(root.getLeft());
            //Visit the node by Printing the node data  
            System.out.printf("%d ",root.getData());
            printInOrder(root.getRight());
        }
    }
	/**
	 * Prints the post order
	 * @param tree inputed tree
	 */
	public static void printPostOrder(BinaryTree tree) {
		Node root = tree.getRoot();
		printPostOrder(root);
		System.out.println();
	}
	/**
	 * Prints the post order
	 * @param node the node
	 */
	  public static void printPostOrder(Node root) {
	        if(root !=  null) {
	        	printPostOrder(root.getLeft());
	            printPostOrder(root.getRight());
	            //Visit the node by Printing the node data  
	            System.out.printf("%d ",root.getData());
	            }
	        }
	/**
	 * Removes the leaves
	 * @param tree the tree in question 
	 */
	public static void removeLeaves(BinaryTree tree) {
		Node root = tree.getRoot();
		removeLeaves(root);
	}
	/**
	 * Removes leaves
	 * @param root
	 */
	@SuppressWarnings("unchecked")
	public static void removeLeaves(Node root) {
		//base Case when root is null
		if(root == null) {
			return;
		}
		else {
			//if left exists and a leaf then remove
			if(root.getLeft() != null && root.getLeft().isLeaf() ){
				root.removeLeft();
			}
			//if right exists and a leaf then remove
			if(root.getRight() != null && root.getRight().isLeaf()){
				root.removeRight();
			} 
			//if they arent null and arent leaves then recurse throught
			if(root.getLeft() != null)
	           removeLeaves(root.getLeft()); 			//recurse left
			if(root.getRight() != null)
	           removeLeaves(root.getRight());          //recurse right   
			
		}
	    }
	public static void main (String[] args) {
		//Set up and input values into tree one
		System.out.println("Tree One:");
		BinaryTree<Integer> tree1 = new BinaryTree<Integer>();
		tree1.insertRoot(1);
		tree1.getRoot().insertLeft(2).insertLeft(4).insertLeft(7);
		tree1.getRoot().insertRight(3).insertRight(6).insertRight(8).insertRight(9);
		tree1.getRoot().getRight().insertLeft(5);
		//count leaves
		System.out.println(countLeaves(tree1));
		//count non leaves
		System.out.println(countNonLeaves(tree1));
		//get height
		System.out.println(getHeight(tree1));
		//print the three orders
		printPreOrder(tree1);
		printInOrder(tree1);
		printPostOrder(tree1);
		//remove leaves and reprint
		System.out.println("After Leaf Removal: ");
		removeLeaves(tree1);
		printPreOrder(tree1);
		printInOrder(tree1);
		printPostOrder(tree1);
		//Repeat with tree two
		System.out.println("Tree Two:");
		BinaryTree<Integer> tree2 = new BinaryTree<Integer>();
		tree2.insertRoot(6);
		tree2.getRoot().insertLeft(4).insertLeft(2).insertLeft(1);
		tree2.getRoot().getLeft().insertRight(5);
		tree2.getRoot().getLeft().getLeft().insertRight(3);
		tree2.getRoot().insertRight(8).insertRight(9);
		tree2.getRoot().getRight().insertLeft(7);
		System.out.println(countLeaves(tree2));
		System.out.println(countNonLeaves(tree2));
		System.out.println(getHeight(tree2));
		printPreOrder(tree2);
		printInOrder(tree2);
		printPostOrder(tree2);
		System.out.println("After Leaf Removal: ");
		removeLeaves(tree2);
		printPreOrder(tree2);
		printInOrder(tree2);
		printPostOrder(tree2);
		 }
	}
