import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day6 {

	int total = 0;
	
	public Day6() {
		partOne();
		partTwo();
	}
	
	private void partOne() {
		
		Scanner scan = null;
		char[][] map;
		ArrayList<String> lines = new ArrayList<>();
		
		int startX = 0, startY = 0;
		
		try {
			scan = new Scanner(new File("2024/day6_1.txt"));
			while(scan.hasNextLine()) {
				lines.add(scan.nextLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		map = new char[lines.size()][lines.get(0).length()];
		for(int i = 0; i < lines.size(); i++) {
			map[i] = lines.get(i).toCharArray();
			if(lines.get(i).contains("^")) {
				startX = lines.get(i).indexOf("^");
				startY = i;
			}
		}
		
		System.out.println("Guard Starts at: [" + startX + ", " + startY + "]");
		
		map = patrol(map, startX, startY);
		
		System.out.println(total+1);
		
	}
	
	private char[][] patrol(char[][] map, int x, int y){
		
		while(inBounds(map, x, y)) {
			char dir = map[y][x];
			int lx = x;
			int ly = y;
			map[y][x] = 'X';
			if(dir == '^')
				y--;
			else if(dir == '>')
				x++;
			else if(dir == 'v')
				y++;
			else if(dir == '<')
				x--;
			
			if(inBounds(map, x, y)) {
				if(map[y][x] == '#') {
					x = lx;
					y = ly;
					map[y][x] = turn(dir);
				}
				else
				{
					if(map[y][x] == '.')
						total++;
					map[y][x] = dir;
				}
			}
		}
		
		return map;
	}
	
	private void printMap(char[][] map) {
		
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[i].length; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		System.out.println();
		
	}
	
	private char turn(char dir) {
		if(dir == '^')
			return '>';
		else if (dir == '>')
			return 'v';
		else if (dir == 'v')
			return '<';
		else
			return '^';
	}
	
	private boolean inBounds(char[][] map, int x, int y) {
		return (x >= 0 && x < map[0].length && y >= 0 && y < map.length); 
	}
	
	private void partTwo() {
		
	}
	
}
