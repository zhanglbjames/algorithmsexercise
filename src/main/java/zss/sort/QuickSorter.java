package zss.sort;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/3/24.
 */

/**
 * <p>Description</p>
 * <p>
 * 快速排序
 *
 * <p>
 * 典型的分治思想的应用
 */
public class QuickSorter extends AbstractSorter {

    public void sort(Comparable[] a) {

    }

    /**
     * 描述：
     * 1.选择左边第一个元素作为基准待定排元素
     * 2.将这个待排定元素挖出来 base = a[start]
     * 3.从end开始向左(rightIndex = end)，直到找到一个小于等于基准元素的元素，挖出来填入左边的坑中
     * 4.从start开始向右（leftIndex = start），直到找到一个大于等于基准元素，挖出来填入右边的坑中
     * 5.直到  leftIndex == rightIndex
     *
     * Note: 右边是先比较再减（记忆方式，右边首先会用a[end]比较）
     *       左边是先加再比较 (记忆方式，左边第一个为预先挖的坑，肯定从第二个位置开始 a[start+1])
     *
     * @param a     待排序数组
     * @param start 起始位置
     * @param end   结束位置
     */
    private int partition(Comparable[] a, int start, int end) {

        if (start == end){//只有一个元素直接返回
            return start;
        }

        //含有两个元素及以上
        Comparable base = a[start];//选择第一个数作为待排定
        int leftIndex = start;
        int rightIndex = end;


        while (leftIndex != rightIndex){
            Boolean tag = true;//左右交换查找标志 true--开始右查找 false--开始左查找
            if (tag) {

            } else {

            }

        }  //指针重合 执行一次 然后退出循环

        return leftIndex;

    }

    public void summary() {

    }
}
