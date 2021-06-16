import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VectorTest {

    @Test
    void gettersAndSettersWork() {
        Vector v = new Vector(8.0, 5.0);
        assertEquals(8, v.getX(), 0.0001);
        assertEquals(5, v.getY(), 0.0001);
        v.setX(-2);
        v.setY(10);
        assertEquals(-2, v.getX(), 0.0001);
        assertEquals(10, v.getY(), 0.0001);
    }

    @Test
    void zeroArgumentConstructorWorks() {
        assertEquals("<0.0, 0.0>", new Vector().toString());
    }

    @Test
    void equalsWorks() {
        assertEquals(new Vector(1, 2), new Vector(1, 2));
        assertNotEquals(new Vector(7, 7), new Vector(7, 3));
    }

    @Test
    void addsCorrectly() {
        Vector a = new Vector(1, 2);
        Vector b = new Vector(4, 6);
        assertEquals("<5.0, 8.0>", a.plus(b).toString());
    }

    @Test
    void plusDoesNotModifyOriginalVectors() {
        Vector a = new Vector(1, 2);
        Vector b = new Vector(4, 6);
        assertEquals("<1.0, 2.0>", a.toString());
        assertEquals("<4.0, 6.0>", b.toString());
    }

    @Test
    void subtractsCorrectly() {
        Vector a = new Vector(1, 2);
        Vector b = new Vector(4, 6);
        assertEquals("<-3.0, -4.0>", a.minus(b).toString());
    }

    @Test
    void minusDoesNotModifyOriginalVectors() {
        Vector a = new Vector(1, 2);
        Vector b = new Vector(4, 6);
        assertEquals("<1.0, 2.0>", a.toString());
        assertEquals("<4.0, 6.0>", b.toString());
    }

    @Test
    void timesDoesNotModifyOriginalVectors() {
        Vector a = new Vector(1, 2);
        Vector b = new Vector(4, 6);
        assertEquals("<1.0, 2.0>", a.toString());
        assertEquals("<4.0, 6.0>", b.toString());
    }

    @Test
    void timesWorks(){
        Vector a = new Vector(1, 2);
        double c = 2;
        assertEquals("<2.0, 4.0>", a.times(c).toString());
    }

    @Test
    void dotDoesNotModifyOriginalVectors() {
        Vector a = new Vector(1, 2);
        Vector b = new Vector(4, 6);
        assertEquals("<1.0, 2.0>", a.toString());
        assertEquals("<4.0, 6.0>", b.toString());
    }

    @Test
    void dotCorrect(){
        Vector a = new Vector(1, 2);
        Vector b = new Vector(4, 6);
        assertEquals(16.0, a.dot(b));
    }

    @Test
    void distanceToCorrect(){
        Vector a = new Vector(1, 2);
        Vector b = new Vector(4, 6);
        assertEquals(5.0, a.distanceTo(b));
    }
}