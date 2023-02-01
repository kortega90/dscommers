package com.kortega.dscommerce.dto;

import com.kortega.dscommerce.entities.Payment;

import java.time.Instant;

public class PaymentDTO {

    private Long id;
    private Instant moment;

    public PaymentDTO(Long id, Instant moment) {
        this.id = id;
        this.moment = moment;
    }
    public PaymentDTO(Payment payment) {
        this.id = payment.getId();
        this.moment = payment.getMoment();
    }

    public Long getId() {
        return id;
    }

    public Instant getMoment() {
        return moment;
    }
}
