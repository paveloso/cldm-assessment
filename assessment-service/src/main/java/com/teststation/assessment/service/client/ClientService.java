package com.teststation.assessment.service.client;

import com.teststation.assessment.model.Client;

public interface ClientService {

    /**
     * Provides funtionality to save entity to the db.
     * @param client entity to be stored into db
     */
    void saveClient(Client client);
}
