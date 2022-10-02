import java.util.Scanner;

public class LongestIncreasingSubseqDP {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int inputLength = scanner.nextInt();
        int[] numbers = new int[inputLength];

        // read in sequence of numbers
        for (int i = 0; i < inputLength; i++) {
            numbers[i] = scanner.nextInt();
        }

        int[] OPT = new int[inputLength];
        for (int i = 0; i < inputLength; i++) {
            OPT[i] = 1;
            for (int j = 0; j < i; j++) {
                if(numbers[j] < numbers[i] && OPT[i] <= OPT[j]) {
                    OPT[i] = OPT[j] + 1;
                }
            }
        }

        int result = 0;
        for (int j : OPT) {
            if (j > result) {
                result = j;
            }
        }
        
        System.out.println(result);
    }
}
