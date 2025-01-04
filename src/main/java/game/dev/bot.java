class Main {
    public static void main (String[] args) throws Exception {
        int[][] puzzle = { // [y][x]
            {0, 4, 12, 3},
            {11, 5, 8, 2},
            {14, 6, 7, 15},
            {10, 9, 13, 1}
        };
        
        boolean isLeft = false, isRight = false, isUp = false, isDown = false;
        boolean isFinished = false;
        
        while (!isFinished) {
            int[] blank_position = find_position(puzzle, 0); // 0: y, 1: x
            System.out.println("y: " + blank_position[0] + " x: " + blank_position[1]);
            
            int[] select_position = find_position(puzzle, 1);
            System.out.println("y: " + select_position[0] + " x: " + select_position[1]);
            
            int[] target_position = {0, 0};
            
            // determine which direction to move to moving position
            isUp = target_position[0] > select_position[0];
            isDown = target_position[0] < select_position[0];
            isLeft = target_position[1] > select_position[1];
            isRight = target_position[1] < select_position[1];
            
            int[] move_position = find_move_position(new boolean[] {
                isLeft, isRight, isDown, isUp
            }, select_position);
            // if (isLeft) move_position = new int[]{ select_position[0], select_position[1] - 1};
            // else if (isRight) move_position = new int[]{ select_position[0], select_position[1] + 1};
            // else if (isDown) move_position = new int[]{ select_position[0] + 1, select_position[1]};
            // else if (isUp) move_position = new int[]{ select_position[0] - 1, select_position[1]};
            System.out.println("y: " + move_position[0] + " x: " + move_position[1]);
            
            // move to correct y position
            while (move_position[0] != blank_position[0]) {
                if (isDown) { // down
                    int temp = puzzle[blank_position[0] + 1][blank_position[1]];
                    puzzle[blank_position[0] + 1][blank_position[1]] = puzzle[blank_position[0]][blank_position[1]];
                    puzzle[blank_position[0]][blank_position[1]] = temp;
                } else if (isUp) { // up
                    int temp = puzzle[blank_position[0] - 1][blank_position[1]];
                    puzzle[blank_position[0] - 1][blank_position[1]] = puzzle[blank_position[0]][blank_position[1]];
                    puzzle[blank_position[0]][blank_position[1]] = temp;
                }
                
                blank_position = find_position(puzzle, 0);
                display_puzzle(puzzle);
            }
            // move to correct x position
            while (move_position[1] != blank_position[1]) {
                if (isRight) { // right
                    int temp = puzzle[blank_position[0]][blank_position[1] + 1];
                    puzzle[blank_position[0]][blank_position[1] + 1] = puzzle[blank_position[0]][blank_position[1]];
                    puzzle[blank_position[0]][blank_position[1]] = temp;
                } else if (isLeft) { // left
                    int temp = puzzle[blank_position[0]][blank_position[1] - 1];
                    puzzle[blank_position[0]][blank_position[1] - 1] = puzzle[blank_position[0]][blank_position[1]];
                    puzzle[blank_position[0]][blank_position[1]] = temp;
                }
                
                blank_position = find_position(puzzle, 0);
                display_puzzle(puzzle);
            }
            move_current_position(puzzle, blank_position, select_position);
            display_puzzle(puzzle);
            isFinished = select_position[0] == 0 && select_position[1] == 0;
        }
    }
    
    static int[] find_position(int[][] puzzle, int position) {
        for (int y = 0; y < puzzle.length; y++) {
            for (int x = 0; x < puzzle[y].length; x++) {
                if (puzzle[y][x] == position) {
                    return new int[]{y, x};
                }
            }
        }
        
        throw new IllegalArgumentException("No blank position!!!");
    }
    static void display_puzzle(int[][] puzzle) throws Exception {
        System.out.println("\033[H\033[2J");
        System.out.flush();
        for (int[] y : puzzle) {
            for (int x : y) {
                System.out.print(x + "\t");
            }
            System.out.println();
        }
        Thread.sleep(500);
    }
    static void move_blank(int[][] puzzle) {
        
    }
    static void move_current_position(int[][] puzzle, int[] target_position, int[] current_position) {
        int temp = puzzle[current_position[0]][current_position[1]];
        puzzle[current_position[0]][current_position[1]] = puzzle[target_position[0]][target_position[1]];
        puzzle[target_position[0]][target_position[1]] = temp;
    }
    static int[] find_move_position(boolean[] direction, int[] current_position) {
        int[] move_position = {0, 0};
        if (direction[0]) move_position = new int[]{ current_position[0], current_position[1] - 1};
        else if (direction[1]) move_position = new int[]{ current_position[0], current_position[1] + 1};
        else if (direction[2]) move_position = new int[]{ current_position[0] + 1, current_position[1]};
        else if (direction[3]) move_position = new int[]{ current_position[0] - 1, current_position[1]};
        return move_position;
    }
}
