package adventofcode.day12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day12 {

	private Scanner scanner;
	private int day = 12;
	
	public Day12() {
		
		System.out.println("## Advent of Code 2023 ##\n#### Day "+ day +" Solutions ####");
		System.out.println("Part 1: " + partOne());
		System.out.println("Part 2: " + partTwo());
	}
	
	private String partOne() {
		
		String solution = "N/A";
		
		try {
			scanner = new Scanner(new File("src/adventofcode/day"+ day +"/test.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		ArrayList<Spring> springs = new ArrayList<>();
		while(scanner.hasNextLine()) {
			String[] line = scanner.nextLine().split(" ");
			char[] spring = line[0].toCharArray();
			String[] configStr = line[1].split(",");
			int[] config = new int[configStr.length];
			for(int i = 0; i < configStr.length; i++) {
				config[i] = Integer.parseInt(configStr[i]);
			}
			springs.add(new Spring(spring, config));
		}
		
		int total = 0;
		for(Spring s : springs) {
			total += s.possibleConfigurations();
		}
		
		solution = total+"";
		scanner.close();
		return solution;
	}
	
	private String partTwo() {
		
		String solution = "N/A";
		
		try {
			scanner = new Scanner(new File("src/adventofcode/day"+ day +"/test.txt"));
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
	
	private class Spring{
		public char[] operation;
		public int[] config;
		int configCount = 0;
		ArrayList<String> possible = new ArrayList<>(); 
		public Spring(char[] operation, int[] config) {
			this.operation = operation;
			this.config = config;
			getPossibilities(operation);
			//validateConfigs();
		}
		
		public void getPossibilities(char[] s) {
			System.out.println(String.copyValueOf(s));
			if(!isValidSoFar(s))
				return;
			boolean full = true;
			for(int i = 0; i < s.length; i++) {
				if(s[i] == '?') {
					char[] t1 = s.clone();
					t1[i] = '.';
					getPossibilities(t1);
					char[] t2 = s.clone();
					t2[i] = '#';
					getPossibilities(t2);
					full = false;
					break;
				}
			}
			if(full) {
				String str = String.copyValueOf(s);
				if(!possible.contains(str)) {
					System.out.println(str);
					possible.add(str);
				}
					
			}
		}
		
		private boolean isValidSoFar(char[] s) {
			int groupIndex = 0;
			int count = 0;
			boolean readingGroup = false;
			for(int i = 0; i < s.length; i++) {
				if(s[i] == '#') {
					readingGroup = true;
					count++;
				}
				else{
					
					if(s[i] == '?')
						return true;
					
					if(readingGroup) {
						readingGroup = false;
						if(config[groupIndex] != count)
							return false;
						count = 0;
						groupIndex++;
					}
				}
			}
			if(groupIndex > config.length)
				return true;
			return false;
		}
		
		public int possibleConfigurations() {
			int configs = 0;
			
			return configs;
		}
	}
}

