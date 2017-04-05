package nowcoder.qunar;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by csqiang on 2017/4/1.
 *
 * @Author mr.darcy
 * Description:
 *
 溢出问题;
 */
public class TwentyCaseToTenCase {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<String> toChange = new ArrayList<>();
        List<Long> result = new ArrayList<>();

        while (input.hasNextLine()) {
            toChange.add(input.nextLine().trim().toLowerCase());
        }

        String str = null;
        char[] chars = null;
        for (int i = 0; i < toChange.size(); i++) {
            str = toChange.get(i);
            chars = str.toCharArray();
            if (str.length() == 0) {
                continue;
            }
            long number = chars[0] - 'a';
            if (number == 0) {
                result.add(0L);
                continue;
            }
            for (int j = 1; j < chars.length; j++) {
                number = number * 26 + chars[j] - 'a';
            }
            result.add(number);
        }

        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }
    }
}


/*

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<String> toChange = new ArrayList<>();
        List<Integer> result = new ArrayList<>();

        while (input.hasNextLine()) {
            toChange.add(input.nextLine().trim().toLowerCase());
        }

        String str = null;
        char[] chars = null;
        for (int i = 0; i < toChange.size(); i++) {
            str = toChange.get(i);
            chars = str.toCharArray();
            if (str.length() == 0) {
                continue;
            }
            int number = chars[0] - 'a';
            if (number == 0) {
                result.add(0);
                continue;
            }
            for (int j = 1; j < chars.length; j++) {
                number = number * 26 + chars[j] - 'a';
            }
            result.add(number);
        }

        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }
    }
}

 */