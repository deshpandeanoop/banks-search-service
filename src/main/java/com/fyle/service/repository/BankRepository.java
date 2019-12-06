package com.fyle.service.repository;

import com.fyle.service.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface BankRepository extends JpaRepository<Bank, BigInteger> {

    @Query(value = "Select bank_id as id, bank_name as name From bank_branches t Where t.ifsc=:ifsc", nativeQuery = true)
    Bank findByIFSCCode(@Param("ifsc") String ifsc);
}
