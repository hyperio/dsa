package strings;

public class Util {
  public static String reverseString(String s) {
    if (s.length() < 2) {
      return s;
    }

    char[] c = s.toCharArray();
    for (int i = 0, j = c.length - 1; i < j; i++, j--) {
      char tmp = c[i];
      c[i] = c[j];
      c[j] = tmp;
    }

    return new String(c);
  }
}
