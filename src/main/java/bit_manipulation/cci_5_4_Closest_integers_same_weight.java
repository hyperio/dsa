package bit_manipulation;

/**
 * Given a positive integer, find the next largest and next smallest number
 * that has the same weight (ie. same amount of 1 bits; same bits but shuffled).
 *
 * EDGE CASES
 *
 * What if all 0s or all 1s?
 *
 * VARIANTS
 *
 * Find closest element (doesn't matter if it's larger or smaller) [see bottom]
 */
class cci_5_4_Closest_integers_same_weight {

  /*
   * Method 1) Brute force
   * Count the amount of 1s in n.
   * Increment/decrement n until you find a number with the same amount of 1s.
   */

  /*
   * Method 2) Bit manipulation
   */

  /**
   * Let c0 = number of trailing 0s and c1 number of 1s following.
   * Let p = c0 + c1
   *
   * EXAMPLE
   *
   * 10110
   * c0 = 1, c1 = 2, p = 3
   *
   * a) Flip p: 11110
   * b) Put c1 - 1 (1s) starting at bit 0: 11001
   */
  static int getNext2(int num) {
    // Compute c0 and c1
    int temp = num;
    int c0 = 0, c1 = 0;

    while (temp > 0 && (temp & 1) == 0) {
      c0++;
      temp >>= 1;
    }

    while ((temp & 1) == 1) {
      c1++;
      temp >>= 1;
    }

    int p = c0 + c1;
    num |= (1 << p);

    // Clear bits at the right of p
    int clearMask = ~0 << p;
    num &= clearMask;

    // Put c1-1 1s starting at bit 0
    int setMask = (1 << (c1 - 1)) - 1;
    num |= setMask;

    return num;
  }

  /**
   * 101101
   * - Flip rightmost non-trailing 1 (say it has position p): 101001
   * - Put : 11011
   * - Flip the leftmost 1 between p and 0: 110001
   */
  static int getPrev2(int num) {
    // Compute c0 and c1
    int temp = num;
    int c1 = 0, c0 = 0;

    while ((temp & 1) == 1) {
      c1++;
      temp >>= 1;
    }

    while (temp > 0 && (temp & 1) == 0) {
      c0++;
      temp >>= 1;
    }

    int p = c0 + c1;
    num &= ~(1 << p);

    // Clear bits at the right of p
    int clearMask = ~0 << p;
    num &= clearMask;

    // Put c1 + 1 1s right after p
    int onesRequired = c1 + 1;
    int setMask = ((1 << onesRequired) - 1) << (p - onesRequired);
    num |= setMask;

    return num;
  }


  /*
   * Method 3) Arithmetic approach
   */

  /**
   * Let c0 = number of trailing 0s (could be 0 if lsb = 1) and c1 number of 1s following.
   * Let p = c0 + c1
   *
   * a) If we set any of the c0 bits the number gets smaller
   *    as we will also need to unset a higher bit
   * b) Set the first non trailing 0
   * c) Put (c1 - 1) 1s starting at lsb
   *
   * EXAMPLE
   *
   * x = 10011000
   * c0 = 3, c1 = 2, p = 5
   *
   * a) Set pth bit to 1 and clear the bits at the right of p
   *    x += 2^c0: 100 11 000 + 1000 = 101 00 000
   * b) Set last (c1 - 1) bits
   *    x += 2^(c1 - 1) - 1: 101 00 000 + 10 - 1 = 101 00 001
   *
   * next = n + 2^c0 + 2^(c1 - 1) - 1
   *
   * Can I assume no edge cases?
   */
  static int getNext3(int x) {
    int c0 = getPositionOfLs1(x);
    int c1 = getPositionOfLs1(~(x >> c0));

    return x + (1 << c0) + (1 << (c1 - 1)) - 1;
  }

  /**
   * Let c1 = number of trailing 1s (could be 0 if lsb = 0) and c0 number of 0s following.
   * Let p = c0 + c1
   *
   * a) If we unset any of the c1 bits the number gets larger
   *    as we will also need to set a higher bit
   * b) Unset the first non-trailing 1 (ie. the one on position p)
   * c) Put (c0 - 1) 0s at lsb, and ones from there up to p
   *
   * EXAMPLE
   *
   * x = 101100111
   * c1 = 3, c0 = 2, p = 5
   *
   * a) Unset pth bit and set all following bits to 1
   *    101 1 00 111 - 2^c1 = 101 1 00 111 - 1 000 = 101 0 11 111
   * b) Put c0 - 1 0s at lsb
   *    101 0 11 111 - (2^(c0 - 1) - 1) = 101 0 11 111 - (10 - 1) = 101 0 11 111 - 1 = 101 0 11 110
   *
   * n - 2^c1 - [ 2^(c0 - 1) - 1 ] = n - 2^c1 - 2^(c0 - 1) + 1
   *
   * Can I assume no edge cases?
   */
  static int getPrev3(int x) {
    int c1 = getPositionOfLs1(~x);
    int c0 = getPositionOfLs1(x >> c1);

    return x - (1 << c1) - (1 << (c0 - 1)) - 1;
  }

  static int getPositionOfLs1(int x) {
    int masks[] = new int[] {
      0x55555555, // 0101 0101
      0x33333333, // 0011 0011
      0x0f0f0f0f, // 0000 1111
      0x00ff00ff, // 0000 0000 1111 1111
      0x0000ffff
    };

    int ls1 = x & ~(x - 1);
    int count = 0;

    for (int i = 0; i < masks.length; i++) {
      if ((masks[i] & ls1) == 0) {
        count |= (1 << i);
      }
    }

    return count;
  }

  /**
   * Swap the first 2 consecutive bits that differ.
   *
   * EXAMPLE
   *
   * x = 100111 0 11, ls1x = 1, ls0x = 100
   * x = 100111 1 00, ls1x = 100, ls0x = 1
   *
   * The one among ls1x and ls0x that is not 1 is at
   * the position where the first bit switching occurs.
   * Swap the bit it selects with previous one.
   */
  static int findClosest1(int x) {
    int ls1x = x & ~(x - 1);   // a 1 on the position of ls 1 of x, 0 elsewhere
    int ls0x = (~x) & ~(~x - 1); // a 1 on the position of ls 0 of x, 0 elsewhere
    int larger = ls1x == 1? ls0x : ls1x;

    int mask = larger | (larger >> 1); // 100 becomes 110 (select consecutive bits)
    return x ^ mask; // swaps; bits are always different
  }

  static int findClosest2(int x) {
    for (int i = 0; i < 31; i++) {
      int currentBit = (x >>> i) & 1;
      int nextBit = (x >>> (i + 1)) & 1;
      if (currentBit != nextBit) {
        int swapMask = (1 << i) | (1 << (i + 1));
        x ^= swapMask;
        break;
      }
    }

    return x;
  }

}
