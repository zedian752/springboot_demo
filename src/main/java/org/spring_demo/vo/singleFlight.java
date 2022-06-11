package org.spring_demo.vo;

import lombok.Data;

@Data
public class singleFlight<T> {
    T res;
    String type; // 消费者
   public enum MessageType{
    CONSUMER("CONSUMER","消费者"),
    PRODUCER("PRODUCER", "生产者"),
    ;

    public String type;
    public String typeDesc;
    MessageType(String type, String typeDesc) {
        this.type = type;
        this.typeDesc = typeDesc;

    }

}
}


