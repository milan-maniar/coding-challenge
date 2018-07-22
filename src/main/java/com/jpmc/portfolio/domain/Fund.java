package com.jpmc.portfolio.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Fund {

    private String name;
    private BigDecimal marketValue;
    private List<Fund> childFunds = new ArrayList<>();
    private List<Fund> parentFunds = new ArrayList<>();

    public Fund(String name, BigDecimal marketValue) {
        this.name = name;
        this.marketValue = marketValue;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getMarketValue() {
        if(!childFunds.isEmpty()) {
            return childFunds.stream().map(Fund::getMarketValue).reduce(BigDecimal.ZERO, BigDecimal::add);
        }
        return marketValue;
    }

    public List<Fund> getChildFunds() {
        return childFunds;
    }

    public List<Fund> getParentFunds() {
        return parentFunds;
    }
}
