package adventofcode.day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day2 {

	private Scanner scanner;
	
	public Day2() {
		
		System.out.println("## Advent of Code 2023 ##\n#### Day 2 Solutions ####");
		System.out.println("Part 1: " + partOne());
		System.out.println("Part 2: " + partTwo());
	}
	
	private String partOne() {
		
		int maxRed = 12;
		int maxGreen = 13;
		int maxBlue = 14;
		
		String solution = "N/A";
		
		try {
			scanner = new Scanner(new File("src/adventofcode/day2/input01.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		int gameTotal = 0;
		
		while(scanner.hasNextLine()) {
			
			boolean gamePossible = true;
			
			String line = scanner.nextLine();
			
			String gameString = line.substring(line.indexOf(':')+1);
			String[] rounds = gameString.split(";");
			
			for(int round = 0; round < rounds.length; round++) {
				
				String[] pulls = rounds[round].split(",");
				for(int pull = 0; pull < pulls.length; pull++) {
					
					String pullString = pulls[pull].trim();
					
					int checkNum = -1;
					
					if(pullString.contains("red")) {
						checkNum = maxRed;
					}else if(pullString.contains("blue")) {
						checkNum = maxBlue;
					}else if(pullString.contains("green")) {
						checkNum = maxGreen;
					}
					
					int pullNum = Integer.parseInt(pullString.split(" ")[0]);
					
					if(pullNum > checkNum) {
						gamePossible = false;
						break;
					}
					
				}
			}
			
			if(gamePossible) {
				gameTotal += Integer.parseInt(line.split(" ")[1].replace(':', ' ').trim());
			}
			
		}
		solution = gameTotal +  "";
		scanner.close();
		return solution;
	}
	
	private String partTwo() {
		
		String solution = "N/A";
		
		try {
			scanner = new Scanner(new File("src/adventofcode/day2/input01.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		int gameTotal = 0;
		
		while(scanner.hasNextLine()) {
			
			int minRed = -1;
			int minBlue = -1;
			int minGreen = -1;
			
			String line = scanner.nextLine();
			
			String gameString = line.substring(line.indexOf(':')+1);
			String[] rounds = gameString.split(";");
			
			for(int round = 0; round < rounds.length; round++) {
				
				String[] pulls = rounds[round].split(",");
				for(int pull = 0; pull < pulls.length; pull++) {
					
					String pullString = pulls[pull].trim();
					
					int pullNum = Integer.parseInt(pullString.split(" ")[0]);
					System.out.println(pullString);
					
					if(pullString.contains("red") && pullNum > minRed) {
						minRed = pullNum;
					}else if(pullString.contains("blue") && pullNum > minBlue) {
						minBlue = pullNum;
					}else if(pullString.contains("green") && pullNum > minGreen) {
						minGreen = pullNum;
					}
				}
			}
			
			int roundTotal = minRed * minBlue * minGreen;
			
			System.out.println(line);
			System.out.println("R: " + minRed + " G: " + minGreen + " B: " + minBlue);
			System.out.println();
			
			gameTotal += roundTotal;
			
		}
		solution = gameTotal +  "";
		scanner.close();
		return solution;
	}
}

