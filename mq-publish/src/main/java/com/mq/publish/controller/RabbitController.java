package com.mq.publish.controller;

import com.mq.publish.rabbit.RabbitTopicConfig;
import com.mq.common.entity.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author damon.liu
 * @date 2024年02月28日 19:27
 */

@Slf4j
@RestController
public class RabbitController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/rabbit/send")
    public ApiResult<String> rabbitSend(@RequestParam("routingKey") String routingKey, @RequestParam("msg") String msg) {
        rabbitTemplate.convertAndSend(RabbitTopicConfig.EXCHANGE_NAME, routingKey, msg);
        return ApiResult.success("发送消息成功！");
    }
}
