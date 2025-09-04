package tech.transactionpropagation.org.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.transactionpropagation.org.entity.User;

import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<User, Long> {

    Optional<User> findByName(String name);

}
