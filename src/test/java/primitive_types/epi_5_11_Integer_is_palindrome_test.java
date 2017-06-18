package primitive_types;

import org.junit.Test;

import static org.junit.Assert.*;
import static primitive_types.epi_5_11_Integer_is_palindrome.*;

public class epi_5_11_Integer_is_palindrome_test {

  @Test
  public void isPalindrome1Test() throws Exception {
    assertTrue(isPalindrome1(12321));
    assertFalse(isPalindrome1(12320));
    assertFalse(isPalindrome1(-12321));
  }

}