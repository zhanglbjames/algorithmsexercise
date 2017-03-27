# 编程实践笔记


## 算法复杂度
> - O(N*N)
>> 冒泡排序、选择排序、插入排序
> - O(N*log(N))
>> 归并排序、快速排序、希尔排序、堆排序

## 可见性的问题
1. 接口的方法默认是public，
抽象实现类不能将其方法的可见性降低(降低为private或者protected)
2. 父类的方法为protected，子类中重写这个方法时不能将其方法的可
见性设置为private，但是可以设置为public
> 所以访问性是允许在子类或者实现类进行提升的，但是不允许降低，
最高为public（interface），最低为private

## 继承或者实现中的泛型参数
见抽象类的实现AbstractXXX

## 泛型数组
```
List<String>[] lsa = new List<String>[10]; // Not really allowed.
List<String>[] lsa = new List<?>[10];//ok
List<String>[] lsa = new List[10];//ok
```

## 接口的方法static
> 1. jdk1.8之前：接口的所有方法都是abstract，对于abstract方法不能定义为static
即，接口所有方法不能为static
> 2. jdk1.8 可以允许接口中出现static方法，但是static 方法不能使用泛型接口的泛
型的类型参数，也不能访问非static修饰的方法；可以访问接口内的public static final类型数据
> 3. static方法必须实现方法体
> 4. jdk1.8 接口中可以实现Default关键字，可以实现默认方法，但是必须实现方法体。
可以访问接口所有方法

## 泛型接口+泛型抽象类
> 1. 在泛型抽象实现类如果方法是对接口的实现
