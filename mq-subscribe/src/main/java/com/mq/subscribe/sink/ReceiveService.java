package com.mq.subscribe.sink;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * @description:
 * @author: damon.liu
 * @date: 2024/2/2 13:48
 */

@EnableBinding(Sink.class)
public class ReceiveService {

    @StreamListener(Sink.INPUT)
    public void receive(Object payload){
        System.out.println("消费者1收到消息： " + payload);
    }

}
