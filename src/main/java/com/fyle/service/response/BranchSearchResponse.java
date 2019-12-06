package com.fyle.service.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fyle.service.model.Branch;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BranchSearchResponse extends BaseResponse{
    private List<Branch> branches;

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }
}
