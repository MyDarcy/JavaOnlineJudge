package jianzhioffer;

/**
 * Created by darcy
 * 2017/5/14--23:50
 * Description:
 * <p>
 * 1 + ... + n 但是不能使用乘除法, for, while, if, else, switch...case等。
 * 求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 */
public class P46_OneToNSum {


    static int number;
    static int result;

    public P46_OneToNSum() {
        result += number;
        number--;
    }


    /**
     * java中依靠购构造函数的思路不行。因为要创建n个对象任然需要循环;
     *
     * @param n
     * @return
     */
    public int Sum_Solution(int n) {
        number = n;
        P46_OneToNSum[] array = new P46_OneToNSum[n];
        return P46_OneToNSum.result;
    }

    /**
     * 利用一场来判定终止;
     *
     * @param number
     * @return
     */
    public int Sum_Solution2(int number) {
        return sum(number);
    }

    private int sum(int number) {
        try {
            int i = 1 / number;
            return number + sum(number - 1);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 链接：https://www.nowcoder.com/questionTerminal/7a0da8fc483247ff8800059e12d7caf1
     * 利用短路条件作为终止条件;
     * @param n
     * @return
     */
    public int Sum_Solution3(int n) {
        int sum = n;
        boolean flag = (sum > 0) && ((sum += Sum_Solution3(--n)) > 0);
        return sum;

    }

    /**
     * 移位性质;
     * @param n
     * @return
     */
    public int Sum_Solution4(int n) {
        int sum = (int) (Math.pow(n,2) + n);
        return sum>>1;
    }


    public static void main(String[] args) {
        P46_OneToNSum demo = new P46_OneToNSum();
        System.out.println(demo.Sum_Solution(10));
    }
}

/**
 * 链接：https://www.nowcoder.com/questionTerminal/7a0da8fc483247ff8800059e12d7caf1
 * 来源：牛客网
 * <p>
 * int Sum_Solution(int n) {
 *         int ans = n;
 *         ans && (ans += Sum_Solution(n - 1));
 *         return ans;
 *     }
 */
