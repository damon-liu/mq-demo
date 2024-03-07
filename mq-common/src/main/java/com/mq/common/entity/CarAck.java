package com.mq.common.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @description:
 * @author: damon.liu
 * @date: 2024/2/2 16:35
 */
@Getter
@Setter
@NoArgsConstructor
public class CarAck implements Serializable {

    private static final long serialVersionUID = -6888318586116092505L;

    private  String carNum;

    private String carBrand;

    private String ackMsg;
}
