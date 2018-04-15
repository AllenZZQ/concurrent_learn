package java_concurrency_in_practice.chapter05;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Vector;
import java.util.concurrent.*;

public class Test extends TestCase {

    private int i = 0;
    private Object o = new Object();

    /**
     * 读写分离，保证数据的最终一致性，无法保证实时一致性
     * synchronizedList对写和读加锁，可以保证实时一致性
     */
    public void test1() {
        CopyOnWriteArrayList ca = new CopyOnWriteArrayList();
        ca.add(1);
        ca.get(1);
        Collections.synchronizedList(new ArrayList<>());
        Vector v = new Vector();
        v.add(1);
        v.get(1);
    }

    public void test2() {
        ConcurrentHashMap map = new ConcurrentHashMap();
        Hashtable ht = new Hashtable();
        ht.put(1, 2);
    }

    public void test3() {
        ExecutorService service = Executors.newCachedThreadPool();
        for (int t = 0; t < 200; t ++) {
            service.execute(() -> {

                    for (int k = 0; k < 1000; k ++) {
                        synchronized (o) {
                        i ++;
                    }
                }
            });
        }
        service.shutdown();
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }

        System.out.println(i);
    }

    public void test4() throws InterruptedException, ExecutionException, TimeoutException {
        FutureTask<String> f = new FutureTask(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(1000);
                return "a";
            }
        });
        new Thread(f).start();
        System.out.println(f.get(0, TimeUnit.SECONDS));
    }
}
