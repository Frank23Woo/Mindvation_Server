package com.mdvns.mdvn.staff.papi.domain;

import java.util.List;

public class LonginRequest {

    private String account;

    private String password;

    private List<String> remarks;

    public LonginRequest() {
        super();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        if (!(o instanceof LonginRequest)) return false;

        LonginRequest that = (LonginRequest) o;

        if (getAccount() != null ? !getAccount().equals(that.getAccount()) : that.getAccount() != null) return false;
        if (getPassword() != null ? !getPassword().equals(that.getPassword()) : that.getPassword() != null)
            return false;
        return getRemarks() != null ? getRemarks().equals(that.getRemarks()) : that.getRemarks() == null;
    }

    @Override
    public int hashCode() {
        int result = getAccount() != null ? getAccount().hashCode() : 0;
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getRemarks() != null ? getRemarks().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LonginRequest{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", remarks=" + remarks +
                '}';
    }
}
