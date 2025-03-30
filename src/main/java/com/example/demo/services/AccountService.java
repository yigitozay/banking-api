package com.example.demo.services;

import com.example.demo.entity.Account;
import com.example.demo.entity.Transaction;
import com.example.demo.repositories.AccountRepository;
import com.example.demo.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    public void deposit(Long accountId, BigDecimal amount){
        Account depositAccount = accountRepository.findById(accountId)
                .orElseThrow(()-> new RuntimeException("Deposit account not found."));
        depositAccount.setBalance(depositAccount.getBalance().add(amount));

        Transaction depositTx = new Transaction(
                Transaction.TransactionType.DEPOSIT,
                amount,
                LocalDateTime.now(),
                depositAccount
        );
        transactionRepository.save(depositTx);
        accountRepository.save(depositAccount);
    }
    public void withdraw(Long accountId, BigDecimal amount){
        Account withdrawAccount = accountRepository.findById(accountId)
                .orElseThrow(()-> new RuntimeException("Withdraw account not found."));
        if(withdrawAccount.getBalance().compareTo(amount) < 0)
        {
            throw new RuntimeException("Insufficient funds");
        }

        withdrawAccount.setBalance(withdrawAccount.getBalance().subtract(amount));

        Transaction withdrawTx = new Transaction(
                Transaction.TransactionType.WITHDRAW,
                amount,
                LocalDateTime.now(),
                withdrawAccount
        );
        transactionRepository.save(withdrawTx);
        accountRepository.save(withdrawAccount);
    }
    @Transactional
    public void transfer(Long fromAccountId, Long toAccountId, BigDecimal amount){
        Account fromAccount = accountRepository.findById(fromAccountId)
                .orElseThrow(() -> new RuntimeException("From account not found"));
        Account toAccount = accountRepository.findById(toAccountId)
                .orElseThrow(() -> new RuntimeException("To account not found"));

        if(fromAccount.getBalance().compareTo(amount)< 0){
            throw new RuntimeException("Insufficient funds");
        }
        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        toAccount.setBalance(toAccount.getBalance().add(amount));

        Transaction withdrawTx = new Transaction(
                Transaction.TransactionType.WITHDRAW,
                amount,
                LocalDateTime.now(),
                fromAccount
        );

        Transaction depositTx = new Transaction(
                Transaction.TransactionType.DEPOSIT,
                amount,
                LocalDateTime.now(),
                toAccount
        );
        transactionRepository.save(withdrawTx);
        transactionRepository.save(depositTx);
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
    }
}
