package zss.sort;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/3/23.
 */

import static zss.util.Print.println;

/**
 * <p>Description</p>
 * 自底向上归并排序
 * <p>
 * 相邻的两两小组分别进行归并，需要使用辅助数组
 * 适用于链接的数据结构 ---链表(当使用链表时，不需要使用辅助空间，就能实现原地排序)
 */
public class BottomMergeSorter extends AbstractSorter {

    private int compareCount = 0;
    private Comparable[] aux;
    private int arrSize;

    /**
     * 子数组按照2的幂进行增加
     *
     * 如果原数组的大小为2的幂的话，那么每次都有两两大小相等数组进行合并 ，
     * 最后一次合并有 subSize*2 = a.length，merge2左数组在边界上（直接返回）
     * 然后subSize *= subSize > a.length 跳出while
     *
     * 如果不是2的幂的话，总会在最后一次合并的时候，左数组大于右数组
     * 最后一次有：subSize*2 > a.length 然后
     *
     * @param a 待排序数组
     */
    public void sort(Comparable[] a) {
        arrSize = a.length;
        aux = new Comparable[arrSize];

        int subSize = 1;
        while (subSize <= a.length) {
            for (int i = 0; i < a.length; i += 2 * subSize) {//两两一对进行递增
                merge2(a,i,subSize);//0,2,4,6  0,4,8,12 上一次不包含下一次的开始
            }
            subSize *= 2;
        }


    }

    /**
     * 两两进行合并
     * <p>
     * <p>
     * 1.左右数组都没有越界 --其实就是还没有到达最后一组，或者正好能两两一组
     * 2.左数组尾部越界 -- 直接return
     * 3.只有右数组尾部越界 --直接将右数组的结尾设置成数组的最后一个索引 size-1
     *
     * @param a          待排序数组
     * @param startIndex 左子数组 范围[startIndex,startIndex+subSize) 右子数组 范围[startIndex+subSize,startIndex+2*subSize)
     * @param subSize    两两合并的子数组的大小
     */
    @SuppressWarnings("unchecked")
    private void merge2(Comparable[] a, int startIndex, int subSize) {

        /*
         * 默认左右数组的起始结束位置设置为
         * 左右都没有越界--正好组成两个大小相同的子数组
         */
        int leftStart = startIndex;//左数组起始位置 包含
        int leftEnd = startIndex + subSize;//左数组结束位置 不包含
        int rightStart = startIndex + subSize;//右数组起始位置 包含
        int rightEnd = startIndex + subSize + subSize;//右数组结束位置 包含

        int leftIndex = leftStart;//左数组当前位置索引
        int rightIndex = rightStart;//右数组当前位置索引

        /*
         * 更新左右数组的起始结束位置
         */
        if (leftEnd >= arrSize) { //左数组越界 [leftStart,arrSize)左数组与全局数组边界完全重合，这是临界值
            return;
        } else if (rightEnd >= arrSize) {//[rightStart,arrSize)右数组与全局数组边界完全重合，这是临界值
            rightEnd = arrSize;
        }

        /*
         * 合并
         */
        for (int i = leftStart; i < rightEnd; i++) {
            aux[i] = a[i];
        }

        for (int index = leftStart; index < rightEnd; index++) {
            //1.左边已经用尽，右边还有，取右边
            if (leftIndex >= leftEnd && rightIndex < rightEnd) {
                a[index] = aux[rightIndex];
                rightIndex++;
            }
            //2.右边用尽，左边还有,取左边
            if (rightIndex >= rightEnd && leftIndex < leftEnd) {
                a[index] = aux[leftIndex];
                leftIndex++;
            }
            //左右两边都没有用尽
            if (leftIndex < leftEnd && rightIndex < rightEnd) {
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

    public void summary() {
        println("比较次数：" + compareCount + " 交换次数：因为采用辅助数组进行归并，所以交换的次数不能在作为性能衡量的标准");
    }
}
