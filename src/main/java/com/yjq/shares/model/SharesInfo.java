package com.yjq.shares.model;

import java.util.Date;

public class SharesInfo {
    private Integer id;

    private String code;

    private String name;

    private Double sharesTotal;

    private Double transactionTotal;

    private Date creationTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Double getSharesTotal() {
        return sharesTotal;
    }

    public void setSharesTotal(Double sharesTotal) {
        this.sharesTotal = sharesTotal;
    }

    public Double getTransactionTotal() {
        return transactionTotal;
    }

    public void setTransactionTotal(Double transactionTotal) {
        this.transactionTotal = transactionTotal;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}