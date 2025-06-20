package com.pluralsight.cardealership.dao;

import com.pluralsight.cardealership.model.LeaseContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class LeaseDao {

    @Autowired
    private DataSource dataSource;

    public void saveLease(LeaseContract contract) {
        String sql = "INSERT INTO lease_contracts (VIN, customer_name, lease_term_months, monthly_payment) VALUES (?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, contract.getVin());
            stmt.setString(2, contract.getCustomerName());
            stmt.setInt(3, contract.getLeaseTermMonths());
            stmt.setDouble(4, contract.getMonthlyPayment());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<LeaseContract> getAllLeases() {
        List<LeaseContract> contracts = new ArrayList<>();
        String sql = "SELECT * FROM lease_contracts";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                contracts.add(new LeaseContract(
                        rs.getString("VIN"),
                        rs.getString("customer_name"),
                        rs.getInt("lease_term_months"),
                        rs.getDouble("monthly_payment")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contracts;
    }
}