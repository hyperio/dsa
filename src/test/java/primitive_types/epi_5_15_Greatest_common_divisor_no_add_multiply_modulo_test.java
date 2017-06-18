package primitive_types;

import org.junit.Test;

import static org.junit.Assert.*;
import static primitive_types.epi_5_15_Greatest_common_divisor_no_add_multiply_modulo.*;


public class epi_5_15_Greatest_common_divisor_no_add_multiply_modulo_test {
  @Test
  public void gcdTest() throws Exception {
    assertEquals(12, gcd(24, 300));
  }
}