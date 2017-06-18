package bit_manipulation;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;

/**
 * The parity of a binary word is
 * - 1 if the number if 1s in the word is odd
 * - 0 otherwise
 */
public class epi_5_1_Compute_parity {

  /**
   * XOR all bits
   * - 1 if odd number of 1s
   * - 0 otherwise
   *
   * COMPLEXITY
   *
   * Time: O(b) where b is the number of bits
   *       Here we have an int in java, so 32
   *
   * Space: O(1)
   */
  static byte parity1(int num) {
    byte result = 0;
    while (num > 0) {
      result ^= num & 1;
      num >>>= 1;
    }
    return result;
  }

  /**
   * Keep clearing the ls1
   *
   * COMPLEXITY
   *
   * Time: O(s) where s is the number of set bits (ie. 1s)
   *
   * Space: O(1)
   */
  static byte parity2(int num) {
    byte result = 0;
    while (num > 0) {
      num &= (num - 1);
      result++;
    }
    return result;
  }

  /**
   * XOR is associative
   * 1. Cache parity of all 16-bit integers
   * 2. Lookup the parity of each group of
   *    16 bits from num in the cache
   * 3. XOR the results
   *
   * We could initialise the cache
   * - static
   * - lazy, with a flag on each entry indicating if it is valid
   *   (ie. computed). If not, compute, store it and set the flag.
   *
   * COMPLEXITY
   *
   * Time
   * - lookup: O(l / 16) = O(l) where l is the number of bits in num
   * - initialisation of lookup table, done once: O(s * 2^16)
   *   using parity2, where is the number of set bits in num
   *
   * Space: O(2^16)
   */
  static byte parity31(int num) {
    byte precomputedParity[] = new byte[] {};
    final int kWordSize = 16;
    final int kL16Mask = 0xFFFF;
    return (byte) (precomputedParity[num >>> (3 * kWordSize)] ^
      precomputedParity[(num >>> (2 * kWordSize)) & kL16Mask] ^
      precomputedParity[(num >>> kWordSize) & kL16Mask] ^
      precomputedParity[num & kL16Mask]);
  }

  static byte parity32(int num) throws ExecutionException {
    LoadingCache<Integer, Byte> precomputedParity = CacheBuilder.newBuilder()
      .build(new CacheLoader<Integer, Byte>() {
        @Override
        public Byte load(Integer key) throws Exception {
          return parity2(key);
        }
      });

    final int kWordSize = 16;
    final int kL16Mask = 0xFFFF;
    final int parity = precomputedParity.get(num >>> (3 * kWordSize)) ^
      precomputedParity.get((num >>> (2 * kWordSize)) & kL16Mask) ^
      precomputedParity.get((num >>> kWordSize) & kL16Mask) ^
      precomputedParity.get(num & kL16Mask);
    return (byte) parity;
  }


  /**
   * Repeat
   *   XOR upped half with lower half
   *   Disregard upper half
   * Parity is the LSB of the result
   *
   * When we are down to eg. 4 bits, we could use a lookup table
   *
   * EXAMPLE
   *
   * 1101 0110
   * 0000 1101
   * ----------
   * xxxx 10 11
   * xxxx 00 10
   * -----------
   * xxxx xx 0 1
   * xxxx xx x 0
   * -----------
   * xxxx xx x 1
   *
   * COMPLEXITY
   *
   * Time: O(log(b) where b is the number of bits.
   *
   * Space: O(1)
   */
  static int parity41(int num) {
    final int kWordLen = 32;
    for (int i = kWordLen / 2; i > 0; i /= 2) {
      num ^= (num >> i);
    }
    return num;
  }

  static int parity42(int num) {
    final int kWordLen = 32;
    for (int i = kWordLen / 2; i > 4; i /= 2) {
      num ^= (num >> i);
    }

    // & 1 because the parity is in the lsb. The rest is junk.
    return fourBitParityLookup(num) & 1;
  }

  /**
   * nth bit in the lookup table (number) represents
   * the parity of 4-bit number n.
   * There are 16 4-bit numbers.
   *
   * 0000 | 0 <- LSB
   * 0001 | 1
   * 0010 | 1
   * 0011 | 0
   * 0100 | 1
   * 0101 | 0
   * 0110 | 0
   * 0111 | 1
   * 1000 | 1
   * 1001 | 0
   * 1010 | 0
   * 1011 | 1
   * 1100 | 0
   * 1101 | 1
   * 1110 | 1
   * 1111 | 0 <- MSB
   */
  static int fourBitParityLookup(int num) {
    final int k4BitLookupTable = 0x6996; // 0110 1001 1001 0110
    return k4BitLookupTable >> num; // 0 <= num <= 15
  }
}
