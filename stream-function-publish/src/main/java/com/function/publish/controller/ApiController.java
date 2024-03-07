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

    @PostMapping("/sendKafka")
    public ApiResult<Boolean> sendKafka(@RequestBody TopicMsg msg) {
//        boolean send = bridge.send("outputKafka-out-0", map);
        String jsonString = JSONObject.toJSONString(msg);
        boolean send = bridge.send(msg.getBindingName(), jsonString);
        return ApiResult.success(send);
    }

    @PostMapping("/sendRabbit")
    public ApiResult<Boolean> sendRabbit(@RequestBody TopicMsg msg) {
        boolean send = bridge.send(msg.getBindingName(),  JSONObject.toJSONString(msg));
        return ApiResult.success(send);
    }
}
