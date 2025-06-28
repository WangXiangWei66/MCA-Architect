//hi用多种调用展示虚拟机栈的工作原理
public class MethodAndStack {
    public static void main(String[] args) {
        A();
        Object o = new Object();
    }

    public static void A() {
        B();
    }

    public static void B() {
        C();
    }

    public static void C() {
        System.out.println("C方法调用");
    }
}
