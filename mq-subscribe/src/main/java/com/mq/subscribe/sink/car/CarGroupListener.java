package com.mq.subscribe.sink.car;

import com.alibaba.fastjson.JSONObject;
import com.mq.common.entity.CarAck;
import com.mq.common.entity.CarMessage;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.SendTo;

/**
 * @description:
 * @author: damon.liu
 * @date: 2024/2/2 16:47
 */
@EnableBinding(CarGroupChannel.class)
public class CarGroupListener {

    @StreamListener(CarGroupChannel.CAR_INPUT_GROUP_1)
    @SendTo(CarGroupChannel.CAR_ACK_OUTPUT)
    public CarAck receiveGroup1(Message<CarMessage> payload){
        CarMessage msg = payload.getPayload();
        MessageHeaders headers = payload.getHeaders();
        System.out.println("分组1收到车辆识别消息： " + JSONObject.toJSONString(msg));
        // 业务处理....

        // 返回ACK消息
        String ack = "分组1已经收到消息并给出了响应";
        CarAck carAckMsg = new CarAck();
        BeanUtils.copyProperties(payload, carAckMsg);
        carAckMsg.setAckMsg(ack);
        //source.sendAckMsg().send(MessageBuilder.withPayload(AckMsg).build());
        return carAckMsg;
    }

    @StreamListener(CarGroupChannel.CAR_INPUT_GROUP_2)
    @SendTo(CarGroupChannel.CAR_ACK_OUTPUT)
    public CarAck receiveGroup2(Message<CarMessage> payload){
        CarMessage msg = payload.getPayload();
        MessageHeaders headers = payload.getHeaders();
        System.out.println("分组2收到车辆识别消息： " + JSONObject.toJSONString(msg));
        // 业务处理....

        // 返回ACK消息
        String ack = "分组2已经收到消息并给出了响应";
        CarAck carAckMsg = new CarAck();
        BeanUtils.copyProperties(payload, carAckMsg);
        carAckMsg.setAckMsg(ack);
        //source.sendAckMsg().send(MessageBuilder.withPayload(AckMsg).build());
        return carAckMsg;
    }
}
