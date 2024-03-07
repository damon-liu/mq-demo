package com.mq.subscribe.rabbit;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 消费者监听器配置，将监听器绑定到消息队列上
 *
 * @author damon.liu
 * @date 2024年02月28日 13:51
 */
@Configuration
public class ListenerConfiguration {

    /**
     * 注入消息监听器适配器
     *
     * @param customAckConsumerListener 自定义监听器对象
     */
    @Bean
    public MessageListenerAdapter
    messageListenerAdapter(CustomAckConsumerListener customAckConsumerListener) {
        //创建自定义监听器适配器对象
        return new MessageListenerAdapter(customAckConsumerListener);
    }

    /**
     * 注入消息监听器容器
     *
     * @param connectionFactory      连接工厂
     * @param messageListenerAdapter 自定义的消息监听器适配器
     */
    @Bean
    public SimpleMessageListenerContainer
    simpleMessageListenerContainer(ConnectionFactory connectionFactory, MessageListenerAdapter messageListenerAdapter) {
        //简单的消息监听器容器对象
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        //绑定消息队列
        container.setQueueNames("test_topic_queue2");
        //设置连接工厂对象
        container.setConnectionFactory(connectionFactory);
        //设置消息监听器适配器
        container.setMessageListener(messageListenerAdapter);
        //设置手动确认消息：NONE(不确认消息)，MANUAL(手动确认消息)，AUTO(自动确认消息)
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        //设置消费端限流，每次拉取消息多少条，默认是250条
        container.setPrefetchCount(1);
        return container;
    }
}
