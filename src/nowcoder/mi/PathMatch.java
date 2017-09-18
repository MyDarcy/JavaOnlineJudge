package nowcoder.mi;

import java.util.*;

/**
 * Created by hzq19
 * Date on 2017/9/18 20:12.
 * Description:
 *
 * 假如有一个HTTP Server，可以对不同的路径（Path）注册不同的处理函数（Handler）。路径的格式为/x/y/z，使用“/”作为分隔符，
 * 被分隔开的每个组件都是只包含字母的非空字符串；路径总是以“/”开头，且不以“/”结尾，也不会出现“/x//y”这样的情况。
 * 为了简化问题，我们将处理函数用整数的ID号表示。
 *
 * 对于客户端的HTTP请求，我们需要根据路径匹配最合适的处理函数。匹配规则是：在所有已经注册的路径中，找到包含该路径的最长前缀。
 * 前缀要求对组件的匹配是完整的，因此“/a/b”是“/a/b/c”的前缀，但不是“/a/bc”的前缀。
 *
 * 输入
 * 输入分两部分：
 * 第一部分是注册的路径列表，每行两个元素，用空格分开，分别为路径和ID号；ID号为非0整数，且不会重复。
 * 第二部分是需要匹配的路径列表，每行一个路径。
 * 两个部分用只包含一个短横线的特殊行分隔。
 *
 * 输出
 * 对于每个需要匹配的路径，输出其匹配的ID号，每行一个；如果没有找到匹配路径，则输出0。
 *
 */
public class PathMatch {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    boolean before = true;

    String line = null;
    Map<String, Integer> map = new HashMap<>();

    List<String> handlers = new LinkedList<>();
    List<String> toBeDone = new LinkedList<>();

    while (input.hasNextLine()) {
      line = input.nextLine().trim();
      if (line.equals("-")) {
        before = false;
      }

      if (before) {
        handlers.add(line);
      } else {
        if (!line.equals("-")) {
          toBeDone.add(line);
        }
      }
    }

    input.close();

    String[] splits = null;
    for (int i = 0; i < handlers.size(); i++) {
      splits = handlers.get(i).split("\\s+");
      map.put(splits[0], Integer.parseInt(splits[1]));
    }


    Set<String> paths = map.keySet();

    for (int i = 0; i < toBeDone.size(); i++) {
      String target = toBeDone.get(i);
      String path = solution(target, paths);
      if (path == null) {
        System.out.println(0);
      } else {
        System.out.println(map.get(path));
      }
    }

    /*System.out.println(map);

    System.out.println(toBeDone);*/
  }

  private static String solution(String target, Set<String> paths) {

    if (target == null || target.length() == 0) {
      return null;
    }

    int maxLength = 0;
    String maxString = null;
    for (String path : paths) {
      if (target.startsWith(path)) {
        if (target.equals(path)) {
          if (path.length() > maxLength) {
            maxLength = path.length();
            maxString = path;
          }
        } else if (target.charAt(path.length()) == '/') {
          if (path.length() > maxLength) {
            maxLength = path.length();
            maxString = path;
          }
        }
      }
    }
    return maxString;
  }
}

/*
/a 1
/a/b 2
/a/b/c/d/e/f/g/h/i 3
/a/bcde 4
-
/a
/a/b
/a/b/c/d
/a/b/c/d/e/f/g/h/i
/a/bcde
/a/bcdefghi
/b
 */