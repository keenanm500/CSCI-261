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

    private static Point getBestDonutShopLocation(Point[] points) {
        
        int[] xValues = new int[points.length];
        int[] yValues = new int[points.length];
        
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

        Point bestLocation = getBestDonutShopLocation(points);

        int sumOfDistances = 0;
        for (int i = 0; i < numberOfPoints; i++) {
            sumOfDistances += getManhattanDistance(bestLocation, points[i]);
        }

        System.out.println(sumOfDistances);
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