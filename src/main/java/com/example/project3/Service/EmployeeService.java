package com.example.project3.Service;

import com.example.project3.Api.ApiException;
import com.example.project3.DTO.EmployeeDTO;
import com.example.project3.Model.Employee;
import com.example.project3.Model.User;
import com.example.project3.Repository.AuthRepository;
import com.example.project3.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final AuthRepository authRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public void register(EmployeeDTO employeeDTO) {
        User user = new User();
        user.setUsername(employeeDTO.getUsername());
        user.setPassword(employeeDTO.getPassword());
        user.setName(employeeDTO.getName());
        user.setEmail(employeeDTO.getEmail());
        user.setRole("EMPLOYEE");
        Employee employee = new Employee();
        employee.setPosition(employeeDTO.getPosition());
        employee.setSalary(employeeDTO.getSalary());
        user.setEmployee(employee);
        employee.setUser(user);
        authRepository.save(user);
        employeeRepository.save(employee);
    }

    public void updateEmployee(Integer auth_id,Employee employee) {
        User user =authRepository.findUserById(auth_id);
        Employee employee1 = employeeRepository.findEmployeeByIdAndUser(auth_id,user);
        if (employee1 == null) {
            throw new ApiException("user not found");
        }
        user.getEmployee().setPosition(employee1.getPosition());
        user.getEmployee().setSalary(employee1.getSalary());
        authRepository.save(user);
    }

    public void deleteEmployee(Integer auth_id) {
        User user =authRepository.findUserById(auth_id);
        Employee employee1 = employeeRepository.findEmployeeByIdAndUser(auth_id,user);
        if (employee1 == null) {
            throw new ApiException("user not found");
        }
        authRepository.delete(user);
    }


}
