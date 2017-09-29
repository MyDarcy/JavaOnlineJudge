package eopi.ch14_sorting;

import java.util.Collections;
import java.util.List;

/**
 * Author by darcy
 * Date on 17-9-29 下午8:55.
 * Description:
 *
 * 从姓名数组中移除first-name第一次重复的元素.
 *
 * Design an efficient algorithm for removing all first-name duplicates from an array. For
 * example, if the input is ((Ian, Botham), (David,Gower), (Ian, Bell), (Ian,Chappell)),
 * one result could be ((Ian, Bell), (David,Gower)); ((David,Gower),(Ian,Botham))
 * would also be acceptable.
 *
 * Hint: Bring equal items close together.
 *
 */
public class P14_3_RemoveFirstNameDuplicate {

  public static class Name implements Comparable<Name> {
    public String firstName;
    public String secondName;

    @Override
    public int compareTo(Name o) {
      int r1 = firstName.compareTo(o.firstName);
      if (r1 != 0) {
        return r1;
      }
      return secondName.compareTo(o.secondName);
    }
  }

  /**
   * 这里是先排序以后然后再处理重复元素.
   *
   * 时间复杂度O(NlogN), 空间复杂度O(1).
   *
   * 如果用散列来做的话,时间复杂度O(N), 空间复杂度O(N)
   *
   * @param nameList
   */
  public static void solution(List<Name> nameList) {
    Collections.sort(nameList);

    // 表示未重复的元素的index.
    int beenWritenIndex = 0;
    for (int first = 1; first < nameList.size(); first++) {

      // 跟已经处理过的元素的最后一个进行对比. 如果相等, 则说明重复, 继续去处理下一个元素.
      // 不相等, 则说明不是重复元素.那么放在当前已经处理元素的后面.
      if (!nameList.get(beenWritenIndex).firstName.equals(nameList.get(first).firstName)) {
        nameList.set(++beenWritenIndex, nameList.get(first));
      }
    }

    nameList.subList(++beenWritenIndex, nameList.size()).clear();
  }
}
