package nowcoder.alibaba;

/**
 * Created by hzq19
 * Date on 2017/8/21 19:16.
 * Description:
 * 小猴子下山，沿着下山的路有一排桃树，每棵树都结了一些桃子。小猴子想摘桃子，但是有一些条件需要遵守，
 * 小猴子只能沿着下山的方向走，不能回头，每颗树最多摘一个，而且一旦摘了一棵树的桃子，
 * 就不能再摘比这棵树结的桃子少的树上的桃子。那么小猴子最多能摘到几颗桃子呢？
 * 举例说明，比如有5棵树，分别结了10，4，5，12，8颗桃子，那么小猴子最多能摘3颗桃子，来自于结了4，5，8颗桃子的桃树。
 */
import java.util.*;
public class MonkeyPeaches {
/** 请完成下面这个函数，实现题目要求的功能 **/
    /** 当然，你也可以不按照这个模板来作答，完全按照自己的想法来 ^-^  **/
    static int pick(int[] peaches) {



        return 0;
    }
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int trees = Integer.parseInt(in.nextLine().trim());
        int[] peaches = new int[trees];
        for (int i = 0; i < peaches.length; i++) {
            peaches[i] = Integer.parseInt(in.nextLine().trim());
        }
        System.out.println(pick(peaches));
    }
}
