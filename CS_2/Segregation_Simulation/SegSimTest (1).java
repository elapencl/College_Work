import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SegSimTest {

    @BeforeEach
    void setUp() {
    }

    @Test
        void countNeighborsWorksInMiddleOfGrid() {
            int[][] grid = new int[3][3];
            grid[1][1]=2;
            grid[0][1]=1;
            grid[2][2]=2;
            assertEquals(0.5,SegSim.countNeighbors(1,1,grid));
        }

    @Test
    void countNeighborsWorksAtEdge() {
        int[][] grid = new int[3][3];
        grid[1][1]=2;
        grid[1][2]=1;
        grid[2][2]=2;
        assertEquals(0.5,SegSim.countNeighbors(2,2,grid));
    }

    @Test
    /**It doesn't work. We tried our best.*/
    void findNewSpotWorks() {
        int[][] grid= new int[2][2];
        grid[0][0]=0;
        grid[0][1]=1;
        grid[1][0]=2;
        grid[1][1]=2;
        assertEquals(grid[0][0]=1,SegSim.findNewSpot(0,1,grid));
    }

}