package com.example.gestionStocks.kafka;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
class KafkaTest {

    @Container
    static final KafkaContainer kafka = new KafkaContainer(
          DockerImageName.parse("confluentinc/cp-kafka:5.4.3")
    );

    @DynamicPropertySource
    static void overrideProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.kafka.producer.bootstrap-servers", kafka::getBootstrapServers);
        registry.add("spring.kafka.consumer.bootstrap-servers", kafka::getBootstrapServers);
    }

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private KafkaConsumer kafkaConsumer;

    @Test
    void testProduceConsume() throws Exception {
        String message = "Hello, Kafka!";

        mockMvc.perform(post("/messages").content(message)).andExpect(status().isOk());

        await()
              .pollInterval(Duration.ofSeconds(3))
              .atMost(10, SECONDS)
              .untilAsserted(() -> {
                    assertThat(kafkaConsumer.getMessages()).hasSize(1);

                  assertThat(kafkaConsumer.getMessages()).containsAll(List.of(message));
              });
    }
}

