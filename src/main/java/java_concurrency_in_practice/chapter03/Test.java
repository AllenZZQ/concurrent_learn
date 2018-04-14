package java_concurrency_in_practice.chapter03;

import junit.framework.TestCase;

public class Test extends TestCase {
    /**
     * volatile保证此变量不会被缓存在线程栈（寄存器）
     * 对test01加上volatile，只能保证这个引用变量变化时（指向其他对象）是可见的，
     * 其属性并不是volatile
     * volatile非阻塞，相比synchronized轻量级些
     */
    private volatile int i = 1;
    private volatile Test test = new Test();
    private volatile boolean flag = false;
    private ThreadLocal<Integer> tl = new ThreadLocal<>();

    /**
     * 多线程时此操作不安全
     * volatile保证线程对变量的更新立即写会共享内存，线程在使用变量时都是从共享内存拿的
     * 因此，线程在拿到i时，此时的值是正确的，但随后的更新操作时，可能i已经变化了，因此并不安全
     */
    public void test01() {
        i ++;
    }

    /**
     * 使用volatile的典型场景，多线程时，使得flag的变化立即可见
     */
    public void test02() {
        while (flag) {

        }
    }

    /**
     * 线程封闭
     * 大体原理，Thread内部有一个ThreadLocalMap,
     * 当调用get方法时，先拿到currentThread的ThreadLocalMap，
     * 然后以此ThreadLocal对象为key拿到此map中保存的值
     */
    public void test03() {
        tl.set(10);
        new Thread(() -> {
            System.out.println(tl.get());
        }).start();
        System.out.println(tl.get());

    }

}

