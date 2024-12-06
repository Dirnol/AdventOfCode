import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day4 {

	public Day4() {
		partOne();
		partTwo();
	}
	
	private void partOne() {
		
		int total = 0;
		
		char[][] matrix = readMatrix("day4_1.txt");
		//printMatrix(matrix);
		
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[i].length; j++) {
				
				if(matrix[i][j] == 'X') {
					ArrayList<Point> Ms = checkNeighbors(matrix, new Point(i, j), 'M');
					for(Point m : Ms) {
						int xDir = m.x - i;
						int yDir = m.y - j;
						
						int ax = m.x + xDir;
						int ay = m.y + yDir;
						int sx = ax + xDir;
						int sy = ay + yDir;
						
						
						
						if(inBounds(ax, ay, matrix) && matrix[ax][ay] == 'A'
								&& inBounds(sx, sy, matrix) && matrix[sx][sy] == 'S') {
							total++;
							//System.out.printf("X:[%d,%d]:%c M:[%d,%d]:%c A:[%d,%d]:%c S:[%d,%d]:%c\n", i, j, matrix[i][j], 
								//m.x, m.y, matrix[m.x][m.y], ax, ay, matrix[ax][ay], sx, sy, matrix[sx][sy]);
						}
						
					}
				}
				
			}
		}
		System.out.println(total);
		
	}
	
	private void partTwo() {
		
		int total = 0;
		
		char[][] matrix = readMatrix("day4_1.txt");
		
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[i].length; j++) {
				
				if(matrix[i][j] == 'A') {
					if(inBounds(i-1, j-1, matrix) && inBounds(i+1, j+1, matrix)) {
						
						boolean rightDiagonal = false;
						boolean leftDiagonal = false;
						
						if((matrix[i-1][j-1] == 'M' && matrix[i+1][j+1] == 'S') ||
								matrix[i-1][j-1] == 'S' && matrix[i+1][j+1] == 'M')
							rightDiagonal = true;
						
						if((matrix[i+1][j-1] == 'M' && matrix[i-1][j+1] == 'S') ||
								matrix[i+1][j-1] == 'S' && matrix[i-1][j+1] == 'M')
							leftDiagonal = true;
						
						if(leftDiagonal && rightDiagonal)
							total++;
						
					}
				}
				
			}
		}
		
		System.out.println(total);
		
	}
	
	private char[][] readMatrix(String fileName){
		char[][] matrix;
		ArrayList<String> lines = new ArrayList<>();
		
		Scanner scan = null;
		
		try {
			scan = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		while(scan.hasNextLine()) {
			String line = scan.nextLine();
			lines.add(line);
		}
		
		matrix = new char[lines.get(0).length()][lines.size()];
		
		for(int i = 0; i < lines.size(); i++) {
			matrix[i] = lines.get(i).toCharArray();
		}
		
		return matrix;
	}
	
	private void printMatrix(char[][] matrix) {
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j]);
			}
			System.out.println();
		}
	}
	
	private ArrayList<Point> checkNeighbors(char[][] matrix, Point point, char letter) {
		
		ArrayList<Point> points = new ArrayList<>();
		
		for(int x = point.x-1; x < point.x+2; x++) {
			for(int y = point.y-1; y < point.y+2; y++) {
				if(inBounds(x, y, matrix)) {
					if(matrix[x][y] == letter) {
						points.add(new Point(x, y));
					}
				}
			}
		}		
		
		return points;
		
	}
	
	private boolean inBounds(int x, int y, char[][] matrix) {
		if(x < 0 || x >= matrix[0].length || y < 0 || y >= matrix.length)
			return false;
		return true;
	}
	
	private class Point{
		
		public int x, y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
	}
	
}
