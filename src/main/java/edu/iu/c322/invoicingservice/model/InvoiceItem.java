package edu.iu.c322.invoicingservice.model;

import java.util.List;
import java.util.Objects;

public class InvoiceItem {
    private String on = "3/30/2023";
    private List<Item> items;
    private Address address;

    public String getOn() {
        return on;
    }

    public void setOn(String on) {
        this.on = on;
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
        return on.equals(that.on) && items.equals(that.items) && address.equals(that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(on, items, address);
    }
}
