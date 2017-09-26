package eopi.ch12_search;

import java.util.*;

/**
 * Author by darcy
 * Date on 17-9-26 上午9:26.
 * Description:
 * <p>
 * 找出缺失的IP地址. 文件中存储的IP是十亿计, 内存是MB级别的.
 * <p>
 * Suppose you were given a file containing roughly one billion IP addresses, each of
 * which is a 32-bit quantity. How would you programmatically find an IP address that
 * is not in the file? Assume you have unlimited drive space but only a few megabytes
 * of RAM at your disposal.
 * <p>
 * Hint: Can you be sure there is an address which is not in the file?
 */
public class P12_9_FindMissingIPs {

  // 桶的个数.
  private static final int NUMBER_BUCKET = 1 << 16;


  /**
   * 解决方案:
   * 1. 排序, 然后找出缺失的序列. 但是耗空间. O(N)也是GB级别的. 如果要用到外排序的话,则比较慢.
   * 2. 数据存储在hash表, 然后查找.
   * 3. bitArray存储所有的值. 然后看哪些bit没有被设置. 所需要的存储空间是 2^32 / 8 byte = 512MB.
   * <p>
   * 4. 新的思路:
   * 总的思路就是高位bit进行分组. 然后针对每个分组进行一轮遍历.
   * <p>
   * 针对本题目: 首先针对高16位进行分组,并且统计各个分组中元素的个数.
   * 然后针对每个分组, 各个元素的低16位确定元素在各个分组中的位置.统计完分组的信息,
   * 那么根据分组的下标(高16位bit)和其值是否设置(低16位bit)就可以获得不存在的元素.
   * <p>
   * 时间复杂度O(2^16 * N); 空间复杂度 4byte * 2^16 = 2^18byte == 1/4 MB.
   *
   * @param integers
   */
  public static void solution(Iterable<Integer> integers) {

    int[] counter = new int[NUMBER_BUCKET];
    Iterator<Integer> iterator = integers.iterator();
    while (iterator.hasNext()) {
      // 高位作为index.
      Integer index = iterator.next() >>> NUMBER_BUCKET;
      counter[index]++;
    }

    for (int i = 0; i < counter.length; i++) {
      if (counter[i] < NUMBER_BUCKET) {
        BitSet bitSet = new BitSet(NUMBER_BUCKET);
        iterator = integers.iterator();
        while (iterator.hasNext()) {
          Integer number = iterator.next();

          // number根据高16位映射到特定的桶中, 同时利用低16位确定桶中的位置.
          if (i == (number >>> NUMBER_BUCKET)) {

            // 获取低16位.
            bitSet.set((NUMBER_BUCKET - 1) & number);
          }
        }

        for (int j = 0; j < (1 << NUMBER_BUCKET); j++) {
          // 没有被设置.
          if (!bitSet.get(j)) {
            System.out.println((i << NUMBER_BUCKET) | j);
          }
        }
      }
    }


  }


  public static final int NUMBER_BUCKET2 = 1 << 4;

  /**
   *
   */
  /*public static void solution2(Iterable<Byte> bytes) {

    byte[] counter = new byte[NUMBER_BUCKET2];
    Iterator<Byte> iterator = bytes.iterator();
    while (iterator.hasNext()) {
      // 高位作为index.
      Byte index = iterator.next() >>> NUMBER_BUCKET2;
      counter[index]++;
    }

    for (int i = 0; i < counter.length; i++) {
      if (counter[i] < NUMBER_BUCKET2) {
        BitSet bitSet = new BitSet(NUMBER_BUCKET2);
        iterator = bytes.iterator();
        while (iterator.hasNext()) {
          Byte number = iterator.next();

          // number根据高4位映射到特定的桶中, 同时利用低4位确定桶中的位置.
          if (i == (number >>> NUMBER_BUCKET2)) {

            // 获取低16位.
            bitSet.set((NUMBER_BUCKET2 - 1) & number);
          }
        }

        for (int j = 0; j < (1 << NUMBER_BUCKET2); j++) {
          // 没有被设置.
          if (!bitSet.get(j)) {
            System.out.println((i << NUMBER_BUCKET2) | j);
          }
        }
      }
    }
  }*/
  public static void main(String[] args) {
    HashSet<Byte> set = new HashSet<>();
    List<Byte> list = new LinkedList<>();

    Random random = new Random(31);
    while (set.size() < 250) {
      byte value = (byte) random.nextInt(256);
      if (!set.contains(value)) {
        set.add(value);
        list.add(value);
      }
    }

    System.out.println(list);
  }

}
