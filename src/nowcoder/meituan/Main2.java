package nowcoder.meituan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by hzq19
 * Date on 2017/10/24 15:25.
 * Description:
 */
public class Main2 {

  /**
   * @param sum 总的金额数目.
   * @param k   分成k份.
   * @return
   */
  public static List<Double> solution(int sum, int k) {
    List<Double> list = new ArrayList<>(Collections.nCopies(k, sum * 1.0 / k));
    Random random = new Random(System.currentTimeMillis());
    for (int i = 0; i < k; i++) {
      double part0 = random.nextDouble() * list.get(i);
      double part1 = list.get(i) - part0;
      int index = random.nextInt(k);
      int partIndex = random.nextInt(2);

      // 也可以循环直到产生和i不同的index.
      if (index != i) {
        list.set(index, list.get(index) + (partIndex == 0 ? part0 : part1));
        list.set(i, partIndex == 0 ? part1 : part0);
      }
      // 否则继续.

    }
    return list;
  }


  public static void main(String[] args) {
    List<Double> result = solution(10, 5);
    System.out.println(result);
  }

}
