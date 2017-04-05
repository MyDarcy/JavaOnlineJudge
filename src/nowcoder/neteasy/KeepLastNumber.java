package nowcoder.neteasy;

import javafx.beans.binding.StringBinding;

import java.util.*;

/**
 * Created by csqiang on 2017/3/26.
 *
 * @Author mr.darcy
 * Description:
小易有一个长度为n序列，小易想移除掉里面的重复元素，但是小易想是对于每种元素保留最后出现的那个。小易遇到了困难,希望你来帮助他。
输入描述:
输入包括两行：
第一行为序列长度n(1 ≤ n ≤ 50)
第二行为n个数sequence[i](1 ≤ sequence[i] ≤ 1000)，以空格分隔
 */
public class KeepLastNumber {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = Integer.parseInt(input.nextLine().trim());
        String[] strArray = input.nextLine().trim().split("\\s+");
        int[] ints = new int[strArray.length];
        for (int i = 0; i < strArray.length; i++) {
            ints[i] = Integer.parseInt(strArray[i]);
        }

        Set<Integer> set = new HashSet<>();
        Deque<Integer> deque = new LinkedList<>();
        for (int i = ints.length - 1; i >= 0; i--) {
            if (!set.contains(ints[i])) {
                set.add(ints[i]);
                deque.addFirst(ints[i]);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!deque.isEmpty()) {
            sb.append(deque.pollFirst() + " ");
        }
        System.out.println(sb.toString().trim());
    }
}
