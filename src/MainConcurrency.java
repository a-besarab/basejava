import java.util.ArrayList;
import java.util.List;

public class MainConcurrency {
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

        doDeadLock(FIRST_OBJECT, SECOND_OBJECT);
        doDeadLock(SECOND_OBJECT, FIRST_OBJECT);
    }

    private synchronized void inc() {
        counter++;
    }

    private static void doDeadLock(Object FIRST_OBJECT, Object SECOND_OBJECT) {
        new Thread(() -> {
            synchronized (FIRST_OBJECT) {
                System.out.println("Block FIRST_OBJECT");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Waiting for SECOND_OBJECT");
                synchronized (SECOND_OBJECT) {
                    System.out.println("Block SECOND_OBJECT");
                }
            }
        }).start();
    }
}
