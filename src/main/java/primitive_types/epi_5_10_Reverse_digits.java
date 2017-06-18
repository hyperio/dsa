package primitive_types;

/**
 * Reverse the decimal representation of a binary encoded integer
 *
 * EDGE CASES
 *
 * 1) Number negative
 * 2) Result out of range.
 *    Say we have 4 bits available, ie. sizeof(int) = 4, and x = 15.
 *    reverse(x) = 51, which does not fit on 4 bits.
 */
class epi_5_10_Reverse_digits {

  /**
   * Method 1) int -> add to string buffer -> reverse buffer -> int
   */
  
  /**
   * Method 2)
   */
  static int reverse2(int x) throws Exception {
    boolean isNegative = x < 0;
    int xAbs = isNegative? -x : x;
    int result = 0;

    while (xAbs > 0) {
      int newResult = result * 10 + (xAbs % 10);
      if (newResult <= result) {
        throw new Exception("Overflow"); // Create custom OverflowException
      }
      result = newResult;
      xAbs /= 10;
    }

    return isNegative? -result : result;
  }

}
