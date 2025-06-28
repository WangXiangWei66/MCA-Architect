/**
 * JVM对栈帧空间的优化
 **/
public class JVMStack {
    public int work(int x) throws Exception {
        int z = (x + 5) * 10;//这行Java代码。如果要翻译成字节码话，有多行
        //1、x+5  （x=10） 15
        Thread.sleep(Integer.MAX_VALUE); //暂停线程但是不会释放栈帧
        return z;
    }

    public static void main(String[] args) throws Exception {
        JVMStack jvmStack = new JVMStack();
        jvmStack.work(10);
    }
}
