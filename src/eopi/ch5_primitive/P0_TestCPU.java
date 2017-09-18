package eopi.ch5_primitive;

import java.io.*;
import java.nio.ByteOrder;

/**
 * Author by darcy
 * Date on 17-9-18 上午10:35.
 * Description:
 *
 * from: http://www.iteedu.com/plang/java/superjava/base/jvmddxd.php
 * from: http://blog.chinaunix.net/uid-1844931-id-3022904.html
 *
 * 测试机器是小端还是大端.
 *
 * 如果将一个32位的整数0x12345678存放到一个整型变量（int）中，这个整型变量采用大端或者小端模式在
 * 内存中的存储由下表所示。为简单起见，本书使用OP0表示一个32位数据的最高字节MSB（Most Significant Byte）
 * ，使用OP3表示一个32位数据最低字节LSB（Least Significant Byte）。
 *
 * 地址偏移	大端模式	  小端模式
 * 0x00	  12（OP0）	78（OP3）
 * 0x01	  34（OP1）	56（OP2）
 * 0x02	  56（OP2）	34（OP1）
 * 0x03  	78（OP3）	12（OP0）
 *
 * 32bit宽的数0x12345678
 * 在Little-endian模式CPU内存中的存放方式（假设从地址0x4000开始存放）为：
 *
 * 内存地址 0x4000 0x4001 0x4002 0x4003
 * 存放内容  0x78   0x56   0x34   0x12
 *
 * 而在Big-endian模式CPU内存中的存放方式则为：
 * 内存地址  0x4000  0x4001 0x4002 0x4003
 * 存放内容    0x12   0x34    0x56  0x78
 *
 * 采用大小模式对数据进行存放的主要区别在于在存放的字节顺序，大端方式将高位存放在低地址，小端方式将高位存放在高地址。
 * 采用大端方式进行数据存放符合人类的正常思维，而采用小端方式进行数据存放利于计算机处理。到目前为止，采用大端或者
 * 小端进行数据存放，其孰优孰劣也没有定论。
 */
public class P0_TestCPU {

  /**
   * JVM是大端.
   */
  public static void test1() {
    byte[] byteAr = new byte[]{0x78,0x56,0x34,0x12};
    ByteArrayInputStream bais = new ByteArrayInputStream(byteAr);
    DataInputStream dis = new DataInputStream(bais);
    try {
      System.out.println(Integer.toHexString(dis.readInt()));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * JVM是大端.
   */
  public static void test2() {
    /**
     * 将整形(int)转为字节数组（byte[]）
     */
    int a = 0x12345678;
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    try {
      dos.writeInt(a);
    } catch (IOException e) {
      e.printStackTrace();
    }
    byte[] b = baos.toByteArray();
    for(int i = 3; i >= 0; i--)
    {
      System.out.print(Integer.toHexString(b[i]));
    }
    System.out.println();
  }

  public static void main(String[] args) {
    if (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN) {
      System.out.println("BIG_ENDIAN");
    } else {
      System.out.println("LITTLE_ENDIAN");
    }


    System.out.println("-----test1----");
    test1();

    System.out.println("---- test2-----");
    test2();
  }
}

/*

// c语言的实现.
#include <stdio.h>

int main()
{
    union ut{
        short s;
        char c[2];
    }u;

    if(sizeof(short) == 2)
    {
        u.s = 0x0102;
        if(u.c[0] == 1 && u.c[1] == 2)
        {
            printf("big enidan.\n");
        } else if(u.c[0] == 2 && u.c[1] == 1)
        {
            printf("little endian.\n");
        }
    }
    return 0;
}
 */

/*
from: http://blog.csdn.net/zhaoshuzhaoshu/article/details/37600857/

1. 什么是大端,什么是小端:
所谓的大端模式，是指数据的低位保存在内存的高地址中，而数据的高位，保存在内存的低地址中；

所谓的小端模式，是指数据的低位保存在内存的低地址中，而数据的高位保存在内存的高地址中。

2.为什么会有大小端:
为什么会有大小端模式之分呢？这是因为在计算机系统中，我们是以字节为单位的，每个地址单元都对应着一个字节，一个字节为8bit。但是在C语言中除了8bit的char之外，还有16bit的short型，32bit的long型（要看具体的编译器），另外，对于位数大于8位的处理器，例如16位或者32位的处理器，由于寄存器宽度大于一个字节，那么必然存在着一个如果将多个字节安排的问题。因此就导致了大端存储模式和小端存储模式。例如一个16bit的short型x，在内存中的地址为0x0010，x的值为0x1122，那么0x11为高字节，0x22为低字节。对于大端模式，就将0x11放在低地址中，即0x0010中，0x22放在高地址中，即0x0011中。小端模式，刚好相反。我们常用的X86结构是小端模式，而KEIL C51则为大端模式。很多的ARM，DSP都为小端模式。有些ARM处理器还可以由硬件来选择是大端模式还是小端模式。

3.大小端在内存中的存放方式举例:
例如，16bit宽的数0x1234在Little-endian模式CPU内存中的存放方式（假设从地址0x4000开始存放）为：

内存地址 0x4000 0x4001
存放内容 0x34   0x12

而在Big-endian模式CPU内存中的存放方式则为：

内存地址  0x4000  0x4001
存放内容    0x12   0x34



32bit宽的数0x12345678
在Little-endian模式CPU内存中的存放方式（假设从地址0x4000开始存放）为：

内存地址 0x4000 0x4001 0x4002 0x4003
存放内容  0x78   0x56   0x34   0x12

而在Big-endian模式CPU内存中的存放方式则为：
内存地址  0x4000  0x4001 0x4002 0x4003
存放内容    0x12   0x34    0x56  0x78



4.如何测试编译器是大端还是小端:
下面这段代码可以用来测试一下你的编译器是大端模式还是小端模式：

#include<stdio.h>



int main()

{

    short int x;

    char x0,x1;

    x=0x1122;

    x0=((char *)&x)[0];  //低地址单元

    x1=((char *)&x)[1];  //高地址单元

    printf("x0=0x%x,x1=0x%x",x0,x1);// 若x0=0x11,则是大端; 若x0=0x22,则是小端......

    return 0;

}


 */