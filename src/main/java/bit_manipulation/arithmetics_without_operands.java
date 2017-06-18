package bit_manipulation;

/**
 * Given 2 integers x and y compute using bit operations only
 * 1) x + y
 * 2) (epi 5.5) x * y
 * 3) (epi 5.6) x / y
 * 4) (epi 5.7) x ^ y
 */
class arithmetics_without_operands {

  /**
   * 1) x + y
   *
   * 0 + 0 = 0, 1 + 1 = 0 (but with a carry)
   * 1 + 0 = 1, 0 + 1 = 1
   *
   * So we need to
   * - xor
   * - take care of the carry
   */
  static int add1(int x, int y) {
    while (y != 0) {
      int carry = x & y;
      x = x ^ y;
      // So that in the next iteration we
      // add the carry in its right place.
      // Basically the carry is the next thing to add to x.
      y = carry << 1;
    }

    return x;
  }

  static int add2(int x, int y) {
    if (y == 0) {
      return x;
    } else {
      return add2(x ^ y, (x & y) << 1);
    }
  }


  static int multiply1(int x, int y) {
    int smaller = Math.min(x, y);
    int larger = Math.max(x, y);

    int prod = larger;

    while (smaller > 1) {
      prod = add1(prod, larger);
      smaller = add1(smaller, -1);
    }

    return prod;
  }

  /**
   * Usual algorithm:
   * x = 011 | 3
   * y = 101 | 5
   *         |
   *    011  | 3*
   *    101  | 5
   *    ---  |
   *    011  |
   *  011    |
   *  -----  |
   *  01111  | 15
   *
   * If kth bit of y is set
   * add x shifted k positions to the left, ie. x * 2^k
   * to result
   *
   * @return x * y
   */
  static int multiply2(int x, int y) {
    int r = 0;

    while (y > 0) {
      if ((y & 1) == 1) {
        r = add1(r, x);
      }
      x <<= 1; // multiply x by 2
      y >>= 1; // to check next bit in next iteration
    }

    return r;
  }

  /**
   * Method 1) brute force
   * @return x / y
   */
  static int divide1(int x, int y) {
    if (y == 0) return -1; // or throw some exception
    if (x == 0) return  0;
    if (x == y) return  1;

    int count = 0;

    while (x >= y) {
      x = add1(x, -y);
      count++;
    }

    return count;
  }

  /**
   * Method 2
   *
   * In each iteration,
   *   subtract y * 2^k from x instead of just y
   *   add 2^k to result
   *
   *
   * @return x / y
   */
  static int divide2(int x, int y) {
    if (y == 0) return -1; // or throw some exception
    if (x == 0) return  0;
    if (x == y) return  1;

    int result = 0;

    while (x > 0) {
      int k = 1;

      /*
       * (y << k) > (y << (k - 1)) checks that there is no overflow
       * Overflow situation:
       *   4 bits, y = 0110, k = 2
       *   y << 2 = 1000, y << (2-1) = y << 1 = 1100, which is larger than 1000
       */
      while (((y << k) > (y << (k - 1))) && (y << k) <= x ) {
        k++;
      }
      k--;

      x = add1(x, -(y << k));
      result |= (1 << k);
    }

    return result;
  }

  /**
   * Method 1
   *
   * Brute force
   *
   * prod = 1
   * Multiply x with prod y times
   *
   * @return x^y
   */

  /**
   * Method 2
   *
   * y = 2k:     x^(2k) = x^k * x^k
   * y = 2k + 1: x^(2k + 1) = x * x^(2k)
   *
   * @return x^y
   */
  static int power2(int x, int y) {
    if (y == 0) return 1;
    if (y == 1) return x;
    if (y < 0) {
      x = 1/x;
      y = -y;
    }

    int power = y >> 1; // y div 2
    int term = power2(x, power); // could use memoisation
    int evenResult = term * term;

    if ((y & 1) == 0) {
      return evenResult;
    } else {
      return x * evenResult;
    }
  }

  /**
   * Method 3
   *
   * Same as method 2 but iterative
   *
   * @return x^y
   */
  static int power3(int x, int y) {
    if (y == 0) return 1;
    if (y == 1) return x;
    if (y < 0) {
      x = 1/x;
      y = -y;
    }

    int result = 1;

    while (y > 0) {
      if ((y & 1) == 1) {
        result *= x;
      }
      x *= x;
      y >>= 1;
    }

    return result;
  }

}
