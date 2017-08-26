package nowcoder.didi;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Created by hzq19
 * Date on 2017/8/26 16:25.
 * Description:
 * 给定无序数组，求第k大的数字。
 *
 * input:
 * 45 67 33 21
 * 2 (第2大)
 *
 * output:
 * 2
 */
public class TheKMaxNumber {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String[] lineNumbers = input.nextLine().trim().split("\\s+");
        int kth = Integer.parseInt(input.nextLine().trim());

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(lineNumbers.length);

        for (int i = 0; i < lineNumbers.length; i++) {
            priorityQueue.add(Integer.parseInt(lineNumbers[i]));
        }

        for (int i = 0; i < (lineNumbers.length - kth); i++) {
            priorityQueue.poll();
        }
        System.out.println(priorityQueue.poll());

    }

}
