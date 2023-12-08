package adventofcode.day01;

import java.io.File;
import java.io.FileNotFoundException;
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
		int totalOneNum = 0;
		
		boolean oneNum = false;
		
		while(scan.hasNextLine()) {
			String line = scan.nextLine();
			String debug = line;
			int first = -1;
			int last = -1;
			for(int i = 0; i < line.length(); i++) {
				char c = line.charAt(i);
				if('0' <= c && c <= '9') {
					if (first == -1) {
						first = Integer.parseInt(c+"");
						oneNum = true;
					}else {
						oneNum = false;
					}
					last = Integer.parseInt(c+"");
				}
			}
			if(oneNum) {
				System.out.println(debug);
				totalOneNum += first;
			}
			int num = Integer.parseInt(first + "" + last);
			total += num;
		}
		
		scan.close();
		
		System.out.println("Total: " + total);
		System.out.println("TotalOneNum: " + totalOneNum);
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
