package adventofcode.day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day1 {
	
	public Day1() {
		
		partOne();
		partTwo();
		
	}
	
	public void partOne() {
		
		Scanner scan = null;
		try {
			scan = new Scanner(new File("src/adventofcode/day1/input01.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		int total = 0;
		while(scan.hasNextLine()) {
			String line = scan.nextLine();
			int first = -1;
			int last = -1;
			for(int i = 0; i < line.length(); i++) {
				char c = line.charAt(i);
				if('0' <= c && c <= '9') {
					if (first == -1) {
						first = Integer.parseInt(c+"");
					}
					last = Integer.parseInt(c+"");
				}
			}
			int num = Integer.parseInt(first + "" + last);
			total += num;
		}
		
		scan.close();
		
		System.out.println("Total: " + total);
	}
	
	public void partTwo() {
		
		String[] numberStrings = {"one","two","three","four","five","six","seven","eight","nine"};
		
		Scanner scan = null;
		try {
			scan = new Scanner(new File("src/adventofcode/day1/input01.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		int total = 0;
		while(scan.hasNextLine()) {
			String line = scan.nextLine();
			
			int first = -1;
			int last = -1;
			
			while(line.length() > 0) {
				int lineNum = -1;
				if(line.charAt(0) >= '0' && line.charAt(0) <= '9') {
					lineNum = Integer.parseInt(line.charAt(0)+"");
				}else {
					for(int i = 0; i < numberStrings.length; i++) {
						if(line.startsWith(numberStrings[i]))
							lineNum = i+1;
					}
				}
				
				line = line.substring(1);
				
				if(lineNum != -1) {
					if(first == -1) {
						first = lineNum;
					}
					last = lineNum;
				}
			}
			
			int num = Integer.parseInt(first + "" + last);
			total += num;
			
		}
		scan.close();
		System.out.println("Total: " + total);
	}
}
