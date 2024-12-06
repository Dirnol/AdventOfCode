package adventofcode.day11;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day11 {

	private Scanner scanner;
	private int day = 11;

	public Day11() {
		System.out.println("## Advent of Code 2023 ##\n#### Day "+ day +" Solutions ####");
		System.out.println("Part 1: " + galaxyPaths(2));
		System.out.println("Part 2: " + galaxyPaths(1000000));
	}

	private String galaxyPaths(long emptyDistance) {
		String solution = "N/A";

		ArrayList<String> lines = new ArrayList<>();
		try {
			scanner = new Scanner(new File("src/adventofcode/day"+ day +"/input01.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		//Keep a list of rows and columns that are empty (i.e. bigger than they appear on the map)
		ArrayList<Integer> expandedRows = new ArrayList<>();
		ArrayList<Integer> expandedCols = new ArrayList<>();

		while(scanner.hasNextLine()) {
			String line = scanner.nextLine();
			lines.add(line);
			//Add empty rows to the expanded row list
			if(!line.contains("#")){
				expandedRows.add(lines.size()-1);
			}
		}
		
		//create the universe
		char[][] universe = new char[lines.size()][lines.get(0).length()];
		for(int r = 0; r < universe.length; r++){
			for(int c = 0; c < universe[r].length; c++){
				universe[r][c] = lines.get(r).charAt(c);
			}
		}

		//transpose the universe, read empty columns, add them to the expanded column list
		for(int c = 0; c < universe[0].length; c++){
			boolean empty = true;
			for(int r = 0; r < universe.length; r++){
				if(universe[r][c] == '#')
					empty = false;
				}
			if(empty){
				expandedCols.add(c);
			}
		}

		//Our universe needs some galaxies
		ArrayList<Galaxy> galaxies = new ArrayList<>();
		for(int r = 0; r < universe.length; r++){
			for(int c = 0; c < universe[r].length; c++){
				if(universe[r][c] == '#')
					galaxies.add(new Galaxy(r, c));
			}
		}

		long total = 0;
		//Find the shortest path between each pair of galaxies
		for(int i = 0; i < galaxies.size(); i++){
			Galaxy a = galaxies.get(i);
			//Don't check earlier galaxies to avoid creating duplicate pairs
			//e.g. 1->2, and 2->1
			for(int j = i+1; j < galaxies.size(); j++){
				long dist = 0;
				Galaxy b = galaxies.get(j);
				//check if one of the columns or rows between the two galaxies is in the expanded list
				//if so, add the empty distance to the overall distance
				//-1 because the empty column/row will be counted again later
				for(int xc : expandedCols){
					if(a.col < b.col){
						if(xc > a.col && xc < b.col)
							dist += emptyDistance-1;
					}else{
						if(xc > b.col && xc < a.col)
							dist += emptyDistance-1;
					}
				}
				for(int xr : expandedRows){
					if(a.row < b.row){
						if(xr > a.row && xr < b.row)
							dist += emptyDistance-1;
					}else{
						if(xr > b.row && xr < a.row)
							dist += emptyDistance-1;
					}
				}
				//add the total empty distance and the actual distance
				total += dist + Math.abs(b.row-a.row) + Math.abs(b.col-a.col);
			}
		}

		solution = total+"";
		scanner.close();
		return solution;
	}

	private class Galaxy{
		public int row, col;
		public Galaxy(int row, int col){
			this.row = row;
			this.col = col;
		}
	}
}
