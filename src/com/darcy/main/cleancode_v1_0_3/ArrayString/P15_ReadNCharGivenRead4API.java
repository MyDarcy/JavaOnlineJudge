package com.darcy.main.cleancode_v1_0_3.ArrayString;

/**
 * Author by darcy
 * Date on 17-9-1 下午8:54.
 * Description:
 *
 * 用给定的每次最多读取4个字符的api函数去读取n个字符到buffer缓冲区中。
 * 实现的read(buffer, n)只能调用一次.
 *
 * The API: int read4(char *buf) reads 4 characters at a time from a file.
 * The return value is the actual number of characters read. For example, it returns 3 if there
 * is only 3 characters left in the file.
 * By using the read4 API, implement the function int read(char *buf, int n) that reads n
 * characters from the file.
 *
 * Note: The read function will only be called once for each test case.
 *
 */
public class P15_ReadNCharGivenRead4API {

  /**
   * System.arraycopy()
   * @param buf
   * @param n
   * @return
   */
  public int read(char[] buf, int n) {

    char[] buffer = new char[4];
    int readBytes = 0;
    boolean eof = false;
    while (!eof && readBytes < n) {
      int sz = read4(buffer);
      if (sz < 4) eof = true;
      int bytes = Math.min(n - readBytes, sz);
      System.arraycopy(buffer /* src */, 0 /* srcPos */,
          buf /* dest */, readBytes /* destPos */, bytes /* length */);
      readBytes += bytes;
    }
    return readBytes;
  }

  private int read4(char[] buffer) {

    return 4;

  }
}
