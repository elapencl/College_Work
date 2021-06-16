import java.lang.reflect.Array;
import java.util.ArrayList;
import java.io.*;

public class Brute implements ScrabbleAI {

    /**
     * When exchanging, always exchange everything.
     */
    private static final boolean[] ALL_TILES = {true, true, true, true, true, true, true};

    /**
     * The GateKeeper through which this Incrementalist accesses the Board.
     */
    private GateKeeper gateKeeper;
    private ArrayList<String> combinations = new ArrayList<String>();
    private ArrayList<String> permutations = new ArrayList<String>();


    @Override
    public void setGateKeeper(GateKeeper gateKeeper) {
        this.gateKeeper = gateKeeper;
    }

    @Override
    public ScrabbleMove chooseMove() {
        if (gateKeeper.getSquare(Location.CENTER) == Board.DOUBLE_WORD_SCORE) {
            return findTwoTileMove();
        }
        return ourMove();
    }

    public class Permutation {
        /**
         * permutation function
         *
         * @param str string to calculate permutation for
         * @param l   starting index
         * @param r   end index
         */
        private void permute(String str, int l, int r, char letter) {
            if (l == r) {

                char[] permuteString = str.toCharArray();
                for(int i = 0; i < permuteString.length; i++){

                    if(permuteString[i] == letter){
                        permuteString[i] = ' ';
                        break;
                    }
                }

                str = "";
                for(int j = 0; j < permuteString.length; j++){
                    str += permuteString[j];
                }

                permutations.add(str);
            } else {
                for (int i = l; i <= r; i++) {
                    str = swap(str, l, i);
                    permute(str, l + 1, r, letter);
                    str = swap(str, l, i);
                }
            }
        }

        /**
         * Swap Characters at position
         *
         * @param a string value
         * @param i position 1
         * @param j position 2
         * @return swapped string
         */
        public String swap(String a, int i, int j) {
            char temp;
            char[] charArray = a.toCharArray();
            temp = charArray[i];
            charArray[i] = charArray[j];
            charArray[j] = temp;
            return String.valueOf(charArray);
        }

    }

    void combinationUtil(char arr[], char data[], int start, int end, int index, int r, char letter) {
        if (index == r) {
            String string = "";
            for (int j = 0; j < r; j++) {
                string += data[j];
            }
            string += letter;
            if(string.length() > 1) {
                combinations.add(string);
            }
            return;
        }

        for (int i = start; i <= end && end - i + 1 >= r - index; i++) {
            data[index] = arr[i];
            combinationUtil(arr, data, i + 1, end, index + 1, r, letter);
        }
    }

    void printCombination(char arr[], int n, int r, char letter) {
        char data[] = new char[r];
        combinationUtil(arr, data, 0, n - 1, 0, r, letter);
    }


