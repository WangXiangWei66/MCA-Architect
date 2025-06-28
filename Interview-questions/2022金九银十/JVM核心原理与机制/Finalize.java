public class Finalize {
//展示了对象如何在垃圾回收前自我拯救一次，但无法实现第二次拯救
    private static Finalize save_hook = null; //类变量

    public void isAlive() {
        System.out.println("我还活着");
    }

    @Override
    public void finalize() {
        System.out.println("finalize方法被执行");
        Finalize.save_hook = this;//自我拯救机制
    }

    public static void main(String[] args) throws Exception {
        save_hook = new Finalize();//对象实例创建
        save_hook = null; //断开引用，对象变为不可达
        System.gc();
        //暂停0.5秒等待他
        Thread.sleep(500);
        if (save_hook != null) {
            save_hook.isAlive();
        } else {
            System.out.println("好了，我现在死了");
        }

        //对象第二次拯救自己
        save_hook = null;
        System.gc();
        //暂停0.5秒等待他
        Thread.sleep(500);
        if (save_hook != null) {
            save_hook.isAlive();
        } else {
            System.out.println("我终于死亡了");
        }
    }
}
