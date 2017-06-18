package bit_manipulation;

import org.junit.Test;
import static org.junit.Assert.*;

import static bit_manipulation.cci_5_2_Double_to_binary_string.*;

public class cci_5_2_Double_to_binary_string_test {

  @Test
  public void convertTest() throws Exception {
    assertEquals("01", convert1(0.25));
    assertEquals("01", convert2(0.25));
  }

}