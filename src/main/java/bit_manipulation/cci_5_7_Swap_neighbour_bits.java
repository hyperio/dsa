package bit_manipulation;

/**
 * Write a function to swap pairwise bits with as few instructions as possible.
 * That is, swap bit 0 and bit 1, bit 2 and bit 3, and so on.
 * EXAMPLE
 *
 * input:  10 01 01
 * output: 01 10 10
 */
class cci_5_7_Swap_neighbour_bits {

  /**
   * On 8 bits
   * 1. Select the odd bits only with a mask 10101010 = 0xAA and move them one position to the right
   * 2. Select the even bits only with a mask 01010101 = 0x55 and move them one position to the left
   * 3. Or the two values
   */
  static int swap1(int num) {
    int oddFlipped = (num & 0xAAAAAAAA) >>> 1;
    int evenFlipped = (num & 0x55555555) << 1;
    return oddFlipped | evenFlipped;
  }

}
