This is a pair programming project. Work with your assigned partner. Only one of you has to turn it in, but make sure you don't both think the other one was doing it.

Overview
You will write a real-time racing game. I've provided a working version as an example. Download race.jar  download, use your command line to navigate to the directory where you saved it, and run it with the command:

java -jar race.jar
This is a two-player game. One player controls their car with the W, A, S, and D keys, the other with I, J, K, and L. The the first player to complete three laps wins -- but you lose immediately if you touch a wall! If you find yourself crashing all the time, slow down. If you're playing with your pair programming partner and one of you is much more experienced, give the other player a head start.

Your game should behave exactly like mine, with the possible exception of the track layout; yours should be at least as complicated as mine.

Program Structure
This is your first complicated object-oriented program. Expect it to take a while to build!

The program will consist of several classes, as shown in the following UML class diagram:

Screen Shot 2019-03-12 at 8.31.04 PM.png

Each box represents a class. Each arrow represents a has-a relationship. For example, a Car has-a Vector. (More precisely, it has two: one indicating the location of the center of the car and another indicating its current velocity.)

This sort of diagram can help you figure out what to do first. In this case, since Vector is the only class that doesn't require any other, you should probably write that first. You may be able to start with the version you wrote for the Defining Objects assignment.

You won't be able to write JUnit tests for the Race class, which handles the user interface (detecting when keys are being pressed, drawing the screen, etc.). You should therefore try to put as little code in that class as possible.

Hints
Everything should be in proper object-oriented form. This means that the only static method should be the main method in the Race class, which might look like this:

    public static void main(String[] args) {
        new Race().run();
    }
This method creates an instance of the Race class and tells it to run. The run method might look like this:

    public void run() {
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-0.05, 1.05);
        int winner = -1;
        while (winner == -1) {
            StdDraw.pause(10);
            handleKeys();
            winner = update();
            draw(winner);
        }
    }
The most difficult part is determining when a car is touching a wall. Here's a method to do that (which would be in the Car class):

    /** Adapted from https://stackoverflow.com/a/34951168/1435803. */
    public boolean intersects(LineSegment line) {
        Vector a = line.getA();
        Vector b = line.getB();
        Vector v = b.minus(a);
        Vector w = center.minus(a);
        double c1 = w.dot(v);
        double c2 = v.dot(v);
        Vector projection; // Projection of center onto line
        if (c1 <= 0) { // Projection is beyond point a
            projection = a;
        } else if (c2 <= c1) { // Projection is beyond point b
            projection = b;
        } else { // Projection is within line segment
            projection = a.plus(v.times(c1 / c2));
        }
        return center.distanceTo(projection) <= radius;
    }
This assumes that Vector has methods minus, dot (dot product), times, and distanceTo.

To keep track of laps, use (for each player) a double that is incremented by 0.5 whenever the player touches the finish line or the (invisible) halfway line on the far side of the track. Of course, if a car touches one of these lines in one step of the simulation, it will almost certainly be touching it in the next step; you'll have to find a way to not count it again until the car touches the other line.

You can tell if, say, the 'A' key is being pressed using this boolean expression:

StdDraw.isKeyPressed(KeyEvent.VK_A)
Remember that only the draw method (in Race) should ever draw anything. Scattering draw commands around different methods will make debugging a nightmare.

What To Hand In
Hand in all of your .java files including tests.
