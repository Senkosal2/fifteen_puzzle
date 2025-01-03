package game.dev;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        // 1
        // double amount = d00;
        // int year = 2;
        // double interest = 8;

        //2
        // int startDay = 7;
        // int dayInMonth = 31;
        // int dayCount = 1;
        // System.out.println("Mo\tTu\tWe\tTh\tFr\tSa\tSu");

        // for (int i = 1; i < startDay; i++) {
        //     System.out.print("\t");
        //     dayCount++;
        // }
        // for (int day = 1; day <= dayInMonth; day++) {
        //     System.out.print(day + "\t");
        //     if (dayCount == 7) {
        //         dayCount = 0;
        //         System.out.println();
        //     }
        //     dayCount++;
        // }
        

        //3
        long delay = 200;
        int operator = 0;
        boolean isExit = false;
        Scanner input = new Scanner(System.in);

        while (!isExit) {
            System.out.println("Choose operators");
            Thread.sleep(delay);
            System.out.println("========================");
            Thread.sleep(delay);
            System.out.println("1. Press 1 to ADD");
            Thread.sleep(delay);
            System.out.println("2. Press 2 to SUBTRACT");
            Thread.sleep(delay);
            System.out.println("3. Press 3 to MULTIPLY");
            Thread.sleep(delay);
            System.out.println("4. Press 4 to DIVISION");
            Thread.sleep(delay);
            System.out.println("5. Press 5 to MODULUS");
            Thread.sleep(delay);
            System.out.println("6. Press 6 to EXIT");
            Thread.sleep(delay);
            int opt = input.nextInt();
            isExit = opt == 6;
            if (opt < 1 || opt > 6) {
                System.out.println("Error operation: please try again!");
            } else {
                System.out.print("Input value of number 1: ");
                String num1 = input.next();
                System.out.print("Input value of number 2: ");
                String num2 = input.next();

                boolean isDecimal = num1.contains(".") || num2.contains(".");
                if (isDecimal) {
                    // Double num1 = num1.to
                    System.out.println("Sum = ");
                }
            }
        }
        
    }
}