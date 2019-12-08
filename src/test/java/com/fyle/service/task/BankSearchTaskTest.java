package com.fyle.service.task;

import com.fyle.service.request.BranchSearchRequest;
import com.fyle.service.response.BankSearchResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BankSearchTaskTest {
    private IBankSearchTask bankSearchTask;

    @Test
    public void Given_Valid_IdfcCode_FetchBankByIFSCCode_Should_Return_BankDetail(){
        assertNotNull(bankSearchTask.fetchBankByIFSCCode("ABHY0065001").getBank());
    }

    @Test
    public void Given_InValid_IdfcCode_FetchBankByIFSCCode_Should_Return_Null(){
        assertNull(bankSearchTask.fetchBankByIFSCCode("SomeInvalidIDFCCode").getBank());
    }

    @Test
    public void Given_Valid_BankSearchRequest_FetchBranchesByBankNameAndCity_Should_Return_BranchDetails(){
        assertTrue(bankSearchTask.fetchBranchesByBankNameAndCity(new BranchSearchRequest("ABHYUDAYA COOPERATIVE BANK LIMITED", "MUMBAI", 0, 1)).getBranches().size() > 0);
    }

    @Test
    public void Given_InValid_BankSearchRequest_FetchBranchesByBankNameAndCity_Should_Return_Null(){
        assertEquals(0, bankSearchTask.fetchBranchesByBankNameAndCity(new BranchSearchRequest("InvalidBankName", "InvalidCity", 0, 1)).getBranches().size());
    }

    @Autowired
    public void setBankSearchTask(IBankSearchTask bankSearchTask) {
        this.bankSearchTask = bankSearchTask;
    }
}
