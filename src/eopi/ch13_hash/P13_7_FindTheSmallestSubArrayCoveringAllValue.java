package eopi.ch13_hash;

import java.util.*;

/**
 * Author by darcy
 * Date on 17-9-26 下午3:56.
 * Description:
 * <p>
 * 字符数组中找最小的子数组包含给定集合中出现的所有字符串.
 * <p>
 * Write a program which takes an array of strings and a set of strings, and return the
 * indices of the starting and ending index of a shortest subarray of the given array that
 * "covers" the set, i.e., contains all strings in the set.
 * <p>
 * Hint: What is the maximum number of minimal subarrays that can cover the query?
 */
public class P13_7_FindTheSmallestSubArrayCoveringAllValue {

  public static class SubArray {
    public Integer start;
    public Integer end;

    public SubArray(Integer start, Integer end) {
      this.start = start;
      this.end = end;
    }

    @Override
    public String toString() {
      return "SubArray{" +
          "start=" + start +
          ", end=" + end +
          '}';
    }
  }

  /**
   * 暴力破解算法.
   * <p>
   * 1.计算所有可能长度的子字符串, 是否包含目标集合中的所有字符串.
   * 时间复杂度O(N^3), 空间复杂度O(N);
   * <p>
   * 2.递增子数组的长度, 同时使用一个hashSet来表明待覆盖的元素集合.
   * 时间复杂度O(N^2), 空间复杂度O(N);
   *
   * @param list
   * @return
   */
  public static SubArray bruteForceSolution(List<String> list) {

    return null;
  }

  /**
   * start或者end往前移动的时候, 可以利用之前的覆盖信息.
   * <p>
   * We can further improve the algorithm by noting that when we move from i to i +1
   * we can reuse the work performed from i. Specifically, let's say the smallest subarray
   * starting at i covering the set ends at j. There is no point in considering subarrays
   * starting at i + 1 and ending before j, since we know they cannot cover the set. When
   * we advance to i + 1, either we still cover the set, or we have to advance j to cover the
   * set. We continuously advance one of i or j, which implies an O(n) time complexity
   * <p>
   * 例子:
   * As a concrete example, consider the array (apple, banana, apple, apple, dog, cat, apple,
   * dog, banana, apple, cat, dog) and the set {banana,cat}. The smallest subarray covering
   * the set starting at 0 ends at 5. Next, we advance to 1. Since the element at 0 is not
   * in the set, the smallest subarray covering the set still ends at 5. Next, we advance to
   * 2. Now we do not cover the set, so we advance from 5 to 8—now the subarray from
   * 2 to 8 covers the set. We update the start index from 2 to 3 to 4 to 5 and continue to
   * cover the set. When we advance to 6, we no longer cover the set, so we advance the
   * end index till we get to 10. We can advance the start index to 8 and still cover the set.]
   * After we move past 8, we cannot cover the set. The shortest subarray covering the
   * set is from 8 to 10.
   *
   * @param list
   * @return
   */
  public static SubArray solution(List<String> list, Set<String> keywords) {

    /**
     * O(N)的时间复杂度.
     */
    Map<String, Integer> keywordToCover = new HashMap<>();
    for (String str : keywords) {
      keywordToCover.put(str, keywordToCover.containsKey(str) ? keywordToCover.get(str) + 1 : 1);
    }

    SubArray subArray = new SubArray(-1, -1);
    int remainingToCover = keywords.size();

    for (int left = 0, right = 0; right < list.size(); right++) {
      Integer keywordCount = keywordToCover.get(list.get(right));
      // right可以覆盖一个.
      if (keywordCount != null) {
        keywordToCover.put(list.get(right), --keywordCount);
        // 真的覆盖了一个元素.不是真的覆盖的话就减到了-1, 就是无效的覆盖.
        if (keywordCount >= 0) {
          remainingToCover--;
        }
      }

      // 一次合理的覆盖.
      while (remainingToCover == 0 && left < right) {
        if (subArray.start == -1 && subArray.end == -1
            || right - left < subArray.end - subArray.start) {
          subArray.start = left;
          subArray.end = right;
        }

        // left要往前移动了.
        keywordCount = keywordToCover.get(list.get(left));
        if (keywordCount != null) {
          keywordToCover.put(list.get(left), ++keywordCount);
          if (keywordCount > 0) {
            remainingToCover++;
          }
        }
        ++left;
      }
    }
    return subArray;
  }

  /**
   * 实现2;
   * book impl.
   * <p>
   * use a doubly linked list L to store the last occurrence
   * (index) of each keyword in O, and hash table H to map each keyword in Q to the
   * corresponding node in L. Each time a word in Q is encountered, we remove its node
   * from L (which we find by using H),create a new node which records the current index
   * in A, and append the new node to the end of L. We also update H.By doing this,
   * each keyword in L is ordered by its order in A; therefore, if L has UQ words (i.e., all
   * keywords are shown) and the current index minus the index stored in the first node
   * in L is less than current best, we update current best. The complexity is still O(n)
   *
   * @return
   */
  public static SubArray findSmallestSubarrayCoveringSubset(
      Iterator<String> iter, List<String> queryStrings) {
    LinkedHashMap<String, Integer> dict = new LinkedHashMap<>();
    for (String s : queryStrings) {
      dict.put(s, null);
    }
    int numStringsFromQueryStringsSeenSoFar = 0;
    SubArray res = new SubArray(-1, -1);
    int idx = 0;
    while (iter.hasNext()) {
      String s = iter.next();
      if (dict.containsKey(s)) {// s is in queryStrings.
        Integer it = dict.get(s);
        if (it == null) {
          // First time seeing this string from queryStrings.
          numStringsFromQueryStringsSeenSoFar++;
        }
        // dict.put(s,idx) won't work because it does not move the entry to
        // the front of the queue if an entry with key s is already present.
        // So we explicitly remove the existing entry with key s, then put
        // (s,idx).
        dict.remove(s);
        dict.put(s, idx);
      }
      if (numStringsFromQueryStringsSeenSoFar == queryStrings.size()) {
        // We have seen all strings in queryStrings, let’s get to work.
        if ((res.start == -1 && res.end == -1)
            || idx - getValueForFirstEntry(dict) < res.end - res.start) {
          res.start = getValueForFirstEntry(dict);
          res.end = idx;
        }
      }
      ++idx;
    }
    return res;
  }

  private static Integer getValueForFirstEntry(
      LinkedHashMap<String, Integer> m) {
    // LinkedHashMap guarantees iteration over key-value pairs takes place in
    // insertion order, most recent first.
    Integer result = null;
    for (Map.Entry<String, Integer> entry : m.entrySet()) {
      result = entry.getValue();
      break;
    }
    return result;
  }

  public static void main(String[] args) {
    List<String> list = Arrays.asList("apple", "banana", "apple", "apple", "dog", "cat", "apple",
        "dog", "banana", "apple", "cat", "dog");
    Set<String> set = new HashSet<String>() {
      {
        add("banana");
        add("cat");
        add("apple");
      }
    };

    SubArray result = solution(list, set);
    System.out.println(result);


  }


}
