package zss.util;
/**
 * Created by zhanglbjames@163.com
 * on 2017/3/21.
 */
import zss.sort.Representable;

/**
 * Print
 *
 * Description:用于打印排序结果到控制台
 */
public class Print{

    public static void println(String s){
        System.out.println(s);
    }

    /**
     * 系统打印对象的封装
     *
     * @param a StingBuilder
     */
    private static void sPrintln(StringBuilder a){

        System.out.println(a);
    }

    /**
     * 组合排序结果字符表示
     *
     * @param a 待表示数组
     * @return java.lang.StringBuilder
     */
    private static StringBuilder present(Representable[] a){

        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < a.length; i++) {
            sb.append(a[i].getRepresentation());
            if(i != a.length){
                sb.append(",");
            }
        }
        return sb;
    }

    /**
     * 开放的打印接口
     *
     * @param a 待输出的排序的对象
     */
    public static  void printArr(Representable[] a){

        sPrintln(present(a));
    }
}
