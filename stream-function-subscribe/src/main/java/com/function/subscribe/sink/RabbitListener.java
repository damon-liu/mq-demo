package com.function.subscribe.sink;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class RabbitListener {

    //Consumer声明一个消息消费者，inputRabbit1就对应inputRabbit1-in-0
    @Bean
    public java.util.function.Consumer<Message<String>> inputRabbit1() {
        return message -> {
            log.info("rabbit分组1收到消息: {}", message.getPayload());
        };
    }

    @Bean
    public java.util.function.Consumer<Message<String>> inputRabbit2() {
        return message -> {
            log.info("inputRabbit2 message: {}", message.getPayload());
        };
    }
}
