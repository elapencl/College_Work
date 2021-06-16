import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;


public class TwoSum {

    public static void main(String[] args) throws FileNotFoundException{

/**
 * reads file
 */
        Scanner readin = new Scanner(new File("./src/dataAlgo.txt"));

        int row = readin.nextInt();
        int col = readin.nextInt();

        int[][] matrix = new int[row][col];
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                matrix[i][j] = readin.nextInt();
            }
        }
        for(int i=0; i<row; i++){ // check sum for rows
            check2Sum(matrix[i]);
        }
    }

    private static void check2Sum(int[] array) {
        int len = array.length;
        HashMap<Integer, Integer> twoSumMap = new HashMap<Integer, Integer>();
        boolean getZero = false;

        for(int i=0; i<len; i++){
            if(twoSumMap.containsKey(-array[i])){
                System.out.print((twoSumMap.get(-array[i])+1) + " " + (i+1));
                getZero = true;
                i=len;
            } else {
                twoSumMap.put(array[i], i);
            }
        }
        if(getZero == false) System.out.print(-1);
        System.out.println();
    }
}