package bit_manipulation;

public class Util {

  /**
   * 1 << 3 = 0..01000
   */
  static boolean getBit(int num, int i) {
    int mask = 1 << i;
    return (num & mask) != 0;
  }

  static boolean getBit(long num, int i) {
    long mask = 1L << i;
    return (num & mask) != 0;
  }

  static int setBit(int num, int i) {
    int mask = 1 << i;
    return num | mask;
  }


  /**
   * ~(1 << 3) = 1..10111
   */
  static int clearBit(int num, int i) {
    int mask = ~(1 << i);
    return num & mask;
  }

  /**
   * (1 << 3) - 1 = 0..01000 - 1 = 0..00111
   */
  static int clearBitsMSBthroughI(int num, int i) {
    int mask = (1 << i) - 1;
    return num & mask;
  }

  /**
   * Clear bits 0 through 2, ie. 0, 1 and 2.
   *
   * 1) ~((1 << 3) - 1) = ~(0..01000 - 1) = ~(0..00111) = 1..11000
   * 2) -1 in 2s complement is a sequence of 1s. -1 << (i + 1)
   */
  static int clearBitsIthrough0(int num, int i) {
    int mask = ~((1 << (i + 1)) - 1);
    // int mask = -1 << (i + 1);
    return num & mask;
  }

  /**
   * EXAMPLE
   *
   * x = 1100, ls1 = 0100
   * masks = [0101, 0011]
   * 1 * [(0100 & 0101) == 0] + 2 * [(0100 & 0011) == 1] = 2
   *
   * Works because ls1 & mask[i] == 0 when there is no 1
   * on postions that are multiple of i (mask[i] contains only 1s up to i).
   *
   * Complexity
   *   - time: O(log2(num))
   */
  static int getPositionOfLs1(int num) {
    int masks[] = new int[] {
      0x55555555, // 0101 0101 0101 0101 0101 0101 0101 0101
      0x33333333, // 0011 0011 0011 0011 0011 0011 0011 0011
      0x0f0f0f0f, // 0000 1111 0000 1111 0000 1111 0000 1111
      0x00ff00ff, // 0000 0000 1111 1111 0000 0000 1111 1111
      0x0000ffff  // 0000 0000 0000 0000 1111 1111 1111 1111
    };

    // Clear all bits except the least significant 1
    int ls1 = num & ~(num - 1);
    int count = 0;

    for (int i = 0; i < masks.length; i++) {
      if ((ls1 & masks[i]) == 0) {
        count |= (1 << i); // count += 2^i
      }
    }

    return count;
  }

  static int countNumberOf1s(int num) {
    int count = 0;
    while (num != 0) {
      num &= (num - 1); // clear the ls1
      count++;
    }
    return count;
  }


  static String toBinaryString(int num) {
    StringBuilder buff = new StringBuilder(32);
    for (int i = 0; i < 32; i++) {
      if ((num & 1) == 1) {
        buff.append('1');
      } else {
        buff.append('0');
      }
      num >>= 1;
    }
    return buff.reverse().toString();
  }

  static String toBinaryString(byte num) {
    StringBuilder buff = new StringBuilder(32);
    for (int i = 0; i < 8; i++) {
      if ((num & 1) == 1) {
        buff.append('1');
      } else {
        buff.append('0');
      }
      num >>= 1;
    }
    return buff.reverse().toString();
  }

  static String toBinaryString(int num, int len) {
    StringBuilder buff = new StringBuilder(32);
    for (int i = 0; i < 32; i++) {
      if ((num & 1) == 1) {
        buff.append('1');
      } else {
        buff.append('0');
      }
      num >>= 1;
    }
    return strings.Util.reverseString(buff.substring(0, len));
  }

  static int fromBinaryString(String str) {
    if (str.isEmpty()) {
      // exception handling
    }
    int num = 0;
    for (int i = 0; i < str.length(); i++) {
      if (str.charAt(i) == '1') {
        num |= (1 << (str.length() - i - 1));
      }
    }
    return num;
  }
}
