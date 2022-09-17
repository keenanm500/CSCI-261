import java.util.Scanner;

public class SmallestTwo {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        
        Integer smallest = null;
        Integer nextSmallest = null;
        for(int i = 0; i < n; i++) {
            // read the next line and check it
            int newInt = in.nextInt();
            if(smallest == null || smallest > newInt) {
                // if we have a new small number
                nextSmallest = smallest;
                smallest = newInt;
            } else if((nextSmallest == null || nextSmallest > newInt) && newInt != smallest) {
                // if we have a new second-smallest number
                nextSmallest = newInt;
            }
        }
        System.out.println(smallest);
        System.out.println(nextSmallest);
    }
    
}
