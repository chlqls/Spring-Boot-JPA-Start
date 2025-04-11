package ch.fhnw.pizza.controller;

import ch.fhnw.pizza.data.domain.Menu;
import ch.fhnw.pizza.business.service.MenuService;
import ch.fhnw.pizza.data.domain.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/menu")
public class MenuController {

    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping(path="/pizzas/{id}", produces = "application/json")
    public ResponseEntity<Pizza> getPizza(@PathVariable Long id) {
        Optional<Pizza> pizza = menuService.findPizzaById(id);
        if (pizza.isPresent()) {
            return ResponseEntity.ok(pizza.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path="/pizzas", produces = "application/json")
    public ResponseEntity<List<Pizza>> getPizzaList() {
        List<Pizza> pizzaList = menuService.getAllPizzas();
        return ResponseEntity.ok(pizzaList);
    }

    @PostMapping(path="/pizzas", consumes="application/json", produces = "application/json")
    public ResponseEntity<Pizza> addPizza(@RequestBody Pizza pizza) {
        Pizza savedPizza = menuService.addPizza(pizza);
        return ResponseEntity.ok(savedPizza);
    }

    @GetMapping(path="/pizzas/search", produces = "application/json")
    public ResponseEntity<List<Pizza>> findPizzaByName(@RequestParam String pizzaName) {
        List<Pizza> pizzas = menuService.findPizzaByName(pizzaName);
        return ResponseEntity.ok(pizzas);
    }

    @GetMapping(path="/", produces = "application/json")
    public ResponseEntity<Menu> getMenuByLocation(@RequestParam String location) {
        Menu menu = menuService.getMenuByLocation(location);
        return ResponseEntity.ok(menu);
    }
}