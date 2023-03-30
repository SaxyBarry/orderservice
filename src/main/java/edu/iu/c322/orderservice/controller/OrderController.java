package edu.iu.c322.orderservice.controller;

import edu.iu.c322.orderservice.model.Order;
import edu.iu.c322.orderservice.model.UpdateRequest;
import edu.iu.c322.orderservice.repository.OrderRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private OrderRepository repository;
    public OrderController(OrderRepository repository) {
        this.repository = repository;
    }
    @GetMapping
    public List<Order> findAll(){
        return repository.findAll();
    }
    @GetMapping("/{id}")
    public List<Order> getOrdersByCustomerId(@PathVariable int id){
        return repository.findAllbyCust(id);
    }
    @PostMapping
    public int create(@Valid @RequestBody Order order){
        return repository.create(order);
    }

    // PUT http://localhost:8080/customers/2
    @PutMapping("/return")
    public void update(@RequestBody UpdateRequest updateRequest){
        repository.update(updateRequest);
    }

    // DELETE http://localhost:8080/customers/2
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        repository.delete(id);
    }
}
