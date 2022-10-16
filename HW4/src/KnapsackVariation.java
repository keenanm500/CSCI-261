import java.util.Scanner;

public class KnapsackVariation {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int w = scanner.nextInt();
        int W = scanner.nextInt();
        int[] wt = new int[n + 1];
        int[] val = new int[n + 1];
        wt[0] = 0;
        val[0] = 0;
        for (int i = 1; i <= n; i++) {
            wt[i] = scanner.nextInt();
            val[i] = scanner.nextInt();
        }
        // wt = mergeSort(wt, val);
        System.out.println(knapsackDP(w, W, wt, val, n));
    }

    private static int knapsackDP(int W1, int W2, int[] w, int[] v, int n) {
        if (n <= 0 || W2 <= 0) {
            return 0;
        }

        Knapsack[][] OPT = new Knapsack[n + 1][W2 + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= W2; j++) {
                OPT[i][j] = new Knapsack(0, 0);
            }
        }
        for (int j = 1; j < n + 1; j++) {
            for (int i = 1; i <= W2; i++) {
                OPT[j][i] = OPT[j - 1][i];
                if (w[j] <= i && 
                        (OPT[j - 1][i - w[j]].value + v[j] > OPT[j][i].value && 
                        OPT[j - 1][i - w[j]].weight + w[j] >= OPT[j][i].weight)) {
                    OPT[j][i] = new Knapsack(OPT[j - 1][i - w[j]], v[j], w[j]);
                }
            }
        }
        System.out.println();
        for (int r = 0; r < OPT.length; r++) {       //for loop for row iteration.
            for (int c = 0; c < OPT[r].length; c++) {   //for loop for column iteration.
                if (OPT[r][c] != null) {
                    System.out.print(OPT[r][c].weight + "-" + OPT[r][c].value + " ");
                } else {
                    System.out.print("N N ");
                }
            }
            System.out.println(); //using this for new line to print array in matrix format.
        }

        // get max knapsack with min weight
        int result = 0;
        for (int i = 0; i < OPT.length; i++) {
            for (int j = 0; j < OPT[i].length; j++) {
                if (OPT[i][j] != null && OPT[i][j].value > result && OPT[i][j].weight >= W1) {
                    result = OPT[i][j].value;
                }
            }
        }
        return result;

    }

    private static int[] mergeSort(int[] array) {
        int n = array.length;
        if(n < 2) {
            return array;
        }

        int firstHalfIndex = 0;
        int secondHalfIndex = 0;
        int[] firstHalf = new int[n/2];
        int[] secondHalf = new int[n - n/2];
        for (int i = 0; i < n/2; i++) {
            firstHalf[firstHalfIndex++] = array[i];
        }
        for (int i = n/2; i < n; i++) {
            secondHalf[secondHalfIndex++] = array[i];
        }

        return merge(mergeSort(firstHalf), mergeSort(secondHalf));
    }

    private static int[] merge(int[] a, int[] b) {
        int aIndex = 0;
        int bIndex = 0;
        int resultIndex = 0;
        int[] result = new int[a.length + b.length];

        while (aIndex < a.length || bIndex < b.length) {
            if (b.length <= bIndex || (a.length > aIndex && a[aIndex] <= b[bIndex])) {
                result[resultIndex++] = b[bIndex++];
            } else {
                result[resultIndex++] = a[aIndex++];
            }
        }
        return result;
    }
}

class Knapsack {
    int value = 0;
    int weight = 0;

    Knapsack(int value, int weight) {
        this.value = value;
        this.weight = weight;
    }

    Knapsack(Knapsack knapsack, int addedValue, int addedWeight) {
        this.value = knapsack.value + addedValue;
        this.weight = knapsack.weight + addedWeight;
    }
}
