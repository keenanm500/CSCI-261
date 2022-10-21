import java.util.Scanner;

public class KnapsackWeightBoundsWithSolution {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int w = scanner.nextInt();
        int W = scanner.nextInt();
        
        Knapsack[] knapsacks = new Knapsack[n];
        int[] wt = new int[n + 1];
        int[] val = new int[n + 1];
        for (int i = 0; i < n; i++) {
            wt[i] = scanner.nextInt();
            val[i] = scanner.nextInt();
        }
        
        findBestKnapsack(w, W, wt, val, n);
        // findBestLegalKnapsack(w, W, augmentedKnapsacks, n);
    }
    
    private static void findBestKnapsack(int w, int W, int[] weights, int[] values, int n) {
        if(n <= 0 || W <= 0) {
            return;
        }
        
        int[][] OPT = new int[n + 1][W + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                OPT[i][j] = 0;
            }
        }

        int[][] WeightOPT = new int[n + 1][W + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                WeightOPT[i][j] = 0;
            }
        }

        for (int j = 1; j < n + 1; j++) {
            for (int v = 1; v <= W; v++) {
                if(weights[j] > v) {
                    OPT[j][v] = OPT[j - 1][v];
                    WeightOPT[j][v] = WeightOPT[j - 1][v];
                } else {
                    int temp = -1;
                    if(WeightOPT[j - 1][v - weights[j]] + weights[j] == v) {
                        temp = OPT[j - 1][v - weights[j]] + values[j];
                    }
                    if(temp >= OPT[j - 1][v]) {
                        OPT[j][v] = temp;
                        WeightOPT[j][v] = WeightOPT[j - 1][v - weights[j]] + weights[j];
                    } else {
                        OPT[j][v] = OPT[j - 1][v];
                        WeightOPT[j][v] = WeightOPT[j - 1][v];
                    }
                }
            }
        }
        
        int result = 0;
        int currentI = 0;
        int currentJ = 0;
        for (int i = 0; i < OPT.length; i++) {
            for (int j = 0; j < OPT[i].length; j++) {
                if(OPT[i][j] > result) {
                    result = OPT[i][j];
                    currentI = i;
                    currentJ = j;
                }
            }
        }

//        int[] ids = new int[OPT.length];
//        int idsIterator = 0;
//        while(currentI > 0) {
//            if(OPT[currentI - 1][currentJ] != OPT[currentI][currentJ]){
//                ids[idsIterator++] = objects[objectRow].id;
//                weight -= objects[objectRow].weight;
//                objectRow--;
//            } else {
//                objectRow--;
//            }
//        }
        
        System.out.println(result);
    }

    private static void findBestLegalKnapsack(int w, int W, Knapsack[] objects, int n) {
        if (n <= 0 || W <= 0) {
            System.out.println("-1");
            return;
        }

        Knapsack[][] OPT = new Knapsack[n + 1][W + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= W; j++) {
                OPT[i][j] = new Knapsack(-1, 0, 0);
            }
        }
        for (int j = 1; j < n + 1; j++) {
            for (int i = 1; i <= W; i++) {
                if(objects[j].weight > i) {
                    OPT[j][i] = OPT[j - 1][i];
                } else {
                    //OPT[j][i] = Math.min()
                }
                
//                OPT[j][i] = OPT[j - 1][i];
//                if (objects[j].weight <= i && 
//                        (OPT[j - 1][i - objects[j].weight].value + objects[j].value > OPT[j][i].value && 
//                        OPT[j - 1][i - objects[j].weight].weight + objects[j].weight >= OPT[j][i].weight)) {
//                    OPT[j][i] = new Knapsack(OPT[j - 1][i - objects[j].weight], objects[j].value, objects[j].weight);
//                }
            }
        }

        // get max knapsack with min weight
        int weight = 0;
        int objectRow = 0;
        int result = 0;
        for (int i = 0; i < OPT.length; i++) {
            for (int j = 0; j < OPT[i].length; j++) {
                if (OPT[i][j] != null && OPT[i][j].value >= result && OPT[i][j].weight >= w) {
                    result = OPT[i][j].value;
                    weight = j;
                    objectRow = i;
                }
            }
        }

        if(result == 0) {
            System.out.println(-1);
            return;
        }
        System.out.println(result);
        
        int[] ids = new int[OPT.length];
        int idsIterator = 0;
        while(objectRow > 0) {
            if(OPT[objectRow - 1][weight].value != OPT[objectRow][weight].value){
                ids[idsIterator++] = objects[objectRow].id;
                weight -= objects[objectRow].weight;
                objectRow--;
            } else {
                objectRow--;
            }
        }

        for (int i: ids) {
            if(i != 0) {
                System.out.print(i + " ");
            }
        }
        
    }
    
}

class Knapsack {
    int id = 0;
    int value = 0;
    int weight = 0;
    
    Knapsack(int value, int weight) {
        this.value = value;
        this.weight = weight;
    }

    Knapsack(int id, int value, int weight) {
        this.id = id;
        this.value = value;
        this.weight = weight;
    }

    Knapsack(Knapsack knapsack, int addedValue, int addedWeight) {
        this.id = -1;
        this.value = knapsack.value + addedValue;
        this.weight = knapsack.weight + addedWeight;
    }
}
