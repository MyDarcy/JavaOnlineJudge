package ctci5th.chapter8.section1;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by darcy
 * 2017/6/23--1:24
 * Description:
 * 请实现一个算法，确定一个字符串的所有字符是否全都不同。这里我们要求不允许使用额外的存储结构。

 * 给定一个string iniString，请返回一个bool值,True代表所有字符全都不同，False代表存在相同的字符。
 * 保证字符串中的字符为ASCII字符。字符串的长度小于等于3000。
 */
public class P811_Differents {

    // 不使用额外的空间.
    public boolean checkDifferent2(String iniString) {
        // write code here
        return !iniString.matches(".*(.)(.*\\1).*");
    }

    // 使用额外的空间
    public boolean checkDifferent(String iniString) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < iniString.length(); i++) {
            // 不等于null, 肯定有此元素.
            if (set.contains(iniString.charAt(i)) ) {
                return false;
            } else {
                set.add(iniString.charAt(i));
            }
        }

        return true;
    }

    public static void main(String[] args) {
        P811_Differents demo = new P811_Differents();
        System.out.println(demo.checkDifferent("122fakbcd"));
        System.out.println(demo.checkDifferent2("122fakbcd"));

    }
}
