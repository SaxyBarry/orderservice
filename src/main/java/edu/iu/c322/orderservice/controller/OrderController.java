package edu.iu.c322.orderservice.controller;

import edu.iu.c322.orderservice.model.Item;
import edu.iu.c322.orderservice.model.Orders;
import edu.iu.c322.orderservice.model.UpdateRequest;
import edu.iu.c322.orderservice.repository.OrderRepository;
import jakarta.persistence.criteria.Predicate;
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
    public List<Orders> findAll(){
        return repository.findAll();
    }
    @GetMapping("/{id}")
    public List<Orders> getOrdersByCustomerId(@PathVariable int id){
        return repository.findAll().stream().filter(x -> x.getCustomerId() == id).toList();
    }
    @PostMapping
    public int create(@Valid @RequestBody Orders order){
        return repository.save(order).getId();
    }

    // PUT http://localhost:8080/customers/2
    @PutMapping("/return")
    public void update(@RequestBody UpdateRequest updateRequest){
        Orders order = repository.findAll().stream().filter(x->x.getId()==updateRequest.getOrderId()).findAny().orElse(null);
        if (order != null){
            List<Item> items = order.getItems();
            Boolean found = false;
            for (Item i:items) {
                if(i.getId() == updateRequest.getItemId()){
                    i.setReason(updateRequest.getReason());
                    i.setDateModified("04/03/2023");
                    i.setStatus("returned");
                    found = true;
                }
            }
            if(!found){
                throw new IllegalStateException("item id not found");
            }
            order.setItems(items);
        }else{
            throw new IllegalStateException("order id not found");
        }
        repository.save(order);
    }

    // DELETE http://localhost:8080/customers/2
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        Orders o = repository.findAll().stream().filter(x->x.getId()==id).findAny().orElse(null);
        if(o!=null){
            repository.delete(o);
        }else{
            throw new IllegalStateException("order id not found");
        }
    }
}
