package game.dev;

import java.util.Scanner;

public class fifteen_puzzle {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // char movement[] = {'a', 'w', 's', 'd'};
        boolean canMoveDown = false, 
        canMoveUp = false, 
        canMoveLeft = false, 
        canMoveRight = false;
        int x = 0, y = 0;
        boolean isFound = false;
        boolean isWin = false;
        int count = 0;

        
        int num[][] = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 0, 15}
        };
        
        while (true) { 
            // clear console
            Clear();
            count = 0;

            // display the box
            for (int[] row : num) {
                for (int col : row) {
                    // System.out.println("#####");
                    System.out.print((col == 0 ? "" : col) + "\t");
                }
                System.out.println();
            }
            for (int[] row : num) {
                for (int col : row) {
                    count++;
                    if (count - col == 0) {
                        isWin = true;
                    } else {
                        isWin = count == 16;
                        break;
                    }
                }
            }
            if (isWin) {
                System.out.println("You win!!!");
                break;
            }
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

            // System.out.println("x: " + x + "\ny: " + y);


            // check for possible movement
            canMoveLeft = x - 1 >= 0 ? num[y][x-1] == 0 : false;
            canMoveRight = x + 1 < num[x].length ? num[y][x+1] == 0 : false;
            canMoveDown = y + 1 < num.length ? num[y+1][x] == 0 : false;
            canMoveUp = y - 1 >= 0 ? num[y-1][x] == 0 : false;

            // display possible movement
            // if (canMoveUp) System.out.println("W: Move Up");
            // if (canMoveDown) System.out.println("S: Move Down");
            // if (canMoveLeft) System.out.println("A: Move Left");
            // if (canMoveRight) System.out.println("D: Move Right");
            
            // System.out.print("Movement: ");
            // char selected_movement = input.next().charAt(0);

            // move right
            // if (selected_movement == 'd' && canMoveRight) {
            if (canMoveRight) {
                // swap value at index
                int temp = num[y][x+1];
                num[y][x+1] = num[y][x];
                num[y][x] = temp;
            }
            // move left
            // if (selected_movement == 'a' && canMoveLeft) {
            if (canMoveLeft) {
                // swap value at index
                int temp = num[y][x-1];
                num[y][x-1] = num[y][x];
                num[y][x] = temp;
            }
            // move up
            // if (selected_movement == 'w' && canMoveUp) {
            if (canMoveUp) {
                // swap value at index
                int temp = num[y-1][x];
                num[y-1][x] = num[y][x];
                num[y][x] = temp;
            }
            // move down
            // if (selected_movement == 's' && canMoveDown) {
            if (canMoveDown) {
                // swap value at index
                int temp = num[y+1][x];
                num[y+1][x] = num[y][x];
                num[y][x] = temp;
            }
            isFound = false;
        }

        // System.out.println("Up: " + canMoveUp);
        // System.out.println("Down: " + canMoveDown);
        // System.out.println("Right: " + canMoveRight);
        // System.out.println("Left: " + canMoveLeft);

        // for (int[] row : num) {
        //     for (int col : row) {
        //         System.out.print(col + "\t");
        //     }
        //     System.out.println();
        // }
    }

    static void Clear() {
        System.out.println("\033[H\033[2J");
        System.out.flush();
        // System.out.println("Cleared!");
    }
}
