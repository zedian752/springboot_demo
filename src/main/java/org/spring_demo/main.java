package org.spring_demo;


import org.spring_demo.utils.SegmentLock;

public class main {
    public static SegmentLock<String> segmentLock = new SegmentLock<String>();
    public static void main(String args[]) throws InterruptedException {
//            new Thread(new Runnable() {
//                @SneakyThrows
//                @Override
//                public void run() {
//                    synchronized ((1 + "1").intern()) {
//
////                        Long start = System.currentTimeMillis();
//                        Thread.sleep(3000);
////                        TimerUtils.getAndPrintEndTime(start);
//                    }
//                }
//            }).start();
//            //
//            Thread.sleep(300);
//            new Thread(new Runnable() {
//                @SneakyThrows
//                @Override
//                public void run() {
//                    Long start = System.currentTimeMillis();
//                    synchronized ((1 + "1").intern()) {
//                    TimerUtils.getAndPrintEndTime(start);
//                    }
//                }
//            }).start();
    }
}
