package com.mq.publish.controller;

import com.mq.publish.source.car.CarGroupHandler;
import com.mq.publish.source.face.FacePartitionHandler;
import com.mq.common.entity.ApiResult;
import com.mq.common.entity.CarMessage;
import com.mq.common.entity.FaceMessage;
import com.mq.publish.source.SendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
public class StreamController {

    @Autowired
    private SendService sendService;
    @Autowired
    private FacePartitionHandler faceSendService;
    @Autowired
    private CarGroupHandler carSendService;


    @GetMapping("/send/{msg}")
    public ApiResult<String> send(@PathVariable("msg") String msg){
        sendService.sendMsg(msg);
        return ApiResult.success( "发送消息成功！");
    }

    @PostMapping("/send/face")
    public ApiResult<String> faceSend(@RequestBody FaceMessage msg){
        faceSendService.sendMsg(msg);
        return ApiResult.success("发送消息成功！");
    }

    @PostMapping("/send/car")
    public ApiResult<String> carSend(@RequestBody CarMessage msg){
        carSendService.sendMsg(msg);
        return ApiResult.success("发送消息成功！");
    }
}
