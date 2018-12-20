import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MainConcurrency {
//    private static volatile int counter;
    private final AtomicInteger atomicCounter = new AtomicInteger();
    //    private static final Object LOCK = new Object();
  //  private static final Lock lock = new ReentrantLock();
    private static final ThreadLocal<SimpleDateFormat> threadLocal = ThreadLocal.withInitial(SimpleDateFormat::new);
    private static final int THREAD_NUMBERS = 10000;

    private static final Object FIRST_OBJECT = new Object();
    private static final Object SECOND_OBJECT = new Object();

    public static void main(String[] args) throws InterruptedException {
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
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        CountDownLatch latch = new CountDownLatch(THREAD_NUMBERS);
//        CompletionService completionService = new ExecutorCompletionService(executorService);
//        List<Thread> threads = new ArrayList<>(THREAD_NUMBERS);
        for (int i = 0; i < THREAD_NUMBERS; i++) {

            Future<Integer> future = executorService.submit(() -> {
//            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.inc();
                    System.out.println(threadLocal.get().format(new Date()));
                }
                latch.countDown();
                return 5;
            });

//            thread.start();
//            threads.add(thread);
        }
//        threads.forEach(t -> {
//            try {
//                t.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
        latch.await(10, TimeUnit.SECONDS);
        executorService.shutdown();
        System.out.println(mainConcurrency.atomicCounter.get());

        doDeadLock(FIRST_OBJECT, SECOND_OBJECT);
        doDeadLock(SECOND_OBJECT, FIRST_OBJECT);
    }

    private void inc() {
//        lock.lock();
//        try {
        atomicCounter.incrementAndGet();
//            counter++;
//        } finally {
//            lock.unlock();
//        }
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
