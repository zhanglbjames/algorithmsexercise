package zss.sort;

/**
 * Created by zhanglbjames@163.com
 * on 2017/3/22.
 */

import static zss.util.Print.println;

/**
 * TopMergeSorter
 * <p>
 * Description:自顶向下归并排序
 * <p>
 * 先递归再合并
 * <p>
 * 典型的分治思想的应用
 * <p>
 * 1.最基本的自顶向下归并排序：
 * 尽量拆分为相等两份，然后对这两个子数组进行合并，
 * 如果子数组还有元素则继续拆分，知道只有一个元素不再拆分，返回<br>
 * @see #doBaseSort(Comparable[], int, int, int)
 * <p>
 * 2.对小规模数组使用插入排序
 * 对于1，不是只有一个元素时返回，而是有小于指定阀值的子数组进行插入排序 <br>
 * @see #doInsertSort(Comparable[], int, int, int)
 */
public class TopMergeSorter extends AbstractSorter {

    /**
     * 插入排序阀值
     */
    private int threshold = 45;
    private int compareCount = 0;
    //private int exchangeCount = 0;

    /**
     * 辅助数组
     */
    private Comparable[] aux;


    /**
     * 是否小数组使用插入排序
     */
    private boolean useInsertSort = false;

    public void sort(Comparable[] a) {
        allocateAuxSpace(a);//分配空间

        if (useInsertSort) {
            doInsertSort(a, 0, (a.length - 1) / 2, a.length - 1);
        } else {
            doBaseSort(a, 0, (a.length - 1) / 2, a.length - 1);
        }

    }

    /**
     * 基本归并排序
     *
     * @param a      待排序数组
     * @param start  开始位置
     * @param middle 中间位置
     * @param end    结束位置
     */
    private void doBaseSort(Comparable[] a, int start, int middle, int end) {

        //只有一个元素直接返回
        if (end - start == 0) {
            return;
        }
        /*
         * 会出现这两种情况 （1,1,2）和（1,2,2）都是可以的要分别讨论（也可以在有三个元素时，指定一种形式在，这样就不用再merge里也考虑这种情况了）
         * 但是无论如何doSort()中，一定有 start <= middle <= end的关系，
         * 即将两个元素拆分为两个独立的元素时（1,1,1）和（2,2,2）
         *
         * 所以要对两个元素的组合是（1,1,2）还是（1,2,2）进行判断
         * 1.如果是（1,1,2） 则将leftEnd设为 start
         * 2.如果是（1,2,2） 则将leftEnd设为 middle - 1
         */
        int leftStart = start;

        //关键部分
        int leftEnd = middle - 1;
        if (start == middle) {
            leftEnd = start;
        }

        int rightStart = leftEnd + 1;
        int rightEnd = end;
        //1.先递归
        doBaseSort(a, leftStart, leftStart + (leftEnd - leftStart) / 2, leftEnd);
        doBaseSort(a, rightStart, rightStart + (rightEnd - rightStart) / 2, rightEnd);
        //2.再合并
        //注意merge，左右两边的子数组一定是有序的即[start,middle) 和 [middle,end]
        merge(a, start, middle, end);

    }

    /**
     * 改进：小数组使用插入排序
     *
     * @param a      待排序小数组
     * @param start  start
     * @param middle middle
     * @param end    end
     * @see InsertSorter#sort(Comparable[], int, int)
     */
    private void doInsertSort(Comparable[] a, int start, int middle, int end) {
        if (end - start <= threshold) {
            InsertSorter insertSorter = new InsertSorter();
            insertSorter.sort(a, start, end);
            return;
        }
        int leftStart = start;


        int leftEnd = middle - 1;
        if (start == middle) {
            leftEnd = start;
        }

        int rightStart = leftEnd + 1;
        int rightEnd = end;
        doInsertSort(a, leftStart, leftStart + (leftEnd - leftStart) / 2, leftEnd);
        doInsertSort(a, rightStart, rightStart + (rightEnd - rightStart) / 2, rightEnd);

        merge(a, start, middle, end);

    }

    /**
     * tip1.只有一个元素，直接返回 ---不用判断，doSort里已经保证出现一个元素直接返回 <br>
     * tip2.左右都没有可用的元素直接返回 ---不用进行判断，因为循环已经保证了总元素是满足条件的 <br>
     * <p>
     * 子数组有两个及以上元素
     * 前提两个元素的形式
     * (1,1,2)需对应 [start,middle],(middle,end]
     * (1,2,2)需对应 [start,middle),[middle,end]
     * 只有这样才会能把这两个元素分到左右两个数组，否则就会出现（）、1,2）--（1,2）、（）
     * <p>
     * 1.左边一半已经插完（取右边元素）
     * 2.右边一半已经插完（取左边元素）
     * 3右边当前元素小于左边当前元素，则将右边当前元素放入指定index位置，然后将右边的当前索引加1，并将index+1
     * 4右边的当前元素大于等于左边的当前元素，则将左边的当前元素放入指定的index位置，然后将左边的当前索引加1，并将index+1
     *
     * @param a      待合并数组
     * @param left   左边起始位置
     * @param middle 中间位置
     * @param right  右边结束位置
     */
    @SuppressWarnings("unchecked")
    private void merge(Comparable[] a, int left, int middle, int right) {
        /*
        * 浅赋值，引用的数组，不需要deep copy 基本类型除外
        */
        for (int i = left; i <= right; i++) {
            aux[i] = a[i];
        }

        int leftIndex = left;//左边子数组范围 [left,middle)
        int rightIndex = middle;//右边的子数组范围[middle,right] (1,2,2)
        if (left == middle) {
            rightIndex = middle + 1;//右边的子数组的范围[middle+1,right] (1,1,2)
        }
        for (int index = left; index <= right; index++) {//这里保证了左右都用尽就会结束循环

            /*tip2.左右都用尽，直接返回
            if (leftIndex >= middle && rightIndex > right) {
                return;
            }
            */
            //1.左边已经用尽，右边还有，取右边
            if (leftIndex >= middle && rightIndex <= right) {
                a[index] = aux[rightIndex];
                rightIndex++;
            }
            //2.右边用尽，左边还有,取左边
            if (rightIndex > right && leftIndex < middle) {
                a[index] = aux[leftIndex];
                leftIndex++;
            }
            //左右两边都没有用尽
            if (leftIndex < middle && rightIndex <= right) {
                //3.左边小于等于右边，取左边
                if (aux[leftIndex].compareTo(aux[rightIndex]) <= 0) {
                    a[index] = aux[leftIndex];
                    leftIndex++;
                } else { //4. 左边大于右边取右边
                    a[index] = aux[rightIndex];
                    rightIndex++;
                }
                compareCount++;
            }

        }

    }

    public int getThreshold() {
        return threshold;
    }

    public TopMergeSorter setThreshold(int threshold) {
        this.threshold = threshold;
        return this;
    }

    public boolean isUseInsertSort() {
        return useInsertSort;
    }

    public TopMergeSorter setUseInsertSort(boolean useInsertSort) {
        this.useInsertSort = useInsertSort;
        return this;
    }

    private void allocateAuxSpace(Comparable[] a) {
        aux = new Comparable[a.length];
    }


    public void summary() {
        println("比较次数：" + compareCount + " 交换次数：因为采用辅助数组进行归并，所以交换的次数不能在作为性能衡量的标准");
    }

}
