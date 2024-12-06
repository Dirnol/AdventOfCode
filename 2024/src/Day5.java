import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Day5 {

	public Day5() {
		partOne();
		partTwo();
	}
	
	ArrayList<Rule> rules = new ArrayList<>();
	
	private void partOne() {
		
		Scanner scan = null;
		
		int total = 0;
		
		try {
			scan = new Scanner(new File("day5_1.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		ArrayList<Integer> order = new ArrayList<>();
		HashMap<Integer,Integer> orderMap = new HashMap<>();
		
		int rulesNum = 0;
		
		boolean readingOrder = true;
		
		while(scan.hasNextLine()) {
			String line = scan.nextLine();
			
			if(line.trim().isEmpty()) {
				readingOrder = false;
				System.out.println(orderMap);
				/*for(Rule r : rules) {
					if(order.indexOf(r.left) > order.indexOf(r.right)) {
						order.set(order.indexOf(r.left), r.right);
						order.set(order.indexOf(r.right), r.left);
						System.out.println("Wrong Order: " + r.left + "|" + r.right);
					}
				}*/
			}
			else if(readingOrder) {
				addToOrder(line, orderMap);
				rulesNum++;
			}else {
				//total += readPages(line, (ArrayList<Integer>)order.clone());
			}
		}
		
		System.out.println(total);
		
	}
	
	private class Page {
		public int num;
		public ArrayList<Integer> right = new ArrayList<>(); 
		public ArrayList<Integer> left = new ArrayList<>(); 
		
		public Page(int num) {
			this.num = num;
		}
	}
	
	private class Rule {
	
		public int left, right;
		
		public Rule(int left, int right) {
			this.left = left;
			this.right = right;
		}
		
	}
	
	private int readPages(String line, ArrayList<Integer> order) {
		String[] numStr = line.split(",");
		ArrayList<Integer> nums = new ArrayList<>();
		
		for(int i = 0; i < numStr.length; i++) {
			nums.add(Integer.parseInt(numStr[i]));
		}
		
		boolean done = false;
		int index = 0;
		while(!done) {
			if(!nums.contains(order.get(index))){
				order.remove(index);
			}else {
				index++;
			}
			if(index >= order.size())
				done = true;
		}
		
		/*for(int i : order) {
			System.out.print(i + ", ");
		}
		System.out.println();*/
		
		int middle = order.size()/2;
		return order.get(middle);		
	}
	
	private void addToOrder(String line, HashMap<Integer, Integer> order) {
		String[] numsString = line.split("\\|");
		int left = Integer.parseInt(numsString[0]);
		int right = Integer.parseInt(numsString[1]);
		
		order.put(left, right);
	}
	
	private void addToOrder(String line, ArrayList<Integer> order) {
		
		String[] numsString = line.split("\\|");
		int left = Integer.parseInt(numsString[0]);
		int right = Integer.parseInt(numsString[1]);
		
		rules.add(new Rule(left, right));
		
		int li = order.indexOf(left);
		int ri = order.indexOf(right);
		
		if(li != -1 && ri != -1) {
			if(li > ri) {
				//order.remove(li);
				//order.add(ri, left);
				order.set(li, right);
				order.set(ri, left);
			}
		}
		
		if(li == -1)
			order.add(0, left);
		
		if(ri == -1)
			order.add(right);
		
		//System.out.println(order);
	}
	
	private void partTwo() {
		
	}
	
}
