package il.stqa.jav.sandbox;

/**
 * Created by ilya on 11/27/17
 */
public class Rectangle {

  public double a;

  public double b;
  public Rectangle( double a, double b) {
    this.a = a;
    this.b = b;
  }

  public double area (){
    return this.a * this.b;
  }

}
