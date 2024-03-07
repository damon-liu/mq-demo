package com.mq.publish.source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;

/**
 * @description:
 *
 * 3.x版本后是 可以看到 @StreamListener 和 @EnableBinding 都打上了@Deprecated 注解。后续的版本更新中会逐渐替换成函数式的方式实现。
 *
 * @author: damon.liu
 * @date: 2024/2/2 13:44
 */

@EnableBinding(Source.class)
public class SendService {

    @Autowired
    private Source source;

    public void sendMsg(String msg) {
        System.out.println("发送消息: " + msg);
        source.output().send(MessageBuilder.withPayload(msg).build());
    }
}
