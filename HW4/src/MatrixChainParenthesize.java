class MatrixChainParenthesize {

    static int[][] OPT;
    
    private static int min(int a, int b) {
        return a <= b ? a : b;
    }

    static int getOptMatrixChain(int[] p, int l, int r) {
        if (l == r) {
            return 0;
        }
        if (OPT[l][r] != -1) {
            return OPT[l][r];
        }
        OPT[l][r] = Integer.MAX_VALUE;
        for (int i = l; i < r; i++) {
            OPT[l][r] = min(OPT[l][r], getOptMatrixChain(p, l, i) + getOptMatrixChain(p, i + 1, r) + p[l - 1] * p[i] * p[r]);
        }
        return OPT[l][r];
    }

    public static void main (String[] args) {

        int[] matrices = {2, 3, 1, 4, 2};

        OPT = new int[matrices.length][matrices.length];
        for (int[] row : OPT) {
            for (int i = 0; i < row.length; i++) {
                row[i] = -1;
            }
        }
        int result = getOptMatrixChain(matrices, 1, matrices.length - 1);
        System.out.println(result);
    }
}