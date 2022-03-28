package com.teststation.assessment.service.message.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teststation.assessment.model.Client;
import com.teststation.assessment.service.client.ClientService;
import com.teststation.assessment.service.message.MessagingConsumerService;
import com.teststation.assessment.util.TaxCalculatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.Optional;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessagingConsumerServiceImpl implements MessagingConsumerService {

    private final Logger logger = LoggerFactory.getLogger(KafkaMessagingConsumerServiceImpl.class);

    @Autowired
    private ClientService clientService;

    @Autowired
    private TaxCalculatorUtil taxCalculatorUtil;

    @Override
    public void receive(String message) {
        logger.info("Received Message in group foo: " + message);

        ObjectMapper mapper = new ObjectMapper();
        Optional<Client> clientOptional = Optional.empty();
        try {
            clientOptional = Optional.ofNullable(mapper.readValue(message, Client.class));
            logger.info("Successfully transformed message into client entity: " + message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        clientOptional.ifPresent(client -> {
            client.setWage(taxCalculatorUtil.addTax(client.getWage()));
            clientService.saveClient(client);
        });
    }

    @KafkaListener(topics = {"cloudmore-assm"})
    public void consume(String message) {
        logger.info("#### -> Consumed message -> " + message);
        this.receive(message);
    }
}
