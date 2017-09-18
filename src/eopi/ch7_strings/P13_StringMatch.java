package eopi.ch7_strings;

/**
 * Author by darcy
 * Date on 17-9-18 下午5:02.
 * Description:
 * <p>
 * 字符串匹配算法.
 * 在字符串s找目标t字符串.
 * <p>
 * 暴力方法不可取.
 * <p>
 * There are three linear time string matching algorithms: KMP, Boyer-Moore, and Rabin-Karp.
 * Rabin-Karp算法是最简单的一种.
 */
public class P13_StringMatch {

  /**
   * rabinKarp的想法是记录s中连续t.length个字符的fingerprint, 如果和target字符串的fingerprint相同,那么
   * 再比较equals是否相等. 优点类似hashCode/equals那一套机制.整个过程只需要遍历一遍字符串, 但是需要不断的更新
   * s中t.length()字符串的fingerprint. **fingerprint一般就是用使用递增的hash函数计算hash码**.
   * <p>
   * The key to efficiency is using an incremental hash function, such as a function
   * with the property that the hash code of a string is an additive function of each
   * individual character.
   *
   * @param s
   * @param target
   * @return
   */
  public static int rabinKarp(String s, String target) {

    if (s == null || target == null || s.length() < target.length()) {
      return -1; // 没有找到.
    }

    // 这里是base等于26.严格的单调递增.
    int base = 26;
    int sHash = 0;
    int tHash = 0;
    int power = 1;
    for (int i = 0; i < target.length(); i++) {
      power = i > 0 ? base * power : 1;
      sHash = sHash * base + s.charAt(i);
      tHash = tHash * base + target.charAt(i);

    }

    for (int i = target.length(); i < s.length(); i++) {
      // 计算[i-target.length(), i)的字符串是否和target相等.
      if (tHash == sHash && s.substring(i - target.length(), i).equals(target)) {
        return i - target.length();
      }
      // 去掉最高位.
      sHash = sHash - s.charAt(i - target.length()) * power;
      // remain * base + 最低位.
      sHash = sHash * base + s.charAt(i);
    }

    // i = s.length() - 1只是处理了上一次的.
    if (tHash == sHash && s.substring(s.length() - target.length(), s.length()).equals(target)) {
      return s.length() - target.length();
    }

    return -1;
  }

  public static void main(String[] args) {
    int index = rabinKarp("abcdefghijklmnopqrstuvwxyz", "opqrst");
    System.out.println(index);

  }
}
