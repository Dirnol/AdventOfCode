package adventofcode.day05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day5 {

	private Scanner scanner;
	private long day = 5;
	
	public Day5() {
		
		System.out.println("## Advent of Code 2023 ##\n#### Day "+ day +" Solutions ####");
		System.out.println("Part 1: " + partOne());
		System.out.println("Part 2: " + partTwo());
	}
	
	private String partOne() {
		
		String solution = "N/A";
		ArrayList<Long> seedList = new ArrayList<>();
		
		long[][][] maps = new long[7][100][3];
		
		try {
			scanner = new Scanner(new File("src/adventofcode/day"+ day +"/test.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		int currentMap = -1;
		int mapIndex = 0;
		
		while(scanner.hasNextLine()) {
			String line = scanner.nextLine();
			
			if(line.isEmpty()) {
				
			}
			else if(line.startsWith("seeds:")) {
				String[] seeds = line.split(":")[1].trim().split(" ");
				for(String s : seeds)
					seedList.add(Long.parseLong(s));
			}
			else if(line.charAt(0) >= 'a' && line.charAt(0) <= 'z') {
				currentMap++;
				mapIndex = 0;
			}
			else if(line.charAt(0) >= '0' && line.charAt(0) <= '9') {
				String[] nums = line.split(" ");
				maps[currentMap][mapIndex][0] = Long.parseLong(nums[0]);
				maps[currentMap][mapIndex][1] = Long.parseLong(nums[1]);
				maps[currentMap][mapIndex][2] = Long.parseLong(nums[2]);
				
				mapIndex++;
			}
			
		}
		
		long smallestLocation = Long.MAX_VALUE;
		
		for(long seed : seedList) {
			long location = seed;
			for(int i = 0; i < 7; i++) {
				
				for(int map = 0; map < maps[i].length; map++) {
					
					if(maps[i][map] == null) {
						break;
					}
					
					long dst = maps[i][map][0];
					long src = maps[i][map][1];
					long rng = maps[i][map][2];
					
					if(location >= src && location <= src+rng) {
						location = dst + (location - src);
						break;
					}
				}
				
			}
			if(location < smallestLocation)
				smallestLocation = location;
		}
		
		solution = smallestLocation + "";
		scanner.close();
		return solution;
	}
	
	private String partTwo() {
		
		String solution = "N/A";
		ArrayList<Long> seedList = new ArrayList<>();
		
		long[][][] maps = new long[7][50][3];
		
		try {
			scanner = new Scanner(new File("src/adventofcode/day"+ day +"/input01.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		int currentMap = -1;
		int mapIndex = 0;
		
		while(scanner.hasNextLine()) {
			String line = scanner.nextLine();
			
			if(line.isEmpty()) {
				
			}
			else if(line.startsWith("seeds:")) {
				String[] seeds = line.split(":")[1].trim().split(" ");
				for(String s : seeds)
					seedList.add(Long.parseLong(s));
			}
			else if(line.charAt(0) >= 'a' && line.charAt(0) <= 'z') {
				currentMap++;
				mapIndex = 0;
			}
			else if(line.charAt(0) >= '0' && line.charAt(0) <= '9') {
				String[] nums = line.split(" ");
				maps[currentMap][mapIndex][0] = Long.parseLong(nums[0]);
				maps[currentMap][mapIndex][1] = Long.parseLong(nums[1]);
				maps[currentMap][mapIndex][2] = Long.parseLong(nums[2]);
				
				mapIndex++;
			}
			
		}
		
		for(long i = 0; i < Long.MAX_VALUE; i++) {
			long testSeed = i;
			
			for(int map = maps.length-1; map >= 0; map--) {
				long[][] subMaps = maps[map];
				
				for(int subMap = 0; subMap < subMaps.length; subMap++) {
					long[] mapEntry = subMaps[subMap];
					
					long dst = mapEntry[0];
					long src = mapEntry[1];
					long rng = mapEntry[2];
					
					if(testSeed >= dst && testSeed <= dst+rng) {
						testSeed = src + (testSeed - dst);
						break;
					}
				}
			}
			
			for(int j = 0; j < seedList.size(); j+=2) {
				long start = seedList.get(j);
				long end = start + seedList.get(j+1);
				if(testSeed >= start && testSeed <= end) {
					solution = i + "";
					return solution;
				}
			}
		}
		
		solution = "No Solution Found";
		scanner.close();
		return solution;
	}
}

