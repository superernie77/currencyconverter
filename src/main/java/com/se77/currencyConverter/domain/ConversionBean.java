package com.se77.currencyConverter.domain;

import javax.persistence.*;

@Entity
@Table(name = "results")
public class ConversionBean {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="result_id")
    private int id;

    private String targetCurrency;

    private String sourceCurrency;

    private Double sourceAmount;

    private Double targetAmount;

    public String getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(String targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    public String getSourceCurrency() {
        return sourceCurrency;
    }

    public void setSourceCurrency(String sourceCurrency) {
        this.sourceCurrency = sourceCurrency;
    }

    public Double getSourceAmount() {
        return sourceAmount;
    }

    public void setSourceAmount(Double sourceAmount) {
        this.sourceAmount = sourceAmount;
    }

    public Double getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(Double targetAmount) {
        this.targetAmount = targetAmount;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
