package bit_manipulation;

/**
 * Write a function to determine the number of bits you
 * would need to flip to convert integer a to integer b.
 */
public class cci_5_6_Flip_bits_to_convert_a_to_b {

  /**
   * Method 1) Count the number of bits set in c = a ^ b.
   * The counting is done by continually clearing the least
   * significant 1 in c until c == 0.
   */
  static int diff1(int a, int b) {
    int count = 0;
    for (int c = a ^ b; c != 0; c = c & (c - 1)) {
      count++;
    }
    return count;
  }

}
