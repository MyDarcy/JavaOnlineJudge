package eopi.ch9_stackQueue;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Author by darcy
 * Date on 17-9-18 下午10:20.
 * Description:
 * <p>
 * 归一化路径.
 * Unix路径中相对路径进行转化,转为绝对路径.
 * <p>
 * darcy@darcy-ubuntu:~$ cd IdeaProjects/JavaForGit/JavaOnlineJudge/.././JavaOnlineJudge/.././JavaOnlineJudge/src/eopi/../../src/
 * darcy@darcy-ubuntu:~$ cd IdeaProjects/JavaForGit/JavaOnlineJudge/src
 * <p>
 * Write a program which takes a pathname, and returns the shortest equivalent path name.
 * Assume individual directories and files have names that use only alphanumeric characters.
 * Subdirectory names may be combined using forward slashes (/), the current directory (.),
 * and parent directory (..)
 */
public class P4_NormalizePath {

  /**
   * @param path
   * @return
   */
  public static String solution(String path) {
    if (path == null || path.length() == 0) {
      throw new RuntimeException("empty string path.");
    }

    Deque<String> stack = new LinkedList<>();
    if (path.startsWith("/")) {
      stack.addLast("/");
    }
    for (String item : path.split("/")) {
      if (item.equals("..")) {
        // 相对路径.
        if (stack.isEmpty() || stack.peekLast().equals("..")) {
          stack.addLast(item);
        } else {
          if (stack.peekLast().equals("/")) {
            throw new RuntimeException("Path error, trying to go up root " + path);
          }

          // 一次 .. 就意味着对parent路径的移除.
          stack.removeLast();
        }

      } else if (!item.equals(".") && !item.isEmpty()) {
        stack.addLast(item);
      }
    }

    // 输入: ../../a/b/./../b/../c
    // 输出: c/a/../..
    /*StringBuilder sb = new StringBuilder();
    if (!stack.isEmpty()) {
      String item = stack.removeLast();
      sb.append(item);
      while (!stack.isEmpty()) {
        if (!item.equals("/")) {
          sb.append("/");
        }
        item = stack.removeLast();
        sb.append(item);
      }
    }
    return sb.toString();*/

    StringBuilder sb = new StringBuilder();
    if (!stack.isEmpty()) {
      String item = stack.removeFirst();
      sb.append(item);
      while (!stack.isEmpty()) {
        if (!item.equals("/")) {
          sb.append("/");
        }
        item = stack.removeFirst();
        sb.append(item);
      }
    }
    return sb.toString();
  }

  /**
   * book solution.
   *
   * @param path
   * @return
   */
  public static String shortestEquivalentPath(String path) {
    if (path.equals("")) {
      throw new IllegalArgumentException("Empty string is not a legal path.");
    }
    Deque<String> pathNames = new LinkedList<>();
    // Special case: starts with which is an absolute path.
    if (path.startsWith("/")) {
      pathNames.addFirst("/");
    }
    for (String token : path.split("/")) {
      if (token.equals("..")) {
        if (pathNames.isEmpty() || pathNames.peekFirst().equals("..")) {
          pathNames.addFirst(token);
        } else {
          if (pathNames.peekFirst().equals("/")) {
            throw new IllegalArgumentException(
                "Path error, trying to go up root " + path);
          }
          pathNames.removeFirst();
        }
      } else if (!token.equals(".") && !token.isEmpty()) { // Must be a name.
        pathNames.addFirst(token);
      }
    }
    StringBuilder result = new StringBuilder();
    if (!pathNames.isEmpty()) {

      /*
      Returns an iterator over the elements in this deque in reverse
      sequential order.  The elements will be returned in order from
      last (tail) to first (head).
       */
      Iterator<String> it = pathNames.descendingIterator();
      String prev = it.next();
      result.append(prev);
      while (it.hasNext()) {
        // 非第一个元素.
        if (!prev.equals("/")) {
          result.append("/");
        }
        prev = it.next();
        result.append(prev);
      }
    }
    return result.toString();
  }

  public static void main(String[] args) {
    String str = "../../a/b/./../b/../c";
    String result = shortestEquivalentPath(str);
    String result2 = solution(str);
    System.out.println(result);
    System.out.println(result2);



  }

}
