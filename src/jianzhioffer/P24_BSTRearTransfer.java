package jianzhioffer;

/**
 * Created by darcy
 * 2017/4/21--0:31
 * Description: 找到根节点，左子树的范围，右子树的范围;
 */
public class P24_BSTRearTransfer {

    /*
       cannot work.
     */
    public boolean VerifySquenceOfBST(int[] sequence) {
        if (sequence == null || sequence.length == 0) {
            return false;
        }

        int sequenceLength = sequence.length;
        int root = sequence[sequenceLength - 1];

        int leftTreeIndex = 0;
        for (; leftTreeIndex < sequenceLength; leftTreeIndex++) {
            if (sequence[leftTreeIndex] > root) {
                break;
            }
        }

        int rightTreeIndex = leftTreeIndex;
        for (; rightTreeIndex < sequenceLength; rightTreeIndex++) {
            if (sequence[rightTreeIndex] < root) {
                return false;
            }
        }

        /*boolean left = true;
        if (leftTreeIndex > 0) {
            left = VerifySquenceOfBST(sequence, 0,  leftTreeIndex);
        }

        boolean right = true;
        if (rightTreeIndex < sequenceLength - 1) {
            right = VerifySquenceOfBST(sequence, rightTreeIndex, )
        }

        return left && right;*/

        return false;


    }

    /*
        ok....
     */
    public boolean VerifySquenceOfBST2(int[] sequence) {
        if (sequence == null || sequence.length == 0) {
            return false;
        }

        int sequenceLength = sequence.length;
        return VerifySquenceOfBST2(sequence, 0, sequenceLength - 1);
    }

    private boolean VerifySquenceOfBST2(int[] sequence, int start, int end) {

        if (start == end) {
            return true;
        }

        int root = sequence[end];
        int leftTreeIndex = start;
        while (sequence[leftTreeIndex] < sequence[end]) {
            leftTreeIndex++;
        }
        if (leftTreeIndex > start) {
            leftTreeIndex--; // 定位到左子树的，
        } // else --> 进入下一轮递归;
        int rightTreeIndex = leftTreeIndex + 1;
        int rightTreeStart = rightTreeIndex;
        if (rightTreeIndex < end) {
            for (; rightTreeIndex < end; rightTreeIndex++) {
                if (sequence[rightTreeIndex] < root) {
                    return false;
                }
            } // 循环退出的时候rightTreeIndex已经到root index了。
        }

        if (rightTreeIndex == end) {
            rightTreeIndex--;
        }

        return VerifySquenceOfBST2(sequence, start, leftTreeIndex)
                && VerifySquenceOfBST2(sequence, rightTreeStart, rightTreeIndex);

    }

    /*
    链接：https://www.nowcoder.com/questionTerminal/a861533d45854474ac791d90e447bafd
    来源：牛客网*/

    public boolean ju(int[] a, int star, int root) {
        if (star >= root)
            return true;
        int i = root;
        //从后面开始找
        while (i > star && a[i - 1] > a[root])
            i--;//找到比根小的坐标
        //从前面开始找 star到i-1应该比根小
        for (int j = star; j < i - 1; j++)
            if (a[j] > a[root])
                return false;

        return ju(a, star, i - 1) && ju(a, i, root - 1);
    }

    /*
    非递归也是一个基于递归的思想：
    左子树一定比右子树小，因此去掉根后，数字分为left，right两部分，right部分的
    最后一个数字是右子树的根, 它也比左子树所有值大，因此我们可以每次只看右子树是否符合条件
    即可，即使到达了左子树左子树也可以看出由左右子树组成的树还像右子树那样处理
 
    对于左子树回到了原问题，对于右子树，左子树的所有值都比右子树的根小可以暂时把他看出右子树的左子树
    只需看看右子树的右子树是否符合要求即可
     */

    /*
    链接：https://www.nowcoder.com/questionTerminal/a861533d45854474ac791d90e447bafd
    来源：牛客网

    bool VerifySquenceOfBST(vector<int> sequence) {
        int size = sequence.size();
        if(0==size)return false;
 
        int i = 0;
        while(--size)
        {
            while(sequence[i++]<sequence[size]);
            while(sequence[i++]>sequence[size]);
 
            if(i<size)return false;
            i=0;
        }
        return true;
    }
     */

}
