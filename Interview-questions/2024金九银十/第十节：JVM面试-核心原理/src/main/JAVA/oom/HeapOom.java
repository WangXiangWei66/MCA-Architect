package oom;
//本代码演示了创建超大数组导致内存溢出
/**
 * VM Args：-Xms30m：初始堆内存大小为 30MB。
 * -Xmx30m ：最大堆内存大小为 30MB（与初始值相同，避免动态扩容）。
 * -XX:+PrintGCDetails ：打印详细的垃圾回收信息
 * 堆内存溢出（直接溢出）
 */
public class HeapOom {

    public static void main(String[] args) {
        String[] strings = new String[35 * 1024 * 1024]; //35M的 数组——堆
    }
}
