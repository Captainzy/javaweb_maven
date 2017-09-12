/**
 * @author zouyang
 * @date 2017/9/12 22:11
 * @description 这是一个测试专用类
 */
public class Test {
    public static void main(String[] args) {
        String a = new String("123");
        String b = a;
        b = new String("456");
        System.out.println(a);
        System.out.println(b);
    }
}
