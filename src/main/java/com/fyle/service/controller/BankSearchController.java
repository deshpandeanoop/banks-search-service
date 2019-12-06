package com.fyle.service.controller;

import com.fyle.service.request.RequestBuilder;
import com.fyle.service.response.BankSearchResponse;
import com.fyle.service.response.BranchSearchResponse;
import com.fyle.service.task.IBankSearchTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;

@RestController
@RequestMapping("/banks")
public class BankSearchController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BankSearchController.class);
    private final IBankSearchTask bankService;

    public BankSearchController(IBankSearchTask bankService) {
        this.bankService = bankService;
    }

    @GetMapping("/{IfscCode}")
    public BankSearchResponse fetchBankByIFSCCode(@PathVariable("IfscCode") String ifscCode){
        LOGGER.info("Received FetchBankByIFSCCode request with IFSCCode {}", ifscCode);
        return bankService.fetchBankByIFSCCode(ifscCode);
    }

    @GetMapping("/{name}/branches/{city}")
    public BranchSearchResponse fetchBranchesByBankNameAndCity(@PathVariable("name") String bankName
            , @PathVariable("city") String city, @QueryParam("offset") Integer offset, @QueryParam("limit") Integer limit){
        LOGGER.info("Received FetchBranchByBankNameAndCity request with params [bankName, city, offset, limit] = [{}, {}, {}, {}] "
                , bankName, city, offset, limit);
        return bankService.fetchBranchesByBankNameAndCity(RequestBuilder.buildBranchSearchRequest(bankName, city, offset, limit));
    }
}
