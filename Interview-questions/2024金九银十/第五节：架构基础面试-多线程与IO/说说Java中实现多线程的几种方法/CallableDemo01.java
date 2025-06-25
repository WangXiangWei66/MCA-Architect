package 说说Java中实现多线程的几种方法;

import java.util.concurrent.Callable;  //带返回值的任务接口
import java.util.concurrent.FutureTask;//可管理Callable/Runnable的异步任务

public class CallableDemo01 {

    /*
    创建线程的第三种实现方式：
      Callable方式
     */

    public static void main(String[] args) throws Exception {
        //创建一个Callable实例
        Callable<Integer> callable = new MyCallable();
        FutureTask<Integer> futureTask = new FutureTask<>(callable);
        //获取一个线程 肯定是要先创建一个Thread对象  futureTask本质上是Runnable接口的实现
        Thread t1 = new Thread(futureTask);
        System.out.println("main方法start....");
        t1.start();//本质还是执行Runnable中的run方法，只是Run方法调用了call方法罢了
        //获取返回的结果
        System.out.println(futureTask.get());//获取开启线程执行完成后返回的结果
        System.out.println("main方法end...");
    }
}

/*
创建Callable的实现类
  我们需要指定Callable的泛型，这个泛型是返回结果的类型
 */

class MyCallable implements Callable<Integer> {
    /*
    线程自动会执行的方法
    @return
    @throws Exception
     */

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            sum += i;
        }
        return sum;
    }
}
