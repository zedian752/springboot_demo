package org.spring_demo.utils;

import java.util.HashMap;

public class TimerUtils {
    private static HashMap<Long, Long> map = new HashMap();
    public static Long startTime(){
            Long time = System.currentTimeMillis();
//            map.put(time, time);
            return time;
    }
    public static Long getAndPrintEndTime(Long startTime) {
        if (startTime == null) throw new Error("时间不能为空");
        Long endTime =  System.currentTimeMillis();
        System.out.println(String.format("开始时间为[%d], 结束时间为：[%d]，间隔为：[%d]", startTime, endTime, endTime - startTime));
        return endTime;
    }
}
