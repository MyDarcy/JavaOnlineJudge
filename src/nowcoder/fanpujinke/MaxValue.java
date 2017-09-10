package nowcoder.fanpujinke;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * Created by hzq19
 * Date on 2017/9/10 15:48.
 * Description:
 * 给定一个3个整数, 加入+()*使得结果最大.
 */
public class MaxValue {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int a1 = input.nextInt();
    int a2 = input.nextInt();
    int a3 = input.nextInt();

    List<Integer> list = new ArrayList<>();
    list.add(a1 + a2 + a3);
    list.add(a1 * a2 * a3);
    list.add(a1 + a2 * a3);
    list.add(a1 * a2 + a3);
    list.add(a1 * (a2 + a3));
    list.add((a1 + a2) * a3);
    list.sort(new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        return o1 - o2;
      }
    });

    System.out.println(list.get(list.size() - 1));
  }

}
