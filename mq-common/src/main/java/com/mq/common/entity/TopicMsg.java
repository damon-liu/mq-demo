package com.mq.common.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @description:
 * @author: damon.liu
 * @date: 2024/2/6 11:09
 */
@Getter
@Setter
@NoArgsConstructor
public class TopicMsg implements Serializable {

    private static final long serialVersionUID = 5591684481697805884L;

    private  Integer id;

    private String msg;

    private String bindingName ;
}
