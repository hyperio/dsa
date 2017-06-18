package bit_manipulation;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static bit_manipulation.cci_5_1_Insert_a_into_b.*;

public class cci_5_1_Insert_a_into_b_test {

  @Test
  public void insertTest() {
    int a = Util.fromBinaryString("100101");
    int b = Util.fromBinaryString("101");
    int i = 1;
    int j = 3;

    int res1 = insert1(a, b, i, j);
    assertEquals("101011", Util.toBinaryString(res1, 6));

    int res2 = insert1(a, b, i, j);
    assertEquals("101011", Util.toBinaryString(res2, 6));
  }

}
