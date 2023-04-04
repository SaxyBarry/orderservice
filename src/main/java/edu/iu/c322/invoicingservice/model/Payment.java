package edu.iu.c322.invoicingservice.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;

import java.util.Objects;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String method;
    private String number;
    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    private Address billingAddress;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return method.equals(payment.method) && number.equals(payment.number) && billingAddress.equals(payment.billingAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(method, number, billingAddress);
    }
}
