package com.mq.publish.kafka;

import lombok.Getter;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @author damon.liu
 * @date 2024年02月28日 19:41
 */
@Configuration
public class MyPartitionTemplate {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Getter
    KafkaTemplate<String,String> kafkaTemplate;

    @PostConstruct
    public void setKafkaTemplate() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        //注意分区器在这里！！！
        props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, CustomizePartitioner.class);
        this.kafkaTemplate = new KafkaTemplate<String, String>(new DefaultKafkaProducerFactory<>(props));
    }

}

