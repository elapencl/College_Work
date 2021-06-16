public class Vector {
    private double x;
    private double y;

    public Vector(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Vector(){
        x = 0.0;
        y = 0.0;
    }

    public double getX() {

        return this.x;
    }

    public void setX(double x){

        this.x = x;
    }

    public double getY() {

        return this.y;
    }

    public void setY(double y) {

        this.y = y;
    }
    public String toString(){
        return "<" + x + ", " + y + ">";
    }

    public Vector plus(Vector b) {
        Vector needed = new Vector();
        needed.x = b.x + this.x;
        needed.y = b.y + this.y;

        return needed;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        return Double.compare(vector.x, x) == 0 && Double.compare(vector.y, y) == 0;
    }
}


