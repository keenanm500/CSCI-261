import java.util.Scanner;

public class MaxRectangle {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int numberOfPoints = scanner.nextInt();
        Point[] polygon = new Point[numberOfPoints];
        
        int maxY = 0;

        for (int i = 0; i < numberOfPoints; i++) {
            polygon[i] = new Point(scanner.nextInt(), scanner.nextInt());
            if(polygon[i].y > maxY) {
                maxY = polygon[i].y;
            }
        }

        System.out.println(maxRectangleArea(polygon, maxY));
    }
    
    private static int maxRectangleArea(Point[] polygon, int maxY) {

        int maxArea = 0;
        int[] open = new int[maxY + 1];

        int previousY = 0;

        for (int j = 1; j < polygon.length; j+=2) {
            int currentX = polygon[j].x;
            int currentY = polygon[j].y;

            // traveling up
            if (currentY > previousY) {
                for (int i = previousY + 1; i <= currentY; i++) {
                    // open a new rectangle
                    open[i] = currentX;
                }
            }
            // traveling down
            else {
                for (int i = previousY; i > currentY; i--) {
                    // check the rectangle
                    int res = (currentX - open[i])*i;
                    if (res > maxArea) {
                        maxArea = res;
                    }
                }
            }

            previousY = currentY;
        }
        
        return maxArea;
    }
    
}

class Point {

    int x = 0;
    int y = 0;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
