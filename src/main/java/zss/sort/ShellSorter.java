package zss.sort;

/**
 * Created by zhanglbjames@163.com
 * on 2017/3/22.
 */
import static zss.util.Print.println;

/**
 * <p> ShellSorter </p>
 *
 * Description希尔排序 <br>
 *
 * 1.选取递增间隔序列 <br>
 * 2.从最大不大于数组长度的间隔space开始<br>
 * 3.对于space有序数组<br>
 * 4.对于space个子数组进行插入排序<br>
 * 5.将space减少（space = space/3）继续 3 ；如果space 小于 1则停止 <br>
 *
 */
public class ShellSorter extends AbstractSorter {


    private int compareCount = 0;
    private int exchangeCount = 0;
    private int spaceCount = 0;

    //间隔的幂参数
    private int exp = 3;
    public void setExp(int exp) {
        this.exp = exp;
    }

    public void sort(Comparable[] a) {
        int space = getInitSpace(a);
        //有个space有序的数组就是一个由space个有序数组组成的数组
        while(space >= 1){
            for (int i = 0; i < space; i++) {//每一个space间隔的数组
                for (int j = i+space; j <a.length ; j += space) {//开始是对子数组的第二个元素和第一个元素进行比较，所以下标从1开始
                    for (int k = j; k >= space; k -= space) {//两两比较，最后一组是左边第二（index）与左边第一进行比较
                        if(less(a,k,space)){
                            exchange(a,k,space);
                        }else{
                            break;
                        }
                    }
                }

            }
            spaceCount ++;
            space = space / exp;//1, 4, 13... --> 0, 1, 4...
        }


    }

    private void exchange(Comparable[] a,int index,int space){
        Comparable tmp = a[index];
        a[index] = a[index-space];
        a[index-space] = tmp;

        exchangeCount++;
    }
    private boolean less(Comparable[] a,int index,int space){
        compareCount++;

        if (a[index].compareTo(a[index - space]) < 0) {
            return true;
        }
        return false;
    }

    private int getInitSpace(Comparable[] a){
        /**
         * N 为待排序数组的长度
         *
         * 找到一个允许的最大间隔
         * */
        int N = a.length;
        int iSpace = 1;
        while(iSpace < N/exp) iSpace = iSpace*exp +1;//1,4,13,40,121,364,1093....
        return iSpace;
    }


    public void summary() {
        println("比较次数：" + compareCount + " 交换次数：" + exchangeCount+" 间隔序列的数量："+spaceCount);
    }

}
