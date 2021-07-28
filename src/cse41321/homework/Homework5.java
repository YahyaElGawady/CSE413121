package cse41321.homework;

import java.util.NoSuchElementException;

import cse41321.containers.ChainedHashTable;
import cse41321.exceptions.DuplicateKeyException;

public class Homework5 {
	public static void main (String[] args) {
		int buckets = 5;
		double maxLoadFactor = 0.5;
		 int resizeMultiplier = 2;
		 
		ChainedHashTable<Integer, String> example = new ChainedHashTable<Integer, String>(buckets, maxLoadFactor, resizeMultiplier);
		try {
			//adds 10 elements and prints the information regarding it.
			example.insert(1, "One");
			System.out.println("buckets " + example.getBuckets() + ", elements " + example.getSize() + ", lf " + example.getLoadFactor() + ", max lf 0.5, resize multiplier 2.0");
			example.insert(2, "Two");
			System.out.println("buckets " + example.getBuckets() + ", elements " + example.getSize() + ", lf " + example.getLoadFactor() + ", max lf 0.5, resize multiplier 2.0");
			example.insert(3, "Three");
			System.out.println("buckets " + example.getBuckets() + ", elements " + example.getSize() + ", lf " + example.getLoadFactor() + ", max lf 0.5, resize multiplier 2.0");
			example.insert(4, "Four");
			System.out.println("buckets " + example.getBuckets() + ", elements " + example.getSize() + ", lf " + example.getLoadFactor() + ", max lf 0.5, resize multiplier 2.0");
			example.insert(5, "Five");
			System.out.println("buckets " + example.getBuckets() + ", elements " + example.getSize() + ", lf " + example.getLoadFactor() + ", max lf 0.5, resize multiplier 2.0");
			example.insert(6, "Six");
			System.out.println("buckets " + example.getBuckets() + ", elements " + example.getSize() + ", lf " + example.getLoadFactor() + ", max lf 0.5, resize multiplier 2.0");
			example.insert(7, "Seven");
			System.out.println("buckets " + example.getBuckets() + ", elements " + example.getSize() + ", lf " + example.getLoadFactor() + ", max lf 0.5, resize multiplier 2.0");
			example.insert(8, "Eight");
			System.out.println("buckets " + example.getBuckets() + ", elements " + example.getSize() + ", lf " + example.getLoadFactor() + ", max lf 0.5, resize multiplier 2.0");
			example.insert(9, "Nine");
			System.out.println("buckets " + example.getBuckets() + ", elements " + example.getSize() + ", lf " + example.getLoadFactor() + ", max lf 0.5, resize multiplier 2.0");
			example.insert(10, "Ten");
			System.out.println("buckets " + example.getBuckets() + ", elements " + example.getSize() + ", lf " + example.getLoadFactor() + ", max lf 0.5, resize multiplier 2.0");
			example.insert(11, "Eleven");
			System.out.println("buckets " + example.getBuckets() + ", elements " + example.getSize() + ", lf " + example.getLoadFactor() + ", max lf 0.5, resize multiplier 2.0");
			
			//Look Up
			System.out.println(example.lookup(10));
			System.out.println(example.lookup(50));

		} catch (IllegalArgumentException | DuplicateKeyException e) {
			e.printStackTrace();
		}
	}

}
