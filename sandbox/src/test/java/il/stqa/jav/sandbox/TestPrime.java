package il.stqa.jav.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestPrime {
  @Test
  public void TestPrime() {
    Assert.assertTrue(Prime.isPrime(3));
  }
}
