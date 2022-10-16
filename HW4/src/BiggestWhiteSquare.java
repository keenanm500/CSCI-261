import java.util.Scanner;

public class BiggestWhiteSquare {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int inputLength = scanner.nextInt();
        boolean[][] square = new boolean[inputLength][inputLength];

        // read in sequence of numbers
        for (int i = 0; i < inputLength; i++) {
            for (int j = 0; j < inputLength; j++) {
                boolean white = false;
                if(scanner.nextInt() == 1) {
                    white = true;
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
    
    public static void findBiggestSquare(boolean[][] square) {
        
        int[][] OPT = new int[square.length][square.length];
        
        for (int i = 0; i < square.length; i++) {
            for (int j = 0; j < square.length; j++) {
                if(square[i][j]) {
                    OPT[i][j] = 1;
                    if((i > 0 && j > 0) && OPT[i - 1][j - 1] == OPT[i - 1][j] && OPT[i - 1][j - 1] == OPT[i][j - 1]) {
                        OPT[i][j] = OPT[i - 1][j - 1] + 1;
                    } else if(i > 0 && j > 0){
                        OPT[i][j] = min(OPT[i - 1][j], OPT[i][j - 1], OPT[i - 1][j - 1]) + 1;
                    }
                } else {
                    OPT[i][j] = 0;
                }
            }
        }

        int result = 0;
        for (int[] i : OPT) {
            for(int j : i) {
                if (j > result) {
                    result = j;
                }
            }
        }

        System.out.println(result);
    }
}
