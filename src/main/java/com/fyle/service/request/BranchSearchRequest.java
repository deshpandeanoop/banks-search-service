package com.fyle.service.request;

public class BranchSearchRequest {
    private String bankName;
    private String city;
    private Integer limit;
    private Integer offset;

    public BranchSearchRequest(String bankName, String city, Integer offset, Integer limit) {
        this.bankName = bankName;
        this.city = city;
        this.limit = limit;
        this.offset = offset;
    }

    public String getBankName() {
        return bankName;
    }

    public String getCity() {
        return city;
    }

    public Integer getLimit() {
        return limit;
    }

    public Integer getOffset() {
        return offset;
    }

    @Override
    public String toString() {
        return "{" +
                "bankName='" + bankName + '\'' +
                ", city='" + city + '\'' +
                ", limit=" + limit +
                ", offset=" + offset +
                '}';
    }
}
