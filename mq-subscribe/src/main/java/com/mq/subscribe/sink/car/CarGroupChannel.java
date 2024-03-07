package com.mq.subscribe.sink.car;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @description:
 * @author: damon.liu
 * @date: 2024/2/2 16:47
 */
public interface CarGroupChannel {

    String CAR_INPUT_GROUP_1 = "car-input-group1";

    String CAR_INPUT_GROUP_2 = "car-input-group2";

    String CAR_ACK_OUTPUT = "car-ack-output";

    @Input(CAR_INPUT_GROUP_1)
    SubscribableChannel input1();

    @Input(CAR_INPUT_GROUP_2)
    SubscribableChannel input2();

    @Output(CAR_ACK_OUTPUT)
    MessageChannel sendAckMsg();
}
