package com.function.subscribe.sink;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class StreamListener {

    //Consumer声明一个消息消费者，inputKafka1就对应inputKafka1-in-0
    @Bean
    public java.util.function.Consumer<Message<String>> inputKafka1() {
        return message -> {
            // 收到消息在这里做一些处理
            log.info("kafka分组1收到消息: {}", message.getPayload());

            // todo: 资料太少，暂未实现手动确认消息
//            Acknowledgment acknowledgment = message.getHeaders().get(KafkaHeaders.ACKNOWLEDGMENT, Acknowledgment.class);
//            if (acknowledgment != null) {
//                acknowledgment.acknowledge();
//            }
        };
    }

    //Consumer声明一个消息消费者，inputKafka2就对应inputKafka2-in-0
    @Bean
    public java.util.function.Consumer<Message<String>> inputKafka2() {
        return message -> {
            log.info("kafka分组2收到消息: {}", message.getPayload());
        };
    }

}
