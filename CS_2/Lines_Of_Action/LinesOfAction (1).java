public class LinesOfAction {
    private Board b; //only instance variable that we need :')

    public static void main(String[] args) {
        new LinesOfAction().run();
    }

    /**
     * run method for lines of action
     */
    void run() {
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-1.2, 8);
        b = new Board();
            b.setGrid();
            b.drawGrid();
            b.update();

    }

}