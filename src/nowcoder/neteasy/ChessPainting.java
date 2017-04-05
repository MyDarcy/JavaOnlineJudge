package nowcoder.neteasy;

import com.sun.xml.internal.fastinfoset.util.StringArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by csqiang on 2017/3/26.
 *
 * @Author mr.darcy
 * Description:
 */
public class ChessPainting {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = Integer.parseInt(input.nextLine().trim());
        String[] stringArray = new String[n];
        int i = 0;
        while (input.hasNextLine()) {
            String nextLine = input.nextLine().trim();
            stringArray[i++] = nextLine;
        }


        int maxToPaint = 0;

        for (int j = 0; j < stringArray[0].length(); j++) {
            char[] charArray = new char[stringArray.length];
            for (int k = 0; k < stringArray.length; k++) {
                charArray[k] = stringArray[k].charAt(j);
            }
            Arrays.sort(charArray);
            int lastIndexOfB = new String(charArray).lastIndexOf('B');
            int max = 0;
            /*if (lastIndexOfB == -1) {
                max = charArray.length;
            }
            if (lastIndexOfB < charArray.length / 2) {
                max = charArray.length - 1 - lastIndexOfB;
            } else {
                max = lastIndexOfB + 1;
            }*/

            max = getLongestSameColor(charArray);

            if (max > maxToPaint) {
                maxToPaint = max;
            }
        }
        System.out.println(maxToPaint);

    }

    private static int getLongestSameColor(char[] charArray) {
        String s = String.valueOf(charArray);
        int from = 0;
        while (from < charArray.length - 1) {

        }
        return 0;
    }
}
/*

8
BBWWBBWW
BBWWBBWW
WWBBWWBB
WWBBWWBB
BBWWBBWW
BBWWBBWW
WWBBWWBB
WWBBWWBB

对应输出应该为:

2

你的输出为:

4

 */