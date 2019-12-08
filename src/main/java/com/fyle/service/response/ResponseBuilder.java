package com.fyle.service.response;

import com.fyle.service.model.Bank;
import com.fyle.service.model.Branch;

import java.util.List;

public final class ResponseBuilder {
    private ResponseBuilder(){

    }

    public static BankSearchResponse build(Bank bank){
        BankSearchResponse response = new BankSearchResponse();
        response.setBank(bank);
        return response;
    }

    public static BranchSearchResponse buildBranchResponse(List<Branch> branches){
        BranchSearchResponse response = new BranchSearchResponse();
        response.setBranches(branches);
        return response;
    }

    public static BaseResponse buildBaseResponse(Integer code, String message){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setErrorCode(code);
        baseResponse.setMessage(message);
        return baseResponse;
    }
}
