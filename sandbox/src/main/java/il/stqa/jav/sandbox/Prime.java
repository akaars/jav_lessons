package il.stqa.jav.sandbox;

public class Prime {
public static boolean isPrime(int n) {
  int m = (int) Math.sqrt(n);
  for (int i=2; i <= m; i++) {
    if (n % i == 0) {
      return false;
    }
  } return true;
}
}
