package nowcoder.neteasy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by hzq19
 * Date on 2017/9/9 15:40.
 * Description:
 *
 * 80%
 *
 */
public class ArraySort {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int number = input.nextInt();
    List<Boolean> list = new ArrayList<>(number);
    List<Integer> numbers = new ArrayList<>();
    for (int i = 0; i < number; i++) {
      int arraySize = input.nextInt();
      /*int[] array = new int[arraySize];
      for (int j = 0; j < arraySize; j++) {
        array[j] = input.nextInt();
      }

      boolean result = solution(array);
      list.add(result);*/

      for (int j = 0; j < arraySize; j++) {
        numbers.add(input.nextInt());
      }
      boolean result2 = solution2(numbers, arraySize);
      list.add(result2);
      numbers.clear();

    }
    // System.out.println(list);
    for (Boolean b : list) {
      if (b) {
        System.out.println("Yes");
      } else {
        System.out.println("No");
      }
    }
  }

  private static boolean solution2(List<Integer> list, int arraySize) {
    int forthTimesCount = 0;
    int twiceTimesCount = 0;
    for (int i = 0; i < arraySize; i++) {
      if (list.get(i) % 4 == 0) {
        forthTimesCount++;
        // 是2的倍数.
      } else if (list.get(i) % 4 != 0 && list.get(i) % 2 == 0) {
        twiceTimesCount++;
      }
    }
    boolean result1 = forthTimesCount >= arraySize / 2;
    boolean result2 = (forthTimesCount * 2 + twiceTimesCount) >= arraySize;
    return result1 || result2;
  }

  /**
   * 必须要有length / 2个数是4的倍数.
   * 或者4的倍数 *2 + 2的倍数
   * @param array
   * @return
   */
  public static boolean solution(int[] array) {
    int forthTimesCount = 0;
    int twiceTimesCount = 0;
    for (int i = 0; i < array.length; i++) {
      if (array[i] % 4 == 0) {
        forthTimesCount++;
        // 是2的倍数.
      } else if (array[i] % 4 != 0 && array[i] % 2 == 0) {
        twiceTimesCount++;
      }
    }
    boolean result1 = forthTimesCount >= array.length / 2;
    boolean result2 = (forthTimesCount * 2 + twiceTimesCount) >= array.length;
    return result1 || result2;
  }

}

/*
2
3
1 10 100
4
1 2 3 4
 */