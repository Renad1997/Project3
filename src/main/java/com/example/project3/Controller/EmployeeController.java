package com.example.project3.Controller;

import com.example.project3.DTO.EmployeeDTO;
import com.example.project3.Model.Employee;
import com.example.project3.Model.User;
import com.example.project3.Service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/get")
    public ResponseEntity getAllEmployees() {
        return ResponseEntity.status(200).body(employeeService.getAllEmployees());
    }

    @PostMapping("/add")
    public ResponseEntity register(@Valid @RequestBody EmployeeDTO employeeDTO) {
        employeeService.register(employeeDTO);
        return ResponseEntity.status(200).body("register successfully");
    }

    @PutMapping("/update")
    public ResponseEntity updateEmployee(@AuthenticationPrincipal User user, @Valid @RequestBody Employee employee) {
    employeeService.updateEmployee(user.getId(), employee);
        return ResponseEntity.status(200).body("update successfully");
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteEmployee(@AuthenticationPrincipal User user ) {
        employeeService.deleteEmployee(user.getId());
        return ResponseEntity.status(200).body("delete successfully");
    }
}
