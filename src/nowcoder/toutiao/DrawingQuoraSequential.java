package nowcoder.toutiao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by csqiang on 2017/3/30.
 *
 * @Author mr.darcy
 * Description:
 *
时间限制：C/C++语言 2000MS；其他语言 4000MS
内存限制：C/C++语言 131072KB；其他语言 655360KB
题目描述：
垂直绘制一个中括号的序列 并用中括号的大小表示层次关系
绘制 [[[]]][] 这个括号序列
如图：



绘图遵守以下原则：
各个括号之间没有空格 只有在左右括号在最里层配对时 中间才会有一条空行
里层的括号必定小于外层的括号
同一层次的括号大小相同（比如上述的样例 最下面的括号和上面的大括号相同大小）
 */
public class DrawingQuoraSequential {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String line = input.nextLine();

        List<Integer> list = new ArrayList<>();
        char[] chars = line.toCharArray();
        /*for (int i = 0; i < chars.length; i++) {
            if ()
        }*/

        int currentIndex = 0;
        while (true) {
            /*if (current == '[' && line.indexOf(currentIndex + 1, ']') != -1) {
                int index = line.indexOf(currentIndex + 1, ']');
                list.add(index - current);
                currentIndex = currentIndex + (index - currentIndex) * 2;
            }*/

            if (line.indexOf(']', currentIndex + 1) != -1) {
                int index = line.indexOf(']', currentIndex + 1);
//                System.out.println(index);
                int length = index - currentIndex;
                list.add(length);
                currentIndex = currentIndex + length * 2;
            } else {
                break;
            }
        }
//        System.out.println(list.size());

        /*for (Integer integer : list) {
            System.out.println(integer);
        }*/

        int max = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) > max) {
                max = list.get(i);
            }
        }
       /* System.out.println(max);*/

       int printMaxLength = 2 * max + 1;
        for (int i = 0; i < list.size(); i++) {
            int length = list.get(i);
            boolean b = false;
            if (length == 1) {
                b = true;
            }
            printHeadTailLine(max);
            while (length >= 0) {
                printBody(length, max, b);
                length--;
            }
            length = 1;
            while (length <= list.get(i)) {
                printBody(length, max, b);
                length++;
            }
            printHeadTailLine(max);
        }

    }

    private static void printBody(int length, int max, boolean b) {
        if (length == 0) {

            System.out.println();
        } else if (length == 1) {
            if (b) {
                System.out.print("|");
                for (int i = 0; i < 2 * max - 1; i++) {
                    System.out.print(" ");
                }
                System.out.println("|");
            } else {

            }
        } else {
            for (int i = 0; i < max - length; i++) {
                System.out.print(" ");
            }
            System.out.print("+");
            System.out.print("|");
            for (int i = 0; i < 2 * length -3; i++) {
                System.out.print("-");
            }
            System.out.print("|");
            System.out.println("+");
        }
    }

    private static void printHeadTailLine(int length) {
        System.out.print("+");
        for (int i = 0; i < 2 * length - 1; i++) {
            System.out.print("-");
        }
        System.out.println("+");
    }
}
