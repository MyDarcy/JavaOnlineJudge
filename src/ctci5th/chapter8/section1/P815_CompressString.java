package ctci5th.chapter8.section1;

/**
 * Created by darcy
 * 2017/6/24--0:11
 * Description:
 *
 * 利用字符重复出现的次数，编写一个方法，实现基本的字符串压缩功能。
 * 比如，字符串“aabcccccaaa”经压缩会变成“a2b1c5a3”。若压缩后的字符串没有变短，则返回原先的字符串。
 * 给定一个string iniString为待压缩的串(长度小于等于10000)，
 * 保证串内字符均由大小写英文字母组成，返回一个string，为所求的压缩后或未变化的串。
 */
public class P815_CompressString {
    /**
     * 直观做法.
     * @param iniString
     * @return
     */
    public String zipString(String iniString) {
        // write code here
        if (iniString == null || iniString.length() <= 1) {
            return iniString;
        }

        StringBuilder sb = new StringBuilder();
        int previous = 0;
        while (true) {
            int i = previous + 1;
            for (; i < iniString.length(); i++) {
                if (iniString.charAt(i) != iniString.charAt(previous)) {
                    break;
                }
            }
            if (i == iniString.length()) {
                break;
            }
            sb.append(iniString.charAt(previous) + "" + (i - previous));
            previous = i;
        }
        sb.append(iniString.charAt(previous) + "" + (iniString.length() - previous));

        if (sb.length() >= iniString.length()) {
            return iniString;
        }
        return sb.toString();
    }

    /**
     * 先计算压缩字符串的长度.
     * 然后创建如此大小的数组, 再遍历一遍原字符串.
     */
    public int countLength(String iniString) {
        if (iniString == null || iniString.isEmpty()) {
            return 0;
        }
        char ch = iniString.charAt(0);
        int size = 0;
        int count = 1;
        for (int i = 1; i < iniString.length(); i++) {
            if (iniString.charAt(i) == ch) {
                count++;
            } else {
                ch = iniString.charAt(i);
                size += 1 + String.valueOf(count).length();
                count = 1;
            }
        }
        size += 1 + String.valueOf(count).length();
        return size;
    }

    public String zipString2(String iniString) {
        int zipLength = countLength(iniString);
        if (zipLength >= iniString.length()) {
            return iniString;
        }

        char[] array = new char[zipLength];
        char ch = iniString.charAt(0);
        int count = 1;
        // 同步目标数组.
        int index = 0;
        for (int i = 1; i < iniString.length(); i++) {
            if (iniString.charAt(i) == ch) {
                count++;
            } else {
                index = update(array, ch, index, count);
                ch = iniString.charAt(i);
                count = 1;
            }
        }
        update(array, ch, index, count);
        return String.valueOf(array);

    }

    private int update(char[] array, char ch, int index, int count) {
        array[index] = ch;
        index++;

        char[] s = String.valueOf(count).toCharArray();
        for (char c : s) {
            array[index++] = c;
        }

        return index;
    }

    public static void main(String[] args) {
        String iniString = "aabbccddeee";
        P815_CompressString demo = new P815_CompressString();
        System.out.println(demo.zipString(iniString));
    }
}
