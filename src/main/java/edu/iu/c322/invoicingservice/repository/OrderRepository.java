package edu.iu.c322.invoicingservice.repository;
import edu.iu.c322.invoicingservice.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer>{

}
