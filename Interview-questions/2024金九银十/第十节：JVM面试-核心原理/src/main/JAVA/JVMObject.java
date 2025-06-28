/**
 * VM参数
 * -Xms30m -Xmx30m  -XX:MaxMetaspaceSize=30m：限制类元数据空间为 30MB
 * -XX:+UseConcMarkSweepGC ： 使用 CMS 收集器（老年代并发标记清除）。
 * -XX:-UseCompressedOops
 */
//手动出发GC对象在新生代和老年代之间的晋升
public class JVMObject {

    public final static String MAN_TYPE = "max";//常量
    public static String WOMAN_TYPE = "woman";//静态变量

    public static void main(String[] args) throws Exception {
        Teacher T1 = new Teacher();
        T1.setName("wangxiangwei1");//入栈了
        T1.setSexType(MAN_TYPE);
        T1.setAge(36);
        for (int i = 0; i < 15; i++) {
            System.gc();//主动出发GC，垃圾回收15次----T1存活，T1进入老年代
        }
        Teacher T2 = new Teacher();
        T2.setName("wangxiangwei2");
        T2.setSexType(MAN_TYPE);
        T2.setAge(18);
        Thread.sleep(Integer.MAX_VALUE);//线程休眠，便于观察内存状态
    }
}

class Teacher {
    String name;
    String sexType;
    int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSexType() {
        return sexType;
    }

    public void setSexType(String sexType) {
        this.sexType = sexType;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
