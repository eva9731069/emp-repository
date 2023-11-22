package com.kafka;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;




@Component
@Slf4j
public class MessageConsumer {
    @KafkaListener(topics = "my-topic", containerFactory = "kafkaListenerContainerFactory")
    public void listen(String message) {

        log.info("Received message: " + message);//consumerFactory

    }
}
