package zss.sort;
/**
 * Created by zhanglbjames@163.com
 * on 2017/3/21.
 */

import org.junit.*;

import static junit.framework.Assert.*;
import static zss.util.Print.println;

public class SortTest {

    private int num = 100;

    @Test
    public void selectSorterTest() {

        println("-------------------------SelectSorter Test--------------------------------");
        println("compare N*N/2  exchange N");
        SortableObj[] arrSelect = ObjArrFactory.generate(num);
        println("length: " + arrSelect.length);
        SelectSorter selectSorter = new SelectSorter();
        println("before sorted");
        selectSorter.show(arrSelect);
        selectSorter.sort(arrSelect);
        assertEquals(true, selectSorter.testSorted(arrSelect));
        println("after sorted");
        selectSorter.show(arrSelect);
        selectSorter.summary();
    }

    @Test
    public void insertSorterTest() {
        println("-------------------------InsertSorter Test--------------------------------");
        println("compare N*N/4  exchange N*N/4");
        SortableObj[] arrInsert = ObjArrFactory.generate(num);
        println("length: " + arrInsert.length);
        InsertSorter insertSorter = new InsertSorter();
        println("before sorted");
        insertSorter.show(arrInsert);
        insertSorter.sort(arrInsert);
        assertEquals(true, insertSorter.testSorted(arrInsert));
        println("after sorted");
        insertSorter.show(arrInsert);
        insertSorter.summary();
    }

    @Test
    public void shellSorterTest() {
        println("-------------------------ShellSorter Test--------------------------------");
        println("compare worst case N(exp1.5)");
        SortableObj[] arrShell = ObjArrFactory.generate(num);
        println("length: " + arrShell.length);
        ShellSorter shellSorter = new ShellSorter();
        //shellSorter.setExp(5);
        println("before sorted");
        shellSorter.show(arrShell);
        shellSorter.sort(arrShell);
        assertEquals(true, shellSorter.testSorted(arrShell));
        println("after sorted");
        shellSorter.show(arrShell);
        shellSorter.summary();
    }

    @Test
    public void topMergeSorterTest() {
        println("-------------------------TopMergeSorter Test--------------------------------");
        println("compare 1/2NlgN ~ NlgN   lg100 = 6");
        SortableObj[] arrTopMerge = ObjArrFactory.generate(num);
        println("length: " + arrTopMerge.length);
        TopMergeSorter topMergeSorter = new TopMergeSorter();
        //shellSorter.setExp(5);
        println("before sorted");
        topMergeSorter.show(arrTopMerge);
        topMergeSorter.sort(arrTopMerge);
        assertEquals(true, topMergeSorter.testSorted(arrTopMerge));
        println("after sorted");
        topMergeSorter.show(arrTopMerge);
        topMergeSorter.summary();
    }

    @Test
    public void topMergeSorterPlusTest() {
        println("-------------------------TopMergeSorterPlus Test--------------------------------");
        println("compare 1/2NlgN ~ NlgN   lg100 = 6");
        SortableObj[] arrTopMergeplus = ObjArrFactory.generate(num);
        println("length: " + arrTopMergeplus.length);
        TopMergeSorter topMergeSorter = new TopMergeSorter();
        //shellSorter.setExp(5);
        println("before sorted");
        topMergeSorter.show(arrTopMergeplus);
        topMergeSorter.setUseInsertSort(true).setThreshold(45).sort(arrTopMergeplus);
        assertEquals(true, topMergeSorter.testSorted(arrTopMergeplus));
        println("after sorted");
        topMergeSorter.show(arrTopMergeplus);
        topMergeSorter.summary();
    }

    @Test
    public void BottomMergeSorterTest() {
        println("-------------------------BottomMergeSorter Test--------------------------------");
        println("compare 1/2NlgN ~ NlgN   lg100 = 6");
        SortableObj[] arrBottomMerge = ObjArrFactory.generate(num);
        println("length: " + arrBottomMerge.length);
        BottomMergeSorter bottomMergeSorter = new BottomMergeSorter();
        //shellSorter.setExp(5);
        println("before sorted");
        bottomMergeSorter.show(arrBottomMerge);
        bottomMergeSorter.sort(arrBottomMerge);
        assertEquals(true, bottomMergeSorter.testSorted(arrBottomMerge));
        println("after sorted");
        bottomMergeSorter.show(arrBottomMerge);
        bottomMergeSorter.summary();
    }

//    @Test
//    public void QuickSorterTest() {
//        println("-------------------------QuickSorter Test--------------------------------");
//        println("时间复杂度 O(N*log(N))    N = 100");
//        SortableObj[] arrQuickSorter = ObjArrFactory.generate(num);
//        println("length: " + arrQuickSorter.length);
//        QuickSorter quickSorter = new QuickSorter();
//        //shellSorter.setExp(5);
//        println("before sorted");
//        quickSorter.show(arrQuickSorter);
//        quickSorter.sort(arrQuickSorter);
//        assertEquals(true, quickSorter.testSorted(arrQuickSorter));
//        println("after sorted");
//        quickSorter.show(arrQuickSorter);
//        quickSorter.summary();
//    }
//
}
