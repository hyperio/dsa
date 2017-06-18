package primitive_types;

/**
 * A palindromic string is one that reads the same backwards.
 * "12321" is a palindrome. "12320" is not. "-12321" is also not.
 * Check if the string representation of an integer is a palindrome.
 *
 * EDGE CASES
 *
 * 1) Number negative
 *    "-12321" reversed is "12321-" != "-12321"
 */
public class epi_5_11_Integer_is_palindrome {

  /**
   * Method 1
   *
   * Repeat
   * - compare ms and ls digits
   * - drop ms and ls digits
   *
   * The number of digits is d = floor(log10(x)) + 1
   * ms digit = x / 10^(d - 1)
   * ls digit = x % 10
   */
  static boolean isPalindrome1(int x) {
    if (x < 0) {
      return false;
    }

    final int kNumDigits = (int) Math.floor(Math.log10(x)) + 1;
    int msdShift = (int) Math.pow(10, kNumDigits - 1);

    for (int i = 0; i < kNumDigits / 2; i++) {
      /* x / msdShift puts the important digit on the ls position
         Say x = 1234 and we are interested in 2. x/10^2 = 12
         Now, to get the digit of interest we do % 10 => 2 */
      if (x % 10 != ((x / msdShift) % 10)) {
        return false;
      }
      x /= 10;
      msdShift /= 100; // not just 10 as we are cutting the last digit of x in previous line
    }

    return true;
  }

}
