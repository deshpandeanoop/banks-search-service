package com.fyle.service.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fyle.service.model.Bank;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BankSearchResponse extends BaseResponse{
    private Bank bank;

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }
}
