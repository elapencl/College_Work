import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

public class DegreeArrayGraph {

    public static void main(String[] args) throws FileNotFoundException {

        ArrayList<ArrayList<Integer>> neighbors = new ArrayList<ArrayList<Integer>>();

        Scanner list = new Scanner(new File("./src/dataAlgo.txt"));

        int vertices = list.nextInt();
        int edges = list.nextInt();          // if not read, v1 is going to read the number of the edge; so this has to be there despite not being called on

        for(int i = 0; i <= vertices; i++){
            ArrayList<Integer> temp = new ArrayList<Integer>();
            neighbors.add(temp);
        }

        while(list.hasNext()) {
            int vertexOne = list.nextInt();
            int vertexTwo = list.nextInt();

            neighbors.get(vertexOne).add(vertexTwo);
            neighbors.get(vertexTwo).add(vertexOne);
        }


        for(int i = 1; i <= vertices; i++){
            System.out.print(neighbors.get(i).size() +" ");
        }

    }
}