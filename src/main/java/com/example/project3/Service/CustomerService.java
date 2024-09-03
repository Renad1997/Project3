package com.example.project3.Service;

import com.example.project3.Api.ApiException;
import com.example.project3.DTO.CustomerDTO;
import com.example.project3.DTO.EmployeeDTO;
import com.example.project3.Model.Account;
import com.example.project3.Model.Customer;
import com.example.project3.Model.Employee;
import com.example.project3.Model.User;
import com.example.project3.Repository.AuthRepository;
import com.example.project3.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AuthRepository authRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public void register(CustomerDTO customerDTO) {
        User user = new User();
        user.setUsername(customerDTO.getUsername());
        String hash = new BCryptPasswordEncoder().encode(customerDTO.getPassword());
        user.setPassword(hash);
        user.setName(customerDTO.getName());
        user.setEmail(customerDTO.getEmail());
        user.setRole("CUSTOMER");
        Customer customer = new Customer();
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customer.setUser(user);
        user.setCustomer(customer);
        authRepository.save(user);
        customerRepository.save(customer);
    }

    public void updateCustomer(Integer auth_id,Customer customer) {
        User user=authRepository.findUserById(auth_id);
        Customer customer1=customerRepository.findCustomerByIdAndUser(auth_id,user);
        if (customer1 == null) {
            throw new ApiException("user not found");
        }
        user.getCustomer().setPhoneNumber(customer.getPhoneNumber());
       authRepository.save(user);
    }

    public void deleteCustomer(Integer auth_id) {
        User user=authRepository.findUserById(auth_id);
        Customer customer1=customerRepository.findCustomerByIdAndUser(auth_id,user);
        if (customer1 == null) {
            throw new ApiException("user not found");
        }
       authRepository.delete(user);
    }

}
