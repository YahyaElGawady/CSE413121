package cse41321.containers;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import cse41321.exceptions.DuplicateKeyException;

public class ChainedHashTable<K, V> {
	//Table of buckets
	private SinglyLinkedList<KeyValuePair<K,V>>[] table;
	
	//Creates size, Max Load Factor, and Resize Factor
	private int size;
	private double maxLoadFactor;
	private int resizeMultiplier;
	
	/**
	 * Creates a Chained Hash Table
	 * @param buckets number of initial buckets
	 * @param maxLoadFactor	Max ratio of allowed elements to buckets
	 * @param resizeMultiplier what to increase size by when maxLoadFactor is hit
	 */
	@SuppressWarnings("unchecked")
	public ChainedHashTable (int buckets, double maxLoadFactor, int resizeMultiplier) {
		// Create table of empty buckets
		table = new SinglyLinkedList[buckets];
		for(int i = 0; i < table.length; ++i) {
			table[i] = new SinglyLinkedList<KeyValuePair<K,V>>();
		}
		
		//set initial variables
		this.maxLoadFactor = maxLoadFactor;
		this.resizeMultiplier = resizeMultiplier;
		size = 0;
	}
	
	/**
	 * Gets Size
	 * @return size
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Returns whether size is zero
	 * @return if the table is empty
	 */
	public boolean isEmpty() {
		return getSize() == 0;
	}

	/**
	 * Inserts Keys and values as KeyValuePairs into table. Increases number of buckets when needed 
	 * @param key the key 
	 * @param value the value
	 * @throws IllegalArgumentException if the key is null
	 * @throws DuplicateKeyException if the key exists
	 */
	@SuppressWarnings("unchecked")
	public void insert(K key, V value) throws
	IllegalArgumentException,
	DuplicateKeyException {
		//checks if key is null
		if(key == null) {
			throw new IllegalArgumentException("key must not be null");
		}
		//check if the key already exists
		if(contains(key)) {
			throw new DuplicateKeyException();
		}
		//inserts it into the proper bucket and increases size
		getBucket(key).insertHead(new KeyValuePair<K, V>(key, value));
		++size;			
		//Checks load factor
		if(getLoadFactor() >= maxLoadFactor) {
			//determines new amt of buckets
			int newBuckets = resizeMultiplier * table.length;
			//creates a temporary storage for key value pairs
			SinglyLinkedList<KeyValuePair<K,V>> temp= new SinglyLinkedList<KeyValuePair<K,V>>();
			// creates the keys iterator
			KeysIterator keys = new KeysIterator();
			//inserts all key value pairs into the new storages
			while(keys.hasNext()) {
				K tempKey = keys.next();
				V tempVal = lookup(tempKey);
				temp.insertHead(new KeyValuePair<K, V>(key, value));
			}
			//overrides the table with new larger array
			table = new SinglyLinkedList[newBuckets];
			//instaniates all lists in the table
			for(int i = 0; i < table.length; ++i) {
				table[i] = new SinglyLinkedList<KeyValuePair<K,V>>();
			}
			//starts with the first element in storage and puts them into the new table
			SinglyLinkedList.Element elem = temp.getHead();
			while(!(elem == null)) {
				getBucket(key).insertHead((KeyValuePair<K, V>) elem.getData());	
				elem = elem.getNext();
			}
			 
		}
	}
	
	public double getLoadFactor() {
		//load factor is size divided by length
		return ((double)getSize() / (double)table.length);
	}
	public double getBuckets() {
		//gets number of buckets
		return table.length;
	}
	
	public V remove (K key) throws
			IllegalArgumentException,
			NoSuchElementException{
		 if(key == null) {
			 throw new IllegalArgumentException("Key must not be null");
		 }
		 
		 //if Empty Bucket
		 SinglyLinkedList<KeyValuePair<K,V>> bucket = getBucket(key);
		 if(bucket.isEmpty()) {
			 throw new NoSuchElementException();
		 }
		 
		 // If at head of Bucket
		 SinglyLinkedList<KeyValuePair<K,V>>.Element elem = bucket.getHead();
		 if(key.equals(elem.getData().getKey())) {
			 --size;
			 return bucket.removeHead().getValue();
		 }
	//scan rest of buckets
	 SinglyLinkedList<KeyValuePair<K,V>>.Element prev = elem;
	 elem = elem.getNext();
	 while(elem != null) {
		 if(key.equals(elem.getData().getKey())) {
			 --size;
		 	return bucket.removeAfter(prev).getValue();
		 }
		 prev = elem;
	 	 elem = elem.getNext();
	 }
	 throw new NoSuchElementException();
	}
	
	public V lookup (K key) throws
			IllegalArgumentException,
			NoSuchElementException{
		 if(key == null) {
			 throw new IllegalArgumentException("Key must not be null");
		 }
		 SinglyLinkedList<KeyValuePair<K,V>>.Element elem = getBucket(key).getHead();
		 while(elem != null) {
			 if(key.equals(elem.getData().getKey())) {
				return elem.getData().getValue();
			 }
		 	 elem = elem.getNext();
		 }
		 throw new NoSuchElementException();
	}
	
	public boolean contains(K key) {
		try {
			lookup(key);
		} catch (IllegalArgumentException ex) {
			return false;
		} catch (NoSuchElementException ex) {
			return false;
		}
		
		return true;
	}
	
	private SinglyLinkedList<KeyValuePair<K, V>> getBucket (K key) {
		//Division Method
		return table[(int) Math.floor((key.hashCode() * (Math.sqrt(5) - 1) ) % 1 * table.length)];
	}
	
	private class KeysIterator implements Iterator<K> {
		private int remaining;	// Number of keys remaining to iterate
		private int bucket;		// Bucket We're iterating
		private SinglyLinkedList<KeyValuePair<K, V>>.Element elem;
								// Position in bucket we're iterating
		
		public KeysIterator() {
			remaining = ChainedHashTable.this.size;
			bucket = 0;
			elem = ChainedHashTable.this.table[bucket].getHead();
		}
		
		public boolean hasNext() {
			return remaining > 0;
		}
		
		public K next() {
			if(hasNext()) {
				// If we've hit end of bucket, move to next non-empty bucket
				while (elem == null) {
					elem = ChainedHashTable.this.table[++bucket].getHead();
				}
				
				// Get key
				K key = elem.getData().getKey();
				
				//Move to next element and decrement entries remaining
				elem = elem.getNext();
				--remaining;
				
				return key;
			} else {
				throw new NoSuchElementException();
			}
			
		}
	}
	
	public Iterable<K> keys(){
		return new Iterable<K>() {
			public Iterator<K> iterator() {
				return new KeysIterator();
			}
		}; 
	}

}
