import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LineSegmentTest {

    @Test
    void gettersAndSettersWork() {
        LineSegment l= new LineSegment(new Vector(8, 5), new Vector(2,3));
        assertEquals(8, l.getA().getX());
        assertEquals(5, l.getA().getY());
        assertEquals(2, l.getB().getX());
        assertEquals(3, l.getB().getY());
        l.getA().setX(-2);
        l.getB().setY(10);
        assertEquals(-2, l.getA().getX());
        assertEquals(5, l.getA().getY());
        assertEquals(2, l.getB().getX());
        assertEquals(10, l.getB().getY());
    }
}