package adventofcode.day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day4 {

	private Scanner scanner;
	private int day = 4;
	
	public Day4() {
		
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
		
		int total = 0;
		
		while(scanner.hasNextLine()) {
			String line = scanner.nextLine();
			
			int pointVal = 0;
			
			ArrayList<Integer> winningNumbers = new ArrayList<>();
			ArrayList<Integer> yourNumbers = new ArrayList<>();
			
			String[] winningNumbersStrings = line.split("\\|")[0].split(":")[1].trim().split(" ");
			String[] yourNumbersStrings = line.split("\\|")[1].trim().split(" ");
			
			for(String s : winningNumbersStrings)
				if(!s.trim().isEmpty())
					winningNumbers.add(Integer.parseInt(s));
			
			for(String s : yourNumbersStrings)
				if(!s.trim().isEmpty())
					yourNumbers.add(Integer.parseInt(s));
			
			for(int num : yourNumbers) {
				if(winningNumbers.contains(num)) {
					if(pointVal == 0)
						pointVal = 1;
					else
						pointVal *= 2;
				}
			}
			total += pointVal;
			
		}
		
		solution = total + "";
		scanner.close();
		return solution;
	}
	
	private String partTwo() {
		
		String solution = "N/A";
		
		int[] cardCopies = new int[204];
		for(int i = 0;  i < cardCopies.length; i++) {
			cardCopies[i] = 1;
		}
		
		
		try {
			scanner = new Scanner(new File("src/adventofcode/day"+ day +"/input01.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		int total = 0;
		
		while(scanner.hasNextLine()) {
			String line = scanner.nextLine();
			
			int cardNum = Integer.parseInt(line.split(":")[0].substring(4).trim())-1;
			
			int winNum = 0;
			
			ArrayList<Integer> winningNumbers = new ArrayList<>();
			ArrayList<Integer> yourNumbers = new ArrayList<>();
			
			String[] winningNumbersStrings = line.split("\\|")[0].split(":")[1].trim().split(" ");
			String[] yourNumbersStrings = line.split("\\|")[1].trim().split(" ");
			
			for(String s : winningNumbersStrings)
				if(!s.trim().isEmpty())
					winningNumbers.add(Integer.parseInt(s));
			
			for(String s : yourNumbersStrings)
				if(!s.trim().isEmpty())
					yourNumbers.add(Integer.parseInt(s));
			
			for(int num : yourNumbers) {
				if(winningNumbers.contains(num)) {
					winNum++;
				}
			}
			
			for(int r = 0; r < cardCopies[cardNum]; r++) {
				for(int i = cardNum+1; i < cardNum+1+winNum; i++) {
					cardCopies[i]++;
				}
			}
			
		}
		
		for(int i : cardCopies) {
			total += i;
		}
		
		solution = total + "";
		scanner.close();
		return solution;
	}
}

