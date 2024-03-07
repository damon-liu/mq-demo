package com.function.publish.source;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author damon.liu
 * @date 2024年02月27日 19:39
 */

@Component
@Log4j2
public class Producer {

    private int id = 1;

    //消息自动生产者，outputKafka1就对应outputKafka1-out-0
    @Bean
    public Supplier<Message<String>> supplierKafka() {
        return () -> {
            log.info("二狗发送第{}次条消息：",id);
            return MessageBuilder.withPayload(
                    String.format("二狗第%d次说：%s", id++, "Hello!")
                    )
                    .build();
        };
    }

    private int num = 1;

    /**
     * 转化函数
     * 它就是中间负责转化的一个东西。例如有三个服务，S1,S2,S3。S1丢给S2一个消息，S2也不消费，只是要把它丢给S3，那么S2中就可以使用Function，将接到的消息转化成S3需要的格式
     * @return
     */
    @Bean
    public Function<Message<String>, Message<String>> functionKafka() {
        return msg -> {
            String payload = msg.getPayload();
            int i = num++;
            log.info("二狗第{}次消息并转发给消费者：{}",id, payload);
            return MessageBuilder.withPayload(String.format("二狗转发第%d次消息：%s", i, payload)).build();
        };
    }

}
