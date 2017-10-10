package ip;

/**
 * @author zouyang
 * @date 2017/10/10 11:13
 * @description TODO
 */
public class Test {
    public static void main(String[] args) {
        String addr = IPSeeker.getInstance().getAddress("174.139.11.234");
        System.out.println(addr);
    }

}
