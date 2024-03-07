package com.mq.publish.source.car;

import com.alibaba.fastjson.JSONObject;
import com.mq.common.entity.CarAck;
import com.mq.common.entity.CarMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.support.MessageBuilder;

/**
 * @description:
 * @author: damon.liu
 * @date: 2024/2/2 16:06
 */
@EnableBinding(CarGroupChannel.class)
public class CarGroupHandler {

    @Autowired
    private CarGroupChannel source;

    public void sendMsg(CarMessage msg){
        System.out.println("车辆识别发送消息: " + JSONObject.toJSONString(msg));
        source.sendMsg().send(MessageBuilder.withPayload(msg).build());
    }

    @StreamListener(target = CarGroupChannel.CAR_GROUP_ACK_INPUT)
    //@StreamListener(target = "car_ack_input",  condition = "payload.class.carBrand='ft'")
    //@StreamListener(target = "car_ack_input", condition = "payload['carBrand']=='ft'")
    public void receiveAck(CarAck carAck){
        System.out.println("车辆识别接收响应: " + JSONObject.toJSONString(carAck));
    }
}
