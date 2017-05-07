package jianzhioffer;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by darcy
 * 2017/5/7--15:35
 * Description:
 * 在一个字符串(1<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,并返回它的位置。
 * 如果字符串为空,返回-1
 */
public class P35_FirstOneTimesInString {

    /**
     * 先统计字符出现的次数，然后出现一次的放入set中，再次遍历str，得出结果。
     * @param str
     * @return
     */
    public int FirstNotRepeatingChar(String str) {
        if (str == null || str.length() == 0) {
            return -1;
        }
        int[] times = new int[52];
        char temp = '0';
        for (int i = 0; i < str.length(); i++) {
            temp = str.charAt(i);
            if ( temp <= 90) {
                times[temp - 'A']++;
            }
            if (temp > 90) {
                times[temp - 'a' + 26]++;
            }
        }
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < times.length; i++) {
            if (times[i] == 1) {
                if (i <= 25) {
                    set.add((char) ('A' + i));
                }
                if (i > 25) {
                    set.add((char) ('a' + i - 26));
                }
            }
        }
        for (int i = 0; i < str.length(); i++) {
            if (set.contains(str.charAt(i))) {
                return i;
            }
        }

        return -1;

    }

    public static void main(String[] args) {
        P35_FirstOneTimesInString demo = new P35_FirstOneTimesInString();
        int index = demo.FirstNotRepeatingChar("abaccdeff");
        System.out.println(index);

    }
}
