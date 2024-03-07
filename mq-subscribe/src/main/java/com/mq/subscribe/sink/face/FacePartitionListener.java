package com.mq.subscribe.sink.face;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

/**
 * @description:
 * @author: damon.liu
 * @date: 2024/2/2 16:47
 */
@EnableBinding(FacePartitionChannel.class)
public class FacePartitionListener {

    @StreamListener(FacePartitionChannel.FACE_INPUT_PARTITION)
    public void receivePartition0(Message<String> msg){
        String payload = msg.getPayload();
        MessageHeaders headers = msg.getHeaders();
        Object partition = headers.get("kafka_receivedPartitionId");
        System.out.println("收到分区: 【"+ partition + "】 人脸识别消息： " + payload);
    }

}
