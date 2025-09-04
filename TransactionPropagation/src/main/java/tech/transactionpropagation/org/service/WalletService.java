package tech.transactionpropagation.org.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class WalletService {

    private TransactionService transactionService;

    public WalletService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Transactional
    public void transfer(Long senderId, Long receiverId, Long amount){
        transactionService.debit(senderId, amount);
        // transactionService.credit(receiverId, amount);
    }
}
