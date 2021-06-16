import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointOfInterestTest {

    private PointOfInterest tester;


    @Test
    void gettersAndSettersWork(){
        String[] textArray = new String[2];
        textArray[0] = "this is a test";
        textArray[1] = "this is not a test";
        tester = new PointOfInterest("testing",textArray, 1, 0, 2, 3);
        tester.setPreviousCompleted(true);
        tester.setCompleted(true);
        tester.setSelected(0);
        String text = tester.getTextArray()[1];
        int correct = tester.getCorrectAnswer();
        int order = tester.getOrder();
        double x = tester.getX();
        double y = tester.getY();
        assertEquals("this is not a test", text);
        assertEquals(1, correct);
        assertEquals(0, tester.getSelected());
        assertEquals(0, order);
        assertEquals(2, x);
        assertEquals(3, y);

    }


}
