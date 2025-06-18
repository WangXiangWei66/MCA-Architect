//
//public class String_equals {
//
//    //object:参数为任意对象，用于与当前字符串比较
//    public boolean equals(Object anObject) {
//        //先判断两个对象的引用是否相同
//        if (this == anObject) {
//            return true;
//        }
//        //下面是类型检查与强制转换
//        //String类内部使用char[] value存储字符序列
//        if (anObject instanceof String) {
//            String anotherString = (String) anObject;
////            int n = value.length;
////            if (n == anotherString.value.length) {
////                //将当前字符串和目标哦字符串的字符数组分别复制到v1和v2
////                char[] v1 = value;
////                char[] v2 = anotherString.value;
////                int i = 0;
////                while (n-- != 0) {
////                    if (v1[i] != v2[i])
////                        return false;
////                    i++;
//                }
//                return true;
//            }
//        }
////        return false;
//    }
//}
//