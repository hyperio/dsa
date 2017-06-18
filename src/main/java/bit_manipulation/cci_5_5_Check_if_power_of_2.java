package bit_manipulation;

/**
 * Explain what the following code does: ((n & (n - 1)) == 0).
 *
 * Let n     = abc100
 *     n - 1 = abc011
 *
 * True if n and n - 1 have no 1s in common. That happens if n is a power of 2,
 * having the representation 10...0.
 *
 * Answer: It is true if n is a power of 2.
 * Observation: n & (n - 1) clears the least significant 1 in n.
 */
class cci_5_5_Check_if_power_of_2 {

  static boolean check1(int num) {
    return (num & (num - 1)) == 0;
  }

}
