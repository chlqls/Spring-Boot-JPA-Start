package ch.fhnw.pizza.business.service;

import ch.fhnw.pizza.data.domain.Menu;
import ch.fhnw.pizza.data.domain.Pizza;
import ch.fhnw.pizza.data.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuService {

    private final PizzaRepository pizzaRepository;

    @Autowired
    public MenuService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    // Find a pizza by its ID
    public Optional<Pizza> findPizzaById(Long id) {
        return pizzaRepository.findById(id);
    }

    // Get all pizzas
    public List<Pizza> getAllPizzas() {
        return pizzaRepository.findAll();
    }

    // Add a new pizza
    public Pizza addPizza(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    // Find pizzas by name
    public List<Pizza> findPizzaByName(String name) {
        return pizzaRepository.findByName(name);
    }

    // Private method to determine the current offer based on location
    private String getCurrentOffer(String location) {
        if ("Basel".equalsIgnoreCase(location)) {
            return "10% off on all large pizzas!!!";
        } else if ("Brugg".equalsIgnoreCase(location)) {
            return "Two for the price of One on all small pizzas!!!";
        } else {
            return "No special offers available at this location.";
        }
    }

    // Public method to get the menu by location
    public Menu getMenuByLocation(String location) {
        Menu menu = new Menu();
        List<Pizza> pizzaList = getAllPizzas();
        menu.setPizzaList(pizzaList);
        menu.setCurrentOffer(getCurrentOffer(location));
        return menu;
    }
}