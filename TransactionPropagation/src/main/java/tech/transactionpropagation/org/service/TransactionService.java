package tech.transactionpropagation.org.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tech.transactionpropagation.org.entity.User;
import tech.transactionpropagation.org.repo.TransactionRepository;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    // @Transactional(propagation = Propagation.NEVER)
    // @Transactional(propagation = Propagation.REQUIRED)
    // @Transactional(propagation = Propagation.MANDATORY)
    // @Transactional(propagation = Propagation.SUPPORTS)
    // @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void debit(Long userId, Long amount) {
        User user = transactionRepository.findById(userId).orElseThrow();
        user.setBalance(user.getBalance() - amount);
        transactionRepository.save(user);
    }

    public void credit(Long userId, Long amount) {
        User user = transactionRepository.findById(userId).orElseThrow();
        user.setBalance(user.getBalance() + amount);
        transactionRepository.save(user);
    }

}
