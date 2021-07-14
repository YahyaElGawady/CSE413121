package cse41321.containers;

import java.util.NoSuchElementException;

public class SinglyLinkedList <E> {
	// An element in a linked list
	public class Element{
		private E data;
		private Element next;
		
		//Only allow SinglyLinkedList to construct Elements
		private Element(E data) {
			this.data = data;
			this.next = null;
		}
		
		public E getData() {
			return data;
		}
		
		public Element getNext() {
			return next;
		}
		
		private SinglyLinkedList getOwner() {
			return SinglyLinkedList.this;
		}
	}
	
	private Element head;
	private Element tail;
	private int size = 0;
	
	public Element getHead() {
		return head;
	}	
	public Element getTail() {
		return tail;
	}
	public int getSize() {
		return size;
	}
	public boolean isEmpty() {
		return size == 0;
	}
	
	public Element insertHead(E data) {
		Element newElement = new Element(data);
		if(isEmpty()) {
			//insert into empty list
			head = newElement;
			tail = newElement;
		}
		else {
			//Insert into non empty list
			newElement.next = head;
			head = newElement; 
		}
		++size;
		return newElement;
	}
	public Element insertTail(E data) {
		Element newElement = new Element(data);
		if(isEmpty()) {
			//insert into empty list
			head = newElement;
			tail = newElement;
		}
		else {
			//Insert into non empty list
			tail.next = newElement;
			tail = newElement; 
		}
		++size;
		return newElement;
	}
	public Element insertAfter(Element element, E data)
			throws IllegalArgumentException {
		if(element == null) {
			throw new IllegalArgumentException(
					"Argument 'element' must not be null");
		}
		if(element.getOwner() != this) {
			throw new IllegalArgumentException(
					"Argument 'element' does not belong to this list");
		}
		//insert new element
		Element newElement = new Element(data);
		if(tail == element) {
			//insert new tail
			element.next = newElement;
			tail = newElement;
		}
		else {
			//insert into middle of list
			newElement.next = element.next;
			element.next = newElement;
		}
		++size;
		
		return newElement;
	}
	public E removeHead() throws NoSuchElementException {
		//check pre-conditions
		if(isEmpty()) {
			throw new NoSuchElementException ("Cannot remove from empty list");
		}
		
		//remove the head
		Element oldHead = head;
		if(size == 1) {
			//handles the removal of the last element
			head = null;
			tail = null;
		}
		else {
			head = head.next;
		}
		--size;
		return oldHead.data;
	}
	//Note that there is no removeTail. This cannot be implemented
	//efficiently because it would require O(n) to scan from head until
	//reaching the item_before_tail.
	public E removeAfter(Element element) 
			throws IllegalArgumentException{
		//check preconditions
		if(element == null) {
			throw new IllegalArgumentException(
					"Argument 'element' must not be null");
		}
		if(element.getOwner() != this) {
			throw new IllegalArgumentException(
					"Argument 'element' does not belong to this list");
		}
		if(element.next == null) {
			throw new IllegalArgumentException(
					"Argument 'element' must have a non-null next element");
		}
		
		//remove element
		Element elementToRemove = element.next;
		if(elementToRemove == tail) {
			//remove the tail
			element.next = null;
			tail = element;
		}
		else {
			element.next = elementToRemove.next;
		}
		--size;
		
		return elementToRemove.data;
		
	}
}
