import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day7 {

    public Day7(){
        partOne();
        partTwo();
    }

    private void partOne() {

        long total = 0;

        Scanner scan;
        try {
            scan = new Scanner(new File("2024/test.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while(scan.hasNextLine()){
            String line = scan.nextLine();
            String[] split = line.split(": ");
            long result = Long.parseLong(split[0]);
            String[] numsStr = split[1].split(" ");
            long[] nums = new long[numsStr.length];
            for(int s = 0; s < nums.length; s++){
                nums[s] = Long.parseLong(numsStr[s]);
            }

            for(int a = 0; a < nums.length-1; a++){
                for(int m = 0; m < nums.length-1; m++){
                    System.out.println(a + " " + m);
                }
            }
            System.out.println();

        }


    }

    private void partTwo() {

    }

}
