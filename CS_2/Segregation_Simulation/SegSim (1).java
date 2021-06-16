import java.awt.*;
import java.util.Random;

public class SegSim {
    public static void main(String[] args) {
        StdDraw.enableDoubleBuffering();
        int gridWidth = 30; /** This variable controls the grid sizes*/
        int[][] grid = createGrid(gridWidth);

        printGrid(grid);
        StdDraw.setScale(-0.5, grid.length - 0.5);
        boolean[][] unhappy = drawGrid(grid);
        while (true) {
            StdDraw.pause(500);
            grid = move(grid, unhappy);
            unhappy = drawGrid(grid);
        }
    }

    /** The move method finds all dissatisfied squares and moves them to an empty square.*/
    static int[][] move(int[][] grid, boolean[][] unhappy) {
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid.length; c++) {
                if (unhappy[r][c]) {
                    grid = findNewSpot(r, c, grid);
                }
            }
        }
        return grid;
    }

    /** findNewSpot uses random number generation to find an empty cell for an unhappy
     *  square to move into. */
    static int[][] findNewSpot(int r, int c, int[][] grid) {
        int type = grid[r][c];
        Random rand = new Random();


        while (grid[r][c] == type) {
            int row = rand.nextInt(grid.length);
            int col = rand.nextInt(grid.length);
            if (grid[row][col] == 0) {
                grid[row][col] = type;
                grid[r][c] = 0;
                return grid;
            }
        }
        return grid;
    }

    /** countNeighbors identifies the number of similar neighbors and divides it by the total number of
     *  neighbors to produce a squares happiness level
     */
    static double countNeighbors(int r, int c, int[][] grid) {
        int type = grid[r][c];
        double countSameType = 0;
        double total = 0;
        int[][] neighbors = {{r - 1, c - 1}, {r - 1, c}, {r - 1, c + 1}, {r, c - 1}, {r, c + 1}, {r + 1, c - 1}, {r + 1, c}, {r + 1, c + 1}};
        for (int i = 0; i < neighbors.length; i++) {
            int row = neighbors[i][0];
            int col = neighbors[i][1];
            if (row >= 0 && row < grid.length && col >= 0 && col < grid.length) {
                if (grid[row][col] == type) {
                    countSameType++;
                    total++;
                }
                if (grid[row][col] != type && grid[row][col] != 0) {
                    total++;
                }
            }
        }
        double fraction = 0;
        if (total != 0) {
            fraction = countSameType / total;
        }
        return fraction;
    }

    static boolean[][] drawGrid(int[][] grid /*boolean[][] revealed, boolean [][] flagged*/) {
        StdDraw.clear();
        boolean[][] dissatisfied = new boolean[grid.length][grid.length];
        double satisfaction = 0.3; /**This variable is the minimum happiness a square must have to be
                                      comfortable
                                      staying where it is.*/

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid.length; c++) {
                if (grid[r][c] == 2) {
                    StdDraw.setPenColor(Color.cyan);
                    StdDraw.filledSquare(c, grid.length - 1 - r, 0.5);
                    double fraction = countNeighbors(r, c, grid);
                    if (fraction <= satisfaction) {
                        dissatisfied[r][c] = true;
                    }
                } else if (grid[r][c] == 1) {
                    StdDraw.setPenColor(Color.PINK);
                    StdDraw.filledSquare(c, grid.length - 1 - r, 0.5);

                    double fraction = countNeighbors(r, c, grid);

                    if (fraction <= satisfaction) {
                        dissatisfied[r][c] = true;
                    }
                } else {
                    StdDraw.setPenColor(StdDraw.WHITE);
                    StdDraw.filledSquare(c, grid.length - 1 - r, 0.5);

                }
                StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.square(c, grid.length - 1 - r, 0.5);
            }
        }

        StdDraw.show();
        return dissatisfied;
    }

    static void printGrid(int[][] grid) {
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid.length; c++) {
                if (grid[r][c] == 2) {
                    StdOut.print("*");
                } else if (grid[r][c] == 1) {
                    StdOut.print("#");
                } else {
                    StdOut.print(".");
                }
            }
            StdOut.println();
        }
    }

    static int[][] createGrid(int w) {
        int[][] grid = new int[w][w];
        int numTiles = grid.length * grid.length;
        int numEmpty = numTiles / 10; /**the denominator is the percentage of empty tiles*/
        numTiles = numTiles - numEmpty;
        double numPink = numTiles * 0.5; /**change this variable to manipulate the ratio of pink to cyan on the board*/
        double numCyan = numTiles - numPink;
        Random rand = new Random();
        for (int i = 0; i < numCyan; i++) {
            int row = rand.nextInt(w);
            int col = rand.nextInt(w);
            if (grid[row][col] == 2) {
                i--;
            } else grid[row][col] = 2;
        }
        for (int i = 0; i < numPink; i++) {
            int row = rand.nextInt(w);
            int col = rand.nextInt(w);
            if (grid[row][col] == 0) {
                grid[row][col] = 1;
            } else i--;
        }

        return grid;
    }


}
