package primitive_types;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Given
 * - a base "b"
 * - a number a_(k-1)a(k-2)...a2a1a0 of k digits in base b, where 0 <= ai < b
 * The decimal value of the number is a0*b^0 + a1*b^1 + ... + b^(k - 1)*a_(k-1)
 *
 * Write a function that performs base conversion
 * Assume 2 <= b1, b2 <= 16
 * Use A - F to represent 10 - 15
 *
 * EDGE CASES
 *
 * 1) Number negative
 * 2) Number in lower case. ASCII lookup might fail. Make everything upper case
 * 3) Overflow when converting from b1 to base 10 (base 10 being the intermediate base
 */
class epi_5_8_Convert_base {

  /**
   * Method 1) b1 -> base 10 -> b2
   *
   * ASSUMPTIONS
   *
   * 1) All letters are uppercase
   */
  static String convert1(int b1, String s, int b2) {
    boolean isNegative = s.charAt(0) == '-';

    // Compute decimal value

    int b1Power = 1;
    int decimalValue = 0;
    final int firstDigitPos = isNegative? 1 : 0;
    for (int i = s.length() - 1; i >= firstDigitPos; i--) {
      final int digit = isDigit(s.charAt(i))? s.charAt(i) - '0' : s.charAt(i) - 'A' + 10;
      decimalValue += digit * b1Power;
      b1Power *= b1;
    }

    // Compute value in b2

    StringBuilder b2Value = new StringBuilder();
    while (decimalValue > 0) {
      int remainder = decimalValue % b2;
      if (remainder > 9) {
        remainder = remainder - 10 + 'A';
      }
      b2Value.append(remainder);
      decimalValue /= b2;
    }

    if (isNegative) {
      b2Value.append('-');
    }

    return b2Value.reverse().toString();
  }

  private static boolean isDigit(char c) {
    return c >= '0' && c <= '9';
  }


  /**
   * Method 2) b1 -> b2
   *
   * a3a2a1a0 is in b1. To convert it to be b2:
   * - a0*b1^0 + a1*b1^1 + a2*b2^2 + a3*b2^3.
   * - Convert recursively each term to b2
   * - Convert the sum to b2
   *
   * Advantages
   * - Avoid base 10
   * - Avoid overflow
   */
  static int convert2(int b1, String s, int b2) {
    throw new NotImplementedException();
  }

}
