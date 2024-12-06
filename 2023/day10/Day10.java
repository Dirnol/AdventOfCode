package adventofcode.day10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Day10 {

	private Scanner scanner;
	private int day = 10;
	
	private enum Direction {NORTH, SOUTH, EAST, WEST};
	
	char[][] zoom = null;
	
	public Day10() {
		
		System.out.println("## Advent of Code 2023 ##\n#### Day "+ day +" Solutions ####");
		System.out.println("Part 1: " + partOne());
		System.out.println("Part 2: " + partTwo());
	}
	
	private String partOne() {
		
		String solution = "N/A";
		
		try {
			scanner = new Scanner(new File("src/adventofcode/day"+ day +"/input01.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		int startRow, startCol;
		int dir1R = 0, dir1C = 0, dir2R = 0, dir2C = 0;
		Direction d1 = null;
		Direction d2 = null;
		
		ArrayList<String> lines = new ArrayList<>();
		while(scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if(line.contains("S")) {
				startRow = lines.size();
				startCol = line.indexOf("S");
				//Hardcode
				line = line.replace('S', '|');
				dir1R = startRow-1;
				dir1C = startCol;
				d1 = Direction.NORTH;
				dir2R = startRow+1;
				dir2C = startCol;
				d2 = Direction.SOUTH;
				
			}
				
			lines.add(line);
		}
		scanner.close();
		
		int[][] map = new int[lines.size()][lines.get(0).length()];

		int dist = 1;
		char c1 = lines.get(dir1R).charAt(dir1C);
		char c2 = lines.get(dir2R).charAt(dir2C);

		while(!(dir1R == dir2R && dir1C == dir2C)) {
			map[dir1R][dir1C] = map[dir2R][dir2C] = dist;
			
			c1 = lines.get(dir1R).charAt(dir1C);
			c2 = lines.get(dir2R).charAt(dir2C);
			
			d1 = nextDir(c1, d1);
			d2 = nextDir(c2, d2);
			
			dir1R = nextRow(dir1R, d1);
			dir1C = nextCol(dir1C, d1);
			dir2R = nextRow(dir2R, d2);
			dir2C = nextCol(dir2C, d2);
			dist++;
			
			
		}		
		
		solution = dist + "";
		return solution;
	}
	
	private int nextRow(int r, Direction d1) {
		if(d1 == Direction.NORTH)
			return r-1;
		else if(d1 == Direction.SOUTH)
			return r+1;
		return r;
	}
	
	private int nextCol(int c, Direction d1) {
		if(d1 == Direction.WEST)
			return c-1;
		else if(d1 == Direction.EAST)
			return c+1;
		return c;
	}
	
	private Direction nextDir(char c, Direction movDir) {
		if(c == 'F')
			if(movDir == Direction.NORTH)
				return Direction.EAST;
			else
				return Direction.SOUTH;
		else if(c == 'J')
			if(movDir == Direction.SOUTH)
				return Direction.WEST;
			else
				return Direction.NORTH;
		else if(c == '|')
			if(movDir == Direction.SOUTH)
				return Direction.SOUTH;
			else
				return Direction.NORTH;
		else if(c == '7')
			if(movDir == Direction.EAST)
				return Direction.SOUTH;
			else
				return Direction.WEST;
		else if(c == 'L')
			if(movDir == Direction.SOUTH)
				return Direction.EAST;
			else
				return Direction.NORTH;
		else if(c == '-')
			if(movDir == Direction.EAST)
				return Direction.EAST;
			else
				return Direction.WEST;
		else {
			System.out.println("I missed something");
			return Direction.NORTH;
		}
	}
	
	private String partTwo() {
		
		String solution = "N/A";
		
		try {
			scanner = new Scanner(new File("src/adventofcode/day"+ day +"/input01.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		int startRow = 0, startCol = 0;
		int dir1R = 0, dir1C = 0, dir2R = 0, dir2C = 0;
		Direction d1 = null;
		Direction d2 = null;
		
		ArrayList<String> lines = new ArrayList<>();
		while(scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if(line.contains("S")) {
				startRow = lines.size();
				startCol = line.indexOf("S");
				//Replace S with correct pipe
				line = line.replace('S', '|');
				dir1R = startRow-1;
				dir1C = startCol;
				d1 = Direction.NORTH;
				dir2R = startRow+1;
				dir2C = startCol;
				d2 = Direction.SOUTH;
				
			}
				
			lines.add(line);
		}
		scanner.close();
		
		int[][] map = new int[lines.size()][lines.get(0).length()];
		char[][] loop = new char[lines.size()][lines.get(0).length()];
		loop[startRow][startCol] = '|';

		int dist = 1;
		char c1 = lines.get(dir1R).charAt(dir1C);
		char c2 = lines.get(dir2R).charAt(dir2C);

		while(!(dir1R == dir2R && dir1C == dir2C)) {
			map[dir1R][dir1C] = map[dir2R][dir2C] = dist;
			
			c1 = lines.get(dir1R).charAt(dir1C);
			c2 = lines.get(dir2R).charAt(dir2C);
			
			loop[dir1R][dir1C] = c1;
			loop[dir2R][dir2C] = c2;
			
			d1 = nextDir(c1, d1);
			d2 = nextDir(c2, d2);
			
			dir1R = nextRow(dir1R, d1);
			dir1C = nextCol(dir1C, d1);
			dir2R = nextRow(dir2R, d2);
			dir2C = nextCol(dir2C, d2);
			dist++;
			
			
		}
		loop[dir1R][dir1C] = lines.get(dir1R).charAt(dir1C);
		
		for(int r = 0; r < map.length; r++) {
			for(int c = 0; c < map[r].length; c++) {
				if(loop[r][c] == 0)
					//loop[r][c] = '0';
					loop[r][c] = ' ';
			}
		}
		
		zoom = new char[loop.length*3][loop[0].length*3];
		for(int r = 0; r < map.length; r++) {
			for(int c = 0; c < map[r].length; c++) {
				zoomChar(r, c, loop[r][c]);
			}
		}
		
		floodFill();
		
		int count = 0;
		for(int r = 0; r < zoom.length; r++) {
			for(int c = 0; c < zoom[r].length; c++) {
				if(zoom[r][c] == '0')
					count++;
				System.out.print(zoom[r][c]);
			}
			System.out.println();
		}
		
		
		solution = count + "";
		return solution;
	}
	
	private void floodFill() {
		
		Queue<Point> queue = new LinkedList<Point>();
		queue.add(new Point(0,0));
		
		while(!queue.isEmpty()) {
			Point p = queue.remove();
			int row = p.row;
			int col = p.col;
			if(row >= 0 && row < zoom.length && col >= 0 && col < zoom[row].length && zoom[row][col] != '#' && zoom[row][col] != '.') {
				if(zoom[row][col] == '0' || zoom[row][col] == ' ')
					zoom[row][col] = '.';
				queue.add(new Point(row, col+1));
				queue.add(new Point(row-1, col));
				queue.add(new Point(row+1, col));
				queue.add(new Point(row, col-1));
			}
		}
	}
	
	private class Point{
		int row, col;
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	private void zoomChar(int row, int col, char c) {
		row = row*3;
		col = col*3;
		if(c == 'F') {
			zoom[row][col]     = ' ';	zoom[row][col+1]   = ' ';	zoom[row][col+2]   = ' ';
			zoom[row+1][col]   = ' ';	zoom[row+1][col+1] = '#';	zoom[row+1][col+2] = '#';
			zoom[row+2][col]   = ' ';	zoom[row+2][col+1] = '#';	zoom[row+2][col+2] = ' ';
		}
		else if(c == 'J') {
			zoom[row][col]     = ' ';	zoom[row][col+1]   = '#';	zoom[row][col+2]   = ' ';
			zoom[row+1][col]   = '#';	zoom[row+1][col+1] = '#';	zoom[row+1][col+2] = ' ';
			zoom[row+2][col]   = ' ';	zoom[row+2][col+1] = ' ';	zoom[row+2][col+2] = ' ';
		}
		else if(c == '|') {
			zoom[row][col]     = ' ';	zoom[row][col+1]   = '#';	zoom[row][col+2]   = ' ';
			zoom[row+1][col]   = ' ';	zoom[row+1][col+1] = '#';	zoom[row+1][col+2] = ' ';
			zoom[row+2][col]   = ' ';	zoom[row+2][col+1] = '#';	zoom[row+2][col+2] = ' ';
		}
		else if(c == '7') {
			zoom[row][col]     = ' ';	zoom[row][col+1]   = ' ';	zoom[row][col+2]   = ' ';
			zoom[row+1][col]   = '#';	zoom[row+1][col+1] = '#';	zoom[row+1][col+2] = ' ';
			zoom[row+2][col]   = ' ';	zoom[row+2][col+1] = '#';	zoom[row+2][col+2] = ' ';
		}
		else if(c == 'L') {
			zoom[row][col]     = ' ';	zoom[row][col+1]   = '#';	zoom[row][col+2]   = ' ';
			zoom[row+1][col]   = ' ';	zoom[row+1][col+1] = '#';	zoom[row+1][col+2] = '#';
			zoom[row+2][col]   = ' ';	zoom[row+2][col+1] = ' ';	zoom[row+2][col+2] = ' ';
		}
		else if(c == '-') {
			zoom[row][col]     = ' ';	zoom[row][col+1]   = ' ';	zoom[row][col+2]   = ' ';
			zoom[row+1][col]   = '#';	zoom[row+1][col+1] = '#';	zoom[row+1][col+2] = '#';
			zoom[row+2][col]   = ' ';	zoom[row+2][col+1] = ' ';	zoom[row+2][col+2] = ' ';
		}
		else if(c == ' ') {
			zoom[row][col]     = '0';	zoom[row][col+1]   = ' ';	zoom[row][col+2]   = ' ';
			zoom[row+1][col]   = ' ';	zoom[row+1][col+1] = ' ';	zoom[row+1][col+2] = ' ';
			zoom[row+2][col]   = ' ';	zoom[row+2][col+1] = ' ';	zoom[row+2][col+2] = ' ';
		}
	}
}

