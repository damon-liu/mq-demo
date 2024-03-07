package com.function.subscribe.sink;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class KafkaListener {

    @Bean
    public java.util.function.Consumer<String> supplierKafka() {
        return str -> {
            log.info("消费者supplierKafka接收到: {}", str);
        };
    }


    @Bean
    public java.util.function.Consumer<String> functionKafkaConsumer() {
        return str -> {
            log.info("消费者functionKafka接收到: {}", str);
        };
    }


    @Bean
    public java.util.function.Consumer<String> partitionTopic3() {
        return str -> {
            log.info("消费者接收到消息: {}", str);
        };
    }
}
