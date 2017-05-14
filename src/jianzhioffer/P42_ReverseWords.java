package jianzhioffer;

/**
 * Created by darcy
 * 2017/5/13--10:30
 * Description:
 */
public class P42_ReverseWords {

    /**
     * 思路：先逆序，然后跟踪每一个单词, 对每一个单词逆序;
     * @param str
     * @return
     */
    public String ReverseSentence(String str) {
        StringBuilder sb = new StringBuilder(str).reverse();
        /*String[] array = sb.toString().split("\\s+");
        StringBuilder result = new StringBuilder();
        // word 之间的空格可以是多个,所以不行。
        for (int i = 0; i < array.length; i++) {
            result.append(new StringBuilder(array[i]).reverse());
        }
        return result.toString();*/
//        System.out.println(sb.toString());

        int first = 0;
        int last = 0;
        StringBuilder result = new StringBuilder();
        while (first < sb.length()) {
            if (sb.charAt(first) == ' ') {
                result.append(' ');
                first++;
                last++;
                // 这里 last = sb.length()在前, sb.charAt(xx)在后.
            } else if (last == sb.length() ||sb.charAt(last) == ' ') {
                result.append(new StringBuilder(sb.substring(first, last)).reverse());
                first = last;
            } else {
                last++;
            }

        }

        return result.toString();

    }

    public String ReverseSentence2(String str) {
        return null;
    }

    public static void main(String[] args) {
        P42_ReverseWords demo = new P42_ReverseWords();
        System.out.println(demo.ReverseSentence("I am a Student."));
    }
}
