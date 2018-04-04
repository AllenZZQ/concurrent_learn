package java_concurrency_in_practice.chapter03;

public class Test01 {
    /**
     * volatile保证此变量不会被缓存在线程栈（寄存器）
     * 对test01加上volatile，只能保证这个引用变量变化时（指向其他对象）是可见的，
     * 其属性并不是volatile
     * volatile非阻塞，相比synchronized轻量级些
     */
    private volatile int i = 1;
    private volatile Test01 test01 = new Test01();
    private volatile boolean flag = false;

    public static void main(String[] args) {
        Test01 test01 = new Test01();
        test01.test01();
        test01.test02();
    }

    /**
     * 使用volatile的典型场景，多线程时，使得flag的变化立即可见
     */
    private void test02() {
        while (flag) {

        }
    }

    /**
     * 多线程时此操作不安全
     * volatile保证线程对变量的更新立即写会共享内存，线程在使用变量时都是从共享内存拿的
     * 因此，线程在拿到i时，此时的值是正确的，但随后的更新操作时，可能i已经变化了，因此并不安全
     */
    private void test01() {
        i ++;
    }
}
