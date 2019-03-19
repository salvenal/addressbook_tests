package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;


public class PointTests {

  @Test

  public void testArea() {
    Point p1 = new Point(2,0);
    Point p2 = new Point(1,0);
    Assert.assertEquals(Point.distance(p1,p2), 1);
  }


}
