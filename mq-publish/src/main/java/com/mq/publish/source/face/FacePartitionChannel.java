package com.mq.publish.source.face;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @description:
 * @author: damon.liu
 * @date: 2024/2/2 16:04
 */
public interface FacePartitionChannel {
    @Output("face-partition-output")
    MessageChannel sendMsg();
}
