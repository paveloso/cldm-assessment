package com.teststation.assessment.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ClientRequestDto {

    /**
     * Client's name to be saved
     */
    @NotBlank
    private String name;

    /**
     * Client's surname to be saved
     */
    @NotBlank
    private String surname;

    /**
     * Client's wage to be saved
     */
    @NotNull
    @Min(0)
    private Double wage;

    /**
     * Date and time of the event
     */
    @NotBlank
    private String eventTime;

    public ClientRequestDto(String name, String surname, Double wage, String eventTime) {
        this.name = name;
        this.surname = surname;
        this.wage = wage;
        this.eventTime = eventTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Double getWage() {
        return wage;
    }

    public void setWage(Double wage) {
        this.wage = wage;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }
}
