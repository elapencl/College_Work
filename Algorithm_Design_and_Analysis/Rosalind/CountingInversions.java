import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CountingInversions {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner scan = new Scanner(new File("./src/dataAlgo.txt"));

        int size = scan.nextInt();
        int [] a = new int[size];
        long count = 0;

        for (int i = 0; i < a.length; i++) {
            a[i] = scan.nextInt();
        }

        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if(a[i] > a[j]){
                    count++;
                }
            }
        }

        StdOut.print(count);

    }
}

