public class LineSegment {
    private Vector a;
    private Vector b;

    //constructors
    public LineSegment(){
        this.a = new Vector();
        this.b = new Vector();
    }

    public LineSegment(Vector a, Vector b){
        this.a = a;
        this.b = b;
    }

    //getters and setters
    public Vector getA(){
        return this.a;
    }

    public Vector getB(){
        return this.b;
    }

    public void setA(Vector a) {
        this.a = a;
    }

    public void setB(Vector b) {
        this.b = b;
    }

    //in order to return our object as a String:
    @Override
    public String toString() {
        return "A: " + a + " B: " + b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineSegment line = (LineSegment) o;
        return a.equals(line.a) && b.equals(line.b);
    }
}
