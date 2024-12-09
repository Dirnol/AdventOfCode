import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

public class Day5 {

	public Day5() {
		partOne();
		partTwo();
	}
	
	private void partOne() {
		
		Scanner scan = null;
		int total = 0;
		
		try {
			scan = new Scanner(new File("2024/day5_1.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		HashMap<Integer,ArrayList<Integer>> orderMap = new HashMap<>();
		
		boolean readingOrder = true;
		
		while(scan.hasNextLine()) {
			String line = scan.nextLine();
			
			if(line.trim().isEmpty()) {
				readingOrder = false;
			}
			else if(readingOrder) {
				addToOrder(line, orderMap);
			}else {
				ArrayList<Integer> pages = readPages(line);
				if(checkOrder(pages, orderMap)) {
					total += pages.get(pages.size()/2);
				}
			}
		}
		System.out.println(total);
	}
	
	private boolean checkOrder(ArrayList<Integer> pages, HashMap<Integer, ArrayList<Integer>> orderMap) {
		boolean correct = true;
		
		for(int i = 0; i < pages.size(); i++) {
			int page = pages.get(i);
			if(orderMap.containsKey(page)) {
				for(int t = 0; t < orderMap.get(page).size(); t++) {
					int test = orderMap.get(page).get(t);
					int testIndex = pages.indexOf(test);
					if(testIndex != -1 && testIndex < i) {
						pages.set(i, test);
						pages.set(testIndex, page);
						return false;
					}
				}
			}
		}
		return correct;
	}
	
	private ArrayList<Integer> readPages(String line) {
		String[] numStr = line.split(",");
		ArrayList<Integer> nums = new ArrayList<>();
		
		for(int i = 0; i < numStr.length; i++) {
			
			nums.add(Integer.parseInt(numStr[i]));
		}
		
		return nums;
	}
	
	private void addToOrder(String line, HashMap<Integer, ArrayList<Integer>> orderMap) {
		String[] numsString = line.split("\\|");
		int left = Integer.parseInt(numsString[0]);
		int right = Integer.parseInt(numsString[1]);
		
		if(orderMap.containsKey(left)) {
			orderMap.get(left).add(right);
		} else {
			ArrayList<Integer> arr = new ArrayList<>();
			arr.add(right);
			orderMap.put(left, arr);
		}
	}
	
	private void partTwo() {
		
		Scanner scan = null;
		int total = 0;
		
		try {
			scan = new Scanner(new File("2024/day5_1.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		ArrayList<Integer> order = new ArrayList<>();
		HashMap<Integer,ArrayList<Integer>> orderMap = new HashMap<>();
		
		boolean readingOrder = true;
		
		while(scan.hasNextLine()) {
			String line = scan.nextLine();
			
			if(line.trim().isEmpty()) {
				readingOrder = false;
			}
			else if(readingOrder) {
				addToOrder(line, orderMap);
			}else {
				
				ArrayList<Integer> pages = readPages(line);
				if(!checkOrder(pages, orderMap)) {
					while(!checkOrder(pages, orderMap)) {}
					total += pages.get(pages.size()/2);
				}
			}
		}
		System.out.println(total);
	}
	
}
