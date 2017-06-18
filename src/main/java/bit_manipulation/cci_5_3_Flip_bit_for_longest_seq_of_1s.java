package bit_manipulation;

/**
 * You have an integer and you can flip exactly one bit from a 0 to a 1.
 * Find the length of the longest sequence of 1s you could create.
 *
 * EXAMPLE
 *
 * Input: 1775 (11011101111)
 * Output: 8. By flipping bit 4.
 */
class cci_5_3_Flip_bit_for_longest_seq_of_1s {

  static int flip1(int num) {
    if (~num == 0) {
      return Integer.SIZE; // assume unsigned, but it is stored as signed.
    }

    int currentLen = 0;
    int prevLen = 0;
    int maxLen = 1;

    while (num > 0) {
      if ((num & 1) == 1) {
        currentLen++;
      } else {
        if ((num & 2) != 0) {
          prevLen = currentLen;
        } else {
          prevLen = 0;
        }
        currentLen = 0;
      }
      maxLen = Math.max(prevLen + currentLen + 1, maxLen);
      num >>= 1;
    }

    return maxLen;
  }

}
