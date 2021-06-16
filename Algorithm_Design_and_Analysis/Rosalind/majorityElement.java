import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class majorityElement {

    public static void main(String[] args) throws FileNotFoundException{

        Scanner scan = new Scanner(new File("./src/dataAlgo.txt"));

        int row = scan.nextInt();
        int col = scan.nextInt();

        int[][] matrix = new int[row][col];

        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                matrix[i][j] = scan.nextInt();
            }
        }
        int[] maj = new int[row];
        for(int i=0; i<row; i++){
            maj[i] = majority(matrix[i]);
        }
        for(int i=0; i<row; i++){
            System.out.print(maj[i] + " ");
        }
    }

    private static int majority(int[] array) {
        int Len = array.length;
        HashMap<Integer, Integer> countMap = new HashMap<Integer, Integer>();
        for(int i=0; i<Len; i++){
            if(countMap.containsKey(array[i])){
                int count = countMap.get(array[i]) + 1;
                if(count > Len/2){
                    return array[i];
                } else {
                    countMap.put(array[i], count);
                }
            } else {
                countMap.put(array[i], 1);
            }
        }
        return -1;
    }

}