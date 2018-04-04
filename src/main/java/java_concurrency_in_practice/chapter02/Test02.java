package java_concurrency_in_practice.chapter02;

import java.util.concurrent.atomic.AtomicInteger;

public class Test02 {
    private AtomicInteger ai1 = new AtomicInteger(0);
    private AtomicInteger ai2 = new AtomicInteger(0);

    public static void main(String[] args) {
        Test02 test02 = new Test02();
        test02.test01();
    }

    /**
     * 并不是线程安全的，两个原子性操作合在一起并不具有原子性
     * 此时要考虑加锁
     */
    private void test01() {
        ai1.incrementAndGet();
        ai2.incrementAndGet();
    }

}
