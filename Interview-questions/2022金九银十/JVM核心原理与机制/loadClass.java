//类加载是java类加载器中的核心方法实现了双亲委派模型（Parent Delegation Model）。
// 该方法负责加载指定名称的类，遵循 "先委派、后加载" 的原则，确保类的唯一性和安全性。
public class loadClass {
    //使用类加载锁（针对类名的锁）保证线程安全，防止多个线程同时加载同一个类。
    //name:类的权限名
    //resolve：是否在加载后解析类（执行链接阶段）
    protected Class<?> loadClass(String name, boolean resolve)
            throws ClassNotFoundException
    {
        synchronized (getClassLoadingLock(name)) {


            // First，在虚拟机内存中查找是否已经加载过此类...类缓存的主要问题所在！！！
            //调用findLoadedClass检查当前类加载器是否已加载该类。若已加载（缓存命中），直接返回缓存的 Class 对象。
            Class<?> c = findLoadedClass(name);

            if (c == null) {
                long t0 = System.nanoTime();
                try {
                    if (parent != null) {
                        //先让上一层加载器进行加载
                        c = parent.loadClass(name, false);//父加载器存在，直接调用父加载器的loadClass方法
                    } else {
                        c = findBootstrapClassOrNull(name); //否则尝试从引导类路径加载
                    }
                } catch (ClassNotFoundException e) {
                    // ClassNotFoundException thrown if class not found
                    // from the non-null parent class loader
                }

                if (c == null) {
                    // If still not found, then invoke findClass in order
                    // to find the class.
                    long t1 = System.nanoTime();
                    //调用此类加载器所实现的findClass方法进行加载
                    c = findClass(name);
                    //记录委派耗时、查找耗时和查找次数
                    // this is the defining class loader; record the stats
                    sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
                    sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                    sun.misc.PerfCounter.getFindClasses().increment();
                }
            }
            if (resolve) {
                //resolveClass方法是当字节码加载到内存后进行链接操作，对文件格式和字节码验证，并为 static 字段分配空间并初始化，符号引用转为直接引用，访问控制，方法覆盖等
                resolveClass(c);
            }
            return c;
        }
    }
}

/**
 * 双亲委派模型工作流程
 * 检查缓存：优先从当前加载器的缓存中查找
 * 向上委派：递归委派给父加载器，直到启动类加载器
 * 逐级尝试加载：
 * 启动类加载器尝试加载
 * 扩展类加载器尝试加载
 * 应用类加载器尝试加载
 * 自定义类加载器尝试加载
 * 解析类（可选）：加载后进行链接操作
 */
