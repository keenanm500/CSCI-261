
import java.util.Scanner;

public class ConnectGraph {
    
    // do a dfs search of each component - link anything not found in the searches
    private static int[][] adjacencyList;
    private static boolean[] isVisited;
    
    private static void DFS(int current) {
        isVisited[current] = true;
        for (int adj : adjacencyList[current]) { // might need to have a check here for not -1 or 0 or something
            if (!isVisited[adj]) {
                DFS(adj);
            }
        }
    }
    
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        
        int vertexCount = scanner.nextInt();
        int edgeCount = scanner.nextInt();
        
        adjacencyList = new int[vertexCount + 1][vertexCount + 1];
        isVisited = new boolean[vertexCount + 1];

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

        int result = -1;
        for (int i = 1; i < vertexCount + 1; i++) {
            if(!isVisited[i]) {
                DFS(i);
                result++;
            }
        }

        System.out.println(result);
    }
}
