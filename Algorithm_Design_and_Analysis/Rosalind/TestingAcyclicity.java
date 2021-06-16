import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestingAcyclicity {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner scan = new Scanner(new File("./src/dataAlgo.txt"));
        int numGraphs = scan.nextInt();

        for (int i = 0; i < numGraphs; i++) {

            int vertices = scan.nextInt();
            int edges = scan.nextInt();

            boolean isCycle = false;
            boolean isConnected[][] = new boolean[vertices][vertices];

            for (int j = 0; j < edges; j++) {
                int vertexOne = scan.nextInt() - 1;
                int vertexTwo = scan.nextInt() - 1;
                isConnected[vertexOne][vertexTwo] = true;
            }

            for (int j = 0; j < vertices; j++) {
                int visited[] = new int[vertices];
                dFS(isConnected, j, visited);
                if(visited[j] > 1){
                    isCycle = true;
                    break;
                }
            }


            if (isCycle) {
                StdOut.print("-1" + " ");
            } else {
                StdOut.print("1" + " ");
            }
        }
    }

    public static int[] dFS(boolean isConnected[][], int currVertex, int visited[]){

        if(visited[currVertex] >= 1){
            visited[currVertex]++;
            return visited;
        }
        visited[currVertex]++;
        for (int i = 0; i < isConnected[currVertex].length; i++) {
            if(isConnected[currVertex][i]){
                dFS(isConnected, i, visited);
            }
        }

        return visited;
    }
}