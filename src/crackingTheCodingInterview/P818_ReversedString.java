package crackingTheCodingInterview;

/**
 * Created by darcy
 * 2017/6/26--0:26
 * Description:
 *
 * 假定我们都知道非常高效的算法来检查一个单词是否为其他字符串的子串。
 * 请将这个算法编写成一个函数，给定两个字符串s1和s2，请编写代码检查s2是否为s1旋转而成，
 * 要求只能调用一次检查子串的函数。
 给定两个字符串s1,s2,请返回bool值代表s2是否由s1旋转而成。
 字符串中字符为英文字母和空格，区分大小写，字符串长度小于等于1000。
 测试样例：
 */
public class P818_ReversedString {
    public boolean checkReverseEqual(String s1, String s2) {
        // write code here
        return (s1 + s1).indexOf(s2) != -1;
    }

    public static void main(String[] args) {
        P818_ReversedString demo = new P818_ReversedString();
        System.out.println(demo.checkReverseEqual("Hello world", "worldhello "));
        System.out.println(demo.checkReverseEqual("waterbottle", "erbottlewat"));

    }

}
