package com.mq.publish.kafka;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

/**
 * @author damon.liu
 * @date 2024年02月28日 19:43
 */
public class CustomizePartitioner implements Partitioner {
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        //定义自己的分区策略,如果key以0开头，发到0号分区,其他都扔到1号分区
        String keyStr = key+"";
        if (keyStr.startsWith("0")){
            System.out.println("发送0号分区");
            return 0;
        }else {
            System.out.println("发送1号分区");
            return 1;
        }
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
