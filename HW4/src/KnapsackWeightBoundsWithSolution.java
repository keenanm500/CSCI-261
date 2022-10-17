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
        wt[0] = 0;
        val[0] = 0;
        for (int i = 0; i < n; i++) {
            int weight = scanner.nextInt();
            int value = scanner.nextInt();
            knapsacks[i] = new Knapsack(i + 1, value, weight);
        }
        knapsacks = mergeSort(knapsacks);

        Knapsack[] augmentedKnapsacks = new Knapsack[n + 1];
        augmentedKnapsacks[0] = new Knapsack(-1, 0, 0);
        for (int i = 1; i <= n; i++) {
            augmentedKnapsacks[i] = knapsacks[i - 1];
        }
        findBestLegalKnapsack(w, W, augmentedKnapsacks, n);
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
                OPT[j][i] = OPT[j - 1][i];
                if (objects[j].weight <= i && 
                        (OPT[j - 1][i - objects[j].weight].value + objects[j].value > OPT[j][i].value && 
                        OPT[j - 1][i - objects[j].weight].weight + objects[j].weight >= OPT[j][i].weight)) {
                    OPT[j][i] = new Knapsack(OPT[j - 1][i - objects[j].weight], objects[j].value, objects[j].weight);
                }
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

        ids = mergeSort(ids);
        for (int i: ids) {
            if(i != 0) {
                System.out.print(i + " ");
            }
        }
        
    }

    private static Knapsack[] mergeSort(Knapsack[] array) {
        int n = array.length;
        if(n < 2) {
            return array;
        }

        int firstHalfIndex = 0;
        int secondHalfIndex = 0;
        Knapsack[] firstHalf = new Knapsack[n/2];
        Knapsack[] secondHalf = new Knapsack[n - n/2];
        for (int i = 0; i < n/2; i++) {
            firstHalf[firstHalfIndex++] = array[i];
        }
        for (int i = n/2; i < n; i++) {
            secondHalf[secondHalfIndex++] = array[i];
        }

        return merge(mergeSort(firstHalf), mergeSort(secondHalf));
    }

    private static Knapsack[] merge(Knapsack[] a, Knapsack[] b) {
        int aIndex = 0;
        int bIndex = 0;
        int resultIndex = 0;
        Knapsack[] result = new Knapsack[a.length + b.length];

        while (aIndex < a.length || bIndex < b.length) {
            if (b.length <= bIndex || (a.length > aIndex && a[aIndex].weight > b[bIndex].weight)) {
                result[resultIndex++] = a[aIndex++];
            } else {
                result[resultIndex++] = b[bIndex++];
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
                result[resultIndex++] = a[aIndex++];
            } else {
                result[resultIndex++] = b[bIndex++];
            }
        }
        return result;
    }
}

class Knapsack {
    int id = 0;
    int value = 0;
    int weight = 0;

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
