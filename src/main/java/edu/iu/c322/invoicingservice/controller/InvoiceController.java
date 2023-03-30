package edu.iu.c322.invoicingservice.controller;

import edu.iu.c322.invoicingservice.model.Invoice;
import edu.iu.c322.invoicingservice.model.Order;
import edu.iu.c322.invoicingservice.model.UpdateRequest;
import edu.iu.c322.invoicingservice.repository.InvoiceRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {
    private InvoiceRepository repository;
    public InvoiceController(InvoiceRepository repository) {
        this.repository = repository;
        repository.createTestOrder();
    }
    @GetMapping("/{id}")
    public Invoice findByOrderId(@PathVariable int id){
        return repository.findbyOrderId(id);
    }

    @PutMapping("/{id}")
    public void updateShipping(@Valid @RequestBody UpdateRequest request,@PathVariable int id){
        repository.update(request, id);
    }


}
