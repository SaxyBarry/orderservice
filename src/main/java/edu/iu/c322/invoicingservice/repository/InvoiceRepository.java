package edu.iu.c322.invoicingservice.repository;

import edu.iu.c322.invoicingservice.model.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class InvoiceRepository {
    private Boolean testCreated = false;
    List<Invoice> invoices = new ArrayList<Invoice>();

    List<Order> orders = new ArrayList<Order>(){};

    public void createTestOrder(){
        if(!testCreated) {
            Order order = new Order();
            order.setId(1);
            Address a = new Address();
            a.setPostalCode(47408);
            a.setCity("Bloomington");
            a.setState("Indiana");
            order.setShippingAddress(a);
            Payment p = new Payment();
            p.setBillingAddress(a);
            p.setMethod("Debit");
            p.setNumber("1234567890");
            order.setPayment(p);
            Item i1 = new Item();
            i1.setName("rose");
            i1.setPrice(3);
            i1.setQuantity(1);
            Item i2 = new Item();
            i2.setName("sunflower");
            i2.setPrice(5);
            i2.setQuantity(3);
            order.setItems(new ArrayList<Item>(Arrays.asList(i1, i2)));
            order.setDateOrdered("3/28/2023");
            order.setCustomerId(1);
            order.setTotal(20.00);
            orders.add(order);
            testCreated = true;
        }
    }

    public Invoice findbyOrderId(int id) {
        return getOrderByID(id);
    }

    public void update(UpdateRequest updateRequest, int id) {
        Invoice invoice = findbyOrderId(id);
        List<InvoiceItem> invoiceItem = invoice.getInvoiceItem();
        for (InvoiceItem i:invoiceItem) {
            List<Item> items = i.getItems();
            if (items.size() > updateRequest.getItemId()-1){
                Item item = items.get(updateRequest.getItemId()-1);
                item.setStatus(updateRequest.getStatus());
                items.set(updateRequest.getItemId()-1, item);
                i.setItems(items);
            }
        }

    }

    private Invoice getOrderByID(int id) {
        return invoices.stream().filter(x -> x.getOrderId() == id).findAny().orElse(createInvoice(id));
    }

    private Invoice createInvoice(int id) {
        Order order = orders.stream().filter(x -> x.getId() == id).findAny().orElse(null);
        if (order == null){
            throw new IllegalStateException("order id not found");
        }
        Invoice invoice = new Invoice();
        invoice.setPayment(order.getPayment());
        invoice.setTotal(order.getTotal());
        invoice.setOrderId(order.getId());
        invoice.setOrderPlaced(order.getDateOrdered());
        InvoiceItem iitem = new InvoiceItem();
        iitem.setAddress(order.getShippingAddress());
        iitem.setItems(order.getItems());
        invoice.addInvoiceItem(iitem);
        invoices.add(invoice);
        return invoice;
    }

}
