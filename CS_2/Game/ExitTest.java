import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExitTest {

    @Test
        //test if its locked, then unlock it, then test if it is unlocked
        //essentially see if door has locking and unlocking capability
    void unlock() {
        Exit exit = new Exit();
        assertTrue(exit.isLocked());
        //now lets test to see if unlocking thw door actually works
        exit.unlock();
        assertFalse(exit.isLocked());

    }

    @Test
    void getX() {
        Exit exit = new Exit();
        assertEquals(5, exit.getX());
    }

    @Test
    void getY() {
        Exit exit = new Exit();
        assertEquals(7, exit.getY());

    }
}