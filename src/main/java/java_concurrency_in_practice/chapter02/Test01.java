package java_concurrency_in_practice.chapter02;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Test01 {

    private AtomicInteger ai = new AtomicInteger(0);

    private AtomicReference<Test01> ar = new AtomicReference<>();

    public static void main(String[] args) {
        Test01 test01 = new Test01();
        test01.test01();
        test01.test02();
    }


    /**
     * AtomicReference,原子操作更新对象
     */
    private void test02() {
        ar.getAndSet(new Test01());
    }

    /**
     * AtomicInteger 实现线程安全的原理
     * 通过CPU的CAS指令进行增加，
     * 当多线程环境下，一个线程执行CAS，若成功，加1，此时其他线程会有失败，
     * 则重复执行，直到成功，所以不会有丢失
     */
    private void test01() {
        ai.incrementAndGet();
        ai.incrementAndGet();
        System.out.println(ai);
    }
}
