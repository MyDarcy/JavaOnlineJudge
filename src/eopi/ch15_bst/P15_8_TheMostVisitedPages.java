package eopi.ch15_bst;

import java.util.List;

/**
 * Author by darcy
 * Date on 17-10-2 下午3:48.
 * Description:
 * <p>
 * 访问次数最多的页.
 * <p>
 * You are given a server log file containing billions of lines. Each line contains a
 * number of fields. For this problem, the relevant field is an id denoting the page that
 * was accessed.
 * <p>
 * Write a function to read the next line from a log file, and a function to find the k
 * most visited pages, where k is an input to the function. Optimize performance for the
 * situation where calls to the two functions are interleaved. You can assume the set of
 * distinct pages is small enough to fit in RAM.
 * <p>
 * As a concrete example, suppose the log file ids appear in the following order:
 * g,a,t,t,a,a,a,g,t,c,t,a,t, i.e., there are four pages with ids a,c,g,t. After the first 10
 * lines have been read, the most common page is a with a count of 4, and the next most
 * common page is t with a count of 3.
 * <p>
 * Hint: For each page, count of the number of times it has been visited
 */
public class P15_8_TheMostVisitedPages {

  /**
   * @param iterable
   * @return
   */
  public static List<Integer> solution(Iterable<Integer> iterable) {

    return null;
  }

}
