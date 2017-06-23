package crackingTheCodingInterview;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by darcy
 * 2017/6/24--0:20
 * Description:
 *
 * 两个字符串同构，即重排一个能够编程另一个.
 */
public class P813_TwoStringSameComposition {
    public boolean checkSam(String stringA, String stringB) {
        // write code here
        char[] a = stringA.toCharArray();
        Arrays.sort(a);
        char[] b = stringB.toCharArray();
        Arrays.sort(b);
        return new String(a).equals(new String(b));
    }

    public boolean checkSam2(String stringA, String stringB) {
        // write code here
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < stringA.length(); i++) {
            char key = stringA.charAt(i);
            if (map.containsKey(key)) {
                map.put(key, map.get(key) + 1);
            } else {
                map.put(key, 1);
            }
        }

        for (int i = 0; i < stringB.length(); i++) {
            char key = stringB.charAt(i);
            if (!map.containsKey(key)) {
                return false;
            } else {
                if (map.get(key) > 0) {
                    map.put(key, map.get(key) - 1);
                } else {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String stringA = "abcd";
        String stringB = "bcad";
        P813_TwoStringSameComposition demo = new P813_TwoStringSameComposition();
        System.out.println(demo.checkSam(stringA, stringB));
        System.out.println(demo.checkSam2(stringA, stringB));

    }
}
