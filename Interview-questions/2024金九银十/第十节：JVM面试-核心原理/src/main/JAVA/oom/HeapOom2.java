package oom;
//本代码通过持续向链表中添加对象，使对象无法被垃圾回收，最终耗尽堆内存，导致内存溢出
//代码场景还适合分析JVM的分代收集机制（新生代、老年代的GC行为）
import java.util.LinkedList; //存储持续创建的对象
import java.util.List;

/**
 * VM Args：-Xms30m -Xmx30m     堆的大小30M
 * 造成一个堆内存溢出(分析下JVM的分代收集)
 * GC调优---生产服务器推荐开启(默认是关闭的)
 * -XX:+HeapDumpOnOutOfMemoryError  ：发生 OOM 时自动生成堆转储文件（.hprof），用于事后分析内存使用情况。
 */
//GC无法回收被链表引用的对象
public class HeapOom2 {

    public static void main(String[] args) throws Exception{
        List<Object> list = new LinkedList<>();
        int i = 0;
        while(true) {
            i++;
            if(i%1000 == 0)  //每创建1000个对象，休眠十秒——放慢速度，便于观察GC
                Thread.sleep(10);
            list.add(new Object());//不能回收
        }
    }
}
