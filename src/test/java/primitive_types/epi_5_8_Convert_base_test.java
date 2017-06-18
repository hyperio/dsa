package primitive_types;

import org.junit.Test;

import static org.junit.Assert.*;
import static primitive_types.epi_5_8_Convert_base.*;

public class epi_5_8_Convert_base_test {

  @Test
  public void convertTest() throws Exception {
    String b13Input = "-AB5C01";
    final String expected = "-46224522";
    final String actual = convert1(13, b13Input, 7);
    assertEquals(expected, actual);
  }

}