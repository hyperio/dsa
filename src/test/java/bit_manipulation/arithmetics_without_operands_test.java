package bit_manipulation;

import org.junit.Test;

import static org.junit.Assert.*;
import static bit_manipulation.arithmetics_without_operands.*;

public class arithmetics_without_operands_test {

  @Test
  public void testAdd() throws Exception {
    assertEquals(12, add1(5, 7));
    assertEquals(2,  add1(7, -5));
    assertEquals(-3, add1(7, -10));
    
    assertEquals(12, add2(5, 7));
    assertEquals(2,  add2(7, -5));
    assertEquals(-3, add2(7, -10));
  }

  @Test
  public void testMultiply() throws Exception {
    assertEquals(15, multiply1(3, 5));
    assertEquals(15, multiply2(3, 5));
  }

  @Test
  public void testDivide() throws Exception {
    assertEquals(5, divide1(16, 3));
    assertEquals(5, divide2(16, 3));
  }

}