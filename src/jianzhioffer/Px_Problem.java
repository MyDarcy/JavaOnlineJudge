package jianzhioffer;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * Author by darcy
 * Date on 17-4-5 下午9:05.
 * Description:
 */
public class Px_Problem {
    public static void main(String[] args) {
        /*System.out.println(111);*/

        TreeSet<String> treeSet = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int comp = new StringBuilder(o1).reverse().toString().compareTo(new StringBuilder(o2).toString());
                return comp;
            }
        });

        String[] strings = {"3", "32", "321", "4", "42"};
        for (int i = 0; i < strings.length; i++) {
            treeSet.add(strings[i]);
        }

    }
}
