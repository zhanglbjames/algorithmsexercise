package zss.sort;
/**
 * Created by zhanglbjames@163.com
 * on 2017/3/21.
 */
import static zss.util.Print.println;

/**
 * Description:选择排序
 * <p>
 * 1.顺序从左到右
 * 2.首先找到数组中最小的那个元素，第一个元素和找到的最小元素互换
 * 3.移动坐标到第二元素，找到除第一个元素外的右边元素的最小值，交换
 * 4.直到进行到倒数第二元素
 * 5.结束
 */
public class SelectSorter extends AbstractSorter {

    private int compareCount = 0;
    private int exchangeCount = 0;


    public void sort(Comparable[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            exchange(a, i, findMinIndex(a, i));
        }
    }

    /**
     * 交换位置
     *
     * @param a 待排序数组
     * @param index 元素位置
     * @param minIndex 找到的最小值的索引
     */
    private void exchange(Comparable[] a, int index, int minIndex) {

        Comparable tmp = a[index];
        a[index] = a[minIndex];
        a[minIndex] = tmp;

        exchangeCount++;
    }

    /**
     * 找到最小的索引
     *
     * @param a 待排序的数组
     * @param start 起始位置
     * @return int
     */
    private int findMinIndex(Comparable[] a, int start) {

        int minIndex = start;
        for (int i = start; i < a.length; i++) {
            if (a[i].compareTo(a[minIndex]) < 0) {
                minIndex = i;
            }

            compareCount++;
        }
        return minIndex;
    }

    public void summary() {
        println("比较次数：" + Integer.toString(compareCount) + " 交换次数：" + Integer.toString(exchangeCount));
    }
}

