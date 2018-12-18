import java.util.ArrayList;
import java.util.List;

public class MainConcurrency implements Runnable {
    private static volatile int counter;
    private static final Object LOCK = new Object();
    private static final int THREAD_NUMBERS = 10000;

    private static final Object FIRST_OBJECT = new Object();
    private static final Object SECOND_OBJECT = new Object();

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());

        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + ", " + getState());

            }
        };
        thread0.start();
        new Thread(() -> System.out.println(Thread.currentThread().getName() + ", " + Thread.currentThread().getState())).start();
        System.out.println(thread0.getState());

        final MainConcurrency mainConcurrency = new MainConcurrency();
        List<Thread> threads = new ArrayList<>(THREAD_NUMBERS);
        for (int i = 0; i < THREAD_NUMBERS; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.inc();
                }
            });
            thread.start();
            threads.add(thread);
        }
        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(counter);

        Thread one = new Thread(new MainConcurrency());
        Thread two = new Thread(new MainConcurrency());
        one.start();
        two.start();

    }

    private synchronized void inc() {
        counter++;
    }

    @Override
    public void run() {
        doOne();
        doTwo();
    }

    private void doOne() {
        synchronized (SECOND_OBJECT) {
            System.out.println("doOne, SECOND_OBJECT");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (FIRST_OBJECT) {
                System.out.println("doOne, FIRST_OBJECT");
            }
        }
    }

    private void doTwo() {
        synchronized (FIRST_OBJECT) {
            System.out.println("doTwo, FIRST_OBJECT");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (SECOND_OBJECT) {
                System.out.println("doTwo, SECOND_OBJECT");
            }
        }
    }
}
