package com.pluralsight.cardealership.controller;

import com.pluralsight.cardealership.dao.VehicleDao;
import com.pluralsight.cardealership.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehiclesController {

    @Autowired
    private VehicleDao vehicleDao;

    // GET /vehicles?minPrice=5000&maxPrice=40000
    @GetMapping
    public List<Vehicle> searchVehicles(
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String make,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) Integer minYear,
            @RequestParam(required = false) Integer maxYear,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) Integer minMiles,
            @RequestParam(required = false) Integer maxMiles,
            @RequestParam(required = false) String type
    ) {
        if (minPrice != null && maxPrice != null) {
            return vehicleDao.searchByPriceRange(minPrice, maxPrice);
        } else if (make != null && model != null) {
            return vehicleDao.searchByMakeModel(make, model);
        } else if (minYear != null && maxYear != null) {
            return vehicleDao.searchByYearRange(minYear, maxYear);
        } else if (color != null) {
            return vehicleDao.searchByColor(color);
        } else if (minMiles != null && maxMiles != null) {
            return vehicleDao.searchByMileageRange(minMiles, maxMiles);
        } else if (type != null) {
            return vehicleDao.searchByType(type);
        }

        return List.of();
    }

    // POST /vehicles
    @PostMapping
    public void addVehicle(@RequestBody Vehicle vehicle) {
        vehicleDao.addVehicle(vehicle);
    }

    // DELETE /vehicles/{vin}
    @DeleteMapping("/{vin}")
    public void deleteVehicle(@PathVariable String vin) {
        vehicleDao.removeVehicleByVIN(vin);
    }
}