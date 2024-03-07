package com.mq.publish.controller;

import com.mq.publish.kafka.MyPartitionTemplate;
import com.mq.publish.util.KafkaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * @author damon.liu
 * @date 2024年02月28日 19:28
 */
@RestController
public class KafkaController {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    //简单demo
    @GetMapping("/kafka/normal/{message}")
    public String sendMessage1(@PathVariable("message") String message) {
        kafkaTemplate.send("simple-topic", message);
        return "发送消息成功！";
    }

    //带回调的生产者,方式一
    @GetMapping("/kafka/callbackOne/{message}")
    public void sendMessage2(@PathVariable("message") String callbackMessage) {
        kafkaTemplate.send("simple-topic", callbackMessage).addCallback(success -> {
            // 消息发送到的topic
            String topic = success.getRecordMetadata().topic();
            // 消息发送到的分区
            int partition = success.getRecordMetadata().partition();
            // 消息在分区内的offset
            long offset = success.getRecordMetadata().offset();
            System.out.println("发送消息成功:" + topic + "-" + partition + "-" + offset);
        }, failure -> {
            System.out.println("发送消息失败:" + failure.getMessage());
        });
    }

    //带回调的生产者,方式二
    @GetMapping("/kafka/callbackTwo/{message}")
    public void sendMessage3(@PathVariable("message") String callbackMessage) {
        kafkaTemplate.send("simple-topic", callbackMessage).addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("发送消息失败："+ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                System.out.println("发送消息成功：" + result.getRecordMetadata().topic() + "-"
                        + result.getRecordMetadata().partition() + "-" + result.getRecordMetadata().offset());
            }
        });
    }




    //指定分区发送，不管key是什么，指定分区发送
    @GetMapping("/kafka/partitionSend")
    public String setPartition(@RequestParam("key") String key, @RequestParam("partition") Integer partition) {
        kafkaTemplate.send("partition-topic-1", partition, key, "key=" + key + "，msg=指定" + partition + "号分区");
        return "发送消息成功！";
    }

    //指定key发送，不指定分区，根据key做hash，相同key到同一个分区
    @GetMapping("/kafka/keySend")
    public String setKey(@RequestParam("key") String key) {
        kafkaTemplate.send("partition-topic-1", key, "key=" + key + "，msg=不指定分区");
        return "发送消息成功！";
    }

    @Autowired
    private MyPartitionTemplate myPartitionTemplate;

    @GetMapping("/kafka/myPartitionSend/{key}")
    public String setPartition3(@PathVariable("key") String key) {
        myPartitionTemplate.getKafkaTemplate().send("partition-topic-1", key,"key="+key+"，msg=自定义分区策略");
        return "发送消息成功！";
    }


    //消费者手动ack
    @GetMapping("/kafka/manualAckSend")
    public String manualAckSend(@RequestParam("msg") String msg) {
        kafkaTemplate.send("manual-ack-topic", msg);
        return "发送消息成功！";
    }





    @Autowired
    private KafkaUtils kafkaUtils;

    @PostMapping("/kafka/delete/topic/{topic}")
    public void deleteTopic(@PathVariable("topic") String topic) {
        kafkaUtils.deleteTopic(Arrays.asList(topic));
    }

}
