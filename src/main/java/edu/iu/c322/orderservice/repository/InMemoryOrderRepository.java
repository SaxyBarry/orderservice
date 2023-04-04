package edu.iu.c322.orderservice.repository;

import edu.iu.c322.orderservice.model.Item;
import edu.iu.c322.orderservice.model.Orders;
import edu.iu.c322.orderservice.model.UpdateRequest;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryOrderRepository {
    List<Orders> orders = new ArrayList<Orders>();
    List<UpdateRequest> updateRequests = new ArrayList<UpdateRequest>();
    public List<Orders> findAll(){
        return orders;
    }
    public List<Orders> findAllbyCust(int id){
        return getOrdersByCustomerId(id);
    }
    public int create(Orders order){
        int id = orders.size()+1;
        order.setId(id);
        orders.add(order);
        return id;
    }
    public void update(UpdateRequest updateRequest){
        Orders x = getOrderByID(updateRequest.getOrderId());
        if(x != null){
            List<Item> items = x.getItems();
            if(items.size() > updateRequest.getItemId()-1){
                Item newItem = items.get(updateRequest.getItemId()-1);
                newItem.setStatus("returned");
                newItem.setReason(updateRequest.getReason());
                items.set(updateRequest.getItemId()-1, newItem);
                x.setItems(items);
                updateRequests.add(updateRequest);
            }else {
                throw new IllegalStateException("item id not found");
            }
        }else{
            throw new IllegalStateException("order id not found");
        }
    }

    public void delete(int id){
        Orders x = getOrderByID(id);
        if(x!=null) {
            orders.remove(x);
        }else{
            throw new IllegalStateException("order id not found");
        }
    }

    private Orders getOrderByID(int id) {
        return orders.stream().filter(x -> x.getId() == id).findAny().orElse(null);
    }
    private List<Orders> getOrdersByCustomerId(int id) {
        return orders.stream().filter(x -> x.getCustomerId() == id).toList();
    }
}
