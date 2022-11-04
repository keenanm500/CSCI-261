
import java.util.Scanner;

public class NumPaths {
    
    // Data Model
    private static int[][] adjacencyList;
    private static boolean[] isVisited;
    
    // DP
    private static int[] distances;
    private static int[] shortestPaths;
    
    // Queue
    private static int queueStart = 0;
    private static int queueEnd = 0;
    private static int[] queue;

    private static void fillShortestPathsFrom(int start) {


        queue[queueEnd++] = start;
        isVisited[start] = true;
        distances[start] = 0;
        shortestPaths[start] = 1;
        
        while(queueEnd - queueStart > -1) {
            
            int current = queue[queueStart++];
            for (int adj : adjacencyList[current]) {
                if(!isVisited[adj]) {
                    queue[queueEnd++] = adj;
                    isVisited[adj] = true;
                }
                if(distances[adj] > distances[current] + 1) {
                    distances[adj] = distances[current] + 1;
                    shortestPaths[adj] = shortestPaths[current];
                } else if(distances[adj] == distances[current] + 1) {
                    shortestPaths[adj] += shortestPaths[current];
                }
            }
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int vertexCount = scanner.nextInt();
        int edgeCount = scanner.nextInt();
        
        int start = scanner.nextInt();
        int end = scanner.nextInt();

        adjacencyList = new int[vertexCount + 1][vertexCount + 1];

        int[] vertexAdjacencyIterator = new int[vertexCount + 1];
        for (int i = 0; i < edgeCount; i++) {
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            adjacencyList[from][vertexAdjacencyIterator[from]++] = to;
            adjacencyList[to][vertexAdjacencyIterator[to]++] = from;
        }

        // this loops once per edge O(m) - just to clean up the arrays
        for (int i = 0; i < adjacencyList.length; i++) {
            int[] temp = new int[vertexAdjacencyIterator[i]];
            for (int j = 0; j < temp.length; j++) {
                temp[j] = adjacencyList[i][j];
            }
            adjacencyList[i] = temp;
        }
        
        isVisited = new boolean[vertexCount + 1];
        queue = new int[vertexCount + 1];
        shortestPaths = new int[vertexCount + 1];
        distances = new int[vertexCount + 1];
        for (int i = 0; i < distances.length; i++) {
            distances[i] = Integer.MAX_VALUE;
        }
        
        fillShortestPathsFrom(start);
        System.out.println(shortestPaths[end]);
    }
}
