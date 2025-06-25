package 介绍下线程中常用方法;
//isAlive方法的使用和线程生命周期中的状态变化
public class isAlive {

    public static void main(String[] args) {
        System.out.println("main start...");
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "....");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        //这里线程并没有启动
        System.out.println("线程的状态：" + t1.isAlive());
        t1.start();
        System.out.println("线程的状态：" + t1.isAlive());
        System.out.println("main end...");
    }
}
