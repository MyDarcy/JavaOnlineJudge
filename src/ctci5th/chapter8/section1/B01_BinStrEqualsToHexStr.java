package ctci5th.chapter8.section1;

/**
 * Created by darcy
 * 2017/6/23--0:16
 * Description:
 *
 * 二进制表示的字符串和十六进制的字符串是否相等.
 */
public class B01_BinStrEqualsToHexStr {

    public boolean compareBinToHex(String binary, String hex) {
        int n1 = toNumber(binary.toLowerCase(), 2);
        int n2 = toNumber(hex.toLowerCase(), 16);
        System.out.println(n1 + "\t" + n2);

        if (n1 < 0 || n2 < 0) {
            return false;
        } else {
            return n1 == n2;
        }
    }

    private int toNumber(String str, int base) {
        // 只能是2进制串或者16进制串.
        if (base < 2 || (base > 10 && base != 16)) {
            return -1;
        }
        int value = 0;
        for (int i = 0; i < str.length(); i++) {
            value *= base;
            value += charToNumber(str.charAt(i));
        }
        return value;

        // 更健全的做法.
        /*
        if (base < 2 || (base > 10 && base != 16)) {
            return -1;
        }

        int value = 0;
        for (int i = str.length() - 1; i >= 0; i--) {
            int digit = charToNumber(str.charAt(i));
            if (digit < 0 || digit >= base) {
                return -1;
            }
            int exp = str.length() - i - 1;
            value += digit * Math.pow(base, exp);
        }
        return value;
        */

    }

    private int charToNumber(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        } else if (c >= 'a' && c <= 'z') {
            return c - 'a' + 10;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        B01_BinStrEqualsToHexStr demo = new B01_BinStrEqualsToHexStr();
        demo.compareBinToHex("111111111100", "ffc");
    }



}
