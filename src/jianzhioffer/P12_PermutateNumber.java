package jianzhioffer;

/**
 * Created by darcy
 * 2017/4/8--23:59
 * Description:
 * 尝试一下打印1~100的全拍列；
 *
 设一组数p = {r1, r2, r3, ... ,rn}, 全排列为perm(p)，pn = p - {rn}
 因此perm(p) = r1perm(p1), r2perm(p2), r3perm(p3), ... , rnperm(pn)。当n = 1时perm(p} = r1。

 http://www.cnblogs.com/nokiaguy/archive/2008/05/11/1191914.html
 http://blog.csdn.net/randyjiawenjie/article/details/6313729
 */
public class P12_PermutateNumber  {
    public static int total = 0;
    public static void swap(int[] ints, int i, int j)
    {
        int temp = ints[i];
        ints[i] = ints[j];
        ints[j] = temp;
    }


    /*
1、首先看最后两个数4, 5。 它们的全排列为4 5和5 4, 即以4开头的5的全排列和以5开头的4的全排列。
由于一个数的全排列就是其本身，从而得到以上结果。

2、再看后三个数3, 4, 5。它们的全排列为3 4 5、3 5 4、 4 3 5、 4 5 3、 5 3 4、 5 4 3 六组数。
即以3开头的和4,5的全排列的组合、以4开头的和3,5的全排列的组合和以5开头的和3,4的全排列的组合.

从而可以推断，设一组数p = {r1, r2, r3, ... ,rn}, 全排列为perm(p)，pn = p - {rn}。
因此perm(p) = r1perm(p1), r2perm(p2), r3perm(p3), ... , rnperm(pn)。当n = 1时perm(p} = r1。
为了更容易理解，将整组数中的所有的数分别与第一个数交换，这样就总是在处理后n-1个数的全排列。
   */
    public static void arrange (int[] ints, int start, int len)
    {
        if (start == len - 1)
        {
            for (int i = 0; i < len; i ++)
            {
                System.out.print(ints[i]+ "  ");
            }
            System.out.println();
            total++;
        }
        else
        {
            for (int i = start; i < len; i ++)
            {
                swap(ints, start, i);
                arrange(ints, start + 1, len);
                swap(ints, start, i);
            }
        }

    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] ints = {0, 1, 2, 3, 4, 5, 6};
        arrange(ints, 0, ints.length);
        System.out.println(total);
    }
}
