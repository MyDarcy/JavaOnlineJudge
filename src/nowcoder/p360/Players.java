package nowcoder.p360;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Created by hzq19
 * Date on 2017/8/26 20:34.
 * Description:
 *
 * 输入
 第一行一个整数n，1≤n≤200000，
 第二行n个整数，第i个整数表示本次比赛第i个出场的运动员上赛季的得分ai，1≤ai≤200000。
 输出
 一行输出n个整数，第i个整数表示本次比赛在第i个出场的运动员之前出场且得分比其高的运动员个数。不要在行末输出多余的空格。

 样例输入
 5
 4 5 1 3 2
 样例输出
 0 0 2 2 3


 */
public class Players {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String numberStr = input.nextLine();
        int number = Integer.parseInt(numberStr);
        String[] strings = input.nextLine().trim().split("\\s+");
        int[] scores = new int[strings.length];

        for (int i = 0; i < strings.length; i++) {
            scores[i] = Integer.parseInt(strings[i]);
        }

        int max = Arrays.stream(scores).max().getAsInt();
        int[] scorePerson = new int[max + 1];

        for (int i = 0; i < scores.length; i++) {
            
        }

    }

}
