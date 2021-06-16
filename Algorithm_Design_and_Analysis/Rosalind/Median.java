import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Median {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner list = new Scanner(new File("./src/dataAlgo.txt"));

        int size = list.nextInt();
        int [] a = new int[size];

        for (int i = 0; i < a.length; i++) {
            a[i] = list.nextInt();
        }

        int nth = list.nextInt();
        int [] count = new int[1000];

        for (int i = 0; i < a.length-1; i++){
            int store = i;
            for (int j = i+1; j < a.length; j++)
                if (a[j] < a[store])
                    store = j;

            int temp = a[store];
            a[store] = a[i];
            a[i] = temp;
        }
        StdOut.print(a[nth-1]);
    }
}
