package 如何停止一个正在运行的线程;
//使用中断机制安全地种植一个正在休眠地线程
public class FunDemo08 extends Thread {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new FunDemo08();
        t1.start();
        Thread.sleep(3000);
        t1.interrupt();//中断线程，将中断标志由false修改为true
        //t1.stop()直接就把线程kill掉了
        System.out.println("main.....");
    }

    @Override
    public void run() {
        System.out.println(this.getName() + "start...");
        int i = 0;
        //Thread.interrupted()如果没有被中断，那么是false如果显示的执行了interrupt方法就会修改为true
        while (!Thread.interrupted()) { //检查中断标志
            //while(!Thread.currentThread().isInterrupted(){
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
            System.out.println(this.getName() + " " + i);
            i++;
        }

        System.out.println(this.getName() + "end...");
    }
}
