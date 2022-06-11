package org.spring_demo.utils;

import java.util.Date;
import java.util.HashMap;


public class LockUtils {
     public static int publicHashCode;
     private static HashMap<String, String> keymap = new HashMap<String, String>();
     private static HashMap<String, String> cacheKeymap = new HashMap<String, String>(); // 缓存区
     private static int maxKeyAmount = 1000;
     private static long  cacheRecordPoint = 0,maxCacheDuration = 10000; // 最大缓存持续时间



    public static void test() throws InterruptedException {
        String c = "123";
        int hashcode = c.hashCode();
        publicHashCode = hashcode;
        new Date();
        synchronized (c) {
            Thread.sleep(10000);
            System.out.println("synchronized.test");
        }
        return ;
//        for (int i = 0; i < 10000; i++) {
//            String keyValue = String.valueOf(i);
//            keymap.put(keyValue, keyValue);
//        }
//        Set<String> set = new LinkedHashSet<>();
//        keymap.forEach((key, value) -> {
//            if (!set.contains(key)) {
//                set.add(key);
//            } else {
//                System.out.println("重复");
//                System.out.println(key.hashCode());
//            }
//        });
    }
    synchronized String getLock(Object key) {
//            String mKey = String.valueOf(key);
//            if (keymap.containsKey(String.valueOf(mKey))) {
//                return keymap.get(mKey);
//            };
//            keymap.put(mKey, mKey);
//            return keymap.get(mKey);
//            keymap.
        return null;
    }

    public static class tsat {

        @Override
        public int hashCode() {
            return LockUtils.publicHashCode;
        }
        public synchronized void tsat_lock_method(){
            System.out.println("tsat.tsat_lock_method");
        }
    }
}
