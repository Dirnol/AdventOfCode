package adventofcode.day07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Day7 {

	private Scanner scanner;
	private int day = 7;
	
	public Day7() {
		
		System.out.println("## Advent of Code 2023 ##\n#### Day "+ day +" Solutions ####");
		System.out.println("Part 1: " + partOne());
		System.out.println("Part 2: " + partTwo());
	}
	
	private String partOne() {
		
		ArrayList<Hand> hands = new ArrayList<>();
		
		String solution = "N/A";
		
		try {
			scanner = new Scanner(new File("src/adventofcode/day"+ day +"/input01.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		while(scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] l = line.split(" ");
			hands.add(new Hand(l[0], Integer.parseInt(l[1])));
		}
		
		Collections.sort(hands);
		
		int total = 0;
		for(int rank = 0; rank < hands.size(); rank++) {
			total += hands.get(rank).getBid() * (rank+1);
		}
		
		solution = total+"";
		scanner.close();
		return solution;
	}
	
	private String partTwo() {
		
		ArrayList<Hand> hands = new ArrayList<>();
		
		String solution = "N/A";
		
		try {
			scanner = new Scanner(new File("src/adventofcode/day"+ day +"/input01.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		while(scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] l = line.split(" ");
			hands.add(new Hand(l[0], Integer.parseInt(l[1])));
		}
		
		Collections.sort(hands);

		int total = 0;
		for(int rank = 0; rank < hands.size(); rank++) {
			int r = rank+1;
			System.out.println(r + ": " + hands.get(rank).getHand() + " " +  hands.get(rank).getStrengthString());
			total += hands.get(rank).getBid() * (rank+1);
		}
		
		solution = total+"";
		scanner.close();
		return solution;
	}
}



