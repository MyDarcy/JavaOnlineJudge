package nowcoder.souhu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by hzq19
 * Date on 2017/8/28 19:43.
 * Description:
 * <p>
 * Kolakoski序列
 */

/*
/*作者：甜椒
链接：https://www.nowcoder.com/discuss/35706
来源：牛客网*/

public class P1_Sequence {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {//注意while处理多个case
            int n = in.nextInt();
            int m = in.nextInt();
            int[] a = new int[m];
            int[] b = new int[n];
            for(int i=0; i<m; ++i) {
            	a[i] = in.nextInt();
            }
            Kolakoski(a, m, b, n); //构造Kolakoski序列
            for(int i=0; i<n; ++i) {
                System.out.println(b[i]);
            }
        }
        in.close();
    }
    private static int[] Kolakoski(int[] a, int m, int[] b, int n) {
    	int cur = a[0];
    	int flag = 0;
        b[0] = a[0];
        if(cur > 1) b[1] = cur;//若第一个数字为1，需要初始化前两位，因为a中不能有重复数，所以不用初始化第三位
        else b[1] = a[1];
        int k=0;
        for(int i=0;k<n;++i)
        {
            for(int j=k;(j<k+b[i])&&(j<n);++j) { //注意判断下标是否超过n
            	b[j]=cur;
            }
            k=k+b[i];
            if(flag<(m-1)) {
            	++flag;
            }else {
            	flag = 0;
            }
            // 循环利用新的元素.
            cur=a[flag];
        }
        return b;
    }
}
/*
30 4
1 2 3 4
1 3 4 4 4 1 1 1 1 2 2 2 2 3 3 3 3 4 1 2 3 4 4 1 1 2 2 3 3 4
 */