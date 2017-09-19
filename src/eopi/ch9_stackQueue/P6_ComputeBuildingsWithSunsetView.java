package eopi.ch9_stackQueue;

import java.util.*;

/**
 * Author by darcy
 * Date on 17-9-19 下午2:22.
 * Description:
 *
 * 一条直线上有n个节点. 每个节点高度height. 如果矮节点在高节点的东边(右边), 那么矮节点就看不到日落.
 * 求能看到日落的buildings.
 * node是自东向西方向拓展的.
 *
 * You are given with a series of buildings that have windows facing west. The buildings
 * are in a straight line, and any building which is to the east of a building of equal or
 * greater height cannot view the sunset.
 *
 * Design an algorithm that processes buildings in east-to-west order and returns the
 * set of buildings which view the sunset. Each building is specified by its height.
 *
 * Hint: When does a building not have a sunset view?
 */


public class P6_ComputeBuildingsWithSunsetView {

  static class Building {
    Integer id;
    Integer height;

    public Building(Integer id, Integer height) {
      this.id = id;
      this.height = height;
    }
  }

  /**
   * 利用一个stack记录当前可以看得到日出的所有的building, 如果当前处理的building比栈顶还高, 那么栈顶就要出栈,
   * 直到遇到一个比当前building还高的building,那么将当前building入栈.
   *
   * @param buildingIterator
   * @return
   */
  public static Deque<Building> solution(Iterator<Building> buildingIterator) {
    Deque<Building> stack = new LinkedList<>();
    int buidlingIndex = 0;
    while (buildingIterator.hasNext()) {
      Building building = buildingIterator.next();
      while (!stack.isEmpty() && Integer.compare(building.height, stack.peekLast().height) >= 0) {
        stack.removeLast();
      }
      stack.addLast(new Building(buidlingIndex++, building.height));
    }
    return stack;
  }

  public static void main(String[] args) {
    int id = 1;
    Building b1 = new Building(id++, 3);
    Building b2 = new Building(id++, 5);
    Building b3 = new Building(id++, 7);
    Building b4 = new Building(id++, 1);
    Building b5 = new Building(id++, 2);
    Building b6 = new Building(id++, 4);
    Building b7 = new Building(id++, 9);

    List<Building> list = new ArrayList<>(Arrays.asList(b1, b2, b3, b4, b5, b6, b7));
    Deque<Building> stack = solution(list.iterator());
    while (!stack.isEmpty()) {
      Building bu = stack.removeLast();
      System.out.println(bu.height);
    }

    // 9
  }

}
