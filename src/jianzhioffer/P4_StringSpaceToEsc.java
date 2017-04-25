package jianzhioffer;

/**
 * Created by darcy
 * 2017/3/26--0:26
 * Description:
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
