//program designed to solve the third Spotify puzzle (work in progress, obscure error persists)
//Author: Jared Moore
import java.util.Scanner;


public class BilateralProjects {

	private int numOfTeams;
	private Scanner input = new Scanner(System.in);
	private int firstArray[][];
	public int shortenedArray[];
	
	private int determineNumber() {		
		
		System.out.print("");
		numOfTeams = input.nextInt();
		firstArray = new int[2][numOfTeams];
		return numOfTeams;
	}
	
	private int[][] addTeams(int number) {
		
		int[][] array = new int[4][number];
		String string1;
		String string2;
		int first = 0;
		int second = 0;		
		
		for (int i = 0; i < number; i++) {
			
			System.out.print("");
			string1 = input.next();
			string2 = input.next();			
			first = Integer.parseInt(string1);
			second = Integer.parseInt(string2);
					
			array[0][i] = first;
			array[1][i] = second;
			array[2][i] = 0;
		}	
		
		return array;	
	}
	
	private int[][] setPicker(int array[][]) { // determine which to pick
		
		for (int i = 0; i < numOfTeams; i++) {// in no way optimized for performance 
			
			array[2][i] = 0;
			array[3][i] = 0;
			int firstValue = array[0][i];
			int secondValue = array[1][i];
			int firstCounter = 1;
			int secondCounter = 1;
			
			for (int j = 0; j < numOfTeams; j++) {// if i is large, this will take a large amount of processing time
				if ((array[0][j] == firstValue) && (array[2][j] != 1))
					firstCounter++;
				if ((array[1][j] == secondValue) && (array[3][j] != 1))
					secondCounter++;
			} // end inner for
			if (secondCounter > firstCounter)
				array[2][i] = 1;
			else
				array[3][i] = 1;
		} // end outer for
		return array;
	}
	
	private int[][] makeFirstArray(int array[][]) {// make array of which one will be sent from each team
		
		for (int i = 0; i < numOfTeams; i++) {
			
			int picker = array[2][i];
			firstArray[0][i] = array[picker][i]; // just using the one that will be sent, other is discarded
			firstArray[1][i] = 0; // I have no idea why I did this
		}
		
		
		return firstArray;
	}
	
	private int[] makeShortenedArray(int array[][]) {
		
		int howMany = 0;
		
		for (int x = 0; x < numOfTeams; x++) {
			int place = 0; // new integer = to 0
			boolean b = false; // boolean = to false
			if (x == 0) { // if index is zero
				place = array[0][0]; // set place to first value
				b = true; // boolean becomes true
			}
			else if (array[1][x] != 1) { // y u no execute!!!
				place = array[0][x];// set integer to next value
				b = true;
			}
			else {
				b = false;
			}
			
			if (b) { // will only execute if integer was set to a value
				for (int z = x + 1; z < numOfTeams; z++) {
					if (place == array[0][z]) {
						array[1][z] = 1;
					} // end if
				} // end inner for
				howMany++; 
			} // end outer for
		}
		
		shortenedArray = new int[howMany];
		int nextIndex = 0;
		
		for (int i = 0; i < numOfTeams; i++) { 
			
			if (array[1][i] != 1) {
				shortenedArray[nextIndex] = array[0][i];
				nextIndex++;
			} // end if
		} // end for
		return shortenedArray;
	}
	
	private int getNumber() {
		
		int number;
		number = shortenedArray.length;
		return number;
	}
	
	public static void main(String[] args) {
		
		BilateralProjects test = new BilateralProjects();
		
		test.makeShortenedArray(test.makeFirstArray(test.setPicker(
				test.addTeams(test.determineNumber()))));
		System.out.println(test.getNumber());
		for (int i = 0; i < test.shortenedArray.length; i++)
			if (i < test.shortenedArray.length - 1)
				System.out.println(test.shortenedArray[i]);
			else
				System.out.print(test.shortenedArray[i]);
		
	}
}
