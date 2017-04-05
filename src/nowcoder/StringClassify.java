package nowcoder;

import java.util.*;

/**
 * 字符串的分类;
 *
 * @Author mr.darcy
 * @Author 2017/3/23
 * @Version 1.0
 */
public class StringClassify {

    public static void main(String[] args) {

        // 将单个字符串排序完毕放入set中;
        Scanner input = new Scanner(System.in);
        // nextInt和nextLine混用易出问题;
        int number = Integer.valueOf(input.nextLine().trim());
        ArrayList<String> strings = new ArrayList<>();
        while (input.hasNextLine()) {
            String str = input.nextLine().trim();
            if (str != null || !str.trim().equals(""))
                strings.add(str);
        }

//        System.out.println(strings.size());

        Set<String> stringSet = new HashSet<>();
        char[] charArray = null;
        ArrayList<String> distinctStrings = new ArrayList<>();
        for (int i = 0; i < strings.size(); i++) {
            charArray = strings.get(i).toCharArray();
            Arrays.sort(charArray);
            stringSet.add(String.valueOf(charArray));

        }

        System.out.println(stringSet.size());
    }
}
/*
4
abcd
bcda
cdab
adcb


4
abcd
abc
dc
abcd
 */