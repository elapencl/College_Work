import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class connectedComponents {

    public static void main(String[] args) throws FileNotFoundException{

        Scanner scan = new Scanner(new File("./src/dataAlgo.txt"));

        int vertice = scan.nextInt();
        int edge = scan.nextInt();

        ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();  //creates graph
        int[] vert = new int[vertice+1];
        for(int i=0; i<=vertice; i++){
            vert[i] = 0;
            ArrayList<Integer> empty = new ArrayList<Integer>();
            graph.add(empty);
        }

        for(int i=0; i<edge; i++){
            int vertexOne = scan.nextInt();
            int vertexTwo = scan.nextInt();
            graph.get(vertexOne).add(vertexTwo);
            graph.get(vertexTwo).add(vertexOne);
        }

        int group = 0;
        for(int i=1; i<=vertice; i++){
            if(vert[i] == 0) {
                group++;
                group(i, graph, vert);
            }
        }
        System.out.println(group);
    }

    private static void group(int n, ArrayList<ArrayList<Integer>> graph, int[] vert) {
        vert[n] = 1;
        int neighbors = graph.get(n).size();
        for(int i=0; i<neighbors; i++){
            int v = graph.get(n).get(i);
            if(vert[v] == 0){
                group(v, graph, vert);
            }
        }
    }

}