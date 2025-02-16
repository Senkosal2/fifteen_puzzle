package group.five;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Solver {
    public static void main(String[] args) {
        int[][] startBoard = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 0, 12},
            {13, 14, 11, 15}
        };

        solvePuzzle(startBoard);
    }

    public static void solvePuzzle(int[][] startBoard) {
        PriorityQueue<int[][]> openSet = new PriorityQueue<>(Comparator.comparingInt(Solver::calculateF));
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
