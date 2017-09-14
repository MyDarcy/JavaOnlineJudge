package com.darcy.main.review;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author by darcy
 * Date on 17-9-14 下午10:18.
 * Description:
 *
 * Trie树.
 *
 */
public class Tries {

  static class TrieNode {
    List<String> words = new ArrayList<>();
    TrieNode[] next = new TrieNode[26];

    public TrieNode() {}
  }

  public void insertNode(TrieNode root, String wd) {
    if (wd.length() == 0) {
      return;
    }

    if (root == null) {
      root = new TrieNode();
    }

    char[] chars = wd.toCharArray();
    Arrays.sort(chars);

    TrieNode next = root;
    int i = 0;
    while (i < chars.length) {
      /*if (next.next[i]) {

      }*/
    }
  }

}
