package com.teststation.assessment.service.message.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.teststation.assessment.dto.ClientRequestDto;
import com.teststation.assessment.service.message.MessagingProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class KafkaMessagingProducerServiceImpl implements MessagingProducerService {

    private final Logger logger = LoggerFactory.getLogger(KafkaMessagingProducerServiceImpl.class);

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaMessagingProducerServiceImpl(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void send(ClientRequestDto clientRequestDto) {

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = null;
        try {
            json = ow.writeValueAsString(clientRequestDto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        logger.info("--- Resulted json: " + json);

        ListenableFuture<SendResult<String, String>> future =
                kafkaTemplate.send("cloudmore-assm", json);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                logger.info("Sent message=[" + clientRequestDto.getName() +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                logger.info("Unable to send message=["
                        + clientRequestDto.getName() + "] due to : " + ex.getMessage());
            }
        });
    }
}
