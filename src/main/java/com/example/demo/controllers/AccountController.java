package com.example.demo.controllers;

import com.example.demo.entity.Account;
import com.example.demo.entity.Transaction;
import com.example.demo.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping
    public List<Account> getAllAccounts(){
        return accountRepository.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id){
        return accountRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account){
        Account saved = accountRepository.save(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable Long id, @RequestBody Account updatedData) {
        // Optional for the safety if the value is missing so it doesnt throw an error.
        Optional<Account> optionalAccount = accountRepository.findById(id);

        if (optionalAccount.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Account existingAccount = optionalAccount.get();

        // update fields manually
        existingAccount.setAccountNumber(updatedData.getAccountNumber());
        existingAccount.setBalance(updatedData.getBalance());
        existingAccount.setCreatedAt(updatedData.getCreatedAt());
        existingAccount.setCustomer(updatedData.getCustomer());
        existingAccount.setTransactions(updatedData.getTransactions());

        // save updated entity
        Account saved = accountRepository.save(existingAccount);

        return ResponseEntity.ok(saved);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        if (!accountRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        accountRepository.deleteById(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

}
