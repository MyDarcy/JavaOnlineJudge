package jianzhioffer;

/**
 * Created by darcy
 * 2017/5/17--0:52
 * Description:
 * <p>
 * 请实现一个函数用来匹配包括'.'和'*'的正则表达式。
 * 模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（包含0次）。
 * 在本题中，匹配是指字符串的所有字符匹配整个模式。
 * 例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配
 */
public class P53_Regex {


    /**
     * @param str
     * @param pattern
     * @return TODO not sovled...
     */
    public boolean match(char[] str, char[] pattern) {

        if (str == null || pattern == null) {
            return false;
        }

        if (str.length == 0 && pattern.length == 0) {
            return true;
        }

        return match(str, 0, pattern, 0);
    }

    private boolean match(char[] str, int strIndex, char[] pattern, int patternIndex) {
        if (strIndex == str.length && patternIndex == pattern.length) {
            return true;
        }

        if (strIndex != str.length && patternIndex == pattern.length) {
            return false;
        }

        if (strIndex != str.length) {
            if (patternIndex < pattern.length - 1) {
                if (pattern[patternIndex + 1] == '*') {
                    if (str[strIndex] == pattern[patternIndex] || (pattern[patternIndex] == '.' && strIndex != str.length)) {
                        // 匹配一个字符，然后进入下一个模式;
                        return match(str, strIndex + 1, pattern, patternIndex + 2)
                                // 匹配了一个字符，仍然留在本模式
                                || match(str, strIndex + 1, pattern, patternIndex)
                                // 忽略了这个模式；
                                || match(str, strIndex, pattern, patternIndex + 2);
                    } else {
                        return match(str, strIndex, pattern, patternIndex + 2);
                    }
                }
            }
        } else {
            // 第一个str已经到了末尾, 然后pattern是a*b*c*.*等等...
            if (patternIndex + 1 < pattern.length && pattern[patternIndex + 1] == '*') {
                return match(str, strIndex, pattern, patternIndex + 2);
            }
            return false;
        }

        if (str[strIndex] == pattern[patternIndex] || (strIndex != str.length && pattern[patternIndex] == '.')) {
            return match(str, strIndex + 1, pattern, patternIndex + 1);
        }

        return false;
    }


    /**
     * 链接：https://www.nowcoder.com/questionTerminal/45327ae22b7b413ea21df13ee7d6429c
     *
     * @param str
     * @param strIndex
     * @param pattern
     * @param patternIndex
     * @return
     */
    public boolean matchCore(char[] str, int strIndex, char[] pattern, int patternIndex) {
        //str到尾，pattern到尾，匹配成功
        if (strIndex == str.length && patternIndex == pattern.length) {
            return true;
        }
        //str未到尾，pattern到尾，匹配失败
        if (strIndex != str.length && patternIndex == pattern.length) {
            return false;
        }
        //str到尾，pattern未到尾(不一定匹配失败，因为a*可以匹配0个字符)
        if (strIndex == str.length && patternIndex != pattern.length) {
            //只有pattern剩下的部分类似a*b*c*的形式，才匹配成功
            if (patternIndex + 1 < pattern.length && pattern[patternIndex + 1] == '*') {
                return matchCore(str, strIndex, pattern, patternIndex + 2);
            }
            return false;
        }

        //str未到尾，pattern未到尾
        if (patternIndex + 1 < pattern.length && pattern[patternIndex + 1] == '*') {
            if (pattern[patternIndex] == str[strIndex] || pattern[patternIndex] == '.') { // || (pattern[patternIndex] == '.' && strIndex != str.length)
                return matchCore(str, strIndex, pattern, patternIndex + 2)//*匹配0个，跳过
                        || matchCore(str, strIndex + 1, pattern, patternIndex + 2)//*匹配1个，跳过
                        || matchCore(str, strIndex + 1, pattern, patternIndex);//*匹配1个，再匹配str中的下一个
            } else {
                //直接跳过*（*匹配到0个）
                return matchCore(str, strIndex, pattern, patternIndex + 2);
            }
        }

        if (pattern[patternIndex] == str[strIndex] || (pattern[patternIndex] == '.' && strIndex != str.length)) {
            return matchCore(str, strIndex + 1, pattern, patternIndex + 1);
        }

        return false;
    }


    /**
     * 链接：https://www.nowcoder.com/questionTerminal/45327ae22b7b413ea21df13ee7d6429c
     * 来源：牛客网
     * <p>
     * 动态规划
     * 如果 pattern[j] == str[i] || pattern[j] == '.', 此时dp[i][j] = dp[i-1][j-1];
     * 如果 pattern[j] == '*'
     *     分两种情况:
     *     1: 如果pattern[j-1] != str[i] && pattern[j-1] != '.',
     * 此时dp[i][j] = dp[i][j-2] //a*匹配0次
     *     2: 如果pattern[j-1] == str[i] || pattern[j-1] == '.'
     *         此时dp[i][j] = dp[i][j-2] // a*匹配0次
     *         或者 dp[i][j] = dp[i][j-1] // a*匹配1次
     *         或者 dp[i][j] = dp[i-1][j] // a*匹配多次
     */


    public boolean match2(char[] str, char[] pattern) {
        boolean[][] dp = new boolean[str.length + 1][pattern.length + 1];
        dp[0][0] = true;
        for (int i = 1; i < dp[0].length; i++) {
            if (pattern[i - 1] == '*') dp[0][i] = dp[0][i - 2];
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (pattern[j - 1] == '.' || pattern[j - 1] == str[i - 1]) dp[i][j] = dp[i - 1][j - 1];
                else if (pattern[j - 1] == '*') {
                    if (pattern[j - 2] != str[i - 1] && pattern[j - 2] != '.') dp[i][j] = dp[i][j - 2];
                    else dp[i][j] = dp[i][j - 1] || dp[i][j - 2] || dp[i - 1][j];
                }
            }
        }
        return dp[str.length][pattern.length];
    }

}
/*
测试用例:
"",".*"

对应输出应该为:

true

你的输出为:

java.lang.ArrayIndexOutOfBoundsException: 0



测试用例:
"a","."

对应输出应该为:

true

你的输出为:

java.lang.ArrayIndexOutOfBoundsException: 1
 */