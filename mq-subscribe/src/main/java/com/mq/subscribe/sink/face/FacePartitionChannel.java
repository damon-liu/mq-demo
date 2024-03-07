package com.mq.subscribe.sink.face;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @description:
 * @author: damon.liu
 * @date: 2024/2/2 16:47
 */
public interface FacePartitionChannel {

    String FACE_INPUT_PARTITION = "face-input-partition";

    @Input(FACE_INPUT_PARTITION)
    SubscribableChannel input1();


}
