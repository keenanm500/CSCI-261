import java.util.Scanner;

public class MaxRectangle {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int numberOfPoints = scanner.nextInt();
        int[] xValues = new int[numberOfPoints];
        int[] yValues = new int[numberOfPoints];
        
        // read in all points into xValues and yValues arrays
        int maxX = 0;
        for (int i = 0; i < numberOfPoints; i++) {
            xValues[i] = scanner.nextInt();
            yValues[i] = scanner.nextInt();
            maxX = xValues[i];
        }

        // create and fill in a bar-graph representing the polygon
        // this has nested for-loops, but iterates only once per 
        // x value, so it is still linear
        int[] graph = new int[maxX + 1];
        int lastX = 0;
        for (int i = 0; i < numberOfPoints; i++) {
            int currentY = yValues[i];
            int currentX = xValues[i];
            for (int j = lastX; j < currentX; j++) {
                graph[j] = currentY;
            }
            lastX = currentX;
        }

        System.out.println(maxRectangleArea(graph));
    }

    private static int maxRectangleArea(int[] graph) {

        int maxX = graph.length - 1;
        
        int openIndex = 0;
        int[] open = new int[maxX*maxX];

        // add a fake first bar to the open-stack
        open[openIndex++] = -1;
        int[] leftSupportOfIndex = new int[maxX + 1];
        int[] rightSupportOfIndex = new int[maxX + 1];
        for (int i = 0; i < maxX; i++){
            leftSupportOfIndex[i] = -1;
            rightSupportOfIndex[i] = maxX;
        }
        
        // iterate over every bar in the graph
        for(int i = 0; i < maxX; i++) {
            while (openIndex >= 0 && open[openIndex] != -1 && graph[i] < graph[open[openIndex]]) {
                // once we start going down, close off anything on the open-stack 
                // that is greater than our current y position
                rightSupportOfIndex[open[openIndex--]] = i;
            }
            // we can simply close the left side with the highest still-open value
            leftSupportOfIndex[i] = open[openIndex];
            
            // add this value to the open-stack as it will be the left-support of future values
            open[1 + openIndex++] = i;
        }

        int maxArea = graph[0];
        for (int i = 0; i < maxX; i++) {
            int result = graph[i] * // height of rectangle
                        (rightSupportOfIndex[i] - leftSupportOfIndex[i] - 1); // width of rectangle
            // '-1' is because the right and left supports are inclusive of bars that don't contribute to this rectangle
            if(result > maxArea) {
                maxArea = result;
            }
        }
        
        return maxArea;
    }
}
