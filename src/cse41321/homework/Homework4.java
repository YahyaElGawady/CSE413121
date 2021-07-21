package cse41321.homework;

import cse41321.containers.Stack;

public class Homework4 {
	/**
	 * Adds two large numbers
	 * @param num1 first Number to be added 
	 * @param num2 second Number to be added 
	 */
	public static void addLargeNumbers(String num1, String num2) {
		// holds the digits of the first number
		Stack<Integer> firstNum = new Stack<Integer>();
		//iterates through the char array version of the first string
		for(char numeral : num1.toCharArray()) {
			//puts the numeric values of numeral on the stack
			firstNum.push(Character.getNumericValue(numeral));
		}
		// holds the digits of the second number
		Stack<Integer> secondNum = new Stack<Integer>();
		//iterates through the char array version of the second string
		for(char numeral : num2.toCharArray()) {
			//puts the numeric values of numeral on the stack
			secondNum.push(Character.getNumericValue(numeral));
		}
		//temporary holds the digits addition
		int result = 0;
		//holds the digits of the result
		Stack<Integer> Result = new Stack<Integer>();
		//repeats while either number isn't zero and result var isnt zero
		while (!firstNum.isEmpty() || !secondNum.isEmpty() || result != 0) {
			// if the the number isn't empty then add the last number added to the stack
			if(!firstNum.isEmpty()) {
				result += firstNum.pop();
			}
			// if the the number isn't empty then add the last number added to the stack
			if(!secondNum.isEmpty()) {
				result += secondNum.pop();
			}
			// push the units digit of result onto the result stack
			Result.push(result % 10);
			// keep the tens digit
			result /= 10;
		} 
		//iterates and pops off and prints the next of the result stack until empty
		while (!Result.isEmpty()) {
			System.out.print(Result.pop());
		}
		//adds the new line char at the end
		System.out.println();
	}
	
	public static void main (String[] args) {
		//Example One
		addLargeNumbers("98732383294829432843298578","98732383294829432843298578");
		//Example Two
		addLargeNumbers("9873238329482998907987867456433543564476457868660787897987999900000432843298578","176876876867856564534535435422342543653654654654654654654654654654654654654647655");
		//Example Three
		addLargeNumbers("9873238329482946876868768768768768768768689403092832843298578","73285798372985729579832759879898732383294829432843298578");
	}
}
