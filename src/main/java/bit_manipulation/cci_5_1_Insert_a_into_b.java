package bit_manipulation;

/**
 * You are given two 32-bit numbers, a and b, and two bit positions, i and j.
 * Write a method to insert b into a such that b starts at bit j and ends at bit i of a.
 * You can assume that the bits j through i have enough space to fit all of b.
 * That is, if b = 10011, you can assume that there are at least 5 bits between j and i.
 * You would not, for example, have j = 3 and i = 2, because B could not fully fit between bit 3 and bit 2.
 *
 * EXAMPLE
 *
 * Input:  A = 10000000000, B = 10011, i = 2, j = 6
 * Output: A = 10001001100
 */
class cci_5_1_Insert_a_into_b {

  /**
   * Example: i = 1, j = 3, 5 bits.
   *
   * j - i + 1 is the length of b: 3 - 1 + 1 = 3.
   * x = 2^(j - i + 1) - 1 = sets first [j - i + 1] bits: 00111
   * y = x << i moves the setting i positions to the left: 01110
   * ~y: 10001
   */
  static int insert1(int a, int b, int i, int j) {
    int mask = ~(((1 << (j - i + 1)) - 1) << i);
    return (a & mask) | (b << i);
  }

  /**
   * ~0 = 11111
   * left = ~0 << (j + 1): ~0 << 4 = 10000
   * right = 2^i - 1 sets first i bits: 2^1 - 1 = 00010 - 1 = 00001.
   * left | right = 10001
   *
   */
  static int insert2(int a, int b, int i, int j) {
    int allOnes = ~0;
    int left = allOnes << (j + 1);
    int right = (1 << i) - 1;
    int mask = left | right;
    return (a & mask) | (b << i);
  }

}
