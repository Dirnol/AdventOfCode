import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Day1 {

	public Day1() {
		
		partOne();
		partTwo();
		
	}
	
	private void partOne() {
		
		ArrayList<Integer> right = new ArrayList<>();
		ArrayList<Integer> left = new ArrayList<>();
		
		Scanner scan = null;
		
		try {
			scan = new Scanner(new File("day1_1.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		while(scan.hasNextLine()) {
			String line = scan.nextLine();
			String[] tokens = line.split("   ");
			left.add(Integer.parseInt(tokens[0]));
			right.add(Integer.parseInt(tokens[1]));			
		}
		
		Collections.sort(right);
		Collections.sort(left);
		
		int total = 0;
		
		for(int i = 0; i < right.size(); i++) {
			total += Math.abs(right.get(i) - left.get(i));
		}
		
		System.out.println(total);
	}
	
	private void partTwo() {

		ArrayList<Integer> left = new ArrayList<>();
		HashMap<Integer, Integer> right = new HashMap<>();
		
		Scanner scan = null;
		
		try {
			scan = new Scanner(new File("day1_1.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		while(scan.hasNextLine()) {
			String line = scan.nextLine();
			String[] tokens = line.split("   ");
			left.add(Integer.parseInt(tokens[0]));
			int rightNum = Integer.parseInt(tokens[1]);
			if(right.containsKey(rightNum))
				right.put(rightNum, right.get(rightNum)+1);
			else
				right.put(rightNum, 1);
		}
		
		int total = 0;
		
		for(int i = 0; i < left.size(); i++) {
			if(right.containsKey(left.get(i)))
				total += left.get(i) * right.get(left.get(i));
		}
		
		System.out.println(total);
	}
	
}
