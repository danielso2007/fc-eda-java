package com.estudoeda.balances.infrastructure.config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.util.backoff.FixedBackOff;
import com.estudoeda.balances.application.domain.event.BalanceUpdatedWalletEvent;


@Configuration
@ConditionalOnProperty(name = "kafka.enabled", havingValue = "true", matchIfMissing = true)
public class KafkaConfig {

    @Value("${kafka.bootstrap.servers}")
    private String bootstrap;
    @Value("${kafka.group.id.config}")
    private String groupIdConfig;

    @Bean
    public ConsumerFactory<String, BalanceUpdatedWalletEvent> consumerFactory() {
        @SuppressWarnings("deprecation")
        JsonDeserializer<BalanceUpdatedWalletEvent> jsonDeserializer = new JsonDeserializer<>(BalanceUpdatedWalletEvent.class);
        jsonDeserializer.setUseTypeHeaders(false);
        Map<String, Object> props = new ConcurrentHashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupIdConfig);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, jsonDeserializer);
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), jsonDeserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, BalanceUpdatedWalletEvent> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, BalanceUpdatedWalletEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setCommonErrorHandler(new DefaultErrorHandler(new FixedBackOff(1000L, 3)));
        return factory;
    }

    public String getBootstrapServers() {
        return bootstrap;
    }

}