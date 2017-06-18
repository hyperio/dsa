package bit_manipulation;

import org.junit.Test;

import static org.junit.Assert.*;
import static bit_manipulation.cci_5_4_Closest_integers_same_weight.*;

public class cci_5_4_Closest_integers_same_weight_test {

  @Test
  public void findClosestTest() throws Exception {
    final int x1 = Util.fromBinaryString("100111");
    final int x2 = Util.fromBinaryString("11000");

    final int expected1 = Util.fromBinaryString("101011");
    final int actual11 = findClosest1(x1);
    assertEquals(expected1, actual11);
    final int actual12 = findClosest2(x1);
    assertEquals(expected1, actual12);

    final int expected2 = Util.fromBinaryString("10100");
    final int actual21 = findClosest1(x2);
    assertEquals(expected2, actual21);
    final int actual22 = findClosest2(x2);
    assertEquals(expected2, actual22);
  }

}