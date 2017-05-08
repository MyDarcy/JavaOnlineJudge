package jianzhioffer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.function.Consumer;

/**
 * Created by darcy
 * 2017/5/6--17:16
 * Description:
 */
public class P30_LeastKNumber {
    /**
     * treeset 红黑树特性来做...
     * @param input
     * @param k
     * @return
     */
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        if (input == null || input.length == 0 || k == 0 || k > input.length)
            return new ArrayList<>();

        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < input.length; i++) {
            set.add(input[i]);
        }

        ArrayList<Integer> list = new ArrayList<>();

        /*set.stream().forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                list.add(integer);
            }
        });*/

        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }

        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            result.add(list.get(i));
        }
        return result;

    }


}
