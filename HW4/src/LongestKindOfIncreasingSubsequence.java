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
                                  // i        // j
        int[][] OPT = new int[inputLength][inputLength];
        
        // keep track of i and j  (Ak + Ai)/2 < Aj
        // for i from 0 -> n
        //   for j from i+1 -> n
        //      for k from 0 -> i
        
        int result = 0;
        for (int i = 0; i < inputLength; i++) {
            for (int j = i + 1; j < inputLength; j++) {
                if(i != j) {
                    OPT[i][j] = 2;
                }
                for (int k = 0; k < i; k++) {
                    if((numbers[k] + numbers[i])/2 < numbers[j] && OPT[i][j] <= OPT[k][i]) {
                        OPT[i][j] = OPT[k][i] + 1;
                    }
                }
                if(OPT[i][j] > result) {
                    result = OPT[i][j];
                }
            }
        }
        
        System.out.println(result);
    }
}
