import java.util.*;
//本代码演示了根据列表实现RandomAccess接口来选择最有的遍历方式
public class RandomAccessTest {

    //首先存储待遍历的列表，通过构造函数对他进行初始化
    private List<String> list = null;

    public RandomAccessTest(List<String> list) {
        this.list = list;
    }

    public void loop() {
        //首先判断当前列表是否支持随机快速访问
        if (list instanceof RandomAccess) {
            //支持(ArrayList)，便是用for循环，通过索引进行访问，时间复杂度O（1）
            System.out.println("采用for循环");
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
            }
        } else {
            //不支持：迭代器，如LinkedList，避免O(n)的随机访问开销
            System.out.println("采用迭代器遍历");
            Iterator it = list.iterator();
            while (it.hasNext()) {//hasNext：检查是否存在下一个元素
                System.out.println(it.next());
            }
        }
    }

    public static void main(String[] args) {
        //下面的代码用于将一组元素转化为列表
        List<String> list = Arrays.asList("123", "456", "789", "1110");
        List<String> list1 = new LinkedList<>();
        list1.add("aaa");
        list1.add("bbb");
        list1.add("ccc");
        new RandomAccessTest(list).loop();
        new RandomAccessTest(list1).loop();
    }
}
