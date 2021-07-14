package cse41321.homework;

import java.util.Comparator;
import java.util.Random;

import cse41321.algorithms.QuickSort;
public class Homework3 {
	static class Car
	{
		//Create Atributes to Car
		public String make;		
		public String model;
		public int mpg;			//Miles Per Gallon
		
		/**
		 * Constructer for Car Object
		 * @param make The make of the car
		 * @param model The model of the car
		 * @param mpg
		 */
		Car (String make, String model, int mpg){
			this.make = make;
			this.model = model;
			this.mpg = mpg;
		}
		
		/**
		 * Reformats the data in car to a string
		 * @return The proper string format of car
		 */
	    @Override
	    public String toString() {
	        return String.format("%s %s (MPG: %d) ", make, model, mpg);	//Formats the string
	    }		
	}
	static class CompareCarsByMakeThenModel implements Comparator<Car>{
		@Override
		public int compare(Car arg0, Car arg1) {
			//Checks if make is equal
			if(arg0.make.compareTo(arg1.make) == 0) { 
				//if its equal returns the comparison of the models
				return arg0.model.compareTo(arg1.model);
			}
			//if the make is unequal return the value of the comparision
			return arg0.make.compareTo(arg1.make);
		}
		
	}
	static class CompareCarsByDescendingMPG implements Comparator<Car>{
		@Override
		public int compare(Car arg0, Car arg1) {
			//Comparing them by mileage descending
			return arg1.mpg - arg0.mpg;
		}
			
		
	}
	static class CompareCarsByMakeThenDescendingMPG implements Comparator<Car>{
		@Override
		public int compare(Car arg0, Car arg1) {
			//check if make is equal 
			if(arg0.make.compareTo(arg1.make) == 0) {
				//Returns  the comparision of mpg
				return arg1.mpg - arg0.mpg;
			}
			//returns the makes comparsion
			return arg0.make.compareTo(arg1.make);
		}
	}
	public static void main (String[] args) {
		//create the array
		Car cars[] = {
				new Car("Toyota", "Camry", 33),
				new Car( "Ford", "Focus", 40),
				new Car("Honda", "Accord", 34),
				new Car("Ford", "Mustang", 31),
				new Car("Honda", "Civic", 39),
				new Car("Toyota", "Prius", 48),
				new Car("Honda", "Fit", 35),
				new Car("Toyota", "Corolla", 35),
				new  Car("Ford", "Taurus", 28)
		};
		//print out the array
		System.out.println("------ Unsorted List ------");
		for(Car car : cars) {
			System.out.println(car);
		}
		//sort and print out new array
		System.out.println("------ Sorted By Make Then Model ------");
		QuickSort.quickSort(cars, new CompareCarsByMakeThenModel());
		for(Car car : cars) {
			System.out.println(car);
		}
		//sort and print out new array
		System.out.println("------ Sorted By Descending MPG ------");
		QuickSort.quickSort(cars, new CompareCarsByDescendingMPG());
		for(Car car : cars) {
			System.out.println(car);
		}
		//sort and print out new array
		System.out.println("------ Sorted By Make Then Descending MPG ------");
		QuickSort.quickSort(cars, new CompareCarsByMakeThenDescendingMPG());
		for(Car car : cars) {
			System.out.println(car);
		}
		System.out.println("----------------------------");

	}

	
}
