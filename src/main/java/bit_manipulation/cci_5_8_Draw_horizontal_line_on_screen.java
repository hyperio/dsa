package bit_manipulation;

/**
 * A monochrome screen is stored as an array of bytes, 8 pixels per byte.
 * The width is given. Height = size of array / width.
 * Implement a function that draws a horizontal line from (x1, y) to (x2, y).
 */
class cci_5_8_Draw_horizontal_line_on_screen {

  /**
   * screen = [
   *   00000 000, 00000000, 00000000, 0000 0000,
   *   00000 000, 00000000, 00000000, 0000 0000,
   *   00000 000, 00000000, 00000000, 0000 0000
   * ]
   *
   * width = 32, x1 = 5, x2 = 28, y = 1
   */
  static void draw(byte[] screen, int width, int x1, int x2, int y) {
    int firstByteInByteStartOffset = x1 % 8; // e 5
    int firstFullByteInRow = x1 / 8;          // e 0
    if (firstByteInByteStartOffset!= 0) {
      firstFullByteInRow++;                   // e 1
    }

    int lastByteInByteEndOffset = x2 % 8;    // e 4
    int lastFullByteInRow = x2 / 8;           // e 3
    if (lastByteInByteEndOffset != 7) {
      lastFullByteInRow--;                    // e 2
    }

    int rowStartIndex = (width / 8) * y; // e 4

    // Set full byes
    for (int b = firstFullByteInRow; b <= lastFullByteInRow; b++) {
      screen[rowStartIndex + b] = (byte) 0xFF;
    }

    // 11111 111 >> 5 = 0000 0111
    byte startMask = (byte) (0XFF >> firstByteInByteStartOffset);
    // ~(1111 1111 >> (3 + 1)) = ~(0000 1111) = 1111 0000
    byte endMask = (byte) ~(0XFF >> (lastByteInByteEndOffset + 1));


    int leftPartialByteIndex = rowStartIndex + firstFullByteInRow - 1;
    int rightPartialByteIndex = rowStartIndex + lastFullByteInRow + 1;

    // If the change is to be made in the same byte, join the masks
    if (leftPartialByteIndex == rightPartialByteIndex) {
      int joinedMask = startMask & endMask;
      screen[leftPartialByteIndex] |= joinedMask;
    } else {
      // If == 0, the first byte is full, no need to go backwards
      if (firstByteInByteStartOffset != 0) {
        screen[leftPartialByteIndex] |= startMask;
      }

      // If == 7, the last byte is full, no need to go forwards
      if (lastByteInByteEndOffset != 7) {
        screen[rightPartialByteIndex] |= endMask;
      }
    }
  }

}
