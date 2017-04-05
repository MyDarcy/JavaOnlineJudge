package nowcoder.qunar;

import java.util.*;

/**
 * Created by csqiang on 2017/4/1.
 *
 * @Author mr.darcy
 * Description:
 */
public class WordChange2 {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        String origin = input.nextLine();
        String target = input.nextLine();

        String[] wordList = input.nextLine().trim().split("\\s+");
        boolean[] used = new boolean[wordList.length];
        int[] pointToPre = new int[wordList.length];

        if (origin.length() != target.length()) {
            return;
        }

        int indexOfTarget = -1;
        for (int i = 0; i < wordList.length; i++) {
            if (target.equals(wordList[i])) {
                indexOfTarget = i;
                used[i] = true;
            }
        }
        // 这个数
        if (indexOfTarget == -1) {
            return;
        }

        Deque<String> deque = new LinkedList<>();
        deque.addLast(wordList[indexOfTarget]);
        deque.addLast("#");
        while (!deque.peek().equals("#")) {
            String str = deque.pollFirst();
            for (int i = 0; i < wordList.length; i++) {
                if (!used[i]) {
                    boolean isDiffOneChar = diff(str, wordList[i]);
                }
            }
        }

    }

    private static boolean diff(String str, String s) {
        int diffCount = 0;
        for (int i = 0; i < str.length(); i++) {

        }

        return false;
    }
}
