package com.yjq.shares.model;

import java.util.Date;

public class SharesPrice {
    private Integer id;

    private String code;

    private Double price;

    private Integer buyOrSell;

    private Integer number;

    private Date time;

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getBuyOrSell() {
        return buyOrSell;
    }

    public void setBuyOrSell(Integer buyOrSell) {
        this.buyOrSell = buyOrSell;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}