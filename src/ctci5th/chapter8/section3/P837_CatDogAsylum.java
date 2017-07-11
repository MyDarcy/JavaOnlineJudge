package ctci5th.chapter8.section3;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Author by darcy
 * Date on 17-7-8 上午11:19.
 * Description:
 *
 *
 * nowcoder:
 * 有家动物收容所只收留猫和狗，但有特殊的收养规则，收养人有两种收养方式，第一种为直接收养所有动物中最早进入收容所的，
 * 第二种为选择收养的动物类型（猫或狗），并收养该种动物中最早进入收容所的。 给定一个操作序列int[][2] ope
 * (C++中为vector<vector<int>>)代表所有事件。若第一个元素为1，则代表有动物进入收容所，第二个元素为动物的编号，
 * 正数代表狗，负数代表猫；若第一个元素为2，则代表有人收养动物，第二个元素若为0，则采取第一种收养方式，若为1，
 * 则指定收养狗，若为-1则指定收养猫。请按顺序返回收养的序列。若出现不合法的操作，即没有可以符合领养要求的动物，
 * 则将这次领养操作忽略。
 * 测试样例：
 * [[1,1],[1,-1],[2,0],[2,-1]]
 * 返回：[1,-1]
 */
public class P837_CatDogAsylum {


    /**
     * 维护一个栈,但是需要在哪一个方式获取动物的时候遍历动物链表,但是这样的复杂度比较高一点.
     * 具体java解法:
     * https://www.nowcoder.com/questionTerminal/6235a76b1e404f748f7c820583125c50
     * @param ope
     * @return
     */
    public ArrayList<Integer> asylum(int[][] ope) {
        // write code here
        Deque<Integer> animals = new LinkedList<>();
        Integer op = 0;
        Integer animal = 0;
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < ope.length; i++) {
            op = ope[i][0];
            animal = ope[i][1];
            if (op == 1) {
                if (animal > 0) {

                } else if (animal < 0) {

                }
            } else if (op == 2) {
                if (animal > 0) {

                } else if (animal < 0) {

                }
            }
        }

        return null;
    }

}
