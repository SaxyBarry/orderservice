package edu.iu.c322.invoicingservice.controller;

import edu.iu.c322.invoicingservice.model.*;
import edu.iu.c322.invoicingservice.repository.InvoiceRepository;
import edu.iu.c322.invoicingservice.repository.OrderRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {
    private InvoiceRepository repository;
    private OrderRepository orderRepository;
    public InvoiceController(InvoiceRepository repository, OrderRepository orderRepository) {
        this.repository = repository;
        this.orderRepository = orderRepository;
    }
    @GetMapping("/{id}")
    public Invoice findByOrderIdreq(@PathVariable int id){
        return findbyOrderId(id);
    }

    private Invoice findbyOrderId(int id){
        Optional<Invoice> i = repository.findAll().stream().filter(x -> x.getOrderId() == id).findFirst();
        if(i.isPresent()){
            return i.get();
        }else{
            return createInvoice(id);
        }
    }
    private Invoice createInvoice(int id) {
        Orders order = orderRepository.findAll().stream().filter(x -> x.getId() == id).findAny().orElse(null);
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
        List<Item> items = new ArrayList<>(order.getItems());
        iitem.setItems(items);
        invoice.addInvoiceItem(iitem);
        repository.save(invoice);
        return invoice;
    }



    @PutMapping("/{id}")
    public void updateShipping(@Valid @RequestBody UpdateRequest request,@PathVariable int id) {
        Invoice invoice = findbyOrderId(id);
        for (InvoiceItem i : invoice.getInvoiceItems()) {
            List<Item> items = i.getItems();
            for (Item i2 : items)
                if (i2.getId() == request.getItemId()) {
                    i2.setStatus(request.getStatus());
                    i.setItems(items);
                }
        }
        repository.save(invoice);
    }
}
