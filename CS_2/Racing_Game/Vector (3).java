import java.util.Objects;

public class Vector {
    private double x;
    private double y;

    //constructors
    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector() {
        x = 0.0;
        y = 0.0;
    }

    //getters and setters
    public double getX() {

        return this.x;
    }

    public double getY() {

        return this.y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "<" + x + ", " + y + ">";
    }

    //method describing addition of vectors
    public Vector plus(Vector v){
        Vector result = new Vector();
        result.x = this.x + v.x;
        result.y = this.y + v.y;
        return result;
    }

    //method describing subtraction of vectors
    public Vector minus(Vector v) {
        Vector result = new Vector();
        result.x = this.x - v.x;
        result.y = this.y - v.y;
        return result;
    }

    //method describing how dot product of two vectors is calculated
    public Double dot(Vector v) {
        Double result = this.x * v.x + this.y * v.y;
        return result;
    }

    //method describing the scalar multiplication of vectors
    public Vector times(Double c) {
        Vector result = new Vector();
        result.x = this.x * c;
        result.y = this.y * c;
        return result;
    }

    //method describing distance between two vectors
    public Double distanceTo(Vector v) {
        Double result = Math.sqrt(Math.pow((v.x - this.x),2) + Math.pow((v.y - this.y),2));
        return result;
    }

    //overriding the equals method to compare two complex objects
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        return Double.compare(vector.x, x) == 0 &&
                Double.compare(vector.y, y) == 0;
    }

    //overriding hashCode() so we don't violate the general contract of Object.hashCode()
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }


}
