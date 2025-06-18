public class Hash {

    static final int hash(Object key) {
        int h;
        //异或是为了让高位数据参与哈希运算，减少哈希冲突的发生
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}
