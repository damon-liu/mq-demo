package com.mq.common.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: damon.liu
 * @date: 2024/2/2 16:26
 */
@Data
public class KafkaMessage  implements Serializable {

    private static final long serialVersionUID = -5886012896705137070L;

    private  Integer partitionId;
}
