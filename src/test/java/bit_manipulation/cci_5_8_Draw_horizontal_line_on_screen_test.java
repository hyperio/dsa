package bit_manipulation;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;
import static bit_manipulation.cci_5_8_Draw_horizontal_line_on_screen.*;

public class cci_5_8_Draw_horizontal_line_on_screen_test {

  void print(byte[] screen, int width) {
    int byteWidth = width / 8;
    int height = screen.length / byteWidth;

    for (int i = 0; i < height; i++) {
      for (int j = i * byteWidth; j < (i + 1) * byteWidth; j++) {
        System.out.print(Util.toBinaryString(screen[j]) + ' ');
      }
      System.out.println();
    }
  }

  @Test
  public void drawTestSeparateBytes() throws Exception {
    byte[] screen = new byte[] {
      0, 0, 0, 0,
      0, 0, 0, 0,
      0, 0, 0, 0
    };

    draw(screen, 32, 5, 28, 1);

    byte[] expected = new byte[] {
      0, 0, 0, 0,
      0x07, (byte)0xFF, (byte)0xFF, (byte)0xF8,
      0, 0, 0, 0
    };

    assertArrayEquals(expected, screen);
  }

  @Test
  public void drawTestSameByte() throws Exception {
    byte[] screen = new byte[] {
      0, 0, 0, 0,
      0, 0, 0, 0,
      0, 0, 0, 0
    };

    draw(screen, 32, 10, 14, 1);

    byte[] expected = new byte[] {
      0, 0, 0, 0,
      0, 0x3E, 0, 0,
      0, 0, 0, 0
    };

    assertArrayEquals(expected, screen);
  }

}