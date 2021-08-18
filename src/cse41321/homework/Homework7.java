package cse41321.homework;

import java.util.Comparator;

import cse41321.containers.Heap;

public class Homework7 {
	static class Person {
		String name;
		int age;
		double height;
		/**
		 * Person Constructor
		 * @param name
		 * @param age
		 * @param height
		 */
		Person (String name, int age, double height){
			this.name = name;
			this.age = age;
			this.height = height;
		}
		/**
		 * returns the string representation of a person
		 */
		@Override
		public String toString() {
			return String.format("Name: %s   Age: %d   Height: %s", name, age, height);
			
		}
	}
	public static Person[] outputSorted(Comparator<Person> comparator, Person[] persons) {
		//create a heap
		Heap<Person> personsHeap = new Heap<Person>(comparator);
		//add all the people into the heap
		for(Person person : persons) {
			personsHeap.insert(person);
		}
		//take out all the people into a new array
		Person[] sortedPersons = new Person[persons.length];
		for(int i = 0; i < sortedPersons.length; i++) {
			sortedPersons[i] = personsHeap.extract();
		}
		//return new array
		return sortedPersons;
	}
	
	public static void main (String[] args) {
		//create five people
		Person person1 = new Person("Jack",17, 5.5);
		Person person2 = new Person("Brian",19, 6.1);
		Person person3 = new Person("Liam",16, 5.1);
		Person person4 = new Person("Alvin",7, 3.6);
		Person person5 = new Person("Alex",70, 5.3 );
		//add them all to an array
		Person[] persons = {person1, person2, person3, person4, person5};
		//sort by ascending name
		System.out.println("Name Sort");
		persons = outputSorted(
				new Comparator<Person> () {
					//compares names
					public int compare(Person p1, Person p2) {
						return p2.name.compareTo(p1.name);
					}
				}
				, persons);
		for(Person person : persons) {
			System.out.println(person); //prints out the sorted person array
		}
		//Resort by ascending age
		System.out.println("Age Sort: ");
		persons = outputSorted(
				new Comparator<Person> () {
					//compares age
					public int compare(Person p1, Person p2) {
						return p2.age - p1.age;
					}
				}
				, persons);
		for(Person person : persons) {
			System.out.println(person); //prints out the sorted person array
		}
		//Resort by ascending height
		System.out.println("Height Sorted: ");
		persons = outputSorted(
				new Comparator<Person> () {
					//compare height
					public int compare(Person p1, Person p2) {
						 if(p1.height - p2.height > 0) {
							 return -1;
						 } else if(p1.height - p2.height < 0) {
							 return 1;
						 } else {
							 return 0;
						 }
					}
				}
				, persons);
		for(Person person : persons) {
			System.out.println(person); //prints out the sorted person array
		}


	}
	
}
