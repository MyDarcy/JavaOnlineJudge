package nowcoder.neteasy;

import java.util.Scanner;

/**
 * Created by hzq19
 * Date on 2017/9/9 16:04.
 * Description:
 */
public class BracketCount {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    String line = input.nextLine();
    int result = 1;
    int count = 0;
    for (int i = 0; i < line.length(); i++) {
      if (line.charAt(i) == '(') {
        count++;
        result *= count;
      } else {
        count--;
      }
    }
    System.out.println(result);
  }
}

/*
(())()
 */