package nowcoder.pingduoduo;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by hzq19 on 2017/8/1.
 */
public class StudentChocolates {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        /**
         * h代表的是学生的需求
         */
        int hNumbers = Integer.parseInt(input.nextLine().trim());
        String[] hString = input.nextLine().trim().split("\\s+");

        /**
         * w代表的是老师的供应。
         */
        int wNumbers = Integer.parseInt(input.nextLine().trim());
        String[] wString = input.nextLine().trim().split("\\s+");

        int[] h = new int[hNumbers];
        for (int i = 0; i < hNumbers; i++) {
            h[i] = Integer.parseInt(hString[i]);
        }

        int[] w = new int[wNumbers];
        for (int i = 0; i < wNumbers; i++) {
            w[i] = Integer.parseInt(wString[i]);
        }

        /*System.out.println("h:" + Arrays.toString(h));
        System.out.println("w:" + Arrays.toString(w));*/
        Arrays.sort(h);
        Arrays.sort(w);
        /*System.out.println("h:" + Arrays.toString(h));
        System.out.println("w:" + Arrays.toString(w));*/

        int count = 0;
        int hIndex = hNumbers - 1;
        int wIndex = wNumbers - 1;
        while (hIndex >= 0 && wIndex >= 0) {
            if (h[hIndex] <= w[wIndex]) {
                count++;
                hIndex--;
                wIndex--;
            } else {
                hIndex--;
            }
        }

        System.out.println(count);

    }

}
/**
 3
 2 2 3
 2
 3 1

 5
 1 3 5 7 9
 3
 2 3 4
 */