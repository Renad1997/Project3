package com.example.project3.Controller;

import com.example.project3.DTO.CustomerDTO;
import com.example.project3.DTO.EmployeeDTO;
import com.example.project3.Model.Customer;
import com.example.project3.Model.User;
import com.example.project3.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/get")
    public ResponseEntity getAllCustomers() {
        return ResponseEntity.status(200).body(customerService.getAllCustomers());
    }

    @PostMapping("/add")
    public ResponseEntity register(@Valid @RequestBody CustomerDTO customerDTO) {
        customerService.register(customerDTO);
        return ResponseEntity.status(200).body("register successfully");
    }

    @PutMapping("/update")
    public ResponseEntity updateCustomer(@AuthenticationPrincipal User user , @Valid @RequestBody Customer customer) {
        customerService.updateCustomer(user.getId(),customer);
        return ResponseEntity.status(200).body("update successfully");
    }

    @DeleteMapping("/delete/{customer_id}")
    public ResponseEntity deleteCustomer(@AuthenticationPrincipal User user, @PathVariable Integer customer_id) {
        customerService.deleteCustomer(user.getId());
        return ResponseEntity.status(200).body("delete successfully");
    }
}
