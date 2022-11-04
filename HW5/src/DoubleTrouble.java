
import java.util.Scanner;

/**
 * Copy-pasted duplicate code violates key software engineering principles necessary for ensuring
 * readability and maintainability of code, however this is a homework assignment that 
 * I will never look at again, so duplicating my code was the correct choice here.
 */
public class DoubleTrouble {
    
    private static char[][] board;
    
    private static int[][][][] pathToVisited = new int[100][100][100][100];

    // Queue
    private static int queueStart = 0;
    private static int queueEnd = 0;
    private static GameState[] queue = new GameState[1000000];
    
    private static int BFS(GameState start) {

        queue[queueEnd++] = start;
        pathToVisited[start.x1][start.y1][start.x2][start.y2] = start.depth;

        while(queueEnd - queueStart > -1 && queue[queueStart] != null) {

            GameState game = queue[queueStart++];
            GameState[] children = new GameState[4];
            int i = 0;
            
            // south
            if (game.x1 + 1 < board.length && game.x2 + 1 < board.length) {
                int newX1 = game.x1;
                int newX2 = game.x2;
                if (board[game.x1 + 1][game.y1] != 'x') {
                    newX1 = game.x1 + 1;
                }
                if (board[game.x2 + 1][game.y2] != 'x') {
                    newX2 = game.x2 + 1;
                }
                if (pathToVisited[newX1][game.y1][newX2][game.y2] == -1 && (newX1 != newX2 || game.y1 != game.y2)) {
                    children[i++] = new GameState(newX1, game.y1, newX2, game.y2, game.depth + 1);
                    pathToVisited[newX1][game.y1][newX2][game.y2] = game.depth + 1;
                }
            } else if(game.x1 + 1 >= board.length && game.x2 + 1 >= board.length){
                // done
                return game.depth + 1;
            }

            // north
            if(game.x1 - 1 >= 0 && game.x2 - 1 >= 0) {
                int newX1 = game.x1;
                int newX2 = game.x2;
                if(board[game.x1 - 1][game.y1] != 'x') {
                    newX1 = game.x1 - 1;
                }
                if(board[game.x2 - 1][game.y2] != 'x') {
                    newX2 = game.x2 - 1;
                }
                if(pathToVisited[newX1][game.y1][newX2][game.y2] == -1 && (newX1 != newX2 || game.y1 != game.y2)) {
                    children[i++] = new GameState(newX1, game.y1, newX2, game.y2, game.depth + 1);
                    pathToVisited[newX1][game.y1][newX2][game.y2] = game.depth + 1;
                }
            } else if(game.x1 - 1 < 0 && game.x2 - 1 < 0){
                // done
                return game.depth + 1;
            }

            // west
            if(game.y1 - 1 >= 0 && game.y2 - 1 >= 0) {
                int newY1 = game.y1;
                int newY2 = game.y2;
                if(board[game.x1][game.y1 - 1] != 'x') {
                    newY1 = game.y1 - 1;
                }
                if(board[game.x2][game.y2 - 1] != 'x') {
                    newY2 = game.y2 - 1;
                }
                if(pathToVisited[game.x1][newY1][game.x2][newY2] == -1 && (newY1 != newY2 || game.x1 != game.x2)) {
                    children[i++] = new GameState(game.x1, newY1, game.x2, newY2, game.depth + 1);
                    pathToVisited[game.x1][newY1][game.x2][newY2] = game.depth + 1;
                }
            } else if(game.y1 - 1 < 0 && game.y2 - 1 < 0){
                // done
                return game.depth + 1;
            }

            // east
            if(game.y1 + 1 < board[0].length && game.y2 + 1 < board[0].length) {
                int newY1 = game.y1;
                int newY2 = game.y2;
                if(board[game.x1][game.y1 + 1] != 'x') {
                    newY1 = game.y1 + 1;
                }
                if(board[game.x2][game.y2 + 1] != 'x') {
                    newY2 = game.y2 + 1;
                }
                if(pathToVisited[game.x1][newY1][game.x2][newY2] == -1 && (newY1 != newY2 || game.x1 != game.x2)) {
                    children[i++] = new GameState(game.x1, newY1, game.x2, newY2, game.depth + 1);
                    pathToVisited[game.x1][newY1][game.x2][newY2] = game.depth + 1;
                }
            } else if(game.y1 + 1 >= board[0].length && game.y2 + 1 >= board[0].length){
                // done
                return game.depth + 1;
            }
            
            // Add children to the queue
            for (GameState next : children) {
                if(next != null) {
                    queue[queueEnd++] = next;
                }
            }
        }
        
        // Whoops, every possibility checked, and no solution!
        return -1;
    }
    
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        
        int height = scanner.nextInt();
        int width = scanner.nextInt();
        board = new char[height][width];
        scanner.nextLine();

        int x1 = 0;
        int y1 = 0;
        int x2 = 0;
        int y2 = 0;
        for (int i = 0; i < height; i++) {
            String buffer = scanner.nextLine();
            for (int j = 0; j < width; j++) {
                char c = buffer.charAt(j);
                board[i][j] = c;
                if(c == '1') {
                    x1 = i;
                    y1 = j;
                } else if(c == '2') {
                    x2 = i;
                    y2 = j;
                }
            }
        }

        // Fill in this array with -1's (this is O((nm)^2) because the array is fixed at the max dimension)
        for (int i = 0; i < pathToVisited.length; i++) {
            for (int j = 0; j < pathToVisited.length; j++) {
                for (int k = 0; k < pathToVisited.length; k++) {
                    for (int l = 0; l < pathToVisited.length; l++) {
                        pathToVisited[i][j][k][l] = -1;
                    }
                }
            }
        }

        // Start the algorithm at the initial state
        int result = BFS(new GameState(x1, y1, x2, y2, 0));
        if(result != -1) {
            System.out.println(result);
        } else {
            System.out.println("STUCK");
        }
    }
    
}

class GameState {

    int x1;
    int y1;
    int x2;
    int y2;

    int depth = 0;

    public GameState(int x1, int y1, int x2, int y2, int depth) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.depth = depth;
    }
}
