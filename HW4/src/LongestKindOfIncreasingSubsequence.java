import java.util.Scanner;

public class LongestKindOfIncreasingSubsequence {

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
            for (int j = 1; j < i; j++) {
                int previousAvg = (numbers[j - 1] + numbers[j])/2;
                if(previousAvg < numbers[i] && OPT[i] <= OPT[j]) {
                    if(OPT[j] == 1) {
                        OPT[j]++;
                    }
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
