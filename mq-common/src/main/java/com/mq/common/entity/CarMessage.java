package com.mq.common.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @description:
 * @author: damon.liu
 * @date: 2024/2/2 16:35
 */
@Getter
@Setter
@NoArgsConstructor
public class CarMessage extends KafkaMessage {

    private  String carNum;

    private String carColor;

    private String carBrand;
}
