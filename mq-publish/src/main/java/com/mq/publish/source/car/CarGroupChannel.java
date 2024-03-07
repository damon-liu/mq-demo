package com.mq.publish.source.car;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @description:
 * @author: damon.liu
 * @date: 2024/2/2 16:04
 */
public interface CarGroupChannel {

    String CAR_GROUP_DEMO_OUTPUT = "car-group-output";
    String CAR_GROUP_ACK_INPUT = "car-group-ack-input";


    @Output(CAR_GROUP_DEMO_OUTPUT)
    MessageChannel sendMsg();

    @Input(CAR_GROUP_ACK_INPUT)
    MessageChannel receiveMsg();
}
