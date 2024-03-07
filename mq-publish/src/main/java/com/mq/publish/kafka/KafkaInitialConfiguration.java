package com.mq.publish.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author damon.liu
 * @date 2024年02月29日 14:04
 */
@Configuration
public class KafkaInitialConfiguration {


    // 创建一个名为partition-topic-2的Topic并设置分区数为2，分区副本数为2
    @Bean
    public NewTopic initialPartitionTopic1(){
        return new NewTopic("partition-topic-1", 2, (short) 2);
    }



}

