import java.util.Scanner;

public class LongestIncreasingSubseqRecursive {

    private static int getMax(int a, int b) {
        if(a > b) {
            return a;
        }
        return b;
    }
    
    private static int findLongestIncreasingSubsequenceTo(int[] numbers, int n){

        int max = 0;
        for(int i = 0; i < n; i++){
            if(numbers[i] < numbers[n]){
                max = getMax(max, findLongestIncreasingSubsequenceTo(numbers, i) + 1);
            }
        }
        return max;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int inputLength = scanner.nextInt();
        int[] numbers = new int[inputLength];

        // read in sequence of numbers
        for (int i = 0; i < inputLength; i++) {
            numbers[i] = scanner.nextInt();
        }
         
        int result = 0;
        for(int i = 0; i < inputLength -1; i++){
            result = getMax(result, findLongestIncreasingSubsequenceTo(numbers, i) + 1);
        }

        System.out.println(result);
    }
}
