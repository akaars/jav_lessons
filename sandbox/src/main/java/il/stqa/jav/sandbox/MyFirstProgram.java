package il.stqa.jav.sandbox;

public class MyFirstProgram {
  public static void main(String[] args) {
    hello("world");
    hello("Ilya");

    Square s = new Square(5);
    System.out.println("Square's area with side " + (int)s.l + " is: " + (int)s.area());

    Rectangle r = new Rectangle(4,6);

    System.out.println("Rectangle's area with sides " + (int)r.a + " and " + (int)r.b + " is: " + (int)r.area());
  }

  public static void hello(String somebody) {
    System.out.println("Hello, " + somebody + "!");
  }

}
