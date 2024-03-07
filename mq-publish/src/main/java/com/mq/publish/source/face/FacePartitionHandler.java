package com.mq.publish.source.face;

import com.alibaba.fastjson.JSONObject;
import com.mq.common.entity.FaceMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;

/**
 * @description:
 * @author: damon.liu
 * @date: 2024/2/2 16:06
 */
@EnableBinding(FacePartitionChannel.class)
public class FacePartitionHandler {

    @Autowired
    private FacePartitionChannel source;

    public void sendMsg(FaceMessage msg){
        System.out.println("人脸识别发送消息: " + JSONObject.toJSONString(msg));
        source.sendMsg().send(MessageBuilder.withPayload(JSONObject.toJSONString(msg)).build());
    }
}
