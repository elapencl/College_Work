public class countingDNANucleotides {
    public static void main(String[] args) {

        In input = new In("data.txt");
        String text = input.readAll();
        char[] characters = text.toCharArray();
        char ch = 'a';
        int[] count = new int[26];

        int A=0;
        int C=0;
        int G=0;
        int T=0;


	for (int i = 0; i < characters.length; i++){ // counts occurence of each ( A, C, G, T)
                if(characters[i]=='A') A++;
                if(characters[i]=='C') C++;
                if(characters[i]=='G') G++;
                if(characters[i]=='T') T++;
            }

        StdOut.println(A + " " + C + " " + G + " " + T);
    }
}

