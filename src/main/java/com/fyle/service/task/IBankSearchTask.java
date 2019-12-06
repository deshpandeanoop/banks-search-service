package com.fyle.service.task;

import com.fyle.service.request.BranchSearchRequest;
import com.fyle.service.response.BankSearchResponse;
import com.fyle.service.response.BranchSearchResponse;

public interface IBankSearchTask {
    BankSearchResponse fetchBankByIFSCCode(String ifscCode);
    BranchSearchResponse fetchBranchesByBankNameAndCity(BranchSearchRequest searchRequest);
}
