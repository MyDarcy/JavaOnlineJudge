package nowcoder.souhu;

import java.util.Scanner;

/**
 * Created by hzq19
 * Date on 2017/8/30 21:22.
 * Description:
 */
public class P1_Sequence_V2  {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] num = new int[m];
        for (int i = 0; i < m; i++) {
            num[i] = sc.nextInt();
        }
        sc.close();
        int[] arrSequ = new int[n];
        int[] arrLen = new int[n];
        arrLen[0] = num[0];
        int i = 0, k = 0, s = 0, sumLen = 0;
        outer:
        while (true) {
            for (i = 0; i < arrLen.length; i++) {
                for (int j = 0; j < arrLen[i]; j++) {
                    arrSequ[s++] = num[k % 4];
                    if (s >= n)
                        break outer;
                }
                sumLen = sumLen + arrLen[i];
                ++k;
                for (int j = sumLen - arrLen[i]; j < sumLen; j++)
                    arrLen[j] = arrSequ[j];
            }
        }

        for (int j = 0; j < n; j++) {
            System.out.print(arrSequ[j] + " ");
        }
    }


}
