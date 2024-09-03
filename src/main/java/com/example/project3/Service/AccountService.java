package com.example.project3.Service;

import com.example.project3.Api.ApiException;
import com.example.project3.Model.Account;
import com.example.project3.Model.Customer;
import com.example.project3.Repository.AccountRepository;
import com.example.project3.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;


    public void createAccount(Integer auth_id,Account account) {
        Customer customer = customerRepository.findCustomerById(auth_id);
       Account account1 = new Account();
       account1.setAccountNumber(account.getAccountNumber());
       account1.setBalance(account.getBalance());
       account1.setActive(account.isActive());
       account1.setCustomer(customer);
       accountRepository.save(account1);
    }

    public void activeAccount(Integer auth_id,Integer account_id) {
        Customer customer = customerRepository.findCustomerById(auth_id);
        Account account1 = accountRepository.findAccountsByIdAndCustomerId(account_id,auth_id);
        if(account1 == null) {
            throw new ApiException("Account not found");
        }
        if(account1.getCustomer().getId() != customer.getId()) {
            throw new ApiException("not your account");
        }
        account1.setActive(true);
        accountRepository.save(account1);
    }

    public Account getAccount(Integer auth_id , Integer account_id) {
       Account account=accountRepository.findAccountsByIdAndCustomerId(account_id,auth_id);
        if(account==null){
            throw new ApiException("Account not found");
        }
        return account;
    }

    public List<Account> getAccounts(Integer auth_id) {
        return accountRepository.findAllByCustomerId(auth_id);
    }

    public void deposit(Integer auth_id,Integer account_id,Integer amount) {
        Account account=accountRepository.findAccountsByIdAndCustomerId(account_id,auth_id);
        if(account==null){
            throw new ApiException("Account not found");
        }
        if(!account.isActive()) {
            throw new ApiException("Account not active");
        }
        account.setBalance(account.getBalance()+amount);
        accountRepository.save(account);
    }

    public void withdraw(Integer auth_id,Integer account_id,Integer amount) {
        Account account=accountRepository.findAccountsByIdAndCustomerId(account_id,auth_id);
        if(account==null){
            throw new ApiException("Account not found");
        }
        if(!account.isActive()) {
            throw new ApiException("Account not active");
        }
        if(account.getBalance()<amount) {
            throw new ApiException("Not enough balance");
        }
        account.setBalance(account.getBalance()-amount);
        accountRepository.save(account);
    }
    public void transferMoney(Integer customer_id,Integer fromAccount_id ,Integer toAccount_id,Integer amount) {
        Account fromAccount=accountRepository.findAccountsByIdAndCustomerId(fromAccount_id,customer_id);
        Account toAccount=accountRepository.findAccountById(toAccount_id);
        if(fromAccount==null || toAccount==null){
            throw new ApiException("Account not found");
        }
        if(!fromAccount.isActive() || !toAccount.isActive()) {
            throw new ApiException("Account not active");
        }
        if(fromAccount.getBalance()<amount) {
            throw new ApiException("Not enough balance");
        }
        fromAccount.setBalance(fromAccount.getBalance()-amount);
        toAccount.setBalance(toAccount.getBalance()+amount);
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
    }

//    public void updateAccount(Integer customer_id,Integer account_id,Account account) {
//        Customer customer = customerRepository.findCustomerById(customer_id);
//        if(customer==null){
//            throw new ApiException("Customer not found");
//        }
//        Account oldAccount = accountRepository.findAccountById(account_id);
//        if(oldAccount==null){
//            throw new ApiException("Account not found");
//        }else if(oldAccount.getCustomer().getId()!=customer_id){
//            throw new ApiException("sorry you dont have authority");
//        }
//        oldAccount.setAccountNumber(account.getAccountNumber());
//        oldAccount.setBalance(account.getBalance());
//        oldAccount.setIsActive(account.getIsActive());
//        oldAccount.setCustomer(customer);
//        accountRepository.save(oldAccount);
//    }
//    public void deleteAccount(Integer customer_id,Integer account_id) {
//        Customer customer = customerRepository.findCustomerById(customer_id);
//        if(customer==null){
//            throw new ApiException("Customer not found");
//        }
//        Account oldAccount = accountRepository.findAccountById(account_id);
//        if(oldAccount==null){
//            throw new ApiException("Account not found");
//        }
//        else if(oldAccount.getCustomer().getId()!=customer_id){
//            throw new ApiException("sorry you dont have authority");
//        }
//        accountRepository.delete(oldAccount);
//    }


}
