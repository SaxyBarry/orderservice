package edu.iu.c322.invoicingservice.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private double total;
    private String orderPlaced;
    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment")
    private Payment payment;
    @OneToMany(cascade = CascadeType.ALL)
    private List<InvoiceItem> invoiceItems = new ArrayList<>();
    private int orderId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<InvoiceItem> getInvoiceItems() {
        return invoiceItems;
    }

    public void setInvoiceItems(List<InvoiceItem> invoiceItems) {
        this.invoiceItems = invoiceItems;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getOrderPlaced() {
        return orderPlaced;
    }

    public void setOrderPlaced(String orderPlaced) {
        this.orderPlaced = orderPlaced;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }


    public void addInvoiceItem(InvoiceItem invoiceItem) {
        invoiceItems.add(invoiceItem);
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return id == invoice.id && Double.compare(invoice.total, total) == 0 && orderId == invoice.orderId && Objects.equals(orderPlaced, invoice.orderPlaced) && Objects.equals(payment, invoice.payment) && Objects.equals(invoiceItems, invoice.invoiceItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, total, orderPlaced, payment, invoiceItems, orderId);
    }
}
