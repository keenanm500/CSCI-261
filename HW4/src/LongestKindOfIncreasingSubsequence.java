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

        // [final element][previous element]
//        int[][] OPT = new int[inputLength][inputLength];
//        for (int i = 0; i < inputLength; i++) {
//            for (int j = 0; j < i; j++) {
//                for (int k = 0; k < j; k++) {
//
//                    int previousAvg = (numbers[k] + numbers[j])/2;
//                    if(previousAvg < numbers[i] && OPT[i][j] <= OPT[i][k]) {
//                        OPT[i][j] = OPT[i][k] + 1;
//                        k++;
//                    }
//                }
//            }
//        }

        int[][] OPT = new int[inputLength][inputLength];
        for (int i = 0; i < inputLength; i++) {
            for (int j = i; j > 0; j--) {
                for (int k = j; k < i; k++) {

                    int previousAvg = (numbers[j] + numbers[k])/2;
                    if(previousAvg < numbers[i] && OPT[i][k] <= OPT[i][j]) {
                        OPT[i][k] = OPT[i][j] + 1;
                        
                    }
                }
            }
        }

        int result = 0;
        for (int[] ints : OPT) {
            for (int j = 0; j < ints.length; j++) {
                if (result < ints[j]) {
                    result = ints[j];
                }
            }
        }

        
        System.out.println(result);
    }
}
