package edu.iu.c322.invoicingservice.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class InvoiceItem {
    private String on_date = "04/08/2023";
    @OneToMany(cascade = CascadeType.ALL)
    private List<Item> items;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;


    public String getOn() {
        return on_date;
    }

    public void setOn(String on) {
        this.on_date = on;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceItem that = (InvoiceItem) o;
        return on_date.equals(that.on_date) && items.equals(that.items) && address.equals(that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(on_date, items, address);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
