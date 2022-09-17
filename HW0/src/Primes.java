import java.util.Scanner;

public class Primes {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        printOrderedPrimesTo(n);
    }
    
    private static void printOrderedPrimesTo(int n) {
        
        // list of all ints 2 - n
        int[] obtuseList = new int[n - 1];
        for(int i = 0, j = 2; j <= n; i++, j++) {
            obtuseList[i] = j;
        }
        
        // zero out non-primes
        for(int i = 0; i < obtuseList.length; i++) {
            for(int j = i + 1; j < obtuseList.length; j++) {
                if(obtuseList[i] != 0 && obtuseList[j] % obtuseList[i] == 0) {
                    obtuseList[j] = 0;
                }
            }
        }

        // print out non-zeros
        for(int i: obtuseList) {
            if(i != 0) {
                System.out.println(i);
            }
        }
    }
}
