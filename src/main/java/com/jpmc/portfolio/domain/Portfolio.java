package com.jpmc.portfolio.domain;

import java.util.HashMap;
import java.util.Map;

public class Portfolio {

    private Map<String, Fund> funds = new HashMap<>();

    public Map<String, Fund> getFunds() {
        return funds;
    }
}
