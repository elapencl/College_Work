import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class threeSum {

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

        for(int i=0; i<row; i++){
            check3Sum(matrix[i]);

        }

    }

    private static void check3Sum(int[] array) {
        int len = array.length;
        HashMap<Integer, Integer> indexMap = new HashMap<Integer, Integer>();
        for(int i=0; i<len; i++){
            indexMap.put(array[i], i);
        }

        Arrays.sort(array);

        for(int i=0; i<len-2; i++){
            int p2 = i+1;
            int p3 = len-1;
            while(p2!=p3){
                int sum = array[p2] + array[p3];
                if(array[i] == -sum){
                    printOrder((indexMap.get(array[i])+1), (indexMap.get(array[p2])+1), (indexMap.get(array[p3]) +1));
                    return;
                } else if(array[i] < -sum){
                    p2++;
                } else {
                    p3--;
                }
            }

        }

        System.out.println(-1);
    }

    private static void printOrder(int a, int b, int c) {
        int m=0;
        if(a>b) {
            m = a;
            a = b;
            b = m;
        }

        if(a>c){
            m = a;
            a = c;
            c = m;
        }

        if(b>c){
            m = b;
            b = c;
            c = m;
        }

        System.out.println(a + " " + b +" " +c);
    }

}