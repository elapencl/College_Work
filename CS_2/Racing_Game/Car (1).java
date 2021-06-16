import java.util.Objects;

public class Car{
    /** Adapted from https://stackoverflow.com/a/34951168/1435803. */

    //instance variables
    private String name;
    private Vector position;
    private Vector velocity;
    private double lapCount;
    private boolean lookingForFinish;
    private double radius;

    //constructor for car
    public Car(String name, Vector position, double radius) {
        this.name = name;
        this.position = position;
        this.velocity = new Vector(0, 0);
        this.lapCount = -0.5;
        this.lookingForFinish = true;
        this.radius = radius;
    }

    //this boolean value is going to be used in Race class when deciding on our winner
    public boolean isLookingForFinish() {
        return lookingForFinish;
    }

    public void setLookingForFinish(boolean lookingForFinish) {
        this.lookingForFinish = lookingForFinish;
    }

    //getters and setters
    public String getName() {
        return name;
    }

    public Vector getPosition() {
        return position;
    }

    public Vector getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector v){
        this.velocity = v;
    }

    //moving car
    public void accelerate(Vector acceleration) {
        this.velocity = velocity.plus(acceleration);
    }

    //updating car's position
    public void drift() {
        this.position = position.plus(velocity);
    }

    //getters and setters for lap count
    //(how many time the running car is crossing the laps
    public double getLapCount(){
        return lapCount;
    }

    public void setLapCount(Double count){
        this.lapCount = count;
    }

    //getters and setters for radius
    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    //checks if car is hitting the wall
    public boolean intersects(LineSegment line) {
        double radius = this.radius;
        Vector a = line.getA();
        Vector b = line.getB();
        Vector v = b.minus(a);
        Vector w = position.minus(a);
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
        return position.distanceTo(projection) <= radius;
    }

    //Override for starship equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return name.equals(car.name) &&
                position.equals(car.position) &&
                velocity.equals(car.velocity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, position, velocity);
    }

    //Override for starship toString method
    @Override
    public String toString() {
        return name + " at " + position + " moving " + velocity;
    }

}
