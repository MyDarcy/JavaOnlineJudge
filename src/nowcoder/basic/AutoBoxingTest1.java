package nowcoder.basic;

/**
 * Created by csqiang on 2017/3/30.
 *
 * @Author mr.darcy
 * Description:
 */
public class AutoBoxingTest1 {
    public void add(Byte b)
    {
        b = b++;
    }
    public void test()
    {
        Byte a = 127;
        Byte b = 127;
        add(++a);
        System.out.print(a + " ");
        add(b);
        System.out.print(b + "");
    }

    public static void main(String[] args) {
        AutoBoxingTest1 test1 = new AutoBoxingTest1();
        test1.test();

        String s=new String("hellowworld");
    }
}
