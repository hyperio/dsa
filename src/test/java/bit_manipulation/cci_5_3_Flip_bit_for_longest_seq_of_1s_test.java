package bit_manipulation;

import org.junit.Test;
import static org.junit.Assert.*;

import static bit_manipulation.cci_5_3_Flip_bit_for_longest_seq_of_1s.*;

public class cci_5_3_Flip_bit_for_longest_seq_of_1s_test {

  @Test
  public void flip1test() throws Exception {
    assertEquals(8, flip1(Util.fromBinaryString("11011101111")));
  }

}