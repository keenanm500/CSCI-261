import java.util.Scanner;
public class Donut {

    public static int getMedian(int[] array, int k) {
        int randomValue = array[0];

        int lessThanIndex = 0;
        int equalToIndex = 0;
        int greaterThanIndex = 0;

        int[] lessThan = new int[array.length];
        int[] equalTo = new int[array.length];
        int[] greaterThan = new int[array.length];

        for (int value: array) {
            if(value > randomValue) {
                greaterThan[greaterThanIndex++] = value;
            } else if(value < randomValue) {
                lessThan[lessThanIndex++] = value;
            } else {
                equalTo[equalToIndex++] = value;
            }
        }

        int[] l = new int[lessThanIndex];
        int[] g = new int[greaterThanIndex];
        int[] e = new int[equalToIndex];
        for (int i = 0; i < lessThanIndex; i++) {
            l[i] = lessThan[i];
        }
        for (int i = 0; i < greaterThanIndex; i++) {
            g[i] = greaterThan[i];
        }
        for (int i = 0; i < equalToIndex; i++) {
            e[i] = equalTo[i];
        }

        if(k == l.length) {
            return randomValue;
        } else if(k >= l.length + e.length) {
            return getMedian(g, k - l.length - e.length);
        } else {
            return getMedian(l, k);
        }
    }

    private static Point getBestDonutShopLocation(Point[] points, boolean maxMedianX, boolean maxMedianY) {


        int[] xValues;
        int[] yValues;
        
        // This will offset the median to the larger or smaller discrete median (since the donut shop has to be
        // on a street corner), so if the entry set is {1, 2, 3, 4}, maxMedianX will cause this to return 3
        // instead of 2.
        if(maxMedianX) {
            xValues = new int[points.length + 1];
            xValues[points.length] = Integer.MAX_VALUE;
        } else {
            xValues = new int[points.length];
        }

        if(maxMedianY) {
            yValues = new int[points.length + 1];
            yValues[points.length] = Integer.MAX_VALUE;
        } else {
            yValues = new int[points.length];
        }
        
        // O(n)
        for (int i = 0; i < points.length; i++) {
            xValues[i]  = points[i].x;
            yValues[i]  = points[i].y;
        }

        // O(n)
        int xResult = getMedian(xValues, xValues.length / 2);

        // O(n)
        int yResult = getMedian(yValues, yValues.length / 2);

        return new Point(xResult, yResult);

    }

    private static int getManhattanDistance(Point point1, Point point2) {

        int xDifference = point1.x - point2.x;
        if(xDifference < 0) {
            xDifference = -xDifference;
        }
        int yDifference = point1.y - point2.y;
        if(yDifference < 0) {
            yDifference = -yDifference;
        }
        return xDifference + yDifference;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberOfPoints = scanner.nextInt();
        Point[] points = new Point[numberOfPoints];

        // read in all points
        for (int i = 0; i < numberOfPoints; i++) {
            points[i] = new Point(scanner.nextInt(), scanner.nextInt());
        }

        Point[] possibleLocations;
        if(points.length % 2 == 0) {
            
            // if the number of points is even, it's necessary to check 4 different locations
            possibleLocations = new Point[]{
                getBestDonutShopLocation(points, false, false),
                getBestDonutShopLocation(points, false, true),
                getBestDonutShopLocation(points, true, false),
                getBestDonutShopLocation(points, true, true)
            };
        } else {
            possibleLocations = new Point[]{getBestDonutShopLocation(points, false, false)};
        }

        int bestSumOfDistances = Integer.MAX_VALUE;
        int sumOfDistances = 0;
        for (Point possibleLocation : possibleLocations) {
            for (int i = 0; i < numberOfPoints; i++) {
                sumOfDistances += getManhattanDistance(possibleLocation, points[i]);
            }
            if (sumOfDistances < bestSumOfDistances) {
                bestSumOfDistances = sumOfDistances;
            }
        }


        System.out.println(bestSumOfDistances);
    }
}

class Point {

    public int x = 0;
    public int y = 0;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}