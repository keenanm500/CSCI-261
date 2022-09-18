import java.util.Scanner;

public class MaxRectangle {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfPoints = scanner.nextInt();
        int[] xValues = new int[numberOfPoints];
        int[] yValues = new int[numberOfPoints];
        
        int maxX = 0;
        for (int i = 0; i < numberOfPoints; i++) {
            xValues[i] = scanner.nextInt();
            yValues[i] = scanner.nextInt();
            maxX = xValues[i];
        }

        // create and fill in a bar-graph representing the polygon
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

        System.out.println(maxRectangleArea(graph, maxX));
    }

    private static int maxRectangleArea(int[] graph, int maxX) {

        int stackIndex = 0;
        int[] stack = new int[maxX];

        // add the first bar to the stack
        stack[stackIndex++] = -1;
        int leftSupport[] = new int[maxX + 1];
        int rightSupport[] = new int[maxX + 1];
        for (int i = 0; i < maxX; i++){
            leftSupport[i] = -1;
            rightSupport[i] = maxX;
        }
        
        int i = 0;
        // iterate over every bar in the graph
        while (i < maxX) {
            while (stackIndex >= 0 && stack[stackIndex] != -1 && graph[i] < graph[stack[stackIndex]]) {
                rightSupport[stack[stackIndex--]] = i;
            }
            if (i > 0 && graph[i] == graph[i-1]) {
                leftSupport[i] = leftSupport[i-1];
            } else {
                leftSupport[i] = stack[stackIndex];
            }
            
            stack[1 + stackIndex++] = i;
            i++;
        }

        int maxArea = graph[0];
        for (int j = 0; j < maxX; j++) {
            int result = graph[j]*(rightSupport[j] - leftSupport[j] - 1);
            if(result > maxArea) {
                maxArea = result;
            }
        }
        
        return maxArea;
    }
}
