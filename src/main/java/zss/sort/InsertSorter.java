package zss.sort;
/**
 * Created by zhanglbjames@163.com
 * on 2017/3/22.
 */

import static zss.util.Print.println;

/**
 * InsertSorter
 * <p>
 * Description:
 * <p>
 * 位置索引从 0 - length-1
 * 向左插入a左边的顺序已经排好，但是还会变化
 * 1.索引为 j = index ,将a[j]与a[j-1]比较，小于则交换位置,就 j = j -1进行2;大于等于则不交换 进行3.
 * 2.相邻的元素两两比较，直到 j = 1
 * 3.将索引位置设为index = index+1
 * 4. index is range of [1,length-1]
 */
public class InsertSorter extends AbstractSorter {

    private int compareCount = 0;
    private int exchangeCount = 0;


    public void sort(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            for (int j = i; j >= 1; j--) {
                if (less(a, j)) {
                    exchange(a, j);
                } else {
                    break;
                }
            }

        }

    }

    /**
     * 在优化自顶向下归并排序的优化
     */
    public void sort(Comparable[] a, int start, int end) {
        for (int i = start + 1; i <= end; i++) {
            for (int j = i; j >= 1 + start; j--) {
                if (less(a, j)) {
                    exchange(a, j);
                } else {
                    break;
                }
            }

        }
    }

    private void exchange(Comparable[] a, int index) {
        Comparable obj = a[index];
        a[index] = a[index - 1];
        a[index - 1] = obj;

        exchangeCount++;
    }

    @SuppressWarnings("unchecked")
    private boolean less(Comparable[] a, int index) {

        compareCount++;

        if (a[index].compareTo(a[index - 1]) < 0) {
            return true;
        }
        return false;
    }

    public void summary() {
        println("比较次数：" + Integer.toString(compareCount) + " 交换次数：" + Integer.toString(exchangeCount));
    }
}
