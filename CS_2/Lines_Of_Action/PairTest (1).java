import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class PairTest {

    @Test
    void constructorWorks() {
        Pair loc = new Pair (8, 9, "");
        assertEquals(8, loc.getRow());
        assertEquals(9, loc.getCol());
        assertEquals("", loc.getPiece());
    }

    @Test
    void equalsWorks() {
        Pair loc = new Pair (2, 1, "pizza");
        Pair roc = new Pair (2, 1, "pizza");
        assertEquals(loc, roc);
    }

    @Test
    void isLegalMoveWorks() {
        Pair loc = new Pair(2, 1, "pizza");
        assertEquals(false, loc.isLegalMove());
    }

    @Test
    void setLegalMoveWorks() {
        Pair loc = new Pair(2, 1, "pizza");
        Pair roc = new Pair(2, 1, "red");
        loc.setLegalMove(true);
        assertTrue(loc.isLegalMove());
    }

    @Test
    void zeroConstructorWorks() {
        Pair loc = new Pair();
        assertEquals("empty", loc.getPiece());
        assertEquals(0, loc.getRow());
        assertEquals(0, loc.getCol());
    }

}