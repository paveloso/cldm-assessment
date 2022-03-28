package com.teststation.assessment.service.message;

public interface MessagingConsumerService {

    /**
     * Provides interaction with the messaging system to fetch the data.
     * @param message json string to be received from the system
     */
    void receive(String message);
}
