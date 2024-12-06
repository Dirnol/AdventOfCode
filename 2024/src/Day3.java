import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.PatternSyntaxException;

public class Day3 {

	public Day3() {
		partOne();
		partTwo();
	}
	
	private void partOne() {
		
		Scanner scan = null;
		
		int total = 0;
		
		
		try {
			scan = new Scanner(new File("day3_1.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(scan.hasNextLine()) {
			String line = scan.nextLine();
			
			String[] candidates = line.split("mul\\(");
			
			for(String s : candidates) {
				int num1 = -1;
				int num2 = -1;
				//System.out.println(s);
				String[] nums = s.split(",");
				//if(nums.length > 1)
					//System.out.println(nums[1] + " " + nums[1].contains(")"));
				if(nums.length > 1 && nums[1].contains(")")) {
					//System.out.println(nums[0]);
					//System.out.println(nums[1]);
					try {
						num1 = Integer.parseInt(nums[0]);
					} catch (NumberFormatException e) {
						//System.out.println(nums[0] + " NaN");
					}
					String[] nums2 = null;
					try {
						nums2 = nums[1].split("\\)");
					}catch(PatternSyntaxException e) {
						//System.out.println(nums[1] + " Invalid Syntax");
					}
					if(nums2 != null && nums2.length >= 1) {
						try {
							num2 = Integer.parseInt(nums2[0]);
						}catch(NumberFormatException e) {
							//System.out.println(nums2[0] + " NaN");
						}
					}
					if(num2 >= 0 && num1 >= 0) {
						total += num1 * num2;
					}else {
						
					}
				}
			}
			
		}
		
		System.out.println(total);
		
	}
	
	private void partTwo() {
		
		Scanner scan = null;
		
		int total = 0;
		
		
		try {
			scan = new Scanner(new File("day3_1.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean startEnable = true;
		while(scan.hasNextLine()) {
			String line = scan.nextLine();
			
			String[] dos = line.split("do\\(\\)");
			for(int i = 0; i < dos.length; i++) {
				
				String d = dos[i];
				
				String check = "";
				
				if(i == dos.length-1) {
					if(d.contains("don't()"))
						startEnable = false;
					else
						startEnable = true;
				}
				
				if(d.contains("don't()")){
					String[] donts = d.split("don't\\(\\)");
					if(i == 0 && !startEnable) {}
					else
						check = donts[0];
				}else {
					
					if(i == 0 && !startEnable) {}
					else
						check = d;
				}
				System.out.println(check);
				String[] candidates = check.split("mul\\(");
				
				for(String s : candidates) {
					int num1 = -1;
					int num2 = -1;
					//System.out.println(s);
					String[] nums = s.split(",");
					//if(nums.length > 1)
						//System.out.println(nums[1] + " " + nums[1].contains(")"));
					if(nums.length > 1 && nums[1].contains(")")) {
						//System.out.println(nums[0]);
						//System.out.println(nums[1]);
						try {
							num1 = Integer.parseInt(nums[0]);
						} catch (NumberFormatException e) {
							//System.out.println(nums[0] + " NaN");
						}
						String[] nums2 = null;
						try {
							nums2 = nums[1].split("\\)");
						}catch(PatternSyntaxException e) {
							//System.out.println(nums[1] + " Invalid Syntax");
						}
						if(nums2 != null && nums2.length >= 1) {
							try {
								num2 = Integer.parseInt(nums2[0]);
							}catch(NumberFormatException e) {
								//System.out.println(nums2[0] + " NaN");
							}
						}
						if(num2 >= 0 && num1 >= 0) {
							total += num1 * num2;
						}
					}
				}
				
			}
			
		}
		
		System.out.println(total);
	}
	
}
