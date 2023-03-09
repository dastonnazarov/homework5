package atto.service;

import atto.container.ComponentContainer;
import atto.dto.Transaction;
import atto.repository.TransactionRepository;

public class TransactionService {
    private TransactionRepository transactionRepository;


    public void addTransaction(Transaction transaction) {
        if (transaction != null) {
            transactionRepository.saveTransaction(transaction);
        } else System.err.println("error!!!!!!!!!!");
    }

    public void setTransactionRepository(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }
}
