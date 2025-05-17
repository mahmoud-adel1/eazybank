package com.eazybytes.repository;

import com.eazybytes.model.AccountTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountTransactionRepository extends JpaRepository<AccountTransaction, Long> {

//    List<AccountTransaction> findByCustomerIdOrderByTransactionDtDesc(Long customerID);
    List<AccountTransaction> findByAccountNumber(Long number);
}
