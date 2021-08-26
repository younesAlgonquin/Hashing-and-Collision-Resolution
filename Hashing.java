import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * CET - CS Academic Level 3
 * <br>Declaration: I declare that this is my own original work and is free from Plagiarism
 * <br>Student Name: Younes Boutaleb
 * <br>Student Number: 041019068 
 * <br>Assignment number: Lab 6
 * <br>Date: 8/1/2021
 * <br>purpose of class: This class implements a hashing algorithm that calculates the index of a string input
 * <br>then store the string in an ArrayList at position index
 * <br>Section #: 303
 * <br>Course: CST8130 - Data Structures
 * <br>Professor: James Mwangi PhD.
 * @author Younes Boutaleb.
 * @version 1.0
 * 
 */
public class Hashing {

	/**
	 * This is the main method which tests the program
	 * @param args
	 */
	public static void main(String[] args) {

		//This is the data structures that stores user's inputs
		ArrayList<String> dataItems = new ArrayList <> (100);

		//initialize all ArrayList elements to null
		for(int i=0; i<100; i++) {

			dataItems.add(null);
		}//end for


		//This is the main scanner object declared in this program
		Scanner input = new Scanner(System.in).useDelimiter("\r\n");

		//This is the menu option chosen by the use
		int option = 0;
		//Input validation state(true/false)
		boolean valid ;

		//This loop continues until the user chose to exit the program
		while(option != 3) {

			valid=false;
			//if the user enters a non integer value the program displays an error message and loops back
			while(!valid) {

				displayMenu();
				//validates integer input
				try {
					option = input.nextInt();
					valid=true;
				}catch(InputMismatchException ex) {
					System.out.println("Input Mismatch Exception while reading user's option from main menu");
					input.nextLine();
				}//end catch
			}//end while

			//This switch structure executes the user's choice when it's valid otherwise it displays an error message
			switch(option) {

			//Insert a new string
			case 1:

				//Prompt for a string
				System.out.print("Enter a string to insert: "); 
				input.nextLine();
				String data = input.nextLine();

				//try statement ensures that the entered string is not null
				try {

					//Provide the hash code of the entered string
					int index = hashString(data);

					//define the index where the string will be added
					//its the first available spot starting from index to 99 
					while(index<100 && dataItems.get(index) !=null) {
						//increment index if the index position in the arrayList is already token
						index++; 
					}//end while

					//print error message when all spots from index to 99 are token
					if(index == 100) {

						System.out.println("String cannot be added");
					}else {
						//add the string at the position index
						dataItems.add(index, data);
						//remove the following element created by the arrayList to shift the existing elements strating from index+1
						dataItems.remove(index+1);
						System.out.println("String successfully added");
					}//end else
					//display ArrayList elements
					displayTable(dataItems, 100);

					//Print error message when entered data is null
				}catch(NullPointerException ex) {

					System.out.println("Please enter a non-null string");
				}//end catch

				break;

				//Search for a specific string in the ArrayList 
			case 2:
				//Prompt for a string
				System.out.print("Enter a string to find: "); 
				input.nextLine();
				data = input.nextLine();
				//try statement ensures that the entered string is not null
				try {
					//Provide the hash code of the entered string
					int index = hashString(data);

					//Search for the first match starting from position index
					while(index<100 && !data.equals(dataItems.get(index))) {
						//increment index if the index element does not match user's input
						index++;
					}//end while
					//print error message the input does not exist
					if(index == 100) {

						System.out.println("String cannot be found");
					}else {
						//Print the string and its index in the ArrayList
						System.out.printf("\"%s\" found at index: %d\n", dataItems.get(index), index);
					}//end else

					//display ArrayList elements
					displayTable(dataItems, 100);

					//Print error message when entered data is null
				}catch(NullPointerException ex) {

					System.out.println("Please enter a non-null string");
				}

				break;

				//Exit the program
			case 3:
				System.out.println("Exiting ...");
				break;

				//displays an error message in case of invalid option
			default:
				System.out.println("Invalid option");
				break;

			}//end switch
		}//end while

		//Close the scanner object
		if(input != null)
			input.close();

	}//end main


	/**
	 * This method displays the menu
	 */
	public static void displayMenu() {

		//This stores the menu options 
		String formatSring= "\n%d. Add a String to the array dataItems";
		formatSring+="\n%d. Search for a String in the array";
		formatSring+="\n%d. Exit\n";

		System.out.print(String.format(formatSring, 1,2,3)); 	

	}//end displayMenu

	/**
	 * This is method calculates the hash code of the given string as the sum of the first two characters(if exist)
	 * @param data This is the string to be converted to a hash code
	 * @return Return an integer value corresponding to a non-null string hash code
	 * @throws NullPointerException This method throws an exception in the string parameter data is null
	 */
	public static int hashString(String data) throws NullPointerException{

		//This is the hash code of the string
		int index = 0;

		//Add first characters as integers to index up to the second character if it exists
		for(int i =0; i<data.length() && i<2; i++) {
			index += (int)data.charAt(i);

		}//end for

		//get the first two digits of index
		index %= 100;

		return index;
	}//end hashString

	/**
	 * This method displays ArrayList records
	 * @param dataItems This is the ArrayList object to be displayed
	 * @param capacity This is the maximum number of element of the ArrayList
	 */
	public static void displayTable(ArrayList <String> dataItems, int capacity) {

		//Print the ArrayList strings
		System.out.print("Table: {");
		for(int i =99; i>=0; i--) {

			if(dataItems.get(i) != null)
				System.out.printf("%d=%s, ", i, dataItems.get(i));
		}//end for

		System.out.print("}");

	}//end displayTable

}//end class
