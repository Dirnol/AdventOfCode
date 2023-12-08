package adventofcode.day08;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Day8 {

	private Scanner scanner;
	private String day = "08";
	HashMap<String, Node> nodeMap;
	
	public Day8() {
		
		System.out.println("## Advent of Code 2023 ##\n#### Day "+ day +" Solutions ####");
		System.out.println("Part 1: " + partOne());
		System.out.println("Part 2: " + partTwo());
	}
	
	private String partOne() {
		
		String solution = "N/A";
		nodeMap = new HashMap<String, Node>();
		
		try {
			scanner = new Scanner(new File("src/adventofcode/day"+ day +"/input01.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		String instruction = scanner.nextLine();
		
		while(scanner.hasNextLine()) {
			String line = scanner.nextLine();
			
			if(!line.isEmpty()) {
				String name = line.split(" = ")[0];
				String[] children = line.split(" = ")[1].substring(1, 9).split(", ");
				
				Node node = nodeMap.get(name);
				if(node == null) {
					node =  new Node(name);
					nodeMap.put(name, node);
				}
				if(node.left == null) {
					Node left = nodeMap.get(children[0]);
					if(left == null) {
						left = new Node(children[0]);
						nodeMap.put(children[0], left);
					}
					node.left = left;
						
				}
				if(node.right == null) {
					Node right = nodeMap.get(children[1]);
					if(right == null) {
						right = new Node(children[1]);
						nodeMap.put(children[1], right);
					}
					node.right = right;
				}
			}
		}
		
		Node start = nodeMap.get("AAA");
		
		int steps = 0;
		while(!start.name.equals("ZZZ")) {
			char next = instruction.charAt(0);
			if(next == 'L')
				start = start.left;
			else
				start = start.right;
			steps++;
			instruction = instruction.substring(1).concat(next+"");
		}
		
		solution = steps + "";
		scanner.close();
		return solution;
	}
	
	private String partTwo() {
		
		String solution = "N/A";
		nodeMap = new HashMap<String, Node>();
		
		try {
			scanner = new Scanner(new File("src/adventofcode/day"+ day +"/input01.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		String instruction = scanner.nextLine();
		
		while(scanner.hasNextLine()) {
			String line = scanner.nextLine();
			
			if(!line.isEmpty()) {
				String name = line.split(" = ")[0];
				String[] children = line.split(" = ")[1].substring(1, 9).split(", ");
				
				Node node = nodeMap.get(name);
				if(node == null) {
					node =  new Node(name);
					nodeMap.put(name, node);
				}
				if(node.left == null) {
					Node left = nodeMap.get(children[0]);
					if(left == null) {
						left = new Node(children[0]);
						nodeMap.put(children[0], left);
					}
					node.left = left;
						
				}
				if(node.right == null) {
					Node right = nodeMap.get(children[1]);
					if(right == null) {
						right = new Node(children[1]);
						nodeMap.put(children[1], right);
					}
					node.right = right;
				}
			}
		}
		
		ArrayList<Node> aNodes = new ArrayList<>();
		for(String s : nodeMap.keySet()) {
			if(s.endsWith("A"))
				aNodes.add(nodeMap.get(s));
		}
		
		int[] steps = new int[aNodes.size()];
		for(int n = 0; n < aNodes.size(); n++) {
			Node test = aNodes.get(n);
			while(!test.name.endsWith("Z")) {
				char next = instruction.charAt(0);
				if(next == 'L')
					test = test.left;
				else
					test = test.right;
				steps[n]++;
				instruction = instruction.substring(1).concat(next+"");
			}
		}
		long lcm = leastCommonMultiple(steps);
		
		solution = lcm + "";
		scanner.close();
		return solution;
	}
	
	private long leastCommonMultiple(int[] arr) {
		long lcm = 1;
		int divisor = 2;
		
		while(true) {
			int counter = 0;
			boolean divisible = false;
			
			for(int i = 0; i < arr.length; i++) {
				if(arr[i] == 0)
					return 0;
				if(arr[i] == 1)
					counter++;
				if(arr[i] % divisor == 0) {
					divisible = true;
					arr[i] = arr[i] / divisor;
				}
			}
			
			if(divisible)
				lcm = lcm * divisor;
			else
				divisor++;
			if(counter == arr.length)
				return lcm;
		}
	}
	
}

