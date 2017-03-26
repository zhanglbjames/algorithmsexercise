package zss.sort;

/**
 * Created by zhanglbjames@163.com
 * on 2017/3/21.
 */
public class SortableObj implements Comparable<SortableObj>,Representable{

    /**
     * SortableObj
     *
     * Description:可排序对象
     */
    private int key;
    private String value;

    public int getKey() {
        return key;
    }

    public SortableObj(){}

    public SortableObj(int key, String value){
        this.key = key;
        this.value = value;
    }
    public int compareTo(SortableObj o) {
        if(key > o.getKey()){
            return 1;
        }else if(key < o.getKey()){
            return -1;
        }
        return 0;
    }
    public String getRepresentation(){
        return "Key:"+Integer.toString(key)+" value:"+value;
    }

}
