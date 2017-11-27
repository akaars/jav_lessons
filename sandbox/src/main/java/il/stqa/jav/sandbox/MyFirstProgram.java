package il.stqa.jav.sandbox;

public class MyFirstProgram {
  public static void main(String[] args) {
    hello("world");
    hello("Ilya");
    double len = 5;
    System.out.println("Square's area with side " + (int)len + " is: " + (int)area(len));

    double a = 4;
    double b = 6;
    System.out.println("Rectangle's area with sides " + (int)a + " and " + (int)b + " is: " + (int)area(a, b));
  }

  public static void hello(String somebody) {
    System.out.println("Hello, " + somebody + "!");
  }

  public static double area(double len) {
    return len * len;
  }

  public static double area (double a, double b){
    return a * b;
  }
}
