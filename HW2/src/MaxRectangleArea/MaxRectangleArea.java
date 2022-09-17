import java.util.Scanner;

public class MaxRectangleArea {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int numberOfPoints = scanner.nextInt();
        Point[] polygon = new Point[numberOfPoints];
        
        int maxY = 0;

        for (int i = 0; i < numberOfPoints; i++) {
            polygon[i] = new Point(scanner.nextInt(), scanner.nextInt());
            if(polygon[i].getY() > maxY) {
                maxY = polygon[i].getY();
            }
        }

        System.out.println(maxRectangleArea(polygon, maxY));
    }
    
    private static int maxRectangleArea(Point[] polygon, int maxY) {
        
        int maxArea = 0;
        Ceiling[] openCeilings = new Ceiling[maxY + 1];
        
        boolean travellingVertically = false;
        int previousY = 0;
        
        for (Point point : polygon) {
            
            if (travellingVertically) {
                // traveling up
                if (point.getY() > previousY) {
                    for (int i = previousY + 1; i <= point.getY(); i++) {
                        // open a new ceiling
                        openCeilings[i] = new Ceiling(point.getX(), i);
                    }
                }
                // traveling down
                else {
                    for (int i = previousY; i > point.getY(); i--) {
                        // close the ceiling
                        openCeilings[i].setEndX(point.getX());
                        if (openCeilings[i].getArea() > maxArea) {
                            maxArea = openCeilings[i].getArea();
                        }
                    }
                }
            }
        
            travellingVertically = !travellingVertically;
            previousY = point.getY();
        }
        
        return maxArea;
    }
    
}
