import java.awt.*;



 /** * In this class we perform all of the functions that are necessary for a Lines of Action game. 
 * We use a series of pair objects to define the locations of the various checkers on the grid (similar to the location objects).
 * This class is quite long because it contains all of the necessary methods for proper play,
 * which allows the LinesOfAction class to be free of extraneous methods, information.
 */

public class Board {

     /**
      *      * boolean hasWon is always false until end of game.
      *      
      */


     /**
      *      * Initializes move to black by default for first move
      *      
      */

     /**
      *      * winner is empty string until end of game
      *      
      */
    private Pair[][] grid;
    private boolean revealPossiblePlays = false;
    private String move = "black"; //begin with black for the first move
    private boolean hasWon = false; //initialize this boolean to false until the hasWon method detects a win
    private String winner = "";

    /**
     *      * Board constructor
     *      
     */


    Board() {
    //everything is initialized in the setGrid method
    }

    /**
     *      * grid getter.
     *      
     */


    public Pair[][] getGrid() {
        return this.grid;
    }

    /**
     *      * move setter
     *      
     */


    private void setmove(String move) {
        this.move = move;
    }

    /**
     *      * Getter for move
     *      
     */


    private String getmove() {
        return move;
    }

     /**
      *      * Setter for is revealPossiblePlays
      *      
      */

    public void setDisplayingPotentialMoves(boolean d) {
        revealPossiblePlays = d;
    }

     /**
      *      * Setter that allows the tester to implement their own versions of the board
      *      
      */


     public void setSquare(Pair square) {
         this.grid[square.getRow()][square.getCol()] = square;
     }

     /**
      *      * Setter for winner
      *      
      */

    public void setWinner(String winner) {
        this.winner = winner;
    }

    /**
     *      * Getter for winner
     *      
     */

    public String getWinner() {
        return this.winner;
    }

    /**
     *      * Sets revealPossiblePlays to false by default
     *      
     */


    /**
     *      * Getter for revealPossiblePlays
     *      
     */

    public boolean revealPossiblePlays() {
        return revealPossiblePlays;
    }

    /**
     *      * Setter for revealPossiblePlays
     *      
     */


    /**
     *   * if the use clicks on a piece, we toggle the legal move views and fill in the green squares accordingly
     *      * if the user clicks on a legal piece, we move to that square on the grid
     *      *explains why we have multiple draw grids throughout this method
     *      
     */


    public void update() {
        while (!hasWon()) {
            Pair location = getClick();
            if (location.getRow() >= 0 && location.getRow() < 8 && location.getCol() >= 0 && location.getCol() < 8) {
                if (getGrid()[location.getCol()][location.getRow()].getPiece().equals(move) && !revealPossiblePlays)
                    revealPossiblePlays = true;
                if (revealPossiblePlays) {
                    moveLegalizer(location);
                    drawGrid();
                    Pair current = getClick();
                    if (current.getRow() >= 0 && current.getCol() >= 0) {
                        while (!getGrid()[current.getCol()][current.getRow()].isLegalMove() && !current.equals(location)) {
                            current = getClick();
                        }
                    }

                    if (getGrid()[current.getCol()][current.getRow()].isLegalMove()) {
                        getGrid()[current.getCol()][current.getRow()].setPiece(move);
                        getGrid()[location.getCol()][location.getRow()].setPiece("empty");
                        if (getmove().equals("red")) {
                            setmove("black");
                        }
                        else  {
                            setmove("red");
                        }
                    }
                    if (current.equals(location)) {
                        setDisplayingPotentialMoves(false);
                    }
                }
                setDisplayingPotentialMoves(false);
                drawGrid();
                if (hasWon()) {
                    drawGrid();
                }
                for (int r = 0; r < 8; r++) {
                    for (int c = 0; c < 8; c++) {
                        getGrid()[r][c].setLegalMove(false);
                    }
                }
            }
        }
        drawGrid();

    }


    /**
     *      * the board is an array of pairs where each pair (minus the ones on the peripheries) have exactly eight neighbors
     *      * we look at all of the neighbors of the same color
     *      * then check if any squares left unchecked
     *      
     */

