package com.example.gestionStocks.kafka;

import com.example.gestionStocks.service.CommandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    @Autowired
    private CommandService stockService;

    @KafkaListener(topics = "my-command", groupId = "my-command-group")
    public void consume(String message) {
        LOGGER.info("Commande reçue: {}", message);
        stockService.updateStockFromCommand(message);
    }
}

