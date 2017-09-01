package com.darcy.main.cleancode_v1_0_3.ArrayString;

/**
 * Author by darcy
 * Date on 17-9-1 下午9:02.
 * Description:
 *
 * 如上题,但是可以调用多次.
 *
 */
public class P16_ReadNCharGivenRead4APIMultiCall {

  private char[] buffer = new char[4];

  public int read(char[] buf, int n) {
    int offset = 0, bufsize = 0;

    int readBytes = 0;
    boolean eof = false;
    while (!eof && readBytes < n) {
      if (bufsize == 0) {
        bufsize = read4(buffer);
        eof = bufsize < 4;
      }
      int bytes = Math.min(n - readBytes, bufsize);
      System.arraycopy(buffer /* src */, offset /* srcPos */,
          buf /* dest */, readBytes /* destPos */, bytes /* length */);
      offset = (offset + bytes) % 4;
      bufsize -= bytes;
      readBytes += bytes;
    }
    return readBytes;

  }

  private int read4(char[] buffer) {
    return 4;
  }

}
