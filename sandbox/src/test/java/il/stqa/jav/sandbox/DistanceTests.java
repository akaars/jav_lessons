package il.stqa.jav.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;
import il.stqa.jav.sandbox.DistanceCalc;

public class DistanceTests {
  @Test
  public void testFunction1() {
    Point p1 = new Point(1, 1);
    Point p2 = new Point(6, 1);
    Assert.assertEquals(p1.x, 1.0);
    Assert.assertEquals(p2.x, 6.0);
    Assert.assertEquals(p1.distance(p2), 5.0);
  }

  @Test
  public void testFunction2() {
    Point p1 = new Point(0, 0);
    Point p2 = new Point(0, 0);
    Assert.assertEquals(p1.distance(p2), 0.0);
  }

  @Test
  public void testFunction3() {
    Point p1 = new Point(3, 4);
    Point p2 = new Point(0, 0);
    Assert.assertEquals(p2.distance(p1), 5.0);
  }

  @Test
  public void testFunction4() {
    Point p1 = new Point(3, 4);
    Point p2 = new Point(0, 0);
    Assert.assertEquals(DistanceCalc.distance(p1, p2), 5.0);
  }
}
