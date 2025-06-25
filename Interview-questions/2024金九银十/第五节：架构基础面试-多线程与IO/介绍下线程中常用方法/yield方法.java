package 介绍下线程中常用方法;

//礼让
public class yield方法 extends Thread {
    public yield方法(String threadName) {
        super(threadName);//设置线程的名称
    }

    public static void main(String[] args) {
        //创建并启动三个线程
        yield方法 f1 = new yield方法("A1");
        yield方法 f2 = new yield方法("A2");
        yield方法 f3 = new yield方法("A3");

        f1.start();
        f2.start();
        f3.start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (i % 10 == 0 && i != 0) {
                //如果i是10的倍数，线程开始礼让
                System.out.println(Thread.currentThread().getName() + "礼让：" + i);
                Thread.currentThread().yield();//让出CPU
            } else {
                System.out.println(this.getName() + ":" + i);
            }
        }
    }
}
