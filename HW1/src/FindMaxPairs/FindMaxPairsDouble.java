import java.util.Scanner;

public class FindMaxPairsDouble {

    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        int setSize = in.nextInt();
        double[] set = new double[setSize];

        for (int i = 0; i < setSize; i++) {
            set[i] = in.nextDouble();
        }

        int sumSetIterator = 0;
        double[] sumSet = new double[(int)Math.pow(setSize, 2)/2];
        
        // O(n^2)
        for(int i = 0; i < set.length - 1; i++) {
            for (int j = i + 1; j < set.length; j++) {
                sumSet[sumSetIterator] = set[i] + set[j];
                sumSetIterator++;
            }
        }
        
        // O(nlogn)
        sumSet = mergeSort(sumSet);
        
        int currentCount = 1;
        int maxCount = 1;
        double currentResult = sumSet[sumSet.length - 1];
        double resultValue = sumSet[sumSet.length - 1];
        
        // O(.5n^2) ≈ O(n^2)
        for(int i = sumSet.length - 2; i >= 0; i--) {
            if(sumSet[i + 1] != 0) {
                if(currentResult == sumSet[i]) {
                    currentCount++;
                } else {
                    if (currentCount >= maxCount) {
                        maxCount = currentCount;
                        resultValue = currentResult;
                    }
                    currentResult = sumSet[i];
                    currentCount = 1;
                }
            }
        }
        if(currentCount >= maxCount) {
            maxCount = currentCount;
            resultValue = currentResult;
        }
        
        // round to 6 digits
        long factor = (long) Math.pow(10, 6);
        resultValue = resultValue * factor;
        long tmp = (long) resultValue;
        System.out.print(maxCount + " ");
        System.out.format("%.6f", (double) tmp / factor);
    }
    
    private static double[] mergeSort(double[] array) {
        int n = array.length;
        if(n < 2) {
            return array;
        }

        int firstHalfIndex = 0;
        int secondHalfIndex = 0;
        double[] firstHalf = new double[n/2];
        double[] secondHalf = new double[n - n/2];
        for (int i = 0; i < n/2; i++) {
            firstHalf[firstHalfIndex++] = array[i];
        }
        for (int i = n/2; i < n; i++) {
            secondHalf[secondHalfIndex++] = array[i];
        }

        return merge(mergeSort(firstHalf), mergeSort(secondHalf));
    }

    private static double[] merge(double[] a, double[] b) {
        int aIndex = 0;
        int bIndex = 0;
        int resultIndex = 0;
        double[] result = new double[a.length + b.length];

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
