import java.util.*;
class MyThread extends Thread {

    int delay; // each thread has its own delay

    MyThread(int delay) {
        this.delay = delay;
    }

    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(Thread.currentThread().getName() + " running: " + i);
            try {
                Thread.sleep(delay);
            } catch (Exception e) {}
        }
    }
}

public class Threads {
    public static void main(String args[]) throws Exception {

        MyThread m1 = new MyThread(100); // fast
        MyThread m2 = new MyThread(300); // medium
        MyThread m3 = new MyThread(600); // slow

        m1.setName("Thread-1");
        m2.setName("Thread-2");
        m3.setName("Thread-3");

        m1.start();
        m2.start();
        m3.start();

        m1.join();
        m2.join();
        m3.join();

        System.out.println("All threads finished");
    }
}