package eopi.ch7_strings;

/**
 * Author by darcy
 * Date on 17-9-18 下午6:39.
 * Description:
 * http://www.jianshu.com/p/e2bd1ee482c3
 * <p>
 * 字符串的KMP算法.
 */
interface StringMatcher {
  /**
   * 从原字符串中查找模式字符串的位置,如果模式字符串存在,则返回模式字符串第一次出现的位置,否则返回-1
   *
   * @param source  原字符串
   * @param pattern 模式字符串
   * @return if substring exists, return the first occurrence of pattern
   * substring, return -1 if not.
   */
  int indexOf(String source, String pattern);
}

/**
 * 暴力匹配
 * <p>
 * 时间复杂度: O(m*n), m = pattern.length, n = source.length
 */
class ViolentStringMatcher implements StringMatcher {

  @Override
  public int indexOf(String source, String pattern) {
    int i = 0, j = 0;
    int sLen = source.length(), pLen = pattern.length();
    char[] src = source.toCharArray();
    char[] ptn = pattern.toCharArray();
    while (i < sLen && j < pLen) {
      if (src[i] == ptn[j]) {
        // 如果当前字符匹配成功,则将两者各自增1,继续比较后面的字符
        i++;
        j++;
      } else {
        // 如果当前字符匹配不成功,则i回溯到此次匹配最开始的位置+1处,也就是i = i - j + 1
        // (因为i,j是同步增长的), j = 0;
        i = i - j + 1;
        j = 0;
      }
    }
    // 匹配成功,则返回模式字符串在原字符串中首次出现的位置;否则返回-1
    if (j == pLen)
      return i - j;
    else
      return -1;
  }
}

/**
 * http://www.jianshu.com/p/e2bd1ee482c3
 *
 * 与朴素算法不同，朴素算法是当遇到不匹配字符时，向后移动一位继续匹配，而KMP算法是当遇到不匹配字符时，不是简单的
 * 向后移一位字符，而是**根据前面已匹配的字符数和模式串前缀和后缀的最大相同字符串长度数组next**的元素来确定向后移动
 * 的位数，所以KMP算法的时间复杂度比朴素算法的要少，并且是线性时间复杂度，即预处理时间复杂度是O(m)，匹配时间复杂
 * 度是O(n)。
 *
 * next数组含义：代表在模式串P中，当前下标对应的字符之前的字符串中，有多大长度的相同前缀后缀。例如如果
 * next [j] = k，代表在模式串P中，下标为j的字符之前的字符串中有最大长度为k 的相同前缀后缀。
 *
 * KMP算法的核心就是求next数组，在字符串匹配的过程中，一旦某个字符匹配不成功，next数组就会指导模式串P到底该相对于
 * S右移多少位再进行下一次匹配，从而避免无效的匹配。
 *
 * next数组求解方法：
 *
 * next[0] = -1。
 * 如果已知next[j] = k,如何求出next[j+1]呢?具体算法如下:
 * 如果p[j] = p[k], 则next[j+1] = next[k] + 1;
 * 如果p[j] != p[k], 则令k=next[k],如果此时p[j]==p[k],则next[j+1]=k+1,如果不相等,则继续递归前缀索引,
 *    令 k=next[k],继续判断,直至k=-1(即k=next[0])或者p[j]=p[k]为止
 */
class KMPStringMatcher implements StringMatcher {

  /**
   * 获取KMP算法中pattern字符串对应的next数组
   *
   * @param pattern 模式字符串对应的字符数组
   * @return
   */
  protected int[] getNext(char[] pattern) {
    // 已知next[j] = k,利用递归的思想求出next[j+1]的值
    // 如果已知next[j] = k,如何求出next[j+1]呢?具体算法如下:
    // 1. 如果pattern[j] = pattern[k], 则next[j+1] = next[k] + 1;
    // 2. 如果pattern[j] != pattern[k], 则令k=next[k],如果此时pattern[j]==pattern[k],则next[j+1]=k+1,
    // 如果不相等,则继续递归前缀索引,令 k=next[k],继续判断,直至k=-1(即k=next[0])或者pattern[j]=pattern[k]为止
    int patternLength = pattern.length;
    int[] next = new int[patternLength];
    // next[k]表示k索引之前的模式字符串的前缀和后缀有多少char匹配.
    int k = -1;

    // 迭代模式串.
    int j = 0;
    next[0] = -1; // next数组中next[0]为-1
    while (j < patternLength - 1) {
      if (k == -1 || pattern[j] == pattern[k]) {
        k++;
        j++;
        next[j] = k;
      } else {
        k = next[k];
      }
    }
    return next;
  }

  @Override
  public int indexOf(String s, String p) {
    int i = 0, j = 0;
    char[] source = s.toCharArray();
    char[] pattern = p.toCharArray();
    int sourceLength = source.length;
    int patternLength = pattern.length;

    int[] next = getNext(pattern);
    while (i < sourceLength && j < patternLength) {
      // 如果j = -1,或者当前字符匹配成功(source[i] = pattern[j]),都让i++,j++
      if (j == -1 || source[i] == pattern[j]) {
        i++;
        j++;
      } else {
        // 如果j!=-1且当前字符匹配失败,则令i不变,j=next[j],即让pattern模式串右移j-next[j]个单位
        // 因为i的前面刚好是和pattern前缀匹配的后缀.

        j = next[j];
      }
    }
    if (j == patternLength)
      return i - j;
    return -1;
  }
}

class OptimizedKMPStringMatcher extends KMPStringMatcher {

  @Override
  protected int[] getNext(char[] pattern) {
    // 已知next[j] = k,利用递归的思想求出next[j+1]的值
    // 如果已知next[j] = k,如何求出next[j+1]呢?具体算法如下:
    // 1. 如果p[j] = p[k], 则next[j+1] = next[k] + 1;
    // 2. 如果p[j] != p[k], 则令k=next[k],如果此时p[j]==p[k],则next[j+1]=k+1,
    // 如果不相等,则继续递归前缀索引,令 k=next[k],继续判断,直至k=-1(即k=next[0])或者p[j]=p[k]为止
    int pLen = pattern.length;
    int[] next = new int[pLen];
    int k = -1;
    int j = 0;
    next[0] = -1; // next数组中next[0]为-1
    while (j < pLen - 1) {
      if (k == -1 || pattern[j] == pattern[k]) {
        k++;
        j++;
        // 修改next数组求法
        if (pattern[j] != pattern[k]) {
          next[j] = k;// KMPStringMatcher中只有这一行
        } else {
          // 不能出现p[j] = p[next[j]],所以如果出现这种情况则继续递归,如 k = next[k],
          // k = next[[next[k]]
          next[j] = next[k];
        }
      } else {
        k = next[k];
      }
    }
    return next;
  }

}

public class P13_KMP {

  public static void main(String[] args) {
    StringMatcher matcher = new ViolentStringMatcher();
    // System.out.println(matcher.indexOf("helloworld", "ow"));
    matcher = new KMPStringMatcher();
    // System.out.println(matcher.indexOf("helloworld", "ow"));
    System.out.println(matcher.indexOf("abcdefgabcdeabca", "abcdeabc"));

    matcher = new OptimizedKMPStringMatcher();
    // System.out.println(matcher.indexOf("helloworld", "ow"));
  }

}
