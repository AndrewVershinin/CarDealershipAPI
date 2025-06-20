package com.pluralsight.cardealership.controller;

import com.pluralsight.cardealership.dao.SalesDao;
import com.pluralsight.cardealership.model.SalesContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales")
public class SalesContractsController {

    @Autowired
    private SalesDao salesDao;

    // GET /sales
    @GetMapping
    public List<SalesContract> getAllSales() {
        return salesDao.getAllSales();
    }

    // POST /sales
    @PostMapping
    public void createSale(@RequestBody SalesContract contract) {
        salesDao.saveSale(contract);
    }
}