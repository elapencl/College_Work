public class Exit {
    //position of the exit in room
    private double x;
    private double y;
    //The room the exit leads to
    private Room dest;
    //True if exit is locked
    private boolean locked;
    //Text array containing text for when player is at exit
    private String[] text;

    /** Exit constructor. */
    public Exit() {
        this.x = 5;
        this.y = 7;
        this.locked = true;
    }

    /** Setter for dest */
    public void setDestination(Room dest){
        this.dest = dest;
    }

    /** Called in the nextRoom method in Room class. Returns false (you can't exit room) when door is locked. */
    public boolean exitRoom(){
        if(locked){
            return false;
        }
        else{
            return true;
        }
    }
    /** Unlocks door. */
    public void unlock(){
        this.locked = false;
    }
    /** Getter for locked */
    public boolean isLocked() {
        return locked;
    }

    /** Getter for destination */
    public Room getDest() {
        return dest;
    }
    /** Getter for x position */
    public double getX() {
        return x;
    }

    /** Getter for y position */
    public double getY() {
        return y;
    }

    /** Getter for text array */
    public String[] getText() {
        return text;
    }

    /** Setter for text array */
    public void setText(String[] text) {
        this.text = text;
    }

    /** toString override */
    @Override
    public String toString() {
        return dest.toString();
    }
}
