package bit_manipulation;

/**
 * Swap bits i and j in a 64-bit integer
 */
class epi_5_2_Swap_bits {

  /**
   * Util.getBit(num, i) = (num & (1 << i)) != 0
   *
   * EXAMPLE
   *
   * num = 10 0 101 1 011, i = 3, j = 7
   *
   * 1L << 3 | 1L << 7 = 00 1 000 1 000
   *
   * 10 0 101 1 011 ^
   * 00 1 000 1 000
   * --------------
   * 10 1 000 0 011
   *
   * COMPLEXITY
   *
   * Time:  O(1)
   * Space: O(1)
   */
  static long swap1(long num, int i, int j) {
    if (Util.getBit(num, i) != Util.getBit(num, j)) {
      num ^= (1L << i) | (1L << j);
    }
    return num;
  }

}
