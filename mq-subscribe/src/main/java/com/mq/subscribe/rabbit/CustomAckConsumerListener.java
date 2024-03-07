package com.mq.subscribe.rabbit;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

/**
 * @author damon.liu
 * @date 2024年02月28日 13:48
 */


@Component
public class CustomAckConsumerListener implements ChannelAwareMessageListener {

    /**
     * 自定义监听器，监听到消息之后，立即执行onMessage方法
     */
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        //获取消息内容
        byte[] messageBody = message.getBody();
        String msg = new String(messageBody, "utf-8");
        System.out.println("rabbit mq 自定义监听器接收到消息 消息内容：" + msg);
        //获取投递标签
        MessageProperties messageProperties = message.getMessageProperties();
        long deliveryTag = messageProperties.getDeliveryTag();
        try {
            //休眠3秒
            Thread.sleep(3000);
//            if (msg.contains("苹果")){
//                throw new RuntimeException("不允许卖苹果手机！！！");
//            }
             //手动签收消息 参数1：消息投递标签 参数2：是否批量签收：true一次性签收所有，false，只签收当前消息
            channel.basicAck(deliveryTag,false);
            System.out.println("rabbit mq 自定义监听器手动签收完成");
        } catch (Exception ex){
            /**
             * 手动拒绝签收
             * 参数1：当前消息的投递标签
             * 参数2：是否批量签收：true一次性签收所有，false，只签收当前消息
             * 参数3：是否重回队列，true为重回队列，false为不重回
             */
            channel.basicNack(deliveryTag,false,true);
            System.out.println("拒绝签收，重回队列：{}"+ex);
        }
    }
}
