package game.dev

public class Main {
    public static void main(String[] args) {
        int m[][] = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12}
        };

        for (int i = 0; i < m[0].length; i++) {
            for (int j = 0; j < m.length; j++) {
                System.out.println(m[j][i]);
            }
        }
    }
}