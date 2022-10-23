import java.util.Scanner;

public class KnapsackWeightBoundsWithSolution {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int inputLength = scanner.nextInt();
        int minWeight = scanner.nextInt();
        int maxWeight = scanner.nextInt();
        
        int[] weights = new int[inputLength + 1];
        int[] costs = new int[inputLength + 1];
        for (int i = 0; i < inputLength; i++) {
            weights[i] = scanner.nextInt();
            costs[i] = scanner.nextInt();
        }
        
        findBestKnapsack(minWeight, maxWeight, weights, costs);
    }
    
    private static void findBestKnapsack(int minWeight, int maxWeight, int[] weights, int[] costs) {
        int n = costs.length;
        if(n <= 0 || maxWeight <= 0) {
            System.out.println(-1);
            return;
        }
        
        int[][] OPT = new int[n + 1][maxWeight + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= maxWeight; j++) {
                OPT[i][j] = 0;
            }
        }

        int[][] WeightOPT = new int[n + 1][maxWeight + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= maxWeight; j++) {
                WeightOPT[i][j] = 0;
            }
        }

        for (int j = 0; j < n; j++) {
            for (int v = 1; v <= maxWeight; v++) {
                if(j == 0) {
                    if(weights[j] == v) {
                        OPT[j][v] = costs[j];
                        WeightOPT[j][v] = weights[j];
                    }
                } else {
                    if (weights[j] > v) {
                        OPT[j][v] = OPT[j - 1][v];
                        WeightOPT[j][v] = WeightOPT[j - 1][v];
                    } else {
                        int temp = -1;
                        if (WeightOPT[j - 1][v - weights[j]] + weights[j] == v) {
                            temp = OPT[j - 1][v - weights[j]] + costs[j];
                        }
                        if (temp >= OPT[j - 1][v]) {
                            OPT[j][v] = temp;
                            WeightOPT[j][v] = WeightOPT[j - 1][v - weights[j]] + weights[j];
                        } else {
                            OPT[j][v] = OPT[j - 1][v];
                            WeightOPT[j][v] = WeightOPT[j - 1][v];
                        }
                    }
                }
            }
        }
        
        int result = 0;
        int currentI = 0;
        int currentJ = 0;
        for (int i = 0; i < OPT.length; i++) {
            for (int j = 0; j < OPT[i].length; j++) {
                if(OPT[i][j] > result && j >= minWeight) {
                    result = OPT[i][j];
                    currentI = i;
                    currentJ = j;
                }
            }
        }

        int[] ids = new int[OPT.length];

        int idsIterator = 0;
        while(currentI > 0) {
            if(OPT[currentI - 1][currentJ] != OPT[currentI][currentJ]){
                ids[idsIterator++] = currentI + 1;
                currentJ -= weights[currentI];
            }
            currentI--;

            if(currentI == 0 && currentJ != 0) {
                ids[idsIterator] = 1;
            }
        }

        System.out.println(result != 0 ? result : -1);

        if(result != 0) {
            for (int i = ids.length - 1; i > -1; i--) {
                if (ids[i] != 0) {
                    System.out.print(ids[i] + " ");
                }
            }
        }
    }
    
}
