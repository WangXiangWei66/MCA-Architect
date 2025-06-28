package oom; //测试内存溢出

import java.nio.ByteBuffer; //不受JVM堆内存限制，直接分配内存
//本代码演示了如何通过ByteBuffer.allocateDirect()分配直接内存，并通过JVM参数限制最大直接内存大小，从而出发OOM
public class DirectOom {

    /**
     * VM Args：-XX:MaxDirectMemorySize=100m  ：通过-XX:MaxDirectMemorySize=100m限制 JVM 可使用的最大直接内存为 100MB。若未显式设置，默认值为堆内存最大值（-Xmx）。
     * 限制最大直接内存100m
     */

    public static void main(String[] args) {
        //直接分配128M的直接内存
        ByteBuffer bb = ByteBuffer.allocateDirect(128 * 1024 * 1024);
    }
}
