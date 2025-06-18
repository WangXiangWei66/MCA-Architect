public class CloseMultipleResources {

    //多资源自动管理
    public static void main(String[] args) {
        //程序会先关闭other 再关闭some
        try (ResourceSome some = new ResourceSome();
             ResourceOther other = new ResourceOther()) {
            some.doSome();
            other.doOther();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

class ResourceSome implements AutoCloseable {
    void doSome() {
        System.out.println("do Something");
    }

    @Override
    public void close() throws Exception {
        System.out.println("some resource is closed");
    }
}

class ResourceOther implements AutoCloseable {
    void doOther() {
        System.out.println("do other things");
    }

    @Override
    public void close() throws Exception {
        System.out.println("other resource is closed");
    }
}
