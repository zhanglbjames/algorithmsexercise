package zss.sort;
/**
 * Created by zhanglbjames@163.com
 * on 2017/3/21.
 */
import java.util.ArrayList;
import java.util.Random;


public class ObjArrFactory {

    private static Random random = new Random();

    /**
     * 随机生成待排序对象数组
     *
     * @return java.util.ArrayList<zss.sort.SortableObj>
     */
    public static SortableObj[] generate(int num) {

        ArrayList<SortableObj> arr = new ArrayList<SortableObj>();

        for (int i = 0; i < num; i++) {
            int key = random.nextInt(80) + 33;
            arr.add(new SortableObj(key, new String(Character.toChars(key))));
        }
        //泛型类型参数 toArray的方法
        return arr.toArray(new SortableObj[arr.size()]);
    }
}

