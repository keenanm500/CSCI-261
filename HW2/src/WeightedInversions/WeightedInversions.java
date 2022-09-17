import java.util.Scanner;

public class WeightedInversions {

    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        int size = in.nextInt();
        Entry[] array = new Entry[size];

        for (int i = 0; i < size; i++) {
            array[i] = new Entry(in.nextInt(), i);
        }

        System.out.println(mergeSortAndCount(array).getCount());
    }

    private static Response mergeSortAndCount(Entry[] array) {

        Response result = new Response();

        int n = array.length;
        if(n < 2) {
            result.setArray(new Entry[1]);
            result.getArray()[0] = new Entry(array[0]);
            return result;
        }

        int firstHalfIndex = 0;
        int secondHalfIndex = 0;
        Entry[] firstHalf = new Entry[n/2];
        Entry[] secondHalf = new Entry[n - n/2];
        for (int i = 0; i < n/2; i++) {
            firstHalf[firstHalfIndex++] = array[i];
        }
        for (int i = n/2; i < n; i++) {
            secondHalf[secondHalfIndex++] = array[i];
        }

        return mergeAndCount(mergeSortAndCount(firstHalf), mergeSortAndCount(secondHalf));
    }

    private static Response mergeAndCount(Response res1, Response res2) {

        Entry[] left = res1.getArray();
        Entry[] right = res2.getArray();
        Response result = new Response();

        result.setCount(res1.getCount() + res2.getCount());
        
        augmentInversionMagnitudes(left);

        int leftIndex = 0;
        int rightIndex = 0;
        int resultIndex = 0;
        result.setArray(new Entry[left.length + right.length]);

        while (leftIndex < left.length || rightIndex < right.length) {
            if (right.length <= rightIndex || (left.length > leftIndex && left[leftIndex].getValue() <= right[rightIndex].getValue())) {
                result.getArray()[resultIndex++] = new Entry(left[leftIndex++]);
            } else if(leftIndex < left.length){
                result.setCount(result.getCount() + right[rightIndex].getOriginalPosition()*(left.length - leftIndex) - left[leftIndex].getInversionMagnitude());
                result.getArray()[resultIndex++] = new Entry(right[rightIndex++]);
            } else {
                result.getArray()[resultIndex++] = new Entry(right[rightIndex++]);
            }
        }

        return result;
    }
    
    private static void augmentInversionMagnitudes(Entry[] array) {
        long currentInversionMagnitude = 0;
        for (int i = array.length - 1; i >= 0; i--) {
            array[i].setInversionMagnitude(currentInversionMagnitude + array[i].getOriginalPosition());
            currentInversionMagnitude = array[i].getInversionMagnitude();
        }
    }

}
