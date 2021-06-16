import java.util.Objects;

public class Pair {
    private int row;
    private int col;
    private String piece;
    private boolean hasBeenChecked;
    private boolean isLegalMove = false;

    /**
     * String piece tells us whether the current pair is empty or red or black
     * boolean hasBeenChecked lets us know whether we have already looked at a pair
     * in the hasWon method
     * boolean isLegalMove is always false if Board.isDisplayingPotetniaMoves is false
     * isLegalMove is only set true if the move is legal and Board.isDisplayingPotetniaMoves is true
     */

    /**
     * setter for piece
     */
    public void setPiece(String piece) {
        this.piece = piece;
    }

    /**
     * getter for piece
     */
    public String getPiece() {
        return piece;
    }

    /**
     *
     *Pair constructor for non-zer arguments
     */
    public Pair (int row, int col, String piece) {
        this.row = row;
        this.col = col;
        this.piece = piece;
    }

    /**
     * Pair constructor for zero arguments
     */
    public Pair () {
        this.row = 0;
        this.col = 0;
        this.piece = "empty";
    }

    /**
     * getter for Col
     */
    public int getCol() {
        return col;
    }
    /**
     * setter for Row
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     setter for Col
     */
    public void setCol(int col) {
        this.col = col;
    }

    /**
     * getter for Row
     */
    public int getRow() {
        return row;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair)) return false;
        Pair pair = (Pair) o;
        return getRow() == pair.getRow() &&
                getCol() == pair.getCol() &&
        getPiece().equals(pair.getPiece());
    }

    /**
     * hasCode that is used in tandem with the equals method
     */
    @Override
    public int hashCode() {
        return Objects.hash(getRow(), getCol());
    }

    /**
     * setter for hasBeenChecked
     */
    public void check(boolean hasBeenChecked) {
    this.hasBeenChecked = hasBeenChecked;
    }
    /**
     * setter for hasBeenChecked
     */
    public boolean hasBeenChecked() {
        return hasBeenChecked;
    }

    /**
     * setter for legalMove
     */
    public void setLegalMove(boolean legalMove) {
    isLegalMove = legalMove;
    }

    /**
     * getter for legalMove
     */
    public boolean isLegalMove() {
        return isLegalMove;
    }


}
