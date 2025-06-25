package 如何停止一个正在运行的线程;
//使用中断机制安全地终止线程
public class FunDemo07 extends Thread {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new FunDemo07();
        t1.start();
        Thread.sleep(3000);
        t1.interrupt();//中断线程，将中断标志由false修改为true
        //t1.stop()直接就把线程给kill掉了
        System.out.println("main...");
    }

    @Override
    public void run() {
        System.out.println(this.getName() + "start...");
        int i = 0;
        //Thread.interrupted()如果没有被中断，那么是false如果显示的执行了interrupt方法就会修改为true
        while (!Thread.interrupted()) { //静态方法：检查当前线程地中断标志，并清楚标志
            System.out.println(this.getName() + " " + i);
            i++;
        }
        System.out.println(this.getName() + "end....");
    }


}
