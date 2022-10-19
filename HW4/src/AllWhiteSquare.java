import java.util.Scanner;

public class AllWhiteSquare {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int inputLength = scanner.nextInt();
        int[][] square = new int[inputLength][inputLength];
        
        for(int i = 0; i < inputLength; i++) {
            String buffer = scanner.next();
            for (int j = 0; j < inputLength; j++) {
                int white = 0;
                if(buffer.charAt(j) == 'w') {
                    white = 1;
                }
                square[i][j] = white;
            }
        }
        
        findBiggestSquare(square);
    }

    private static int min(int a, int b, int c) {
        if(a <= b && a <= c) {
            return a;
        } else if(b <= c) {
            return b;
        } else {
            return c;
        }
    }

    public static void findBiggestSquare(int[][] OPT) {

        int result = 0;
        for (int i = 0; i < OPT.length; i++) {
            for (int j = 0; j < OPT.length; j++) {
                if(OPT[i][j] != 0 && i > 0 && j > 0) {
                    OPT[i][j] = min(OPT[i - 1][j - 1], OPT[i - 1][j], OPT[i][j - 1]) + 1;
                    if(OPT[i][j] > result) {
                        result = OPT[i][j];
                    }
                }
            }
        }

        System.out.println(result);
    }
}
