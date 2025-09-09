Spring Data JPA Transaction Internal Working

1. Transactions Without @Transactional

Without a @Transactional annotation, JPA will trigger individual transactions for every database interaction.

⚠️ If an error occurs in between, the previous transaction will not be rolled back, which can leave the system in an
inconsistent state.

Example transaction logs:

Txn Logger --> New transaction started (propagation=0) (name=SimpleJpaRepository.findById)
Txn Logger --> Transaction committed (name=SimpleJpaRepository.findById)

Txn Logger --> New transaction started (propagation=0) (name=SimpleJpaRepository.save)
Txn Logger --> Transaction committed (name=SimpleJpaRepository.save)

Txn Logger --> New transaction started (propagation=0) (name=SimpleJpaRepository.findById)
Txn Logger --> Transaction committed (name=SimpleJpaRepository.findById)

Txn Logger --> New transaction started (propagation=0) (name=SimpleJpaRepository.save)
Txn Logger --> Transaction committed (name=SimpleJpaRepository.save)

2. Transaction Propagation

Propagation defines the behavior of a transactional method when it is called inside an existing transaction.

a. REQUIRED (default)

Joins the existing transaction if available.

If none exists, a new transaction will be created.

Txn Logger --> New transaction started (propagation=0) (name=WalletService.transfer)
Txn Logger --> Transaction committed (name=WalletService.transfer)

b. REQUIRES_NEW

Always creates a new transaction, even if one already exists.

The existing transaction is suspended until the new one completes.

Txn Logger --> New transaction started (propagation=0) (name=WalletService.transfer)
Txn Logger --> New transaction started (propagation=3) (name=TransactionService.debit)
Txn Logger --> Transaction committed (name=TransactionService.debit)
Txn Logger --> Transaction committed (name=WalletService.transfer)

c. MANDATORY

Requires an existing transaction.

If no transaction exists, an exception will be thrown.

Example:

Transfer failed: No existing transaction found for transaction marked with propagation 'MANDATORY'

org.springframework.transaction.IllegalTransactionStateException:
No existing transaction found for transaction marked with propagation 'MANDATORY'

d. NEVER

Must not run within a transaction.

If a transaction exists, an exception will be thrown.

Example:

Txn Logger --> New transaction started (propagation=0) (name=WalletService.transfer)
Txn Logger --> Transaction rolled back (name=WalletService.transfer)

Transfer failed: Existing transaction found for transaction marked with propagation 'NEVER'

e. SUPPORTS

Joins the existing transaction if one exists.

Runs without a transaction if none is present.

f. NOT_SUPPORTED

Suspends the existing transaction if one exists.

Runs without creating a new transaction.

Summary

REQUIRED → Joins or creates a transaction (default).

REQUIRES_NEW → Always creates a new transaction.

MANDATORY → Must have an existing transaction.

NEVER → Must not have a transaction.

SUPPORTS → Uses transaction if available, else runs without one.

NOT_SUPPORTED → Suspends existing transaction, runs without one.