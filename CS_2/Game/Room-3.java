import java.util.ArrayList;
import java.util.List;

public class Room {
    private String name;
    private People player;
    private List<PointOfInterest> points;
    private Exit exit;



    /**
     * Returns the exit object of the room
     */
    public  Exit getExit() {
        return exit;
    }

    /**
     * Returns the player object of the room
     */
    public People getPlayer() {
        return player;
    }

    /**
     * Constructs a new room object
     * @param nameOfRoom A string containing the name of the room
     */
    public Room(String nameOfRoom){
        this.name = nameOfRoom;
        this.exit = new Exit();
        this.player = new People();
        this.points = new ArrayList<>();

    }

    /** Getter for room name */
    public String getName() {
        return name;
    }

    /**
     * Returns an array list containing the point of interest objects in the room
     */
    public List<PointOfInterest> getPoints() {
        return points;
    }

    /**
     * Adds a new point of interest to the room
     * @param p The point of interest that is being added to the room
     */
    public void addPOI(PointOfInterest p){
        points.add(p);
        if (points.size() == 1){
            points.get(0).setPreviousCompleted(true);
        }
    }


    /**
     * Figures out of if the exit of the room should be locked or not.
     * If it should not be locked the exit is unlocked, otherwise it stays locked.
     */
    public void shouldDoorBeLocked(){
        boolean shouldBeLocked = false;
        if(exit.isLocked()){
            for (PointOfInterest p : points){
                if(!p.isCompleted()){
                    shouldBeLocked = true;
                }
            }
        }
        if(!shouldBeLocked){
            exit.unlock();
        }
    }


    /**
     * Returns the room that the exit of the current room leads to
     */
    public Room nextRoom(){
        if(player.getX() == 5 && player.getY() == 7){
            if(exit.exitRoom()){
                return exit.getDest();
            }
        }
        return this;
    }

    /**
     * Sets where the exit of the room leads to
     * @param destination The destination of the exit of the room
     */
    public void setExit(Room destination){
        exit.setDestination(destination);
    }



    @Override
    public String toString() {
        return name;
    }
}
