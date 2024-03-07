package com.mq.subscribe.rabbit;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.stereotype.Component;

/**
 * @author damon.liu
 * @date 2024年02月28日 11:14
 */

@Component
public class RabbitListener {
    /**
     * 监听某个队列的消息
     * @param message 接收到的消息
     */
    @org.springframework.amqp.rabbit.annotation.RabbitListener(queuesToDeclare = { @Queue(value = "test_topic_queue1",durable = "true", autoDelete = "false") })
    public void testTopicQueue1(String message){
        System.out.println("rabbit mq 接收到testTopicQueue1队列消息：" + message);
    }

//    @org.springframework.amqp.rabbit.annotation.RabbitListener(queuesToDeclare = { @Queue(value = "test_topic_queue2",durable = "true", autoDelete = "false") })
//    public void testTopicQueue2(String message){
//         System.out.println("rabbit mq 接收到testTopicQueue2队列消息：" + message);
//    }
}
