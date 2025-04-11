package ch.fhnw.pizza.data.repository;

import ch.fhnw.pizza.data.domain.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Long> {

    // Find pizzas by name
    List<Pizza> findByName(String name);

    // Find pizzas by a given topping
    List<Pizza> findByToppingsContaining(String topping);
}