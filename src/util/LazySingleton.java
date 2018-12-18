package util;

public class LazySingleton {
    volatile private static LazySingleton INSTANCE;

//    public static LazySingleton getInstance() {
//        if (INSTANCE == null) {
//            synchronized (LazySingleton.class) {
//                if (INSTANCE == null) {
//                    INSTANCE = new LazySingleton();
//                }
//            }
//        }
//        return INSTANCE;
//    }

    private LazySingleton() {
    }
    private static class LazySingletonHolder{
        private static final LazySingleton INSTANCE = new LazySingleton();
    }
    public static LazySingleton getInstance(){
        return LazySingletonHolder.INSTANCE;
    }
}
