package adventofcode.day03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day3 {

	private Scanner scanner;
	private int day = 3;

	public Day3() {

		System.out.println("## Advent of Code 2023 ##\n#### Day " + day + " Solutions ####");
		System.out.println("Part 1: " + partOne());
		System.out.println("Part 2: " + partTwo());
	}

	private String partOne() {

		String solution = "N/A";

		try {
			scanner = new Scanner(new File("src/adventofcode/day" + day + "/input01.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		String prevLine, currLine, nextLine;
		prevLine = currLine = nextLine = "";

		int total = 0;
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();

			nextLine = currLine;
			currLine = prevLine;
			prevLine = line;

			if (currLine != null) {
				for (int i = 0; i < currLine.length(); i++) {
					if (currLine.charAt(i) != '.' && !(currLine.charAt(i) >= '0' && currLine.charAt(i) <= '9')) {
						// Found a symbol, check adjacent positions and diagonals

						// check left
						if (i > 0 && isDigit(currLine, i - 1))
							total += findFullNumber(currLine, i - 1);

						// check right
						if (i < currLine.length() - 1 && isDigit(currLine, i + 1))
							total += findFullNumber(currLine, i + 1);

						// check top, if nothing, check top diagonals
						if (!prevLine.isEmpty() && isDigit(prevLine, i))
							total += findFullNumber(prevLine, i);
						else {
							if (!prevLine.isEmpty() && i > 0 && isDigit(prevLine, i - 1))
								total += findFullNumber(prevLine, i - 1);
							if (!prevLine.isEmpty() && i < prevLine.length() - 1 && isDigit(prevLine, i + 1))
								total += findFullNumber(prevLine, i + 1);
						}

						// check bottom, if nothing, check bottom diagonals
						if (!nextLine.isEmpty() && isDigit(nextLine, i))
							total += findFullNumber(nextLine, i);
						else {
							if (!nextLine.isEmpty() && i > 0 && isDigit(nextLine, i - 1))
								total += findFullNumber(nextLine, i - 1);
							if (!nextLine.isEmpty() && i < nextLine.length() - 1 && isDigit(nextLine, i + 1))
								total += findFullNumber(nextLine, i + 1);
						}
					}
				}
			}

		}

		solution = total + "";
		scanner.close();
		return solution;
	}

	private int findFullNumber(String line, int cursor) {
		String number = "";
		// Move the cursor to the beginning of the number
		while (cursor > 0 && isDigit(line, cursor - 1)) {
			cursor--;
		}

		// read the number
		while (cursor < line.length() && isDigit(line, cursor)) {
			number += line.charAt(cursor);
			cursor++;
		}

		return Integer.parseInt(number);
	}

	private boolean isDigit(String line, int index) {
		return line.charAt(index) >= '0' && line.charAt(index) <= '9';
	}

	private String partTwo() {

		String solution = "N/A";

		try {
			scanner = new Scanner(new File("src/adventofcode/day" + day + "/input01.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		String prevLine, currLine, nextLine;
		prevLine = currLine = nextLine = "";

		int total = 0;
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();

			nextLine = currLine;
			currLine = prevLine;
			prevLine = line;

			if (currLine != null) {
				for (int i = 0; i < currLine.length(); i++) {
					if (currLine.charAt(i) == '*') {
						// Found a possible gear, check adjacent positions and diagonals
						int adjacentNumsFound = 0;
						int ratio = 1;

						// check left
						if (i > 0 && isDigit(currLine, i - 1)) {
							ratio *= findFullNumber(currLine, i - 1);
							adjacentNumsFound++;
						}

						// check right
						if (i < currLine.length() - 1 && isDigit(currLine, i + 1)) {
							ratio *= findFullNumber(currLine, i + 1);
							adjacentNumsFound++;
						}

						// check top, if nothing, check top diagonals
						if (!prevLine.isEmpty() && isDigit(prevLine, i)) {
							ratio *= findFullNumber(prevLine, i);
							adjacentNumsFound++;
						} else {
							if (!prevLine.isEmpty() && i > 0 && isDigit(prevLine, i - 1)) {
								ratio *= findFullNumber(prevLine, i - 1);
								adjacentNumsFound++;
							}
							if (!prevLine.isEmpty() && i < prevLine.length() - 1 && isDigit(prevLine, i + 1)) {
								ratio *= findFullNumber(prevLine, i + 1);
								adjacentNumsFound++;
							}
						}

						// check bottom, if nothing, check bottom diagonals
						if (!nextLine.isEmpty() && isDigit(nextLine, i)) {
							ratio *= findFullNumber(nextLine, i);
							adjacentNumsFound++;
						} else {
							if (!nextLine.isEmpty() && i > 0 && isDigit(nextLine, i - 1)) {
								ratio *= findFullNumber(nextLine, i - 1);
								adjacentNumsFound++;
							}
							if (!nextLine.isEmpty() && i < nextLine.length() - 1 && isDigit(nextLine, i + 1)) {
								ratio *= findFullNumber(nextLine, i + 1);
								adjacentNumsFound++;
							}
						}

						if (adjacentNumsFound > 1) {
							total += ratio;
						}
					}
				}
			}
		}

		solution = total + "";
		scanner.close();
		return solution;
	}
}
