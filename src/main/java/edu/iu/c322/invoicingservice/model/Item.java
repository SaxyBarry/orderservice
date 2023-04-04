package edu.iu.c322.invoicingservice.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Item {
    private String name;
    private int quantity;
    private int price;
    private String status = "ordered";
    private String reason = "";
    private String dateModified = "";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return quantity == item.quantity && price == item.price && name.equals(item.name) && status.equals(item.status) && reason.equals(item.reason) && dateModified.equals(item.dateModified);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, quantity, price, status, reason, dateModified);
    }
}
