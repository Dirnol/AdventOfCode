package adventofcode.day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day6 {

	private Scanner scanner;
	private int day = 6;
	
	public Day6() {
		
		System.out.println("## Advent of Code 2023 ##\n#### Day "+ day +" Solutions ####");
		System.out.println("Part 1: " + partOne());
		System.out.println("Part 2: " + partTwo());
	}

	private boolean isDigit(String line, int index) {
		return line.charAt(index) >= '0' && line.charAt(index) <= '9';
	}
	
	private String partOne() {
		
		String solution = "N/A";
		
		try {
			scanner = new Scanner(new File("src/adventofcode/day"+ day +"/input01.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		ArrayList<Integer> time = new ArrayList<>();
		ArrayList<Integer> distance = new ArrayList<>();
		
		while(scanner.hasNextLine()) {
			String line = scanner.nextLine();
			
			if(line.startsWith("Time"))
				readValues(line, time);
			
			if(line.startsWith("Distance"))
				readValues(line, distance);
		}
		
		int total = 1;
		for(int i = 0; i < time.size(); i++) {
			int wins = 0;
			for(int t = 0; t < time.get(i); t++) {
				int speed = t;
				int travelTime = time.get(i) - t;
				int totalDistance = travelTime * speed;
				if(totalDistance > distance.get(i))
					wins++;
			}
			total *= wins;
		}
		
		solution = total + "";
		scanner.close();
		return solution;
	}
	
	private void readValues(String line, ArrayList<Integer> out) {
		String numPart = line.split(":")[1].trim();
		String num = "";
		for(int i = 0; i < numPart.length(); i++) {
			if(isDigit(numPart, i))
				num += numPart.charAt(i);
			else {
				if(!num.isEmpty()) {
					out.add(Integer.parseInt(num));
					num = "";
				}
			}
		}
		out.add(Integer.parseInt(num));
	}
	
	private String partTwo() {
		
		String solution = "N/A";
		
		try {
			scanner = new Scanner(new File("src/adventofcode/day"+ day +"/input01.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		long time =  0;
		long distance = 0;
		
		while(scanner.hasNextLine()) {
			String line = scanner.nextLine();
			
			if(line.startsWith("Time"))
				time = Long.parseLong(line.split(":")[1].replace(" ", ""));
			
			if(line.startsWith("Distance"))
				distance = Long.parseLong(line.split(":")[1].replace(" ", ""));
		}
		
		int wins = 0;
		for(int t = 0; t < time; t++) {
			long speed = t;
			long travelTime = time - t;
			long totalDistance = travelTime * speed;
			if(totalDistance > distance)
				wins++;
		}
		
		solution = wins + "";
		scanner.close();
		return solution;
	}
}

