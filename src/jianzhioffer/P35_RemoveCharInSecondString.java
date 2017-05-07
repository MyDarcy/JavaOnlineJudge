package jianzhioffer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by darcy
 * 2017/5/7--16:14
 * Description:
 * 给定两个字符串，在第一个字符串中移除所有出现在第二个字符串中子符;
 */
public class P35_RemoveCharInSecondString {
    public String soluton(String str, String second) {
        Set<Character> set = new HashSet<Character>();
        for (int i = 0; i < second.length(); i++) {
            set.add(second.charAt(i));
        }
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            if (set.contains(str.charAt(i))) {
                continue;
            }
            result += str.charAt(i);
        }
        return result;
    }

    public static void main(String[] args) {
        P35_RemoveCharInSecondString demo = new P35_RemoveCharInSecondString();
        String result = demo.soluton("We are Student..", "aeiou");
        System.out.println(result);
    }
}
