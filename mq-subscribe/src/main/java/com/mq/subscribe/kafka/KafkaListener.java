package com.mq.subscribe.kafka;

import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author damon.liu
 * @date 2024年02月28日 19:30
 */
@Component
@Log4j2
public class KafkaListener {

    @org.springframework.kafka.annotation.KafkaListener(topics = {"simple-topic"}, groupId = "simple-group-0")
    @SendTo("simple-resp-topic")
    public String onMessageGroup0_0(ConsumerRecord<?, ?> record) {
        Object value = record.value();
        String s = String.valueOf(value);
        System.out.println("topic：" + record.topic() + " 分组：【0】，消费者：【0】" + " 接收到消息：" + s);
        return "此条为group0下的消费者0转发的消息！";
    }

    @org.springframework.kafka.annotation.KafkaListener(topics = {"simple-topic"}, groupId = "simple-group-0")
    @SendTo("simple-resp-topic")
    public String onMessageGroup0_1(ConsumerRecord<?, ?> record) {
        Object value = record.value();
        String s = String.valueOf(value);
        System.out.println("topic：" + record.topic() + " 分组：【0】，消费者：【1】" + " 接收到消息：" + s);
        return "此条为group0下的消费者1转发的消息！";
    }


    @org.springframework.kafka.annotation.KafkaListener(topics = {"simple-topic"}, groupId = "simple-group-1")
    @SendTo("simple-resp-topic")
    public String onMessageGroup1(ConsumerRecord<?, ?> record) {
        Object value = record.value();
        String s = String.valueOf(value);
        System.out.println("topic：" + record.topic() + " 分组：【0】" + "接收到消息：" + s);
        return "此条为group1转发的消息！";
    }

    @org.springframework.kafka.annotation.KafkaListener(topics = {"simple-resp-topic"})
    public void onMessage0(ConsumerRecord<?, ?> record) {
        // 消费的哪个topic、partition的消息,打印出消息内容
        Object value = record.value();
        String s = String.valueOf(value);
        System.out.println(record.topic() + "接收消息内容： " + s);
    }


    // 分区消费
    @org.springframework.kafka.annotation.KafkaListener(topics = {"partition-topic-1"}, topicPattern = "0")
    public void onMessage2(ConsumerRecord<?, ?> consumerRecord) {
        Optional<?> optional = Optional.ofNullable(consumerRecord.value());
        if (optional.isPresent()) {
            Object msg = optional.get();
            log.info("分区【0】接收消息:{}", msg);
        }
    }

    @org.springframework.kafka.annotation.KafkaListener(topics = {"partition-topic-1"}, topicPattern = "1")
    public void onMessage3(ConsumerRecord<?, ?> consumerRecord) {
        Optional<?> optional = Optional.ofNullable(consumerRecord.value());
        if (optional.isPresent()) {
            Object msg = optional.get();
            log.info("分区【1】接收消息:{}", msg);
        }
    }



    // 分组消费,手动ack
    //@org.springframework.kafka.annotation.KafkaListener(topics = "manual-ack-topic", groupId = "manual-group-1", errorHandler = "consumerAwareErrorHandler")
    @org.springframework.kafka.annotation.KafkaListener(topics = "manual-ack-topic", groupId = "manual-group-1",
            containerFactory = "manualKafkaListenerContainerFactory",errorHandler = "consumerAwareErrorHandler")
    public void onMessage4(ConsumerRecord<?, ?> consumerRecord, Consumer consumer, Acknowledgment ack) {
        Optional<?> optional = Optional.ofNullable(consumerRecord.value());
        if (optional.isPresent()) {
            Object msg = optional.get();
            log.info("手动ack 分组【1】 接收到消息: [{}]", msg);
            ack.acknowledge();
        }
    }

//    @org.springframework.kafka.annotation.KafkaListener(topics = "manual-ack-topic", groupId = "manual-group-2")
    @org.springframework.kafka.annotation.KafkaListener(topics = "manual-ack-topic", groupId = "manual-group-2",
            containerFactory = "manualKafkaListenerContainerFactory",errorHandler = "consumerAwareErrorHandler")
    public void onMessage5(ConsumerRecord<?, ?> consumerRecord, Consumer consumer, Acknowledgment ack) {
        Optional<?> optional = Optional.ofNullable(consumerRecord.value());
        if (optional.isPresent()) {
            Object msg = optional.get();
            log.info("手动ack 分组【2】 接收到消息: [{}]", msg);
            ack.acknowledge();
        }
    }
}
