package com.function.publish.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author damon.liu
 * @date 2024年02月29日 20:07
 */
@Configuration
public class KafkaInitialConfiguration {

    @Bean
    public NewTopic partitionTopic2() {
        return new NewTopic("partition-topic-2", 2, (short) 1);  // 3 is the number of partitions and 1 is the replication factor
    }

    @Bean
    public NewTopic partitionTopic3() {
        return new NewTopic("partition-topic-3", 3, (short) 1);  // 3 is the number of partitions and 1 is the replication factor
    }

}
