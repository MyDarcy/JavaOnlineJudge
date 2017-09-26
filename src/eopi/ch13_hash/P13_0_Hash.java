package eopi.ch13_hash;


import java.util.*;

/**
 * Author by darcy
 * Date on 17-9-26 下午2:21.
 * Description:
 *
 * 1. Hash表:
 *   查找, 插入, 删除的时间复杂度是O(1 + n/m); n是总的元素的个数, m是桶的个数.
 *   如果n/m比较大, 那么要进行rehash.
 * 2. Hash表的关键在于要找到一个合适的hashCode()函数, 使得key能尽可能的均匀分布.
 * 3. 作为hash表结构的key应该是不可变的. 否则容易导致内存泄漏.
 * 4. 有些情况可以预定义一个查找表.
 * 5. LinkedHashMap可以维持元素的插入顺序.
 * 6. retailALL()可以求交集.
 * 7. LinkedHashMap可以指定迭代顺序为插入顺序, 也可以是访问顺序（加上容量限制就是妥妥的LRU实现.）
 * 8. 如果不重写equals()和hashCode()方法,那么可以使用Objects提供的方法.但是感觉容易出问题.
 *
 */
public class P13_0_Hash {
  /**
   * 寻找变位词集合.
   * 变位词就是词都是由相同的字符组成,但是顺序不同.
   */
  public static List<List<String>> findAnagrams(List<String> list) {
    Map<String, List<String>> anagrams = new HashMap<>();
    for (int i = 0; i < list.size(); i++) {
      char[] chars = list.get(i).toCharArray();
      Arrays.sort(chars);
      String sortedString = new String(chars);
      if (!anagrams.containsKey(sortedString)) {
        anagrams.put(sortedString, new LinkedList<>());
      }
      anagrams.get(sortedString).add(list.get(i));
    }

    List<List<String>> anagramsGroup = new LinkedList<>();
    for (Map.Entry<String, List<String>> item :
        anagrams.entrySet()) {
      if (item.getValue().size() >= 2) {
        anagramsGroup.add(item.getValue());
      }
    }

    return anagramsGroup;
  }


}
