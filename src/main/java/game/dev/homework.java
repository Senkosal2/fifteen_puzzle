package game.dev;

public class homework {
    public static void main(String[] args) {
        int a[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int b[] = {11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        int A[] = new int[a.length + b.length]; // or new int[20]
        int B[] = new int[a.length + b.length]; // or new int[20]

        int index = 0;
        for (int i = 0; i < 10; i++) {
            A[index] = a[i];
            index++;
            A[index] = b[i];
            index++;

            B[i] = a[i];
            B[i+10] = b[i];
        }
        // for (int i = 0; i < A.length; i++) {
        //     int index = i / 2;
        //     if (i % 2 == 0) A[i] = a[index];
        //     else A[i] = b[index];

        //     if (i < 10) B[i] = a[i];
        //     else B[i] = b[i - 10];
        // }

        System.out.print("A: ");
        for (int c : A) System.out.print(c + ", ");
        System.out.println();
        System.out.print("B: ");
        for (int c : B) System.out.print(c + ", ");
    }
}
