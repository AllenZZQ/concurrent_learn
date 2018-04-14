package java_concurrency_in_practice.chapter02;

import junit.framework.TestCase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Test extends TestCase {

    private int i = 0;
    private AtomicInteger ai = new AtomicInteger(0);

    private AtomicInteger ai1 = new AtomicInteger(0);
    private AtomicInteger ai2 = new AtomicInteger(0);

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

    /**
     * AtomicReference,原子操作更新对象
     */
    private void test02() {
//        ar.getAndSet(new Test01());
    }

    /**
     * 并不是线程安全的
     */
    public void test03() {
        AtomicReference<Test> ar = new AtomicReference<>(new Test());
        ExecutorService service = Executors.newCachedThreadPool();
        for (int t = 0; t < 20; t ++) {
            service.execute(() -> {
                for (int i = 0; i < 1000; i ++) {
                    ar.get().add();
                }
            });
        }
        service.shutdown();
        while (Thread.activeCount() > 2) {
            System.out.println(Thread.activeCount());
            Thread.yield();
        }
        System.out.println(ar.get().i);
    }

    private void add() {
        i ++;
    }

    /**
     * 两个原子操作并不是线程安全的
     */
    private void test04() {
        ai1.incrementAndGet();
        ai2.incrementAndGet();
    }
}
