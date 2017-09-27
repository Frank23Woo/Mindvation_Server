package com.mdvns.mdvn.pchklst.papi.domain;


import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class CreatePCheckListResponse {

    /*  */
    private PCheckList pCheckList;
    private List<String> remarks;

    public PCheckList getpCheckList() {
        return pCheckList;
    }

    public void setpCheckList(PCheckList pCheckList) {
        this.pCheckList = pCheckList;
    }

    public List<String> getRemarks() {
        return remarks;
    }

    public void setRemarks(List<String> remarks) {
        this.remarks = remarks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CreatePCheckListResponse that = (CreatePCheckListResponse) o;

        if (pCheckList != null ? !pCheckList.equals(that.pCheckList) : that.pCheckList != null) return false;
        return remarks != null ? remarks.equals(that.remarks) : that.remarks == null;
    }

    @Override
    public int hashCode() {
        int result = pCheckList != null ? pCheckList.hashCode() : 0;
        result = 31 * result + (remarks != null ? remarks.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CreatePCheckListResponse{" +
                "pCheckList=" + pCheckList +
                ", remarks=" + remarks +
                '}';
    }
}
