public class Starship {
    private String name;
    private Vector position;
    private Vector velocity;

    public Starship (String name, Vector position){
        this.name = name;
        this.position = position;
        this.velocity = new Vector();
    }

    public String getName() {
        return name;
    }

    public String setName(String name){
        return this.name = name;
    }

    public Vector getPosition(){
        return position;
    }

    public Vector setPosition(Vector position){
        return this.position = position;
    }

    public Vector getVelocity(){
        return velocity;
    }

    public Vector setVelocity(Vector velocity){
        return this.velocity = velocity;
    }

    public String toString(){
        return name + " at " + position.toString() + " moving " + velocity.toString();
    }

    public void accelerate(Vector vector){
        this.setVelocity(this.velocity.plus(vector));
    }

    public void drift() {
        this.setPosition(this.position.plus(this.velocity));
    }
}
