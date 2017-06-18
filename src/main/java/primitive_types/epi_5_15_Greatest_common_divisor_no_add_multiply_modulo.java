package primitive_types;

/**
 * gcd(x, y) without using multiplication, division and modulus.
 *
 * SOLUTION 1
 *
 * gcd(x, y) = gcd(x - y, y) [x > y]
 * Repeated subtraction is equivalent to division
 * Eventually, x will be equal to x mod y
 * So gcd(x, y) = gcd(y, x mod y) [x mod y < y that's why I put it on second position]
 *
 * SOLUTION 2
 *
 * gcd(x, y) = 2 * gcd (x / 2, y / 2) if x and y are even
 *             gcd(x / 2, y) if x is even and y is odd
 *               A number is a multiple of its gcd
 *               A divisor of an odd number cannot be even
 *               Even = multiple of 2
 *               2 cannot divide y
 *             gcd(x - y, y) if both are odd
 */
public class epi_5_15_Greatest_common_divisor_no_add_multiply_modulo {
  static long gcd(long x, long y) {
    if (x == 0) {
      return y;
    } else if (y == 0) {
      return x;
    } else if ((x & 1) == 0 && (y & 1) == 0) { // both even
      return gcd(x >> 1, y >> 1) << 1;  // 2 * gcd(x / 2, y / 2)
    } else if ((x & 1) == 0) { // x even, y odd
      return gcd(x >> 1, y);
    } else if ((y & 1) == 0) { // x odd, y even
      return gcd(x, y >> 1);
    } else if (x > y) { // both odd, x > y
      return gcd(x - y, y);
    }

    return gcd(x, y - x); // both odd, x <= y
  }
}
