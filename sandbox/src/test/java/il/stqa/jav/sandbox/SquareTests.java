package il.stqa.jav.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SquareTests {
    @Test
    public void testArea() {

        Square s = new Square(5);
        org.testng.Assert.assertEquals(s.area(),  (double)25);
    }
}
