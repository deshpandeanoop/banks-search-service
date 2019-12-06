package com.fyle.service.request;

public final class RequestBuilder {

    private RequestBuilder(){
    }

    public static BranchSearchRequest buildBranchSearchRequest(String bankName, String city, Integer offset, Integer limit){
        return new BranchSearchRequest(bankName, city, offset, limit);
    }
}
