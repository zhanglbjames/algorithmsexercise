package zss.sort;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/7/9.
 */

import java.util.ArrayList;
import java.util.Comparator;

import static zss.util.Print.println;

/**
 * <p>Description</p>
 * 堆排序
 */
public class HeapSorter extends AbstractSorter{

    private int compareCount = 0;
    private int exchangeCount= 0;
    private Comparable[] heap;
    private int elementCount = 0;
    private int maxLength = 16;

    public HeapSorter(int maxLength) {
        this.maxLength = maxLength;
    }
    public HeapSorter(Comparable[] a, int maxLength) {
        this(maxLength);
        if (a.length > maxLength){
            throw new IllegalArgumentException("maxLength is less the length of arrays a");
        }
        initLazy();
        for (int i = 0; i < a.length; i++) {
            elementCount ++;
            heap[i +1] = a[i];
        }
        constructHeap();
    }
    // 新添加的元素放在数组末尾，然后对最后一个元素上浮
    public void add(Comparable element){
        if (heap == null){
            initLazy();
        }
        if (elementCount < maxLength){
            heap[++elementCount] = element;
            swim(elementCount);
        }
        else throw new IndexOutOfBoundsException("element is equals maxLength, can not add any element");
    }
    // 获取堆顶最大的元素
    public Comparable getMax(){

        // 保证heap为空返回null
        if (elementCount <= 0 || heap ==null){
            return null;
        }
        // 先将数组第一个元素和最后一个元素互换
        exchange(1,elementCount);

        // 再将堆顶元素下沉，忽略最后一个元素
        sink(1,elementCount-1);

        // 返回并删除最后一个元素
        Comparable max = heap[elementCount];
        heap[elementCount] = null;
        elementCount --;

        return max;
    }
    // 延迟初始化
    private void initLazy(){
        if (heap == null && maxLength > 0){
            this.heap = new Comparable[maxLength +1];
        }
    }
    // 用下沉+一半 快速构建heap
    private void constructHeap() {
        for (int i = elementCount/2; i >=1 ; i--) {
            sink(i,elementCount);
        }
    }
    // 添加新元素时使用上浮
    private void swim(int index){
        int parent = 0;
        while ((parent = index/2) >= 1){
            // 子节点大于父节点则交换
            if (more(index,parent)){
                exchange(index,parent);
                // 更新下标
                index = parent;
            }
            // 小于父节点则停止上浮
            else break;
        }
    }
    // 用于删除最大元素或者构建堆时使用下沉
    private void sink(int index, int range) {
        int leftChild = 0;

        while ((leftChild = index * 2) <= range) {
            // 交换下标，默认为left
            int exchangeIndex= leftChild;

            //1. 找出左右节点哪个大
            // 先判断是否有右节点（不用判断是否有左节点，一定有左节点，
            // 这是因为在构建和添加时都是先左后右，在数组的最后添加）
            // 堆是完全二叉树，只有最后一个节点的父节点才会出现单个子节点
            if (leftChild < range){ // 小于说明一定也有右子树
                compareCount ++;
                // 如果right不小于left，则将exchangeIndex设置为right
                if (!more(leftChild,leftChild+1)){
                    exchangeIndex = leftChild +1;
                }
            }
            //2.子节点大于父节点则交换，并更新下标
            compareCount ++;
            if (more(exchangeIndex,index)){
                exchange(exchangeIndex,index);
                // 更新下标
                index = exchangeIndex;
                exchangeCount ++;
            }
            // 否则结束下沉
            else break;
        }

    }
    private void exchange(int index1, int index2) {
        Comparable obj = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = obj;

        exchangeCount++;
    }

    @SuppressWarnings("unchecked")
    private boolean more( int index1, int index2) {

        compareCount++;

        if (heap[index1].compareTo(heap[index2]) > 0) return true;

        else return false;
    }

    @Override
    public void sort(Comparable[] a) {

    }
    public SortableObj[] sort() {
        SortableObj a = null;
        ArrayList<SortableObj> result = new ArrayList<>();
        while ((a = (SortableObj) getMax()) != null) {
            result.add(a);
        }
        return result.toArray(new SortableObj[0]);
    }

    @Override
    public void summary() {
        println("比较次数：" + compareCount + " 交换次数：" + exchangeCount);

    }

    public static void main(String[] args) {
//        HeapSorter sorter = new HeapSorter(20);
//        SortableObj[] objArr = ObjArrFactory.generate(10);
//        for (SortableObj obj : objArr){
//            sorter.add(obj);
//        }
//        sorter.show(objArr);
//        SortableObj[] result1 = sorter.sort();
//        sorter.show(result1);
        SortableObj[] objArr = ObjArrFactory.generate(10);
        HeapSorter sorter = new HeapSorter(objArr,20);
        sorter.show(objArr);
        SortableObj[] result1 = sorter.sort();
        sorter.show(result1);

    }
}
