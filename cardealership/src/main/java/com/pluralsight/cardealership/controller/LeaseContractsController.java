package com.pluralsight.cardealership.controller;

import com.pluralsight.cardealership.dao.LeaseDao;
import com.pluralsight.cardealership.model.LeaseContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leases")
public class LeaseContractsController {

    @Autowired
    private LeaseDao leaseDao;

    // GET /leases
    @GetMapping
    public List<LeaseContract> getAllLeases() {
        return leaseDao.getAllLeases();
    }

    // POST /leases
    @PostMapping
    public void createLease(@RequestBody LeaseContract contract) {
        leaseDao.saveLease(contract);
    }
}