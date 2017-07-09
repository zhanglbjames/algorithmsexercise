package zss.sort;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/3/24.
 */

import java.util.Random;
import static zss.util.Print.println;

/**
 * <p>Description</p>
 * <p>
 * 快速排序
 * <p>
 * <p>
 * 典型的分治思想的应用
 */
public class QuickSorter extends AbstractSorter {

    private static Random random = new Random();
    private int compareCount = 0;
    private int exchangeCount= 0;

    public void sort(Comparable[] a) {
        partition(a,0,a.length-1);
    }

    /**
     * 描述：
     * 1.随机选择一个划分值a[base] 其中 base 范围为 [start,end]，并将其与最后一个元素进行交换
     * 2.在数组的左边划分一个小于等于区间（下称区间），刚开始这个区间没有元素
     * 3.从左到右，和划分值进行比较，如果小于等于这个划分值则与小于等于区间外的右边第一个元素
     * 进行交换，之后将区间吸纳刚刚交换的右临元素
     * 4.如果大于则不交换，区间也不扩容，然后继续向右进行 3
     * 5.直到倒数第二个元素
     * 6.最后将最后一个元素（划分值）直接和区间右临的第一个元素交换位置
     *
     * 改进：
     * 三相切分，当数组中重复元素比较多时能减少重复元素排定位置的递归次数
     * 即小于基准值的放在左边，大于基准值的放在右边，中间的正好就是等于基准值的，
     * 然后下一次排定时就只排定小于和大于基准值的区间，这样就可以节省等于基准值的递归排定
     *
     * @param a     待排序数组
     * @param start 起始位置
     * @param end   结束位置
     */
    @SuppressWarnings("unchecked")
    private void partition(Comparable[] a, int start, int end) {

        /*
        * 等于 ：只有一个元素而且，但上次分区的时候排定的位置不是上个数组的首个位置
        * start > end : 其实指的是 start - end = 1 ，也就是上次分区的时候排定的位置是上个数组的首个位置
        * 则有 partition(a,start,start-1);因为小于等于区间始终未空，也就index没有增加过 始终为 index = start -1
        * */
        if (start >= end) {
            return;
        }

        //1.
        //start + [0,end-start] = [start,end]   [0,end-start] = nextInt(end-start+1)
        int base = start + random.nextInt(end - start + 1);//nextInt(3) -> 0,1,2
        exchange(a, base, end);

        //2.
        int index = start - 1;//区间右边界指针（被包含元素的最有一个索引）
        for (int i = start; i < end; i++) { //不包括最后一个元素(划分值)
            if (a[i].compareTo(a[end]) <= 0) {
                exchange(a, index + 1, i);
                index++;
                exchangeCount ++;
            }
            compareCount ++;
        }

        //6.
        exchange(a, index + 1, end);
        exchangeCount ++;

        //递归  [start,index] -> index+1(已排定) -> [index+2,end]
        partition(a, start, index);
        partition(a, index + 2, end);

    }

    private void exchange(Comparable[] a, int i, int j) {
        Comparable tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }


    public void summary() {
        println("比较次数：" + compareCount + " 交换次数：" + exchangeCount);

    }
}
