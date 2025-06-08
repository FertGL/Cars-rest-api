package ru.FertGl.carsapi.Controller;


import org.springframework.web.bind.annotation.*;
import ru.FertGl.carsapi.Car;
import ru.FertGl.carsapi.model.CarModel;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/car")
public class CarController {
    private final CarModel carModel = new CarModel();

    @GetMapping("/")
    public List<Car> getAll() {
        return carModel.get();
    }

    @GetMapping("/{id}")
    public Car getById(@PathVariable int id) {
        return carModel.getById(id);
    }

    @PostMapping("/")
    public Car create(@RequestBody Car car) {
        return carModel.add(car);
    }

    @PutMapping("/{id}")
    public Car update(@PathVariable int id,
                      @RequestBody Car car) {
        return carModel.update(id,car);
    }

    @PatchMapping("/{id}")
    public boolean update(@PathVariable int id,
                          @RequestParam(name = "color",required = false,
                                  defaultValue = "White") String newColor) {
        return carModel.update(id,newColor);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable int id) {
        return carModel.delete(id);
    }

    @GetMapping("/filter")
    public List<Car> filter(@RequestParam(name = "color",required = false) String color,
                          @RequestParam(name = "brand", required = false) String brand) {
        if (!brand.isEmpty() & !color.isEmpty()) {
            return carModel.filter(brand,color);
        } else if (color.isEmpty()) {
            return carModel.filterBrand(brand);
        } else if (brand.isEmpty()) {
            return carModel.filterColor(color);
        } else {
            return new ArrayList<>();
        }
    }

    @GetMapping("/sort")
    public List<Car> sort(@RequestParam(name = "by",required = false, defaultValue = "price") String by,
                          @RequestParam(name = "order",required = false, defaultValue = "desc") String order) {
        return carModel.sort(by,order);
    }
}
