package com.fyle.service.repository;

import com.fyle.service.model.Branch;
import com.fyle.service.request.BranchSearchRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepository extends JpaRepository<Branch, String> {

    @Query(value = "Select ifsc, branch, address, district, city, state From bank_branches Where bank_name=:#{#searchRequest.bankName}" +
            " and city=:#{#searchRequest.city} offset :#{#searchRequest.offset} limit :#{#searchRequest.limit}", nativeQuery = true)
    List<Branch> findByBankNameAndCity(@Param("searchRequest") BranchSearchRequest searchRequest);
}