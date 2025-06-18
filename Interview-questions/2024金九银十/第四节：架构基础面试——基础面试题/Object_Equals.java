public class Object_Equals {

    //重写了Object类的equals方法
    public boolean equals(Object obj) {
        return (this == obj);
    }

    public static void main(String[] args) {
        String a = new String("ab");//a为一个引用
        String b = new String("ab");//b为另一个引用，队形的内容一样
        String aa = "ab";//放在常量池钟
        String bb = "ab";//从常量池中查找
        System.out.println(aa == bb);
        System.out.println(a == b);
        System.out.println(a.equals(b));
        System.out.println(42 == 42.0);
    }
}