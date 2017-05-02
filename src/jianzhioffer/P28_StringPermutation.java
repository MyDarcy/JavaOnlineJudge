package jianzhioffer;

import java.util.*;

/**
 * Created by darcy
 * 2017/5/2--23:49
 * Description:
 * 输入一个字符串,按字典序打印出该字符串中字符的所有排列。
 * 例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
 *
 * 输入一个字符串,长度不超过9(可能有字符重复),字符只包括大小写字母。
 *
 */
public class P28_StringPermutation {



    private ArrayList<String> list = new ArrayList<>();

    /**
     * 解题思路: 字符串的所有排列==第一个字符的所有可能情况+后面所有字符的排列;
     * @param str
     * @return
     */
    public ArrayList<String> Permutation(String str) {

        int strLength = str.length();

        // 传入StringBuilder;
        Permutation(new StringBuilder(str), 0, strLength);
        /*return new ArrayList<>(new LinkedHashSet<>(list));*/

        // 因为输入的字符可能是重复的;而且牛客网上需要结果按序排列;
        Collections.sort(list);
        return new ArrayList<>(new LinkedHashSet<>(list));
    }

    private void Permutation(StringBuilder sb, int start, int strLength) {
        if (start == strLength - 1) {
            list.add(sb.toString());
        } else {
            // 所有字符都与第一个字符交换，这样第一个字符就有所有可能的取值情况;
            for (int i = start; i < strLength; i++) {
                swap(sb, i, start);
                Permutation(sb, start + 1, strLength);
                swap(sb, i, start);
            }
        }
    }

    private void swap(StringBuilder sb, int i, int start) {
        char temp = sb.charAt(i);
        sb.setCharAt(i, sb.charAt(start));
        sb.setCharAt(start, temp);
    }

}
