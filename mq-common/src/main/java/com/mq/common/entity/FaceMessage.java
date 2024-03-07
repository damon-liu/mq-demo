package com.mq.common.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @description:
 * @author: damon.liu
 * @date: 2024/2/2 16:36
 */
@Getter
@Setter
@NoArgsConstructor
public class FaceMessage {

    private String name;

    private Integer age;

    @Override
    public String toString() {
        return "FaceMessage{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
