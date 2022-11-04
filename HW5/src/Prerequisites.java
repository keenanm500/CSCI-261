
import java.util.Scanner;

public class Prerequisites {
    
    // just find longest path in directed acyclic graph (something to do with topological orders)

    private static int[][] adjacencyList;
    private static boolean[] isVisited;
    private static int[] distances;

    private static void DFS(int current) {
        isVisited[current] = true;
        for (int adj : adjacencyList[current]) { // might need to have a check here for not -1 or 0 or something
            if (!isVisited[adj]) {
                DFS(adj);
            }
            distances[current] = Math.max(distances[current], 1 + distances[adj]);
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int vertexCount = scanner.nextInt();
        
        adjacencyList = new int[vertexCount + 1][150];

        int[] vertexAdjacencyIterator = new int[vertexCount + 1];
        
        int nextReq;
        for (int i = 1; i < vertexCount; i++) {
             do {
                 nextReq = scanner.nextInt();
                 if(nextReq != 0) {
                     adjacencyList[nextReq][vertexAdjacencyIterator[nextReq]++] = i;
                 }
             } while (nextReq != 0);
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
        distances = new int[vertexCount + 1];
        for (int i = 0; i < distances.length; i++) {
            distances[i] = 1;
        }

        for (int i = 0; i < adjacencyList.length; i++) {
            if(!isVisited[i]) {
                DFS(i);
            }
        }
        
        int result = 0;
        for (int i = 0; i < adjacencyList.length; i++) {
            if(distances[i] > result) {
                result = distances[i];
            }
        }
        
        System.out.println(result);
    }
}
