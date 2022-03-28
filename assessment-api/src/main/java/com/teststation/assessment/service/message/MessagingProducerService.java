package com.teststation.assessment.service.message;

import com.teststation.assessment.dto.ClientRequestDto;

public interface MessagingProducerService {

    /**
     * Provides interaction with the incoming request and messaging provider
     * @param clientRequestDto data to be sent into the messaging system
     */
    void send(ClientRequestDto clientRequestDto);

}
