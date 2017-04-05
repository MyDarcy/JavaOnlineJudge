package nowcoder.neteasy;

import java.util.*;

/**
 * Created by csqiang on 2017/3/26.
 *
 * @Author mr.darcy
 * Description:
    */
public class SurpriseReg {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        String expression = input.nextLine().trim();

        Set<Character> numberSet = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            numberSet.add((char)(i + '0'));
        }

        Set<Character> operatorSet = new HashSet<>();
        operatorSet.add('+');
        operatorSet.add('-');
        operatorSet.add('*');

        Deque<Integer> opsNumbber = new LinkedList<>();
        Deque<Character> opsSign = new LinkedList<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            if (numberSet.contains(ch)) {
                opsNumbber.addLast(ch - '0');
            } else if (operatorSet.contains(ch)) {
                opsSign.addLast(ch);
            } else if (ch == ')') {
                while (!opsSign.isEmpty()) {
                    int  i1 = opsNumbber.pollFirst();
                    int  i2 = opsNumbber.pollFirst();
                    char ops = opsSign.pollFirst();
                    opsNumbber.addFirst(getResult(i1, i2, ops));
                }
            }
        }
        while (!opsSign.isEmpty()) {
            int  i1 = opsNumbber.pollFirst();
            int  i2 = opsNumbber.pollFirst();
            char ops = opsSign.pollFirst();
            opsNumbber.addFirst(getResult(i1, i2, ops));
        }

        System.out.println(opsNumbber.pop());

    }

    private static Integer getResult(int i1, int i2, char ops) {
        if (ops == '+') {
            return i1 + i2;
        } else if (ops == '-') {
            return i1 - i2;
        } else {
            return i1 * i2;
        }
    }
}
