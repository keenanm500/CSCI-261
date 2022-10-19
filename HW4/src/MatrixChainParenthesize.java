import java.util.Scanner;

class MatrixChainParenthesize {

    static int getOptMatrixChain(int[] matrixArray) {

        int[][] OPT = new int[matrixArray.length][matrixArray.length];
        for (int d = 0; d < OPT.length; d++) {
            for (int i = 1; i < OPT.length - d; i++) {
                int j = i + d;
                if(i == j) {
                    OPT[i][j] = 0;
                } else {
                    OPT[i][j] = Integer.MAX_VALUE;
                }
                for (int k = i; k <= j - 1; k++){
                    int tmp = OPT[i][k] + OPT[k + 1][j] + matrixArray[i - 1] * matrixArray[k] * matrixArray[j];
                    if (OPT[i][j] > tmp) {
                        OPT[i][j] = tmp;
                    } 
                }
            }
        }
        return OPT[1][OPT.length - 1];
    }

    public static void main (String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] matrices = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            matrices[i] = scanner.nextInt();
        }
        
        System.out.println(getOptMatrixChain(matrices));
    }
}