package jianzhioffer;

/**
 * Created by darcy
 * 2017/3/26--0:26
 * Description:
 * 题目描述
 请实现一个函数，将一个字符串中的空格替换成“%20”。例如，
 当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 */
public class P4_StringSpaceToEsc {

    public String replaceSpace(StringBuffer str) {
        if (str == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                stringBuilder.append("%20");
            } else {
                stringBuilder.append(str.charAt(i));
            }
        }
        return stringBuilder.toString();
    }


    public static void main(String[] args) {
        P4_StringSpaceToEsc p4_stringSpaceToEsc = new P4_StringSpaceToEsc();
        String s = p4_stringSpaceToEsc.replaceSpace(new StringBuffer(" abc cba "));
        System.out.println(s);
    }
}
