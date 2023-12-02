package adventofcode.day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day6 {

	private Scanner scanner;
	private int day = 6;
	
	public Day6() {
		
		System.out.println("## Advent of Code 2023 ##\n#### Day "+ day +" Solutions ####");
		System.out.println("Part 1: " + partOne());
		System.out.println("Part 2: " + partTwo());
	}
	
	private String partOne() {
		
		String solution = "N/A";
		
		try {
			scanner = new Scanner(new File("src/adventofcode/day"+ day +"/input01.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		while(scanner.hasNextLine()) {
			String line = scanner.nextLine();
			
			//Do stuff with the input here
			
		}
		
		scanner.close();
		return solution;
	}
	
	private String partTwo() {
		
		String solution = "N/A";
		
		try {
			scanner = new Scanner(new File("src/adventofcode/day"+ day +"/input01.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		while(scanner.hasNextLine()) {
			String line = scanner.nextLine();
			
			//Do stuff with the input here
			
		}
		
		scanner.close();
		return solution;
	}
}

