package nowcoder.toutiao;

import java.util.*;

/**
 * Created by csqiang on 2017/3/30.
 *
 * @Author mr.darcy
 * Description:
 */
public class QueryInMStringOfNString {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String[] mnString = input.nextLine().trim().split("\\s+");
        //段落的行数;
        int N = Integer.parseInt(mnString[0]);

        //查询的个数;
        int M = Integer.parseInt(mnString[1]);

        String[] paragraph = new String[N];
        String[] querys = new String[M];
        int i = 0;
        while (input.hasNextLine()) {
            if (i < N) {
                paragraph[i++] = input.nextLine();
            } else {
                querys[i-N] = input.nextLine();
                i++;
            }
        }

        /*System.out.println(Arrays.toString(paragraph));

        System.out.println();

        System.out.println(Arrays.toString(querys));*/

        int[] result = new int[M];
        List<Set<String>> setList = new ArrayList<>();
        for (int j = 0; j < N; j++) {
            setList.add(new HashSet<>());
        }

        for (int j = 0; j < N; j++) {
            setList.get(j).addAll(Arrays.asList(paragraph[j].split("\\s+")));
        }

        for (int j = 0; j < M; j++) {

            String[] words = querys[j].split("\\s+");
            Set<String> wordsSet = new HashSet<>();
            wordsSet.addAll(Arrays.asList(words));

            int maxWordCount = 0;
            int maxWordCountIndex = 0;
            for (int k = 0; k < N; k++) {
                int wordCount = 0;
                for (String str : wordsSet) {
                    if (setList.get(k).contains(str)) {
                        wordCount++;
                    }
                }
                if (maxWordCount < wordCount) {
                    maxWordCount = wordCount;
                    maxWordCountIndex = k;
                }
            }
            result[j] = maxWordCountIndex;
        }

        for (int j = 0; j < M; j++) {
            System.out.println(paragraph[result[j]]);
        }
    }
}
