package jianzhioffer;

import java.util.*;

/**
 * Created by darcy
 * 2017/5/20--0:20
 * Description:
 * 请实现一个函数用来找出字符流中第一个只出现一次的字符。
 * 例如，当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"。
 * 当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。
 * <p>
 * 如果当前字符流没有存在出现一次的字符，返回#字符。
 */
public class P55_FirstNonDuplicateCharInStream {


    /**
     * 思路：要么利用理由的hash表
     * 要么自己构造一个hash表, 因为字母最大256个，可以一次性分配;
     */
    private Map<Character, Integer> linkedHashMap = new LinkedHashMap<>();


    //Insert one char from stringstream
    public void Insert(char ch) {
        if (linkedHashMap.containsKey(ch)) {
            Integer integer = linkedHashMap.get(ch);
            linkedHashMap.put(ch, integer + 1);
        } else {
            linkedHashMap.put(ch, 1);
        }
    }

    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce() {
        Iterator<Map.Entry<Character, Integer>> iterator = linkedHashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Character, Integer> entry = iterator.next();
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return '#';
    }


    /**
     * 链接：https://www.nowcoder.com/questionTerminal/00de97733b8e4f97a3fb5c680ee10720
     */


    int[] hashtable = new int[256];
    StringBuffer s = new StringBuffer();

    //Insert one char from stringstream
    public void Insert2(char ch) {
        s.append(ch);
        if (hashtable[ch] == 0)
            hashtable[ch] = 1;
        else hashtable[ch] += 1;
    }

    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce2() {
        char[] str = s.toString().toCharArray();
        for (char c : str) {
            if (hashtable[c] == 1)
                return c;
        }
        return '#';
    }

    /**
     * 使用一个HashMap来统计字符出现的次数，同时用一个ArrayList来记录输入流，
     * 每次返回第一个出现一次的字符都是在这个ArrayList（输入流）中的字符作为key去map中查找。
     * 链接：https://www.nowcoder.com/questionTerminal/00de97733b8e4f97a3fb5c680ee10720
     */


    HashMap<Character, Integer> map = new HashMap();
    ArrayList<Character> list = new ArrayList<Character>();
    // 改成StringBuilder不是更好!!!!

    //Insert one char from stringstream
    public void Insert3(char ch) {
        if (map.containsKey(ch)) {
            map.put(ch, map.get(ch) + 1);
        } else {
            map.put(ch, 1);
        }

        list.add(ch);
    }

    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce3() {
        char c = '#';
        for (char key : list) {
            if (map.get(key) == 1) {
                c = key;
                break;
            }
        }

        return c;
    }
}
