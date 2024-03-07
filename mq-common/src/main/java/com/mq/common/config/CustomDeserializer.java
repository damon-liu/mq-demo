package com.mq.common.config;
import org.apache.kafka.common.serialization.Deserializer;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomDeserializer implements Deserializer<Object> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Object deserialize(String topic, byte[] data) {
        String dataStr = new String(data);
        if (dataStr.startsWith("{") && dataStr.endsWith("}")) {
            try {
                return objectMapper.readValue(dataStr, Object.class);
            } catch (Exception e) {
                throw new RuntimeException("Failed to deserialize object", e);
            }
        } else {
            return dataStr;
        }
    }
}