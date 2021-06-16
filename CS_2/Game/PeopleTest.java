import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PeopleTest {

    @Test
    void accuratelyMoves() {
        People newPeople = new People();
        assertEquals(0, newPeople.getX());
        assertEquals(0, newPeople.getY());
        newPeople.move(10, 25);
        assertEquals(10, newPeople.getX());
        assertEquals(25, newPeople.getY());
    }

}