import java.util.Random;
import java.util.Scanner;
import java.time.LocalTime;
class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        boolean canMoveDown = false, 
        canMoveUp = false, 
        canMoveLeft = false, 
        canMoveRight = false;
        int x = 0, y = 0;
        boolean isFound = false;
        boolean isWin = false;
        int count = 0;
        int moves = 0;
        int seconds = 0;
        LocalTime start = LocalTime.now();
        LocalTime end = LocalTime.now();

        // int num[][] = {
        //     {1, 2, 3, 4},
        //     {5, 6, 7, 8},
        //     {9, 10, 11, 12},
        //     {13, 14, 15, 0}
        // };
        int[][] num = Randomize();
        
        while (true) { 
            // clear console
            Clear();
            count = 0;

            // display total moves
            System.out.println("Moves: " + moves);

            // display the box
            for (int[] row : num) {
                for (int col : row) {
                    System.out.print((col == 0 ? "" : col) + "\t");
                }
                System.out.println();
            }
            
            // win condition
            for (int i = 0; i < num.length; i++) {
                for (int j = 0; j < num[i].length; j++) {
                    count++;
                    int current = num[i][j];
                    if (current - count == 0) {
                        isWin = true;
                    } else {
                        isWin = count == 16;
                        break;
                    }
                }
                if (!isWin) break;
            }
            if (isWin) {
                end = LocalTime.now();
                int startSecond = (start.getHour() * 3600) + (start.getMinute() * 60) + start.getSecond();
                int endSecond = (end.getHour() * 3600) + (end.getMinute() * 60) + end.getSecond();
                System.out.println("Total Time: " + (endSecond - startSecond) + "s");
                System.out.println("You win!!!");
                break;
            }
            
            // get number
            System.out.print("Number to move: ");
            int selected_number = input.nextInt();
    
            // extract position[y][x] of the selected number to move
            for (int row = 0; row < num.length; row++) {
                y = row;
                for (int col = 0; col < num[row].length; col++) {
                    x = col;
                    if (num[row][col] == selected_number) isFound = true;
                    if (isFound) break;
                }
                if (isFound) break;
            }

            // check for possible movement
            canMoveLeft = x - 1 >= 0 ? num[y][x-1] == 0 : false;
            canMoveRight = x + 1 < num[x].length ? num[y][x+1] == 0 : false;
            canMoveDown = y + 1 < num.length ? num[y+1][x] == 0 : false;
            canMoveUp = y - 1 >= 0 ? num[y-1][x] == 0 : false;

            // move right
            if (canMoveRight) {
                // swap value at index
                int temp = num[y][x+1];
                num[y][x+1] = num[y][x];
                num[y][x] = temp;
            }
            // move left
            if (canMoveLeft) {
                // swap value at index
                int temp = num[y][x-1];
                num[y][x-1] = num[y][x];
                num[y][x] = temp;
            }
            // move up
            if (canMoveUp) {
                // swap value at index
                int temp = num[y-1][x];
                num[y-1][x] = num[y][x];
                num[y][x] = temp;
            }
            // move down
            if (canMoveDown) {
                // swap value at index
                int temp = num[y+1][x];
                num[y+1][x] = num[y][x];
                num[y][x] = temp;
            }
            isFound = false;
            moves++;
        }
    }
    
    static int[][] Randomize() {
        int[] array = new int[16];
        int[][] numbers = new int[4][4];
        int count = 0;
        
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }

        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers[i].length; j++) {
                numbers[i][j] = array[count];
                count++;
            }
        }
        
        return numbers;
    }
    static void Clear() {
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }
    static void SolvePuzzle() {
        
    }
}
