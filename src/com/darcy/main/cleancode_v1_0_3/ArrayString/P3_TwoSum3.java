package com.darcy.main.cleancode_v1_0_3.ArrayString;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hzq19
 * Date on 2017/9/1 0:54.
 * Description:
 * <p>
 * 实现Two-sum的类，支持add和find操作
 * add(input):
 * find(value):
 */
public class P3_TwoSum3 {

  /*
  add – O(n) runtime, find – O(1) runtime, O(n2) space – Store pair sums in hash table:
  We could store all possible pair sums into a hash table. The extra space needed is in the
  order of O(n2). You would also need an extra O(n) space to store the list of added
  numbers. Each add operation essentially go through the list and form new pair sums that
  go into the hash table. The find operation involves a single hash table lookup in O(1)
  runtime.

  This method is useful if the number of find operations far exceeds the number of add
  operations.

  add – O(n) runtime, find – O(n) runtime, O(n) space – Binary search + Two pointers:
  Maintain a sorted array of numbers. First, we search for the correct insert position in
  O(log n) time using a modified binary search (See Question [48. Search Insert Position]).
  Each add operation takes O(n) time because all elements after the inserted element need
  to be shifted. For find operation we could then apply the [Two pointers] approach in O(n)
  runtime.

  add – O(1) runtime, find – O(nlog n) runtime, O(n) space – Sort + Two pointers:
  We could do a slight adjustment to the previous method. We store each input into an
  array without maintaining its sorted order. For find operation we first sort the array in
  O(n log n) time, then apply the [Two pointers] approach in O(n) runtime.
  (无序array + )

  add – O(1) runtime, find – O(n) runtime, O(n) space – Store input in hash table:
  A simpler approach is to store each input into a hash table with the input as key and its
  count as value. To find if a pair sum exists, just iterate through the hash table in O(n)
  runtime. Make sure you are able to handle duplicates correctly.(OK.)
   */

  Map<Integer, Integer> map = new HashMap<>();

  public void add(Integer input) {
    int count = map.getOrDefault(input, 0);
    map.put(input, count + 1);
  }

  public boolean find(int target) {
    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
      int x = entry.getKey();
      int y = target - x;
      if (x == y) {
        if (map.get(x) >= 2) {
          return true;
        }
      } else if (map.containsKey(y)) {
        return true;
      } else {
        return false;
      }
    }
    return false;
  }

  public static void main(String[] args) {
    P3_TwoSum3 demo = new P3_TwoSum3();
    demo.add(1);
    demo.add(3);
    demo.add(5);
    System.out.println(demo.find(4));
    System.out.println(demo.find(7));

  }


}
