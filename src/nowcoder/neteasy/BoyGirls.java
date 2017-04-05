package nowcoder.neteasy;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by csqiang on 2017/3/25.
 *
 * @Author mr.darcy
 * Description:
 * 在幼儿园有n个小朋友排列为一个队伍，从左到右一个挨着一个编号为(0~n-1)。其中有一些是男生，有一些是女生，男生用'B'表示，女生用'G'表示。小朋友们都很顽皮，当一个男生挨着的是女生的时候就会发生矛盾。作为幼儿园的老师，你需要让男生挨着女生或者女生挨着男生的情况最少。你只能在原队形上进行调整，每次调整只能让相邻的两个小朋友交换位置，现在需要尽快完成队伍调整，你需要计算出最少需要调整多少次可以让上述情况最少。例如：
GGBBG -> GGBGB -> GGGBB
这样就使之前的两处男女相邻变为一处相邻，需要调整队形2次
输入描述:
输入数据包括一个长度为n且只包含G和B的字符串.n不超过50.
 */
public class BoyGirls {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String queue =  input.nextLine().trim();

        // 如果i是男孩，那么统计它左边和右边各有多少个女孩;
        /*int[] left = new int[queue.length()];
        int[] right = new int[queue.length()];*/

        /*ArrayList<Integer> leftList = new ArrayList<>();
        ArrayList<Integer> rightList = new ArrayList<>();*/

        int boysCurrentNumber = 0;
        int girlsCurrentNumber = 0;
        int girlsRightOfBoys = 0;
        int girlsLeftOfBoys = 0;

        for (int i = 0; i < queue.length(); i++) {
            // 女生的话;
            if (queue.charAt(i) == 'G') {
                // 统计女生在男生右边的次数;
                girlsRightOfBoys += boysCurrentNumber;
                girlsCurrentNumber++;
            } else {
                // 统计女生在男生左边的次数;
                girlsLeftOfBoys += girlsCurrentNumber;
                boysCurrentNumber++;
            }
        }

        if (girlsRightOfBoys > girlsLeftOfBoys) {
            System.out.println(girlsLeftOfBoys);
        } else {
            System.out.println(girlsRightOfBoys);
        }

    }
}
