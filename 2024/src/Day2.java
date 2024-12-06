import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day2 {

	public Day2() {
		partOne();
		partTwo();
	}
	
	private void partOne() {
		
		Scanner scan = null;
		
		int safeNum = 0;
		
		try {
			scan = new Scanner(new File("day2_1.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		while(scan.hasNextLine()) {
			String line = scan.nextLine();
			
			String[] tokens = line.split(" ");
			int[] record = new int[tokens.length];
			for(int i = 0; i < tokens.length; i++) {
				record[i] = Integer.parseInt(tokens[i]);
			}
			
			boolean safe = true;
			boolean ascending = true;
			
			if(record[0] > record[1])
				ascending = false;
			
			for(int i = 1; i < record.length; i++) {
				if((ascending && record[i] < record[i-1])
					|| (!ascending && record[i] > record[i-1])){
						safe = false;
					}
				if(Math.abs(record[i] - record[i-1]) < 1 || Math.abs(record[i] - record[i-1]) > 3)
					safe = false;
			}
			
			if(safe) {
				safeNum++;
			}
			
		}
		
		System.out.println(safeNum);
	}
	
	private void partTwo() {
		Scanner scan = null;
		
		int safeNum = 0;
		
		try {
			scan = new Scanner(new File("day2_1.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		while(scan.hasNextLine()) {
			
			ArrayList<int[]> dampenedCandidates = new ArrayList<>();
			
			String line = scan.nextLine();
			//System.out.println(line);
			
			String[] tokens = line.split(" ");
			int[] record = new int[tokens.length];
			for(int i = 0; i < tokens.length; i++) {
				record[i] = Integer.parseInt(tokens[i]);
			}
			
			boolean safe = true;
			boolean ascending = true;
			
			if(record[0] > record[1])
				ascending = false;
			
			for(int i = 1; i < record.length; i++) {
				if((ascending && record[i] < record[i-1])
					|| (!ascending && record[i] > record[i-1])){
						safe = false;
						
						int[] newOne = new int[record.length-1];
						int[] newTwo = new int[record.length-1];
						
						for(int j = 0; j < newOne.length; j++) {
							if(j < i) {
								newOne[j] = record[j];
								newTwo[j] = record[j];
								if(j == i-1) {
									newTwo[j] = record[j+1];
									
								}
							}
							else if(j > i){
								newOne[j] = record[j+1];
								newTwo[j] = record[j+1];
							}
							else if(j == i) {
								newOne[j] = record[j+1];
								newTwo[j] = record[j+1];
							}
							
						}
						
						dampenedCandidates.add(newOne);
						dampenedCandidates.add(newTwo);
						
						/*System.out.println("Record: ");
						printRecord(record);
						System.out.println("Dampened Candidates: ");
						printRecord(newOne);
						printRecord(newTwo);*/
						
						if(i == 2) {
							int[] newThree = new int[record.length-1];
							for(int j = 0; j < newThree.length; j++) {
								newThree[j] = record[j+1];
							}
							dampenedCandidates.add(newThree);
							//printRecord(newThree);
						}
						//System.out.println("\n");
						
					}
				if(Math.abs(record[i] - record[i-1]) < 1 || Math.abs(record[i] - record[i-1]) > 3) {
					safe = false;
					
					int[] newOne = new int[record.length-1];
					int[] newTwo = new int[record.length-1];
					
					for(int j = 0; j < newOne.length; j++) {
						if(j < i) {
							newOne[j] = record[j];
							newTwo[j] = record[j];
							if(j == i-1) {
								newTwo[j] = record[j+1];
								
							}
						}
						else if(j > i){
							newOne[j] = record[j+1];
							newTwo[j] = record[j+1];
						}
						else if(j == i) {
							newOne[j] = record[j+1];
							newTwo[j] = record[j+1];
						}
						
					}
					
					dampenedCandidates.add(newOne);
					dampenedCandidates.add(newTwo);
					
					/*System.out.println("Record: ");
					printRecord(record);
					System.out.println("Dampened Candidates: ");
					printRecord(newOne);
					printRecord(newTwo);*/
				}
			}
			
			if(!safe) {
				//printRecord(record);
			}
			for(int[] candidate : dampenedCandidates) {
				
				//printRecord(candidate);
				
				boolean safeCand = true;
				boolean ascCand = true;
				
				if(candidate[0] > candidate[1])
					ascCand = false;
				
				for(int i = 1; i < candidate.length; i++) {
					if((ascCand && candidate[i] < candidate[i-1])
						|| (!ascCand && candidate[i] > candidate[i-1])){
							safeCand = false;
						}
					if(Math.abs(candidate[i] - candidate[i-1]) < 1 || Math.abs(candidate[i] - candidate[i-1]) > 3) {
						safeCand = false;
					}
				}
				if(safeCand) {
					safe = true;
					break;
				}
			}
			
			if(safe) {
				safeNum++;
			}else {
				//printRecord(record);
				//System.out.println();
			}
			
		}
		
		System.out.println(safeNum);
	}
	
	private void printRecord(int[] record) {
		for(int i : record) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
	
}
