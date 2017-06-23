package crackingTheCodingInterview;

/**
 * Created by darcy
 * 2017/6/24--0:13
 * Description:
 * 翻转字符串。
 */
public class P812_ReverseString {
    public String reverseString(String iniString) {
        // write code here
        if (iniString.length() <= 1) {
            return iniString;
        }
        int length = iniString.length();
        char[] chars = iniString.toCharArray();
        char temp = '0';
        for (int i = 0; i < length / 2; i++) {
            temp = chars[i];
            chars[i] = chars[length - 1 - i];
            chars[length - 1 - i] = temp;
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        P812_ReverseString demo = new P812_ReverseString();
        System.out.println(demo.reverseString("abcde"));
        System.out.println(demo.reverseString("abcdef"));
    }
}
