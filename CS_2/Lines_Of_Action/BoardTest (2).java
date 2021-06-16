import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardTest {

   @Test
    void constructorWorks() {
        Board board = new Board();
        board.setGrid();
        board.setSquare(new Pair (3, 4, "red") );
        assertEquals("red", board.getGrid()[3][4].getPiece());

    }


    @Test
    void hasWonWorks() {
        Board b = new Board();
        StdDraw.setScale(-1.2, 8);
        b.setGrid();
        for (int i = 0; i < 8; i++) {
            b.setSquare(new Pair(1, i, "red"));
        }

        b.drawGrid();
        b.update();
        assertEquals("red", b.getWinner());
    }

    @Test
    void setGridWorks() {
       Board b = new Board();
       b.setGrid();
       assertEquals("red", b.getGrid()[1][0].getPiece());
       assertEquals("black", b.getGrid()[0][1].getPiece());
       assertEquals("empty", b.getGrid()[2][2].getPiece());
    }
    @Test
    void findsHorizontalPieces() {
    Board board = new Board();
    board.setGrid();
    Pair location = new Pair(0, 0, "");
    assertEquals(6, board.findHorizontalPieces(location));
    Pair secondLocation = new Pair(1, 0, "");
    assertEquals(6, board.findHorizontalPieces(secondLocation));
    Pair thirdLocation = new Pair(0, 4, "");
    assertEquals(2, board.findHorizontalPieces(thirdLocation));
    }

    @Test
    void findsVerticalPieces() {
       //note that pair locations are column, row because the way the graphics window is drawn
    Board board = new Board();
    board.setGrid();
    Pair location = new Pair(0, 0, "");
    assertEquals(6, board.findVerticalPieces(location));
    Pair secondLocation = new Pair(1, 0, "");
    assertEquals(2, board.findVerticalPieces(secondLocation));
    Pair thirdLocation = new Pair(0, 4, "");
    assertEquals(6, board.findVerticalPieces(thirdLocation));
    }


}

