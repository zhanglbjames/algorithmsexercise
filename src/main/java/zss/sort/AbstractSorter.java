package zss.sort;

import static zss.util.Print.printArr;

/**
 * Created by zhanglbjames@163.com
 * on 2017/3/21.
 */
public abstract class AbstractSorter implements Sortable{
    
    abstract public void sort(Comparable[] a);

    abstract public void summary();

    public void show(Representable[] a){
        printArr(a);
    }

    /**
     * 测试原数组已经排好序
     *
     * @param a 原数组
     * @return boolean
     */
    @SuppressWarnings("unchecked")
    public boolean testSorted(Comparable[] a){

        for (int i = 1; i < a.length; i++) {
            if(a[i].compareTo(a[i-1]) < 0) {
                return false;
            }
        }
        return true;
    }

}
