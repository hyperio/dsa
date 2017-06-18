package bit_manipulation;

/**
 * Given a real number between 0 and 1 that is passed as a double
 * print the binary representation. If the number cannot be represented
 * in binary with at most 32 characters print "ERROR".
 */
class cci_5_2_Double_to_binary_string {

  static String convert1(double num) {
    if (num >= 1 || num <= 0) {
      return "ERROR";
    }

    StringBuilder buff = new StringBuilder(32);
    while (num > 0) {
      if (buff.length() >= 32) {
        return "ERROR";
      }

      num *= 2;
      if (num >= 1) {
        buff.append(1);
        num -= 1;
      } else {
        buff.append(0);
      }
    }
    return buff.toString();
  }

  static String convert2(double num) {
    if (num >= 1 || num <= 0) {
      return "ERROR";
    }

    StringBuilder buff = new StringBuilder(32);
    double comp = 0.5;
    while (num > 0) {
      if (buff.length() >= 32) {
        return "ERROR";
      }

      if (num >= comp) {
        buff.append(1);
        num -= comp;
      } else {
        buff.append(0);
      }
      comp /= 2;
    }
    return buff.toString();
  }

}
