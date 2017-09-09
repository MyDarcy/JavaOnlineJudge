package nowcoder.sogou;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by hzq19
 * Date on 2017/9/8 16:52.
 * Description:
 *
 * 定义圆周上两点的距离s为这两点之间的劣弧对应的圆心角度数(0<=s<=180)，现输入圆周上的n个点（n>=2），
 * 以角度a表示其位置(0<=a<360)，输入按a从小到大排序。求最远的一对点之间的距离。
 输入
 第一行为点个数n，后跟n行，每行一个双精度浮点数，表示点的角度（小数点后保留8位），例如输入样例中为4个点的输入：

 输出
 输出最远的一对点之间的距离（双精度浮点数，小数点后保留8位）和'\n'换行符。
 例如输入样例中，10.00000000与183.00000000两个点之间的距离为173.00000000，
 大于10.00000000与198.0000000之间的距离172.00000000，所以应输出：
 173.00000000

 环上的最长距离。

 */
public class LongestDistance {

  public static void main(String[] args) {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    List<String> list = reader.lines().collect(toList());
    // System.out.println(list);
    if (list == null || list.size() <= 1) {
      System.out.printf("%.8f", 0.0);
      System.exit(0);
    }
    int numbers = Integer.parseInt(list.get(0));
    list.remove(0);
    // System.out.println(list);

    List<Double> doubles = list.stream().map(Double::parseDouble).collect(toList());
    /*for (Double d : doubles) {
      System.out.printf("%.8f\n", d.doubleValue());
    }*/

    double result = solution2(doubles);

    System.out.printf("%.8f", result);
  }



  public static double solution2(List<Double> list) {

    int start = 0;
    int end = list.size() - 1;
    double max = 0.0;
    while (start < end) {
      double d1 = list.get(start);
      double d2 = list.get(end);
      double temp = d2 - d1;
      if (temp > 180) {
        temp = 360 - temp;
      }

      if (temp > max) {
        max = temp;
      }

      if (region(d2) == 4 && region(d1) == 1) {
        end--;
        start++;
      }

      if (region(d2) - region(d1) == 2) {
        end--;
      }

    }
    return 0;
  }

  public static int region(double degree) {
    if (degree < 90) {
      return 1;
    } else if (degree < 180) {
      return 2;
    } else if (degree < 270) {
      return 3;
    } else {
      return 4;
    }
  }


  private static double solution(List<Double> list) {
    int start = 0;
    int end = list.size() - 1;
    double max = 0.0;
    double d1 = 0.0;
    double d2 = 0.0;
    double temp = 0.0;
    for (int i = 0; i < list.size(); i++) {
      d1 = list.get(i);
      for (int j = i + 1; j < list.size(); j++) {
        d2 = list.get(j);
        temp  = d2 - d1;
        if (temp > 180) {
          temp = 360 - temp;
        }

        if (temp > max) {
          max = temp;
        }
      }
    }
    return max;
  }

}
