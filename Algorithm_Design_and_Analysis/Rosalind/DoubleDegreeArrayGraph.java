import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DoubleDegreeArrayGraph {

    public static void main(String[] args) throws FileNotFoundException {

        ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();

        Scanner readScan = new Scanner(new File("./src/dataAlgo.txt"));

        int vertex = readScan.nextInt();
        int edge = readScan.nextInt();

        for (int i = 0; i <= vertex; i++) {
            ArrayList<Integer> temp = new ArrayList<Integer>();
            graph.add(temp);
        }

        while (readScan.hasNext()) {

            int vertexOne = readScan.nextInt();
            int vertexTwo = readScan.nextInt();

            graph.get(vertexOne).add(vertexTwo);
            graph.get(vertexTwo).add(vertexOne);
        }

        readScan.close();

        for (int i = 1; i <= vertex; i++) {
            int degrees = 0;

            for (int j = 0; j < graph.get(i).size(); j++) {

                degrees += graph.get(graph.get(i).get(j)).size();

            }

            System.out.print(degrees + " ");
        }

    }

}