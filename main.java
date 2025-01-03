import java.util.Random;
class Main {
    public static void main(String[] args) {
        int[] array = new int[16];
        
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

        for (int num : array) {
            System.out.print(num + " ");
        }
    }
}
