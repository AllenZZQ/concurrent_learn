package java_concurrency_in_practice.chapter01;

import java.util.Timer;
import java.util.TimerTask;

public class Test01 {
    public static void main(String[] args) {
        Test01 test01 = new Test01();
        test01();
        test01.test02();
    }

    /**
     * 锁住某对象，谁执行wait、notify、notifAll，否则报错
     */
    private void test02() {
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
    private static void test01() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("hello");
            }
        }, 1000, 2000);
    }
}

