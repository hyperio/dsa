package bit_manipulation;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class UtilTest {

  @Test
  public void toBinaryStringTest() {
    assertEquals("0101", Util.toBinaryString(5, 4));
  }

  @Test
  public void fromBinaryStringTest() {
    assertEquals(
        "0101",
        Util.toBinaryString(Util.fromBinaryString("0101"), 4));
  }

  @Test
  public void testGetPositionOfLS1() {
    int expected = 3;
    int actual = Util.getPositionOfLs1(Util.fromBinaryString("1011000"));
    assertEquals(expected, actual);
  }

}
