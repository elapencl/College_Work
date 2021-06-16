public class People {
    private int x;
    private int y;

    /** People constructor. Starts position of player at 0,0 */
    public People() {
        this.x = 0;
        this.y = 0;
    }

    /** Moves player */
    public void move(int x, int y){
        this.x += x;
        this.y += y;
    }

    /** Getter for x*/
    public int getX() {
        return x;
    }

    /** Getter for y */
    public int getY() {
        return y;
    }

}
