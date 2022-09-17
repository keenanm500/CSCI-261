import java.util.Scanner;

public class Planters {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int occupiedPlanters = in.nextInt();
        int vacantPlanters = in.nextInt();
        Planter[] planters = new Planter[occupiedPlanters + vacantPlanters];

        for (int i = 0; i < occupiedPlanters; i++) {
            planters[i] = new Planter(in.nextInt(), true);
        }
        for (int i = occupiedPlanters; i < occupiedPlanters + vacantPlanters; i++) {
            planters[i] = new Planter(in.nextInt(), false);
        }

        // O(nlogn)
        planters = mergeSort(planters);
        
        // O(n)
        if(replant(planters)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
    
    private static boolean replant(Planter[] planters) {
        
        int emptyPlanterIndex = 0;
        int maxEmptyPlanterIndex = 0;
        Planter[] emptyPlanters = new Planter[planters.length];
        for(int i = planters.length - 1; i >= 0; i--) {
            if(!planters[i].isOccupied()) {
                emptyPlanters[emptyPlanterIndex++] = planters[i];
            } else if(emptyPlanters[maxEmptyPlanterIndex] != null && emptyPlanters[maxEmptyPlanterIndex].getSize() > planters[i].getSize()){
                maxEmptyPlanterIndex++;
                planters[i].setOccupied(false);
                emptyPlanters[emptyPlanterIndex++] = planters[i];
            } else {
                return false;
            }
        }
        
        return true;
    }

    private static Planter[] mergeSort(Planter[] array) {
        int n = array.length;
        if(n < 2) {
            return array;
        }

        int firstHalfIndex = 0;
        int secondHalfIndex = 0;
        Planter[] firstHalf = new Planter[n/2];
        Planter[] secondHalf = new Planter[n - n/2];
        for (int i = 0; i < n/2; i++) {
            firstHalf[firstHalfIndex++] = array[i];
        }
        for (int i = n/2; i < n; i++) {
            secondHalf[secondHalfIndex++] = array[i];
        }

        return merge(mergeSort(firstHalf), mergeSort(secondHalf));
    }

    private static Planter[] merge(Planter[] a, Planter[] b) {
        int aIndex = 0;
        int bIndex = 0;
        int resultIndex = 0;
        Planter[] result = new Planter[a.length + b.length];

        while (aIndex < a.length || bIndex < b.length) {
            if (b.length <= bIndex || (a.length > aIndex && a[aIndex].getSize() <= b[bIndex].getSize())) {
                result[resultIndex++] = a[aIndex++];
            } else {
                result[resultIndex++] = b[bIndex++];
            }
        }
        return result;
    }
}

