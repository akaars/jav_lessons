package il.stqa.jav.sandbox;

/**
 * Created by ilya on 11/27/17
 */
public class DistanceCalc {
  public static void main(String[] args){

    Point p1 = new Point(0,0);
    Point p2 = new Point(3,4);
    System.out.println("The distance between two defined points calculated by internal static function" +
            " is: " + distance(p1, p2));
    System.out.println("The distance between two defined points calculated by method is: " + p1.distance(p2));
  }
  public static double distance(Point p1, Point p2) {
    return Math.sqrt(Math.pow((p2.x - p1.x), 2.0) + Math.pow((p2.y - p1.y), 2.0));
  }
}