    public boolean hasWon() {
        boolean canCheckMoreReds = true; //allows one depth-first hasWon() search per game-event per player
        boolean canCheckMoreBlacks = true;
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if (!getGrid()[r][c].hasBeenChecked() && !getGrid()[r][c].getPiece().equals("empty")) {
                    if (getGrid()[r][c].getPiece().equals("black") && canCheckMoreBlacks) {
                        canCheckMoreBlacks = false;
                        checkIfWon("black", getGrid()[r][c]);
                    } else if (grid[r][c].getPiece().equals("red") && canCheckMoreReds) {
                        canCheckMoreReds = false;
                        checkIfWon("red", getGrid()[r][c]);
                    }
                }
            }
        }


        boolean blackHasWon = true; //true by default, set false upon encountering unchecked square following checkIfWon()
        boolean redHasWon = true;

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if (!grid[r][c].getPiece().equals("empty") && !grid[r][c].hasBeenChecked())
                    if (grid[r][c].getPiece().equals("black")) blackHasWon = false;
                    else redHasWon = false;
            }
        }
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                grid[r][c].check(false);
            }
        }
        if (blackHasWon || redHasWon) {
            hasWon = true;
            if (blackHasWon && redHasWon) setWinner("tie");
            if (blackHasWon && !redHasWon) setWinner("black");
            if (!blackHasWon && redHasWon) setWinner("red");
            return true;
        } else return false;
    }



    /**
     *      * Set grid initializes the double array of pairs
     *      *call once at the beginning of each game
     *      
     */


    public void setGrid() {
        grid = new Pair[8][8];
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                getGrid()[r][c] = new Pair(r, c, "");
                if ((r == 0 || r == 7) && (c < 7 && c > 0)) {
                    getGrid()[r][c].setPiece("black");
                }
                else if ((c == 0 || c == 7) && (r < 7 && r > 0)) {
                    getGrid()[r][c].setPiece("red"); }
                else  {
                    getGrid()[r][c].setPiece("empty");
                }
            }
        }
    }

    /**
     *      * draw grid should be called at the end of each update call
     *      * and upon starting up the game
     *      
     */


    void drawGrid() {
        StdDraw.clear();
        if (!hasWon) {
            for (int r = 0; r < 8; r++) {
                for (int c = 0; c < 8; c++) {
                    if (getGrid()[r][c].isLegalMove() && revealPossiblePlays()) {
                        StdDraw.setPenColor(Color.green);
                        StdDraw.filledSquare(c, 7 - r, 0.5);
                        StdDraw.setPenColor(StdDraw.BLACK);
                    } else if ((r % 2 == 0 && c % 2 == 0) || (r % 2 == 1 && c % 2 == 1)) { //forms checkerboard pattern
                        StdDraw.setPenColor(StdDraw.GRAY);
                        StdDraw.filledSquare(c, 7 - r, 0.5);
                        StdDraw.setPenColor(StdDraw.BLACK);
                        StdDraw.square(c, 7 - r, 0.5);
                    }
                }
            }

            StdDraw.square(3.5, 3.5, 4);


            for (int q = 1; q <= 8; q++) {
                StdDraw.text(-0.9, -1 + q, String.valueOf(q));
            }
            for (int z = 97; z < 105; z++) {
                StdDraw.text(0 + z - 97, -0.9, Character.toString((char) z - 32));
            }
            for (int r = 0; r < 8; r++) {
                for (int c = 0; c < 8; c++) {
                    if (getGrid()[r][c].getPiece().equals("black")) {
                        StdDraw.filledCircle(c, 7 - r, .3);
                    }
                    StdDraw.setPenColor(Color.RED);
                    if (getGrid()[r][c].getPiece().equals("red")) StdDraw.filledCircle(c, 7 - r, .3);
                    StdDraw.setPenColor(Color.BLACK);
                }
            }
        }
        if (getmove().equals("black")) {
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.text(4.5, 7.7, "Black's turn");
        }
        if (getmove().equals("red")) {
            StdDraw.setPenColor(Color.red);
            StdDraw.text(4.5, 7.7, "Red's turn");
            StdDraw.setPenColor(Color.black);
        }

        if (hasWon) {
            StdDraw.clear();
            if (getWinner().equals("tie")) StdDraw.text(5, 5, "It's a tie!!");
            else StdDraw.text(5, 5, getWinner() + " wins!");
        }

        StdDraw.show();
    }


    /**
     *      * gets click
     *      *
     *      * @return location as a pair object with null getPiece() return value
     *      
     */


    public Pair getClick() {
        Pair location = new Pair();
        while (!StdDraw.isMousePressed()) {
        }//wait for mouse click;
        while (StdDraw.isMousePressed()) {
            location.setRow((int) (Math.round(StdDraw.mouseX())));
            location.setCol((int) (Math.round(7 - StdDraw.mouseY())));
        }
        if (location.getRow() >=0 && location.getCol() >= 0 && location.getCol() < 8 && location.getCol() <8) {
            return location;
        }
        return new Pair(0, 0, ""); //if the x and y coordinates are outside of the grid
    }
     /**
      *      * Recursive method used to determine if we have a connected group of squares
      *      
      */

     public void checkIfWon(String color, Pair location) {
         getGrid()[location.getRow()][location.getCol()].check(true);
         Pair[] neighbors = {new Pair(location.getRow() - 1, location.getCol() - 1, color),
                 new Pair(location.getRow() - 1, location.getCol(), color),
                 new Pair(location.getRow() - 1, location.getCol() + 1, color),
                 new Pair(location.getRow(), location.getCol() - 1, color),
                 new Pair(location.getRow(), location.getCol() + 1, color),
                 new Pair(location.getRow() + 1, location.getCol() - 1, color),
                 new Pair(location.getRow() + 1, location.getCol(), color),
                 new Pair(location.getRow() + 1, location.getCol() + 1, color)
         };
         for (int i = 0; i < 8; i++) {
             if (neighbors[i].getRow() >= 0 && neighbors[i].getRow() < 8 && neighbors[i].getCol() >= 0 && neighbors[i].getCol() < 8) {


                 if (grid[neighbors[i].getRow()][neighbors[i].getCol()].getPiece().equals(color) && !grid[neighbors[i].getRow()][neighbors[i].getCol()].hasBeenChecked()) {
                     checkIfWon(color, grid[neighbors[i].getRow()][neighbors[i].getCol()]); //same color, recursive call
                 }
             }
         }

     }
    /**
     *      * checks for valid moves in all directions, used for displaying and allowing moves
     *      
     */


    int findTopLeftDiagonalPieces(Pair location) {
        int pieces = 0;
        int x = location.getRow();
        int y = location.getCol();
        while (x < 7 && y < 7) {
            x++;
            y++;
        }
        while (x >= 0 && y >= 0) {
            if (!getGrid()[y][x].getPiece().equals("empty")) {
                pieces++;
            }
            x--;
            y--;
        }
        return pieces;
    }


    int findTopRightDiagonalPieces(Pair location) {
        int pieces = 0;
        int x = location.getRow();
        int y = location.getCol();
        while (x > 0 && y < 7) {
            x--;
            y++;
        }
        while (x <= 7 && y >= 0) {
            if (!getGrid()[y][x].getPiece().equals("empty")) {
                pieces++;
            }
            x++;
            y--;
        }
        return pieces;
        //this determines how many pieces are in any given line
    }


    int findVerticalPieces(Pair location) {
        int pieces = 0;
        for (int i = 0; i < 8; i++) {
            if (!getGrid()[i][location.getRow()].getPiece().equals("empty")) {
                pieces++;
            }
        }
        return pieces;
    }


    int findHorizontalPieces(Pair location) {
        int pieces = 0;
        for (int i = 0; i < 8; i++) {
            if (!getGrid()[location.getCol()][i].getPiece().equals("empty")) {
                pieces++;
            }
        }
        return pieces;
    }


    int isValidMoveLeft(Pair location) {
        String similar = getGrid()[location.getCol()][location.getRow()].getPiece();
        for (int i = 0; i < findHorizontalPieces(location); i++) {
            if (!(((location.getRow() - findHorizontalPieces(location)) >= 0) && (getGrid()[location.getCol()][location.getRow() - i].getPiece().equals(similar)
                    || getGrid()[location.getCol()][location.getRow() - i].getPiece().equals("empty"))
                    &&
                    !getGrid()[location.getCol()][location.getRow() - findHorizontalPieces(location)].getPiece().equals(similar)))
                return 0;
        }
        return findHorizontalPieces(location);
    }


    int isValidMoveRight(Pair location) {
        String similar = getGrid()[location.getCol()][location.getRow()].getPiece();
        for (int i = 0; i < findHorizontalPieces(location); i++) {
            if (!(((location.getRow() + findHorizontalPieces(location)) <= 7)
                    && (getGrid()[location.getCol()][location.getRow() + i].getPiece().equals(similar)
                    || getGrid()[location.getCol()][location.getRow() + i].getPiece().equals("empty"))
                    &&
                    !getGrid()[location.getCol()][location.getRow() + findHorizontalPieces(location)].getPiece().equals(similar)))
                return 0;
        }
        return findHorizontalPieces(location);
    }


    int isValidMoveDown(Pair location) {
        for (int i = 0; i < findVerticalPieces(location); i++) {
            if (!(((location.getCol() + findVerticalPieces(location)) <= 7)
                    &&
                    (getGrid()[location.getCol() + i][location.getRow()].getPiece().equals(getGrid()[location.getCol()][location.getRow()].getPiece())
                            || getGrid()[location.getCol() + i][location.getRow()].getPiece().equals("empty"))
                    &&
                    !getGrid()[location.getCol() + findVerticalPieces(location)][location.getRow()].getPiece().equals(getGrid()[location.getCol()][location.getRow()].getPiece())))
                return 0;
        }
        return findVerticalPieces(location);
    }


    int isValidMoveUp(Pair location) {
        String similar = getGrid()[location.getCol()][location.getRow()].getPiece();
        for (int i = 0; i < findVerticalPieces(location); i++) {
            if (!(((location.getCol() - findVerticalPieces(location)) >= 0)
                    && (getGrid()[location.getCol() - i][location.getRow()].getPiece().equals(similar)
                    || getGrid()[location.getCol() - i][location.getRow()].getPiece().equals("empty"))
                    &&
                    !getGrid()[location.getCol() - findVerticalPieces(location)][location.getRow()].getPiece().equals(similar)))
                return 0;
        }
        return findVerticalPieces(location);
    }


    int isValidMoveTopLeftDiagonally(Pair location) {
        String similar = getGrid()[location.getCol()][location.getRow()].getPiece();
        for (int i = 0; i < findTopLeftDiagonalPieces(location); i++) {
            if (!(((location.getRow() - findTopLeftDiagonalPieces(location)) >= 0 && (location.getCol() - findTopLeftDiagonalPieces(location)) >= 0)
                    && (getGrid()[location.getCol() - i][location.getRow() - i].getPiece().equals(similar)
                    || getGrid()[location.getCol() - i][location.getRow() - i].getPiece().equals("empty"))
                    &&
                    !getGrid()[location.getCol() - findTopLeftDiagonalPieces(location)][location.getRow() - findTopLeftDiagonalPieces(location)].getPiece().equals(similar)))
                return 0;
        }
        return findTopLeftDiagonalPieces(location);
    }


    int isValidMoveBottomRightDiagonally(Pair location) {
        for (int i = 0; i < findTopLeftDiagonalPieces(location); i++) {
            if (!(((location.getRow() + findTopLeftDiagonalPieces(location)) <= 7
                    && (location.getCol() + findTopLeftDiagonalPieces(location)) <= 7)
                    &&
                    (getGrid()[location.getCol() + i][location.getRow() + i].getPiece().equals(getGrid()[location.getCol()][location.getRow()].getPiece())
                            || getGrid()[location.getCol() + i][location.getRow() + i].getPiece().equals("empty"))
                    &&
                    !getGrid()[location.getCol() + findTopLeftDiagonalPieces(location)][location.getRow() + findTopLeftDiagonalPieces(location)].getPiece().equals(getGrid()[location.getCol()][location.getRow()].getPiece())))
                return 0;
        }
        return findTopLeftDiagonalPieces(location);
    }


    int isValidMoveTopRightDiagonally(Pair location) {
        for (int i = 0; i < findTopRightDiagonalPieces(location); i++) {
            if (!(((location.getRow() + findTopRightDiagonalPieces(location)) <= 7 && (location.getCol() - findTopRightDiagonalPieces(location)) >= 0)
                    &&
                    (getGrid()[location.getCol() - i][location.getRow() + i].getPiece().equals(getGrid()[location.getCol()][location.getRow()].getPiece())
                            || getGrid()[location.getCol() - i][location.getRow() + i].getPiece().equals("empty"))
                    &&
                    !getGrid()[location.getCol() - findTopRightDiagonalPieces(location)][location.getRow() + findTopRightDiagonalPieces(location)].getPiece().equals(getGrid()[location.getCol()][location.getRow()].getPiece())))
                return 0;
        }
        return findTopRightDiagonalPieces(location);
    }


    private int isValidMoveBottomLeftDiagonally(Pair location) {
        for (int i = 0; i < findTopRightDiagonalPieces(location); i++) {
            if (!(((location.getRow() - findTopRightDiagonalPieces(location)) >= 0 && (location.getCol() + findTopRightDiagonalPieces(location)) <= 7)
                    &&
                    (getGrid()[location.getCol() + i][location.getRow() - i].getPiece().equals(getGrid()[location.getCol()][location.getRow()].getPiece())
                            || getGrid()[location.getCol() + i][location.getRow() - i].getPiece().equals("empty"))
                    &&
                    !getGrid()[location.getCol() + findTopRightDiagonalPieces(location)][location.getRow() - findTopRightDiagonalPieces(location)].getPiece().equals(getGrid()[location.getCol()][location.getRow()].getPiece())))
                return 0;
        }
        return findTopRightDiagonalPieces(location);
    }

     /**
      *
      * we set all of the legal moves equal to true in this method that we can use to
      * display the associated green squares
      */
    public void moveLegalizer (Pair location) {
        if (isValidMoveUp(location) > 0) {
            getGrid()[location.getCol() - isValidMoveUp(location)][location.getRow()].setLegalMove(true);
        }

        if (isValidMoveDown(location) > 0) {
            getGrid()[location.getCol() + isValidMoveDown(location)][location.getRow()].setLegalMove(true);
        }

        if (isValidMoveLeft(location) > 0) {
            getGrid()[location.getCol()][location.getRow() - isValidMoveLeft(location)].setLegalMove(true);
        }

        if (isValidMoveRight(location) > 0) {
            getGrid()[location.getCol()][location.getRow() + isValidMoveRight(location)].setLegalMove(true);
        }

        if (isValidMoveTopLeftDiagonally(location) > 0) {
            getGrid()[location.getCol() - isValidMoveTopLeftDiagonally(location)][location.getRow() - isValidMoveTopLeftDiagonally(location)].setLegalMove(true);
        }

        if (isValidMoveTopRightDiagonally(location) > 0) {
            getGrid()[location.getCol() - isValidMoveTopRightDiagonally(location)][location.getRow() + isValidMoveTopRightDiagonally(location)].setLegalMove(true);
        }

        if (isValidMoveBottomLeftDiagonally(location) > 0) {
            getGrid()[location.getCol() + isValidMoveBottomLeftDiagonally(location)][location.getRow() - isValidMoveBottomLeftDiagonally(location)].setLegalMove(true);
        }

        if (isValidMoveBottomRightDiagonally(location) > 0) {
            getGrid()[location.getCol() + isValidMoveBottomRightDiagonally(location)][location.getRow() + isValidMoveBottomRightDiagonally(location)].setLegalMove(true);
        }


    }

}