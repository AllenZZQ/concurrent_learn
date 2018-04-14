package java_concurrency_in_practice.chapter01;

import junit.framework.TestCase;

import java.util.Timer;
import java.util.TimerTask;

public class Test extends TestCase {

    /**
     * 锁住某对象，谁执行wait、notify、notifAll，否则报错
     */
    public void test02() {
        synchronized (this) {
            try {
                System.out.println("before wait");
                this.wait();
                System.out.println("after wait");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }



    /**
     * 定时任务，延迟1秒，每隔2秒执行
     */
    public static void test01() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("hello");
            }
        }, 1000, 2000);
    }
}