    /**
     * Returns a ScrabbleMove
     */
    private ScrabbleMove ourMove(){

        long startTime = System.currentTimeMillis();

        ArrayList<Character> hand = gateKeeper.getHand();
        String bestWord = "";
        int bestScore = -1;
        Location bestLocation = new Location(-1,-1);
        Location bestDirection = new Location(-1, -1);

        for(int row = 0; row < Board.WIDTH; row++) {
            for (int col = 0; col < Board.WIDTH; col++) {

                long elapsedTime = System.currentTimeMillis() - startTime;
                long elapsedSeconds = elapsedTime / 1000;
                if (elapsedSeconds > 30){
                    if(bestWord != null && bestWord != ""){
                        return new PlayWord(bestWord, bestLocation, bestDirection);
                    }
                    else {
                        return findOneTileMove();
                    }
                }

                Location location = new Location(row, col);

                //ASCII CHECK IF THERE IS A LETTER HERE
                if ((gateKeeper.getSquare(location) > 64 && gateKeeper.getSquare(location) < 91) || (
                        (gateKeeper.getSquare(location) > 96 && gateKeeper.getSquare(location) < 123))) {
                    //normal letter (lowercase)
                    if ((gateKeeper.getSquare(location) > 96 && gateKeeper.getSquare(location) < 123)) {

                        //vertical
                        Location locationVU = new Location(0,0) ;
                        Location locationVD = new Location(0,0) ;

                        int rowV = location.getRow();
                        int columnV = location.getColumn();
                        if (rowV != 0) {
                            locationVU = new Location(rowV - 1, columnV);
                        }
                        else{
                            locationVU = new Location(rowV, columnV);
                        }

                        if (rowV != 14){
                            locationVD = new Location(rowV+1, columnV);

                        }
                        else{
                            locationVD = new Location(rowV, columnV);

                        }

                        if((gateKeeper.getSquare(locationVU) < 96 || gateKeeper.getSquare(locationVU) > 123) &&
                                (gateKeeper.getSquare(locationVD) < 96 || gateKeeper.getSquare(locationVD) > 123)) {
                            Location direction1 = new Location(1, 0);
                            String wordScore1[] = findMove(gateKeeper.getSquare(location), direction1, location);
                            String bestWord1 = wordScore1[0];
                            int bestScore1 = Integer.parseInt(wordScore1[1]);

                            if (bestScore < bestScore1) {
                                bestScore = bestScore1;
                                bestWord = bestWord1;
                                bestLocation = new Location(Integer.parseInt(wordScore1[2]), Integer.parseInt(wordScore1[3]));
                                bestDirection = direction1;
                            }
                        }

                        //horizontal
                        int rowH = location.getRow();
                        int columnH = location.getColumn();
                        Location locationHL = new Location(0,0);
                        Location locationHR = new Location(0,0);


                        if (columnH != 0) {
                            locationHL = new Location(rowH, columnH - 1);
                        }
                        else{
                            locationHL = new Location(rowH, columnH);
                        }

                        if (columnH != 14){
                            locationHR  = new Location(rowH, columnH+1);
                        }
                        else {
                            locationHR  = new Location(rowH, columnH);

                        }

                        if((gateKeeper.getSquare(locationHL) < 96 || gateKeeper.getSquare(locationHL) > 123) &&
                                (gateKeeper.getSquare(locationHR) < 96 || gateKeeper.getSquare(locationHR) > 123)) {
                            Location direction2 = new Location(0, 1);
                            String wordScore2[] = findMove(gateKeeper.getSquare(location), direction2, location);
                            String bestWord2 = wordScore2[0];
                            int bestScore2 = Integer.parseInt(wordScore2[1]);

                            if (bestScore < bestScore2) {
                                bestScore = bestScore2;
                                bestWord = bestWord2;
                                bestLocation = new Location(Integer.parseInt(wordScore2[2]), Integer.parseInt(wordScore2[3]));
                                bestDirection = direction2;
                            }
                        }

                    }

                }
            }
        }

        if (bestWord == null || bestWord == "") {
            return findOneTileMove();
        }

        return new PlayWord(bestWord, bestLocation, bestDirection);
    }

