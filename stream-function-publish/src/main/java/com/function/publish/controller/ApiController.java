package com.function.publish.controller;


import com.alibaba.fastjson.JSONObject;
import com.mq.common.entity.ApiResult;
import com.mq.common.entity.TopicMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/function")
public class ApiController {

    @Autowired
    private StreamBridge bridge;

    @PostMapping("/sendKafka/{bindingName}")
    public ApiResult<Boolean> sendKafka(@PathVariable String bindingName,@RequestBody TopicMsg msg) {
        String jsonString = JSONObject.toJSONString(msg);
        boolean send = bridge.send(bindingName, jsonString);
        return ApiResult.success(send);
    }

    @PostMapping("/sendRabbit/{bindingName}")
    public ApiResult<Boolean> sendRabbit(@PathVariable String bindingName, @RequestBody TopicMsg msg) {
        boolean send = bridge.send(bindingName,  JSONObject.toJSONString(msg));
        return ApiResult.success(send);
    }
}
