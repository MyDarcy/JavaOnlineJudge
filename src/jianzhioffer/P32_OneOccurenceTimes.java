package jianzhioffer;

import jdk.nashorn.internal.objects.NativeUint16Array;

import java.util.Random;

/**
 * Created by darcy
 * 2017/5/6--20:12
 * Description:
 */
public class P32_OneOccurenceTimes {

    /**
     * 分析数字;
     * 1~21345: 1~1345 + 1346~21345(最高位出现1的次数)
     *
     * @param n
     * @return
     */
    public int NumberOf1Between1AndN_Solution2(int n) {
        String numberStr = String.valueOf(n);
        int count = 0;
        for (int i = 0; i < numberStr.length(); i++) {
            String substring = numberStr.substring(i);
            if (substring.charAt(0) == '0') {
                continue;
            }
            int iter = solution(substring);
            count += iter;
        }
        return count;
    }

    private int solution(String str) {

        int count = 0;

        //
        if (str.length() == 1) {
            if (str.charAt(0) != '0') {
                return 1;
            } else {
                return 0;
            }
        }

        String subStr = null;
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == '0') {
                continue;
            } else {
                subStr = str.substring(i);
                break;
            }
        }

        // 最高位出现1个个数;
        if (str.charAt(0) > '1') {
            count += ((int) (Math.pow(10, str.length() - 1)));
        } else {
            if (subStr != null) {
                count += (Integer.parseInt(subStr) + 1);
            } else {
                count += 1;
            }
        }

        // 其他为出现1个数;
        count += (str.charAt(0) - '0') * (str.length() - 1) * ((int) Math.pow(10, str.length() - 2));

        return count;


    }


/*    链接：https://www.nowcoder.com/questionTerminal/bd7f978302044eee894445e244c7eee6
    来源：牛客网*/

    public int NumberOf1Between1AndN_Solution3(int n) {

        int num = n;
        int count = 0;// 计算数字中含有1的数字个数。
        int strLen = 0;//每个数字的长度
        for (int i = 0; i <= num; i++) {
            String str = String.valueOf(i);
            strLen = str.length();
            for (int j = 0; j < strLen; j++) {
                if (str.charAt(j) == '1') {
                    count++;
                }
            }
        }

        return count;
    }


    /**
     * 统计每个数中1出现的次数;
     *
     * @param n
     * @return
     */
    public int NumberOf1Between1AndN_Solution(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            count += oneInNumber(i);
        }
        return count;
    }

    private int oneInNumber(int number) {
        int count = 0;
        int remain = 0;
        while (number != 0) {
            remain = number % 10;
            if (remain == 1) {
                count++;
            }
            number /= 10;
        }
        return count;
    }

    public static void main(String[] args) {
        P32_OneOccurenceTimes demo = new P32_OneOccurenceTimes();
        Random random = new Random(31);
        for (int i = 0; i < 10; i++) {
            int number = random.nextInt(100000000);
            long time1 = System.nanoTime();
            int count = demo.NumberOf1Between1AndN_Solution(number);
            long time2 = System.nanoTime();
            int count2 = demo.NumberOf1Between1AndN_Solution2(number);
            long time3 = System.nanoTime();
            System.out.println(number + "\t" + count + " time:" + (time2 - time1) + "\t" + count2 + " time:" + (time3 - time2));
        }

    }

}
/**
 链接：https://www.nowcoder.com/questionTerminal/bd7f978302044eee894445e244c7eee6
 来源：牛客网

 /*
       我们从低位到高位求每位1出现的次数，累加求和即可
         例如：求0~abcde中1的个数，现在我们求c这一位中1出现的次数，其他位雷同
         有两部分组成
         第一部分：ab * 100，表示当ab这两位在0~ab-1范围内时，de可以从0~99取值
           第二部分：如果c>1时，当ab为ab时1的个数为0~99
                   如果c=1时，当ab为ab时1的个数为de + 1
                 如果c<0时，当ab为ab是1的个数为0
    
    int NumberOf1Between1AndN_Solution(int n)
            {
                int exp = 1;
                int ans = 0; 
                while(n / exp)
                {
                    ans += n / (exp * 10) * exp;
                    if(n % (exp * 10) / exp  > 1) ans += exp;
                    else if(n % (exp * 10) / exp == 1) ans += (n % exp + 1);
                    exp *= 10;
                }
                return ans;
            }
 */