package nowcoder.pingduoduo;

import java.util.*;

/**
 * Created by hzq19
 * Date on 2017/9/2 15:05.
 * Description:
 *
 * 小熊根据武力值的顺序依次进食。
 * 小熊不会吃撑.
 *
 */
public class P1_Main {

  static class Bear {
    int fight;
    int hungry;

    @Override
    public String toString() {
      return "Bear{" +
          "fight=" + fight +
          ", hungry=" + hungry +
          '}';
    }
  }

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int bearNumber = input.nextInt();
    int candyNumber = input.nextInt();
    int[] candyEnergy = new int[candyNumber];
    int i = 0;
    while (i < candyNumber) {
      candyEnergy[i++] = input.nextInt(); // 每个糖果能填充的饥饿值。
    }

    i = 0;
    List<Bear> bears = new ArrayList<>(bearNumber);
    while (i < bearNumber) {
      Bear bear = new Bear();
      bear.fight = input.nextInt();
      bear.hungry = input.nextInt();
      bears.add(bear);
      i++;
    }
    input.close();

    /*System.out.println(bears);*/

    // 根据武力值对bear进行大到小的排序.
    /*Collections.sort(bears, new Comparator<Bear>() {
      @Override
      public int compare(Bear o1, Bear o2) {
        return o2.fight - o1.fight;
      }
    });*/

    // 根据糖果所含的能量进行小到大的排序.
    /*Arrays.sort(candyEnergy);*/


    /*System.out.println(bears);
    System.out.println(Arrays.toString(candyEnergy));*/

    List<Integer> list = solution(bears, candyEnergy);
    for (Integer item : list) {
      System.out.println(item);
    }

  }

  private static List<Integer> solution(List<Bear> bears, int[] candyEnergy) {
    boolean[] candyEat = new boolean[candyEnergy.length];

    List<Integer> leftEnergy = new LinkedList<>();

    // 遍历每一只熊。
    for (Bear bear : bears) {
      int eneryNeed = bear.hungry;// bear所需的能量.

      for (int i = candyEnergy.length - 1; i >= 0; i--) {
        // 该candy还没有被吃掉.
        if (!candyEat[i]) {
          if (candyEnergy[i] <= eneryNeed) {
            eneryNeed -= candyEnergy[i];
            candyEat[i] = true;
          } /*else {
            // 任意糖果都比小熊的剩余饥饿值大.
            leftEnergy.add(eneryNeed);
            break;
          }*/
        }
      }
      leftEnergy.add(eneryNeed);
    }
    return leftEnergy;
  }


}

/*
2 5
5 6 10 20 30
4 34
3 35
 */