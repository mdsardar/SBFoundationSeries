package tech.transactionpropagation.org.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionStatus;

@Configuration
public class TransactionMangerConfig {
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf) {
            @Override
            protected void doBegin(Object transaction, TransactionDefinition definition) {
                super.doBegin(transaction, definition);
                System.out.println("Txn Logger --> New transaction started (propagation) " +
                        definition.getPropagationBehavior() + ") "
                        + "(name=" + definition.getName() + ")");
            }

            @Override
            protected void doCommit(DefaultTransactionStatus status) {
                super.doCommit(status);
                System.out.println("Txn  Logger --> Transaction Commited" + "(name = " + status.getTransactionName() + ")");
            }

            @Override
            protected void doRollback(DefaultTransactionStatus status) {
                super.doRollback(status);
                System.out.println("Txn Logger --> Txn Rollbacked" + "(name = " + status.getTransactionName() + ")");
            }
        };
    }
}
