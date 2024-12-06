package adventofcode.day09;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day9 {

	private Scanner scanner;
	private String day = "09";
	
	public Day9() {
		
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
			String[] line = scanner.nextLine().trim().split(" ");
			int[] history = new int[line.length];
			for(int i = 0 ; i < history.length; i++)
				history[i] = Integer.parseInt(line[i]);
			
			ArrayList<int[]> historyArr = new ArrayList<>();
			historyArr.add(history);
			boolean allZero = false;
			while(!allZero) {
				int[] next = new int[history.length-1];
				allZero = true;
				for(int i = 0; i < next.length; i++) {
					next[i] = history[i+1] - history[i];
					if(next[i] != 0)
						allZero = false;
				}
				historyArr.add(next);
				history = next;
			}
			int future = 0;
			for(int i = historyArr.size()-2; i >= 0; i--) {
				future = future + historyArr.get(i)[historyArr.get(i).length-1];
			}
			total += future;
			
		}
		solution = total + "";
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
		
		int total = 0;
		while(scanner.hasNextLine()) {
			String[] line = scanner.nextLine().trim().split(" ");
			int[] history = new int[line.length];
			for(int i = 0 ; i < history.length; i++)
				history[i] = Integer.parseInt(line[i]);
			
			ArrayList<int[]> historyArr = new ArrayList<>();
			historyArr.add(history);
			boolean allZero = false;
			while(!allZero) {
				int[] next = new int[history.length-1];
				allZero = true;
				for(int i = 0; i < next.length; i++) {
					next[i] = history[i+1] - history[i];
					if(next[i] != 0)
						allZero = false;
				}
				historyArr.add(next);
				history = next;
			}
			int past = 0;
			for(int i = historyArr.size()-2; i >= 0; i--) {
				past = historyArr.get(i)[0] - past;
			}
			total += past;
			
		}
		solution = total + "";
		scanner.close();
		return solution;
	}
}

