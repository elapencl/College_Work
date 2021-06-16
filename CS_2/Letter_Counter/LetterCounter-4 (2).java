import java.awt.*;

public class LetterCounter {
    public static void main(String[] args) {

        In input = new In("text.txt");
        String text = input.readLine();
        text = text.toLowerCase();
        char[] characters = text.toCharArray();
        char ch = 'a';
        int[] count = new int[26];
        double mheight = 0;

        StdDraw.setCanvasSize(500, 500);      // bar chart skeleton drawer / scaler
        StdDraw.setXscale(-5, 27);
        StdDraw.setYscale(-0.1, 1.1);
        StdDraw.rectangle(13, 0.5, 13, 0.5);
        StdDraw.text(13, 1.05, "Letter Distribution");
        StdDraw.text(-2.5, 0, "0");

        for (int z = 0; z < 26; z++) {         // counts the occurence of each letter
            for (int i = 0; i < characters.length; i++)
                if (ch == characters[i]) {
                    count[z]++;
                }
            StdOut.println("The letter " + ch + " can be found " + count[z] + " times ");
            ch++;
        }

        for (int z = 0; z < 26; z++) {      // finds the letter occuring the most
            if (count[z] > mheight) {
                mheight = count[z];
            }
        }

        int heightint = (int) mheight;         // to get rid of decimal (.0) at the end of the double
        String height = Integer.toString(heightint);  //converts integer to string (for max height on the graph)
        StdDraw.text(-2.5, 1, height);

        ch = 'a';                        // letters on the bottom below the bar chart
        double x = 0.5;
        for (int j = 0; j < 26; j++) {
            String chstring = Character.toString(ch);
            StdDraw.text(x, -0.05, chstring);
            ch++;
            x++;
        }

        x = 0.5;                                      // draw bars into the bar-chart
        for (int j = 0; j < 26; j++) {
            double y = count[j] / mheight / 2;
            StdDraw.setPenColor(Color.RED);
            StdDraw.filledRectangle(x, y, 0.4, y);
            StdDraw.setPenColor(Color.black);
            StdDraw.rectangle(x, y, 0.4, y);
            x++;
        }
    }
}

