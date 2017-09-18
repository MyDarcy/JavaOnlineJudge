package eopi.ch7_strings;

/**
 * Author by darcy
 * Date on 17-9-18 下午4:23.
 * Description:
 * <p>
 * 根据长度的进行编解码压缩.
 * For example, the RLE of "aaaabcccaa" is "4alb3c2a". The decoding of "3e4f2e" returns "eeeffffee"
 */
public class P12_RunLengthEncodingDecoding {

  /**
   * @param str
   * @return
   */
  public static String encode(String str) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < str.length(); i++) {
      int count = 1;
      while (i + 1 < str.length() && str.charAt(i) == str.charAt(i + 1)) {
        i++;
        count++;
      }
      sb.append(count);
      sb.append(str.charAt(i));
    }
    return sb.toString();
  }

  /**
   * @param str
   * @return
   */
  public static String decode(String str) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < str.length() / 2; i++) {
      int number = Character.getNumericValue(str.charAt(2 * i));
      char c = str.charAt(2 * i + 1);
      for (int j = 0; j < number; j++) {
        sb.append(c);
      }
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    String encStr = encode("aaaabcccaafbg");
    System.out.println(encStr);

    String decStr = decode("3e4f2e");
    System.out.println(decStr);
  }

}
