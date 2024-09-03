package com.example.project3.Controller;

import com.example.project3.Model.Account;
import com.example.project3.Model.Customer;
import com.example.project3.Model.User;
import com.example.project3.Service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/add")
    public ResponseEntity createAccount(@AuthenticationPrincipal User user, @RequestBody @Valid Account account){
        accountService.createAccount(user.getId(),account);
        return ResponseEntity.status(200).body("Account created successfully");
    }

    @PutMapping("/active/{account_id}")
    public ResponseEntity activeAccount(@AuthenticationPrincipal User user, @PathVariable Integer account_id){
        accountService.activeAccount(user.getId(),account_id);
        return ResponseEntity.status(200).body(user.getUsername()+ " Account activated successfully");
    }

    @GetMapping("/getAccount/{account_id}")
    public ResponseEntity getAccount(@AuthenticationPrincipal User user, @PathVariable Integer account_id){
        return ResponseEntity.status(200).body(accountService.getAccount(user.getId(),account_id));
    }

    @GetMapping("/getAccounts")
    public ResponseEntity getAccounts(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(200).body(accountService.getAccounts(user.getId()));
    }

    @PutMapping("/deposit/{account_id}/{amount}")
    public ResponseEntity deposit(@AuthenticationPrincipal User user,@PathVariable Integer account_id,@PathVariable Integer amount){
        accountService.deposit(user.getId(),account_id,amount);
        return ResponseEntity.status(200).body(user.getUsername()+ " Account deposited successfully");
    }

    @PutMapping("/withdraw/{account_id}/{amount}")
    public ResponseEntity withdraw(@AuthenticationPrincipal User user,@PathVariable Integer account_id,@PathVariable Integer amount){
        accountService.withdraw(user.getId(),account_id,amount);
        return ResponseEntity.status(200).body(user.getUsername()+ " Account withdrawn successfully");
    }

    @PutMapping("/transferMoney/{fromAccount_id}/{toAccount_id}/{amount}")
    public ResponseEntity transferMoney(@AuthenticationPrincipal User user,@PathVariable Integer fromAccount_id,@PathVariable Integer toAccount_id,@PathVariable Integer amount){
        accountService.transferMoney(user.getId(),fromAccount_id,toAccount_id,amount);
        return ResponseEntity.status(200).body(user.getUsername()+ " Transfer Money " + amount +  "To" + toAccount_id);
    }




}
