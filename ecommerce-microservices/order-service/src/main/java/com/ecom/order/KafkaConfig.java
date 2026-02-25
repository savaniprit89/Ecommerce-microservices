package com.ecom.order;

import com.ecom.common.events.Topics;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Bean
    public ProducerFactory<String, Object> producerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean public NewTopic t1(){ return TopicBuilder.name(Topics.ORDER_CREATED).partitions(1).replicas(1).build(); }
    @Bean public NewTopic t2(){ return TopicBuilder.name(Topics.INVENTORY_RESERVED).partitions(1).replicas(1).build(); }
    @Bean public NewTopic t3(){ return TopicBuilder.name(Topics.INVENTORY_FAILED).partitions(1).replicas(1).build(); }
    @Bean public NewTopic t4(){ return TopicBuilder.name(Topics.PAYMENT_SUCCESS).partitions(1).replicas(1).build(); }
    @Bean public NewTopic t5(){ return TopicBuilder.name(Topics.PAYMENT_FAILED).partitions(1).replicas(1).build(); }
}
