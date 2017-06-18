package primitive_types;

import org.junit.Test;

import static org.junit.Assert.*;
import static primitive_types.epi_5_10_Reverse_digits.*;

public class epi_5_10_Reverse_digits_test {

  @Test
  public void reverse1Test() throws Exception {
    final int input1 = 2345;
    final int expected1 = 5432;
    final int actual1 = reverse2(input1);
    assertEquals(expected1, actual1);

    final int input2 = -2345;
    final int expected2 = -5432;
    final int actual2 = reverse2(input2);
    assertEquals(expected2, actual2);
  }

}