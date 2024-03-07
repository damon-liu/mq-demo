package com.mq.publish.rabbit;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author damon.liu
 * @date 2024年02月28日 10:53
 */
@Configuration
public class RabbitTopicConfig {
    /*定义交换机名称*/
    public static  final String EXCHANGE_NAME="test_topic_exchange";
    /*定义队列名称*/
    public static final String QUEUE_NAME_1 = "test_topic_queue1";

    public static final String QUEUE_NAME_2 = "test_topic_queue2";
    /*交换机*/
    @Bean("testTopicExchange")
    public Exchange bootExchange(){
        return ExchangeBuilder.topicExchange(EXCHANGE_NAME).durable(true).build();
    }

    /* 2.Queue 队列*/
    @Bean("testTopicQueue1")
    public Queue testTopicQueue1(){
        return QueueBuilder.durable(QUEUE_NAME_1).build();
    }

    @Bean("testTopicQueue2")
    public Queue testTopicQueue2(){
        return QueueBuilder.durable(QUEUE_NAME_2).build();
    }

    /*队列和交换机绑定关系*/
    /*
     * 知道哪个队列
     * 知道哪个交换机
     * routing key
     * */
    @Bean
    public Binding getTopicBinding1(@Qualifier("testTopicQueue1") Queue queue, @Qualifier("testTopicExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("test1.#").noargs();
    }

    @Bean
    public Binding getTopicBinding2(@Qualifier("testTopicQueue2") Queue queue, @Qualifier("testTopicExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("test2.#").noargs();
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        // 服务启动时候开启自动启动
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }
}
