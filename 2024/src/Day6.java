
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day6 {

    int total = 0;
    int loopTotal = 0;
    char[][] originalMap;
    int startX = 0;
    int startY = 0;
    long loopStartTime = System.currentTimeMillis();

    public Day6() {
        
        //test(Arrays.stream(test).map(char[]::clone).toArray(char[][]::new));
        readMap("2024/day6_1.txt");
        //partOne();
        partTwo();
    }
    
    private record PosAndDir(int x, int y, char dir) {};

    private void readMap(String fileString){
        Scanner scan = null;
        ArrayList<String> lines = new ArrayList<>();

        try {
            scan = new Scanner(new File(fileString));
            while (scan.hasNextLine()) {
                lines.add(scan.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        originalMap = new char[lines.size()][lines.get(0).length()];
        for (int i = 0; i < lines.size(); i++) {
            originalMap[i] = lines.get(i).toCharArray();
            if (lines.get(i).contains("^")) {
                this.startX = lines.get(i).indexOf("^");
                this.startY = i;
            }
        }
    }
    
    private void partOne() {
        char[][] patrolMap = Arrays.stream(originalMap).map(char[]::clone).toArray(char[][]::new);
        patrolMap = patrol(patrolMap, startX, startY);
        System.out.println(total + 1);
    }

    private char[][] patrol(char[][] map, int x, int y) {
        
        int loopCount = 0;
        int steps = 0;
        while (inBounds(map, x, y)) {
            steps++;
            if(steps > 100000)
                return null;
                //printMap(map);
            char dir = map[y][x];
            int lx = x;
            int ly = y;
            map[y][x] = 'X';
            if (dir == '^') {
                y--;
            } else if (dir == '>') {
                x++;
            } else if (dir == 'v') {
                y++;
            } else if (dir == '<') {
                x--;
            }

            if (inBounds(map, x, y)) {
                if (map[y][x] == '#' || map[y][x] == 'O') {
                    
                    if(map[y][x] == 'O'){
                        loopCount++;
                        if(loopCount > 1){
                            //System.out.println(loopTotal);
                            //return null;
                        }
                    }
                    
                    x = lx;
                    y = ly;
                    map[y][x] = turn(dir);
                } else {
                    if (map[y][x] == '.') {
                        total++;
                    }
                    map[y][x] = dir;
                }
            }
        }

        return map;
    }
    
    private char[][] patrolLoop(char[][] map, int x, int y) {

        while (inBounds(map, x, y)) {
            char dir = map[y][x];
            int lx = x;
            int ly = y;
            map[y][x] = '.';
            if (dir == '^') {
                y--;
            } else if (dir == '>') {
                x++;
            } else if (dir == 'v') {
                y++;
            } else if (dir == '<') {
                x--;
            }else{
                System.out.println("uh oh");
            }

            if (inBounds(map, x, y)) {
                if (map[y][x] == '#') {
                    x = lx;
                    y = ly;
                    map[y][x] = turn(dir);
                } else {
                    checkLoop(map, x, y);
                    map[y][x] = dir;
                    //map[ly][lx] = dir;
                    
                    //map[ly][lx] = '.';
                    
                    
                }
            }
        }

        return map;
    }
    
    private boolean checkLoop(char[][] map, int x, int y){
        if(x == startX && y == startY)
            return false;
        char[][] copy = Arrays.stream(map).map(char[]::clone).toArray(char[][]::new);
        copy[y][x] = 'O';
        copy[startY][startX] = '^';
        if(patrol(copy, startX, startY) == null){
            loopTotal++;
            return true;
        }
        return false;
    }

    private void printMap(char[][] map) {

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
        System.out.println();

    }

    private char turn(char dir) {
        if (dir == '^') {
            return '>';
        } else if (dir == '>') {
            return 'v';
        } else if (dir == 'v') {
            return '<';
        } else {
            return '^';
        }
    }

    private boolean inBounds(char[][] map, int x, int y) {
        return (x >= 0 && x < map[0].length && y >= 0 && y < map.length);
    }

    private void partTwo() {
        char[][] patrolMap = Arrays.stream(originalMap).map(char[]::clone).toArray(char[][]::new);
        patrolMap = patrolLoop(patrolMap, startX, startY);
        System.out.println(loopTotal);
        
        //loop through the map, at every location that has an X, try placing a wall, then run the patrol and check for a loop.
//        for(int i = 0; i < map.length; i++){
//            for(int j = 0; j < map[i].length; j++){
//                
//                if(map[i][j] == 'X')
//                
//            }
//        }
        
    }

}
