package zss.sort;

/**
 * Created by zhanglbjames@163.com
 * on 2017/3/21.
 */
public interface Sortable {
    void sort(Comparable[] a);
    boolean testSorted(Comparable[] a);
    void summary();
    void show(Representable[] a);
}