    /**
     * Returns a String[] containing the best word, score, and the placement location
     */
    private String[] findMove(char letter, Location direction, Location location) {
        permutations.clear();
        String[] wordScore = new String[4];
        ArrayList<Character> hand = gateKeeper.getHand();
        String bestWord = null;
        int bestScore = -1;

        char[] set = new char[7];
        int k = 2;

        for (int i = 0; i < hand.size(); i++) {

            char a = hand.get(i);

            if (a == '_') {
                a = 'E';
            }
            set[i] = a;
        }

        char arr[] = set;
        int n = arr.length;

        for (int r = arr.length; r > 0; r--) {
            printCombination(arr, n, r, letter);
        }

        for (int i = 0; i < combinations.size(); i++) {
            String str = combinations.get(i);
            int m = str.length();
            Permutation permutation = new Permutation();
            permutation.permute(str, 0, m - 1,letter);
        }

        Location actualLocation = new Location(-1,-1);
        Location bestLocation = new Location(-1,-1);
        int row = location.getRow();
        int col = location.getColumn();

        for (int i = 0; i < permutations.size(); i++) {
            try {

                String word = permutations.get(i);
                char[] word2 = word.toCharArray();

                for(int j = 0; j < word2.length; j++){
                    if(word2[j] == ' '){

                        if (direction.equals(Location.HORIZONTAL)) {
                            actualLocation = new Location(row,col - j);
                        }
                        else if(direction.equals(Location.VERTICAL)){
                            actualLocation = new Location(row - j,col);
                        }
                    }
                }

                gateKeeper.verifyLegality(word,actualLocation , direction);
                int score = gateKeeper.score(word, actualLocation, direction);

                if (score > bestScore) {
                    bestScore = score;
                    bestWord = word;
                    bestLocation = actualLocation;

                    if (bestScore > 10 && bestWord.length() > 1 ) {
                        wordScore[0] = bestWord;
                        wordScore[1] = Integer.toString(bestScore);
                        wordScore[2] = Integer.toString(bestLocation.getRow());
                        wordScore[3] = Integer.toString(bestLocation.getColumn());

                        return wordScore;
                    }
                }

            } catch (IllegalMoveException e) {
                // It wasn't legal; go on to the next one
            }
        }

        wordScore[0] = bestWord;
        wordScore[1] = Integer.toString(bestScore);
        wordScore[2] = Integer.toString(bestLocation.getRow());
        wordScore[3] = Integer.toString(bestLocation.getColumn());

        return wordScore;
    }

    //same method in incrementalist
    private ScrabbleMove findTwoTileMove() {
        ArrayList<Character> hand = gateKeeper.getHand();
        String bestWord = null;
        int bestScore = -1;
        for (int i = 0; i < hand.size(); i++) {
            for (int j = 0; j < hand.size(); j++) {
                if (i != j) {
                    try {
                        char a = hand.get(i);
                        if (a == '_') {
                            a = 'E';
                        }
                        char b = hand.get(j);
                        if (b == '_') {
                            b = 'E';
                        }
                        String word = "" + a + b;
                        gateKeeper.verifyLegality(word, Location.CENTER, Location.HORIZONTAL);
                        int score = gateKeeper.score(word, Location.CENTER, Location.HORIZONTAL);
                        if (score > bestScore) {
                            bestScore = score;
                            bestWord = word;
                        }
                    } catch (IllegalMoveException e) {
                        // It wasn't legal; go on to the next one
                    }
                }
            }
        }
        if (bestScore > -1) {
            return new PlayWord(bestWord, Location.CENTER, Location.HORIZONTAL);
        }
        return new ExchangeTiles(ALL_TILES);
    }

    //same method in incrementalist
    private ScrabbleMove findOneTileMove() {
        ArrayList<Character> hand = gateKeeper.getHand();
        PlayWord bestMove = null;
        int bestScore = -1;
        for (int i = 0; i < hand.size(); i++) {
            char c = hand.get(i);
            if (c == '_') {
                c = 'E'; // This could be improved slightly by trying all possibilities for the blank
            }
            for (String word : new String[] {c + " ", " " + c}) {
                for (int row = 0; row < Board.WIDTH; row++) {
                    for (int col = 0; col < Board.WIDTH; col++) {
                        Location location = new Location(row, col);
                        for (Location direction : new Location[] {Location.HORIZONTAL, Location.VERTICAL}) {
                            try {
                                gateKeeper.verifyLegality(word, location, direction);
                                int score = gateKeeper.score(word, location, direction);
                                if (score > bestScore) {
                                    bestScore = score;
                                    bestMove = new PlayWord(word, location, direction);
                                }
                            } catch (IllegalMoveException e) {
                                // It wasn't legal; go on to the next one
                            }
                        }
                    }
                }
            }
        }
        if (bestMove != null) {
            return bestMove;
        }
        return new ExchangeTiles(ALL_TILES);
    }
}