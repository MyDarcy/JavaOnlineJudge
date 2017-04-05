package nowcoder.qunar;

import java.util.*;

/**
 * Created by csqiang on 2017/4/1.
 *
 * @Author mr.darcy
 * Description:
 */
/*
有一个单词列表，一个初始单词和一个最终单词，初始单词需要通过单词列表逐步变换到最终单词，求变换所需的最短变换路径长度。
变换规则：每次只能变动1个字母（不可交换位置，如：从abc变成cba属于变动了2个字母），每次变换只能从单词列表中选取。
例如：初始单词hot，最终单词dog，单词列表[got, dot, god, dog, lot, log]，最短变换路径为[hot,dot,dog]，最短变换路径长度为3。
注：单词列表中包含最终单词，不包含初始单词；列表中每一项单词长度与初始单词、最终单词相同；列表中单词不重复；所有字母均为小写。
 */
public class WordChange {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        String origin = input.nextLine();
        String target = input.nextLine();

        String[] wordList = input.nextLine().trim().split("\\s+");
        boolean[] used = new boolean[wordList.length];

        if (origin.length() != target.length()) {
            return;
        }

        int diffCharCount = 0;
        for (int i = 0; i < origin.length(); i++) {
            if (origin.charAt(i) != target.charAt(i)) {

                diffCharCount++;
            }
        }

        Map<Integer, List<String>> numberAndWordList = new HashMap<>();

        String str = null;
        for (int i = 0; i < wordList.length; i++) {
            if (wordList[i].length() != origin.length()) {
                continue;
            }
            str = wordList[i];
            int diffCount = 0;
            for (int j = 0; j < str.length(); j++) {
                if (str.charAt(j) != origin.charAt(j)) {
                    diffCount++;
                }
            }
            List<String> list = numberAndWordList.get(diffCount);
            if (list == null) {
                numberAndWordList.put(diffCount, new ArrayList<>());
            }
            numberAndWordList.get(diffCount).add(wordList[i]);
        }

        /*for (Integer key : numberAndWordList.keySet()) {
            for (int i = 0; i < numberAndWordList.get(key).size(); i++) {
                System.out.print(numberAndWordList.get(key).get(i) + " ");
            }
            System.out.println();
        }*/

        int maxDifferentCharNumber = numberAndWordList.keySet().stream().max(Integer::compareTo).get();
        System.out.println(maxDifferentCharNumber);

        Set<String> set = new HashSet<>();
        set.addAll(Arrays.asList(wordList));
        // 不包含;
        if (!set.contains(target)) {
            return;
        }

        System.out.println(maxDifferentCharNumber + 1);

        /*for (int i = 1; i < maxDifferentCharNumber; i++) {
            *//*if (i == maxDifferentCharNumber) {
                int index = numberAndWordList.get(i).indexOf(target);
                // 最后一个list中不包含，直接返回;
                if (index == -1) {
                    return;
                }
            }*//*

            List<String> first = numberAndWordList.get(i);
            List<String> second = numberAndWordList.get(i);




        }*/
    }
}
