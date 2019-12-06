package com.fyle.service.task;

import com.fyle.service.repository.BankRepository;
import com.fyle.service.repository.BranchRepository;
import com.fyle.service.request.BranchSearchRequest;
import com.fyle.service.response.BankSearchResponse;
import com.fyle.service.response.ResponseBuilder;
import com.fyle.service.response.BranchSearchResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BankSearchTask implements IBankSearchTask {
    private static final Logger LOGGER = LoggerFactory.getLogger(BankSearchTask.class);
    private final BankRepository bankRepository;
    private final BranchRepository branchRepository;

    public BankSearchTask(BankRepository bankRepository, BranchRepository branchRepository) {
        this.bankRepository = bankRepository;
        this.branchRepository = branchRepository;
    }

    @Override
    public BankSearchResponse fetchBankByIFSCCode(String ifscCode) {
        LOGGER.info("Initiated transaction, fetching bank detail with IFSC code {}", ifscCode);
        return ResponseBuilder.build(bankRepository.findByIFSCCode(ifscCode));
    }

    @Override
    public BranchSearchResponse fetchBranchesByBankNameAndCity(BranchSearchRequest searchRequest) {
        LOGGER.info("Initiated transaction, fetching branches for given input {}", searchRequest);
        return ResponseBuilder.buildBranchResponse(branchRepository.findByBankNameAndCity(searchRequest));
    }
}