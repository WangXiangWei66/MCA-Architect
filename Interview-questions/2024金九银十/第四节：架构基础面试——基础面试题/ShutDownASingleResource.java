//展示了自动管理资源机制的使用，确保实现了AutoCloseable接口的资源在使用后被正确关闭
public class ShutDownASingleResource {
    //主类定义和入口方法
    public static void main(String[] args) {
        //try-with-resources：Java 7 引入的语法糖，用于自动关闭实现了AutoCloseable接口的资源。
        //无论try块正常结束，还是抛出异常，资源的closed方法都会自动被调用
        try (Resource res = new Resource()) {//在try括号中声明并初始化资源
            res.doSome();//调用doSome方法，try块结束后，自动调用res.close()
        } catch (Exception ex) {
            ex.printStackTrace();//如果上面调用的两种方法抛出异常，会被捕捉到并打印堆栈信息
        }
    }
}
//这个接口实现了Close方法
class Resource implements AutoCloseable {

    public Resource() {
    }

    void doSome() {
        System.out.println("do something");//无返回值，执行业务的核心逻辑
    }

    @Override//重写父接口方法
    public void close() throws Exception {
        System.out.println("resource is closed");//用于释放资源
    }
}

