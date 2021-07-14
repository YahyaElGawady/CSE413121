package cse41321.homework;
import java.util.Random;
public class Homework1 {
	public static void main (String[] args) {
		Random rand = new Random();
		final int NUM_READINGS = 60;
		final int INSERTS_PER_READING = 1000;
		final double NANO_SECONDS_PER_SECOND = 1E+9;
		//creates the array that is going to be changed
		int[] array = {0};
		//prints out header to chart 
		System.out.println("Array length \t Seconds per insert");
		for (int i = 0; i < NUM_READINGS; i++ ) {		
			//logs start time
			long startTime = System.nanoTime();			
			//adds a thousand new elements into array
			for(int j = 0; j < INSERTS_PER_READING; j++) {	
				//gets a random index between 0 and array length
				int index = rand.nextInt(array.length);
				//gets a random integer to put into the array
				int value = rand.nextInt();
				//insert new element into array
				array = Homework1.insert(array, index, value);
				
			}
			//log stop time
			long stopTime = System.nanoTime();
			//turn difference in times into seconds
			double time = (double)(stopTime - startTime) / NANO_SECONDS_PER_SECOND;
			//finds the time to insert one element
			double timePerInsert = time / INSERTS_PER_READING;
			//prints out the length and average time to insert element
			System.out.printf("%d \t\t %f\n", array.length, timePerInsert);
		}
	}
	//Overall Big O notation is O(n)
	static int[] insert(int[] array, int index, int value ) {
		//creates a new array that is one longer that original array
		int[] newArray = new int[array.length + 1];		//O(1)
		//iterates through all elements before goal index and copies them to new array
		for (int i = 0; i < index; i++) {				//O(n)
			//copies element
			newArray[i] = array[i];						//O(1)
		}
		//adds the desired element at proper index
		newArray[index] = value;						//O(1)
		//copies rest of the array to new array but one further because of the new element
		for(int i = index; i < array.length; i++) {		//O(n) 
			newArray[i + 1] = array[i];					//O(1)
		}
		//returns new array
		return newArray; 								//O(1)
	}
}
