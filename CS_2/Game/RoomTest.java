import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    @Test
    void addPOI() {
        Room newRoom = new Room("New Room");
        String[] tempTextArray = new String[5];
        PointOfInterest nextRoom = new PointOfInterest("next room", tempTextArray, 0, 1, 5, 5);
        newRoom.addPOI(nextRoom);
        assertTrue(newRoom.getPoints().get(0).isPreviousCompleted());

    }

    @Test
    void shouldDoorBeLocked() {
        Room newRoom = new Room("New Room");
        String[] tempTextArray = new String[5];
        PointOfInterest nextRoom = new PointOfInterest("next room", tempTextArray, 0, 1, 5, 5);
        PointOfInterest thirdRoom = new PointOfInterest("third room", tempTextArray, 0, 1, 5, 5);
        newRoom.addPOI(nextRoom);
        newRoom.addPOI(thirdRoom);
        assertTrue(newRoom.getPoints().get(0).isPreviousCompleted());
        assertTrue(newRoom.getExit().isLocked());
        nextRoom.setCompleted(true);
        thirdRoom.setCompleted(true);
        newRoom.shouldDoorBeLocked();
        assertFalse(newRoom.getExit().isLocked());
    }

    @Test
    void nextRoom() {
        Room newRoom = new Room("New Room");
        Room destination = new Room("destination");
        newRoom.setExit(destination);
        newRoom.getPlayer().move(5,7);
        newRoom.getExit().unlock();
        assertEquals(newRoom.nextRoom(), destination);
    }


}