package com.teststation.assessment.controller;

import com.teststation.assessment.dto.ClientRequestDto;
import com.teststation.assessment.service.message.MessagingProducerService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

    @Autowired
    private MessagingProducerService messagingProducerService;

    /**
     * Provides endpoint to save data into the system.
     * @param clientRequestDto holds data to be saved
     * @return status 200 if ok.
     */
    @PostMapping("/client")
    public String sendClient(@RequestBody @Valid ClientRequestDto clientRequestDto) {
        messagingProducerService.send(clientRequestDto);
        return HttpStatus.OK.toString();
    }
}
