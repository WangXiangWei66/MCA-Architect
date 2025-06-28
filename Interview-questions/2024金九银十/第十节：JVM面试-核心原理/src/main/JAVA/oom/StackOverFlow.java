package oom;
//无限递归调用导致JAVA虚拟机溢出
/**
 * 栈溢出  -Xss=1m
 */
//栈溢出原理：每个 Java 方法调用会创建一个栈帧（Stack Frame），存储局部变量、操作数栈等信息。当递归深度过大时，栈空间耗尽，触发StackOverflowError。
public class StackOverFlow {

    static int i = 0; //记录递归效用 次数
    long ll = 9;//实例变量（存储在堆中，不影响栈深度）

    //死递归写法
    //每个栈帧约占24字节
    public void stack() {//方法不断执行-栈帧不断入栈(不出栈)
        long j = 200; //局部变量，存储在栈帧中
        long k = 900;
        long jk = j + k;
        i++;
        stack();//方法一直会执行这一段
    }

    public static void main(String[] args) throws Throwable {
        try {
            StackOverFlow javaStack = new StackOverFlow();
            javaStack.stack();
        } catch (Throwable throwable) {
            System.out.println("stack方法执行的次数：" + i);
            throw throwable;
        }
    }
}
