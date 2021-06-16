import edu.princeton.cs.algs4.Quick;

public class PartialSort {
    public Comparable[] comp;

    public PartialSort(int i) {
        comp =new Comparable[i];
    }

    public static void main(String[] args) {
        In in = new In("dataAlgo.txt");
        int i=in.readInt();
        Median med = new Median(i);
        for(int j=0;j<i;j++) {
            med.comp[j]=in.readInt();
        }
        Quick.sort(med.comp);
        int k=in.readInt();
        for(int l=0;l<k;l++){
            StdOut.print(med.comp[l]+" ");
        }
    }
}
