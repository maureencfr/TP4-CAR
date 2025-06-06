package com.example.gestionStocks.kafka;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    private final KafkaConsumer kafkaConsumer;

    public KafkaController(KafkaConsumer kafkaConsumer) {
        this.kafkaConsumer = kafkaConsumer;
    }

    @GetMapping("/messages")
    public void consumeMessage(@RequestBody String message) {
        kafkaConsumer.consume(message);
    }
}
