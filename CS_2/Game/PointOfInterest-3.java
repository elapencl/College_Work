public class PointOfInterest {
    private double x;
    private double y;
    private boolean previousCompleted;
    private boolean completed;
    private int correctAnswer;
    private String[] textArray;
    //tells what option the user chooses - set to 0 until they input something
    private int selected;
    private int order;
    private String name;

    /**
     * Sets the current status(true/false) of the point of interest to that of the input
     *
     * @param completed The desired status of the point of interest
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    /**
     * Returns an int that represents the correct answer to the question asked by the point of interest
     */
    public int getCorrectAnswer() {
        return correctAnswer;
    }

    /**
     * Point of Interest constructor.
     */
    public PointOfInterest(String name, String[] textArray, int correctAnswer, int order, double x, double y) {
        this.previousCompleted = false;
        this.correctAnswer = correctAnswer;
        this.x = x;
        this.y = y;
        this.textArray = textArray;
        this.selected = 0;
        this.order = order;
        this.name = name;
    }

    /**
     * Returns the x-value of the position of the point of interest
     */
    public double getX() {
        return x;
    }

    /**
     * Returns the y-value of the position of the point of interest
     */
    public double getY() {
        return y;
    }

    /**
     * Tells if the point of interest has been completed or not
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Sets if the previous point of interest has been completed or not
     */

    public void setPreviousCompleted(boolean previousCompleted) {
        this.previousCompleted = previousCompleted;
    }

    /**
     * Tells if the previous point of interest has been completed or not
     */
    public boolean isPreviousCompleted() {
        return previousCompleted;
    }

    /**
     * Getter for text array
     */

    public String[] getTextArray() {
        return textArray;
    }

    /**
     * Getter for what option is selected by player
     */

    public int getSelected() {
        return selected;
    }

    /**
     * Setter for selected
     */
    public void setSelected(int selected) {
        this.selected = selected;
    }

    /**
     * Getter for order (int signature of the point of interest)
     */
    public int getOrder() {
        return order;
    }

}
