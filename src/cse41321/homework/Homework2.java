package cse41321.homework;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import cse41321.containers.SinglyLinkedList;

public class Homework2 {
	/**
	 * adds a coefficient element to the end of polynomial list 
	 * @param polynomial the list of the polynomial
	 * @param coefficient the coefficient being appended
	 */
	static void appendTerm(SinglyLinkedList<Double> polynomial, double coefficient) {
		//checks if list is empty
		if(polynomial.isEmpty()) {
			//adds head and tail to the empty list
			polynomial.insertHead(coefficient);
		} 
		else {
			//Adds the coefficient after the tail
			polynomial.insertAfter(polynomial.getTail(), coefficient);
		}		
	}
	/**
	 * displays the formatted polynomial
	 * @param polynomial the polynomial to be displayed
	 */
	static void display(SinglyLinkedList<Double> polynomial) {
		//calculates the highest order based of list size
		int highestOrder = polynomial.getSize() - 1; // one less due to x^0 element
		//starts at the head of the list
		SinglyLinkedList.Element coeffienct = polynomial.getHead();
		//gets the coeffienct value of the element
		double coeffienctData = (double)coeffienct.getData();
		//iterates backwards through the size from size-1 to 0 
		for(int i = highestOrder; i >= 0; i--) {
			//only print if the coeffienct isn't zero
			if((double) coeffienct.getData() != 0) {
				//adds the operator if the coeffienct isn't associated with highest power
				if(i != highestOrder) {
					//negatives get a subtraction symbol
					if ((double) coeffienct.getData() < 0) {
						System.out.print(" - ");
					} 
					else {
						System.out.print(" + ");
					}
					//take the absolute value of the data because negatives are accounted for above
					coeffienctData = Math.abs((double) coeffienct.getData());
				}
				//you print coeffienct unless its one except if its the constants
				if(coeffienctData != 1 || i == 0 ) {
					System.out.print(coeffienctData);
				}
				//prints just x if its power is one or it prints the power of x
				if( i == 1) {
					System.out.print("x");
				} else if(i != 0) {
					System.out.print("x^" + i);
				}
				}
				coeffienct = coeffienct.getNext();
			}
		//adds the new line to the end of display
		System.out.println();
	}
	/**
	 * evaluates the polynomial at value x
	 * @param polynomial the choosen polynomial
	 * @param x the value that the polynomial is being evaluated at
	 * @return the solution to the polynomial at x
	 */
	static Double evaluate(SinglyLinkedList<Double> polynomial, double x) {
		//calculates the highest order based of list size
		int highestOrder = polynomial.getSize() - 1; // one less due to x^0 element
		//starts at the head of the list
		SinglyLinkedList.Element coeffienct = polynomial.getHead();
		//makes a variable to solution and initializes it
		double solution = 0;
		//iterates through the orders backwards
		for(int i = highestOrder; i >= 0; i--) {
			//takes the sum of the previous solution and the coeffienct times x^i
			solution += ((double) coeffienct.getData() * Math.pow(x, i));
			//sets coeffienct to whatevers next
			coeffienct = coeffienct.getNext();
		}
		//returns the final solution
		return solution;
	}
	
	/**
	 * Tests the polynomials functions on a Linear function
	 */
	@Test
	void testLinear() {
		//creates polynomial
		SinglyLinkedList<Double> polynomial = new SinglyLinkedList<Double>();
		//adds needed terms
		appendTerm(polynomial, 1.0);
		appendTerm(polynomial, 1.0);
		//displays polynomial
		display(polynomial);
		//make sure it evaluates to the right value
		assertTrue(evaluate(polynomial,1.0) == 2.0);
	}
	
	/**
	 * Tests the polynomials functions on a Quadratic function
	 */
	@Test
	void testQuadratic() {
		//creates polynomial
		SinglyLinkedList<Double> polynomial = new SinglyLinkedList<Double>();
		//adds needed terms
		appendTerm(polynomial, 1.0);
		appendTerm(polynomial, 0);
		appendTerm(polynomial, -1.0);
		//displays polynomial
		display(polynomial);
		//make sure it evaluates to the right value
		assertTrue(evaluate(polynomial,2.03) == 3.120899999999999);
	}
	
	/**
	 * Tests the polynomials functions on a Cubic function
	 */
	@Test
	void testCubic() {
		//creates polynomial
		SinglyLinkedList<Double> polynomial = new SinglyLinkedList<Double>();
		//adds needed terms
		appendTerm(polynomial, -3.0);
		appendTerm(polynomial, 0.5);
		appendTerm(polynomial, -2.0);
		appendTerm(polynomial, 0);
		//displays polynomial
		display(polynomial);
		//make sure it evaluates to the right value
		assertTrue(evaluate(polynomial,05.0) == -372.5);
	}
	
	/**
	 * Tests the polynomials functions on a Quartic function
	 */
	@Test
	void testQuartic() {
		//creates polynomial
		SinglyLinkedList<Double> polynomial = new SinglyLinkedList<Double>();
		//adds needed terms
		appendTerm(polynomial, -0.3125);
		appendTerm(polynomial, 0);
		appendTerm(polynomial, -9.915);
		appendTerm(polynomial, -7.75);
		appendTerm(polynomial, -40.0);
		//displays polynomial
		display(polynomial);
		//make sure it evaluates to the right value
		assertTrue(evaluate(polynomial,123.45) == -7.273167168625821E7);
	}

}
