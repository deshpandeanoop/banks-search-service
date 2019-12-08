package com.fyle.service.controller;

import com.fyle.service.request.RequestBuilder;
import com.fyle.service.response.BankSearchResponse;
import com.fyle.service.response.BranchSearchResponse;
import com.fyle.service.task.IBankSearchTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.ws.rs.QueryParam;

@RestController
@RequestMapping("/banks")
@Validated
public class BankSearchController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BankSearchController.class);
    private final IBankSearchTask bankService;

    public BankSearchController(IBankSearchTask bankService) {
        this.bankService = bankService;
    }

    @GetMapping("/{IfscCode}")
    public BankSearchResponse fetchBankByIFSCCode(@NotBlank(message = "IFSC code cannot be blank") @PathVariable("IfscCode") String ifscCode){
        LOGGER.info("Received FetchBankByIFSCCode request with IFSCCode {}", ifscCode);
        return bankService.fetchBankByIFSCCode(ifscCode);
    }

    @GetMapping("/{name}/branches/{city}")
    public BranchSearchResponse fetchBranchesByBankNameAndCity(@NotBlank(message = "Bank name cannot be blank") @PathVariable("name") String bankName
            , @NotBlank(message = "City name cannot be blank") @PathVariable("city") String city
            , @Min(value = 0, message = "OffSet should be positive integer") @QueryParam("offset") Integer offset
            ,@Min(value = 1, message = "Limit should be positive integer with minimum value 1") @QueryParam("limit") Integer limit){
        LOGGER.info("Received FetchBranchByBankNameAndCity request with params [bankName, city, offset, limit] = [{}, {}, {}, {}] "
                , bankName, city, offset, limit);
        return bankService.fetchBranchesByBankNameAndCity(RequestBuilder.buildBranchSearchRequest(bankName, city, offset, limit));
    }
}
