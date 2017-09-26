package eopi.ch13_hash;

import com.sun.org.apache.bcel.internal.generic.INEG;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Author by darcy
 * Date on 17-9-26 下午3:11.
 * Description:
 * <p>
 * 实现LRU缓存.
 *
 * 1. 插入新的元素作为新访问的元素.
 * 2. 插入已经存在的元素同样把它更新为最新元素.
 * 3. 查找操作同样将元素更新为最新元素.
 *
 */
public class P13_3_ImplLRU {

  private LinkedHashMap<Integer, Integer> lru;

  public P13_3_ImplLRU(int capacity) {

    /**
     * 重写removeEldestEntry方法来实现有界存储和LRU.
     */
    lru = new LinkedHashMap<Integer, Integer>() {
      @Override
      protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > capacity;
      }
    };
  }

  public Integer lookup(Integer key) {
    if (!lru.containsKey(key)) {
      return null;
    }
    return lru.get(key);
  }

  public Integer insert(Integer key, Integer value) {
    Integer oldValue = lru.get(key);
    if (!lru.containsKey(key)) {
      lru.put(key, value);
      return oldValue;
    } else {
      return null;
    }
  }

  public Integer remove(Integer key) {
    return lru.remove(key);
  }

}
