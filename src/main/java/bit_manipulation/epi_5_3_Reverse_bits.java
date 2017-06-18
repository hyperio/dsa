package bit_manipulation;

/**
 * Reverse the bits of a 64-bit word
 */
class epi_5_3_Reverse_bits {


  /**
   * Method 1) Brute force
   *
   * For b_ls <- bits from the ls half of x
   *   swap b_ls with b_ms (the correspondent msb)
   *
   * COMPLEXITY
   *
   * Time:  O(h) where h is the length of the ls half of x
   * Space: O(1)
   */

  /**
   * Method 2)
   *
   * Let x = y3y2y1y0 where |yi| = 16 bits
   * 1. Reverse the order of ys in x: x = y0y1y2y3
   * 2. Reverse the bits in each y.
   *    Store the reversed version of each y
   *    in a lookup table in advance.
   */
  static long reverse2(long x) {
    long[] precomputedReverse = new long[65536];

    final int kWordSize = 16;
    final int kFirst16BitsMask = 0xFFFF;

    long y3Flipped = precomputedReverse[(int)(x >>> (3 * kWordSize))];
    long y2Flipped = precomputedReverse[(int)(x >>> (2 * kWordSize)) & kFirst16BitsMask];
    long y1Flipped = precomputedReverse[(int)(x >>> kWordSize) & kFirst16BitsMask];
    long y0Flipped = precomputedReverse[(int)(x & kFirst16BitsMask)];

    return y3Flipped |
      (y2Flipped << kWordSize) |
      (y1Flipped) << (2 * kWordSize) |
      (y0Flipped) << (3 * kWordSize);
  }
}
