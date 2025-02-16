package group.five;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        boolean canMoveDown = false, canMoveUp = false, canMoveLeft = false, canMoveRight = false;
        int x = 0, y = 0;
        boolean isFound = false;
        boolean isWin = false;
        int count = 0;
        int moves = 0;
        int seconds = 0;
        int selected_number = 0;
        LocalTime start = LocalTime.now();
        LocalTime end = LocalTime.now();
        byte option = 0, mode = 0;

        int[][] puzzle = new int[][]{
            {0, 0 ,0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        };
        while (true) { 
            // clear console
            Clear();
            count = 0;

            boolean isValid = true;
            System.out.println("=========================");
            System.out.println("=         MODE          =");
            System.out.println("= 1. Solve by you.      =");
            System.out.println("= 2. Solve by computer. =");
            System.out.println("=========================");
            do {
                System.out.print("Please choose mode: ");
                try {
                    mode = input.nextByte();

                    if (mode != 1 && mode != 2) {
                        System.out.println("Invalid Mode! Please choose again!");
                        isValid = false;
                    } else isValid = true;
                } catch (InputMismatchException misMatchError) {
                    System.out.println("Invalid Mode! Please choose again!");
                    input.nextLine();
                }
            } while (!isValid);
            
            Clear();

            System.out.println("===============================");
            System.out.println("=           OPTION            =");
            System.out.println("= 1. Create the puzzle (you). =");
            System.out.println("= 2. Generate the puzzle.     =");
            System.out.println("===============================");
            System.out.print("Please select the option: ");
            do {
                System.out.print("Please choose mode: ");
                try {
                    option = input.nextByte();

                    if (option != 1 && option != 2) {
                        System.out.println("Invalid Option! Please choose again!");
                        isValid = false;
                    } else isValid = true;
                } catch (InputMismatchException misMatchError) {
                    System.out.println("Invalid Option! Please choose again!");
                    input.nextLine();
                }
            } while (!isValid);

            if (option == 1 && mode == 1) {
                DisplayPuzzle(puzzle);
                System.out.println("Please number to create the puzzle: ");
                System.out.println("");
            }

            if (canMoveDown || canMoveUp || canMoveRight || canMoveLeft) {
                moves++;
            } else {
                System.out.println("Cannot move this number: ");
                System.out.println("Please try again!");
            }

            // display total moves
            DisplayPuzzle(puzzle, moves);
            
            // win condition
            isWin = isGoal(puzzle);

            if (isWin) {
                end = LocalTime.now(); 
                int startSecond = (start.getHour() * 3600) + (start.getMinute() * 60) + start.getSecond();
                int endSecond = (end.getHour() * 3600) + (end.getMinute() * 60) + end.getSecond();
                System.out.println("Total Time: " + (endSecond - startSecond) + "s");
                System.out.println("You win!!!");
                break;
            }
            
            // get puzzleber
            boolean isValidNumber = true;

            do {
                isValidNumber = true;
                try {
                    System.out.print("Number to move: ");
                    selected_number = input.nextInt();

                    if (selected_number < 1 || selected_number > 15) {
                        System.out.println("Please enter number between 1 to 15 only!!!");
                        isValidNumber = false;
                    }
                } catch (InputMismatchException misMatchError) {
                    System.out.println("Invalid input! Please enter number between 1 to 15 only!!!");
                    isValidNumber = false;
                    input.nextLine();
                }
            } while (!isValidNumber);
                        
            // extract position[y][x] of the selected puzzleber to move
            for (int row = 0; row < puzzle.length; row++) {
                y = row;
                for (int col = 0; col < puzzle[row].length; col++) {
                    x = col;
                    if (puzzle[row][col] == selected_number) isFound = true;
                    if (isFound) break;
                }
                if (isFound) break;
            }

            // check for possible movement
            canMoveLeft = x - 1 >= 0 ? puzzle[y][x-1] == 0 : false;
            canMoveRight = x + 1 < puzzle[x].length ? puzzle[y][x+1] == 0 : false;
            canMoveDown = y + 1 < puzzle.length ? puzzle[y+1][x] == 0 : false;
            canMoveUp = y - 1 >= 0 ? puzzle[y-1][x] == 0 : false;

            // move right
            if (canMoveRight) {
                // swap value at index
                int temp = puzzle[y][x+1];
                puzzle[y][x+1] = puzzle[y][x];
                puzzle[y][x] = temp;
            }
            // move left
            if (canMoveLeft) {
                // swap value at index
                int temp = puzzle[y][x-1];
                puzzle[y][x-1] = puzzle[y][x];
                puzzle[y][x] = temp;
            }
            // move up
            if (canMoveUp) {
                // swap value at index
                int temp = puzzle[y-1][x];
                puzzle[y-1][x] = puzzle[y][x];
                puzzle[y][x] = temp;
            }
            // move down
            if (canMoveDown) {
                // swap value at index
                int temp = puzzle[y+1][x];
                puzzle[y+1][x] = puzzle[y][x];
                puzzle[y][x] = temp;
            }
            isFound = false;
            
        }
    }

    // random the puzzle by computer
    static int[][] Randomize() {
        int[] array = new int[16];
        int[][] puzzlebers = new int[4][4];
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
        
        for (int i = 0; i < puzzlebers.length; i++) {
            for (int j = 0; j < puzzlebers[i].length; j++) {
                puzzlebers[i][j] = array[count];
                count++;
            }
        }
        
        return puzzlebers;
    }

    // clear the display
    static void Clear() {
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }

    static void DisplayPuzzle(int[][] puzzle, int moves) {
        System.out.println("Moves: " + moves);

        for (int i = 0; i < puzzle.length; i++) {
            System.out.println("+----+----+----+----+");
            for (int j = 0; j < puzzle[i].length; j++) {
                System.out.print("| ");
                if (puzzle[i][j] == 0) System.out.print("   ");
                else if (puzzle[i][j] < 10) System.out.print("0" + puzzle[i][j] + " ");
                else System.out.print(puzzle[i][j] + " ");
                if (j == 3) System.out.print("|");
            }
            System.out.println(i == 3 ? "\n+----+----+----+----+" : "");
        }
    }

    static void DisplayPuzzle(int[][] puzzle) {
        for (int i = 0; i < puzzle.length; i++) {
            System.out.println("+----+----+----+----+");
            for (int j = 0; j < puzzle[i].length; j++) {
                System.out.print("| ");
                if (puzzle[i][j] == 0) System.out.print("   ");
                else if (puzzle[i][j] < 10) System.out.print("0" + puzzle[i][j] + " ");
                else System.out.print(puzzle[i][j] + " ");
                if (j == 3) System.out.print("|");
            }
            System.out.println(i == 3 ? "\n+----+----+----+----+" : "");
        }
    }

    public static void solvePuzzle(int[][] startBoard) {
        PriorityQueue<int[][]> openSet = new PriorityQueue<>(Comparator.comparingInt(Main::calculateF));
        Map<String, Integer> gScores = new HashMap<>();
        Map<String, int[][]> parentMap = new HashMap<>();

        openSet.add(startBoard);
        gScores.put(boardToString(startBoard), 0);

        while (!openSet.isEmpty()) {
            int[][] current = openSet.poll();

            if (isGoal(current)) {
                printSolutionPath(current, parentMap);
                return;
            }

            for (int[][] neighbor : getNeighbors(current)) {
                String neighborKey = boardToString(neighbor);
                int tentativeG = gScores.get(boardToString(current)) + 1;

                if (!gScores.containsKey(neighborKey) || tentativeG < gScores.get(neighborKey)) {
                    gScores.put(neighborKey, tentativeG);
                    parentMap.put(neighborKey, current);
                    openSet.add(neighbor);
                }
            }
        }
        System.out.println("No solution found.");
    }

    private static List<int[][]> getNeighbors(int[][] board) {
        List<int[][]> neighbors = new ArrayList<>();
        int[] zeroPos = locateZero(board);
        int row = zeroPos[0], col = zeroPos[1];
        int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // up, down, left, right

        for (int[] move : moves) {
            int newRow = row + move[0];
            int newCol = col + move[1];

            if (newRow >= 0 && newRow < 4 && newCol >= 0 && newCol < 4) {
                int[][] newBoard = copyBoard(board);
                newBoard[row][col] = newBoard[newRow][newCol];
                newBoard[newRow][newCol] = 0;
                neighbors.add(newBoard);
            }
        }
        return neighbors;
    }

    private static boolean isGoal(int[][] board) {
        int[][] goal = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
        };
        return Arrays.deepEquals(board, goal);
    }

    private static int calculateF(int[][] board) {
        return calculateHeuristic(board) + calculateG(board);
    }

    private static int calculateG(int[][] board) {
        return 0; // Not tracking g-cost explicitly
    }

    private static int calculateHeuristic(int[][] board) {
        int h = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int value = board[i][j];
                if (value != 0) {
                    int goalRow = (value - 1) / 4;
                    int goalCol = (value - 1) % 4;
                    h += Math.abs(i - goalRow) + Math.abs(j - goalCol);
                }
            }
        }
        return h;
    }

    private static int[] locateZero(int[][] board) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == 0) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    private static int[][] copyBoard(int[][] board) {
        int[][] copy = new int[4][4];
        for (int i = 0; i < 4; i++)
            copy[i] = board[i].clone();
        return copy;
    }

    private static String boardToString(int[][] board) {
        StringBuilder sb = new StringBuilder();
        for (int[] row : board) {
            for (int num : row) {
                sb.append(num).append(",");
            }
        }
        return sb.toString();
    }

    private static void printSolutionPath(int[][] state, Map<String, int[][]> parentMap) {
        List<int[][]> path = new ArrayList<>();
        while (state != null) {
            path.add(state);
            state = parentMap.get(boardToString(state));
        }
        Collections.reverse(path);
        for (int[][] step : path) {
            printBoard(step);
        }
        System.out.println("Solved in " + (path.size() - 1) + " moves!");
    }

    private static void printBoard(int[][] board) {
        for (int[] row : board) {
            for (int num : row) {
                System.out.print((num == 0 ? "   " : (num < 10 ? "0" + num : num)) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}