package game.dev;

import java.util.HashSet;
import java.util.Set;

public class array {
    public static void main(String[] args) {
        int numbers[][] = new int[4][4];
        boolean isDuplicate = false, isDone = false, isFirst = true;
        int random_number = 0;
        int count = 0;
        Set<Integer> numbers1 = new HashSet<>();
        
        while (numbers1.size() <= 16) {
            // isDuplicate = false;
            random_number = (int)(Math.random() * 15);
            // System.out.println(random_number);
            // for (int i = 0; i < numbers.length; i++) {
            //     for (int j = 0; j < numbers[i].length; j++) {
            //         if (numbers[i][j] == random_number) {
            //             isDuplicate = true;
            //             break;
            //         } else {
            //             numbers[i][j] = random_number;
            //         }
            //     }
            //     if (isDuplicate) break;
            //     // isDone = true;
            // }
            // isDone = !isFirst && !isDuplicate;
            // isFirst = false;
            numbers1.add(random_number);
        }
        // for (int[] number : numbers) {
        //     for (int n : number) {
        //         System.out.print(n + "\t");
        //     }
        //     System.out.println();
        // }
        for (int num : numbers1) {
            System.out.println(num);
        }
    }

}
