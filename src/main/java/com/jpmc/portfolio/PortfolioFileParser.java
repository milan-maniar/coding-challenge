package com.jpmc.portfolio;

import com.jpmc.portfolio.domain.Fund;
import com.jpmc.portfolio.domain.Portfolio;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;

class PortfolioFileParser {

    private static final String DELIMITER = ",";

    static Portfolio parse(Path portfolioInputFilePath) throws IOException {
        Portfolio portfolio = new Portfolio();
        Files.lines(portfolioInputFilePath).forEach(
                line -> {
                    String[] fields = line.trim().split(DELIMITER);
                    if (fields.length != 3) {
                        System.out.println("Ignoring invalid line:" + line);
                    } else {
                        String parentFundName = fields[0];
                        String fundName = fields[1];
                        try {
                            BigDecimal marketValue = new BigDecimal(fields[2]);
                            Fund fund = getOrCreateFund(portfolio, fundName, marketValue);
                            Fund parentFund = getOrCreateFund(portfolio, parentFundName, marketValue);
                            parentFund.getChildFunds().add(fund);
                            fund.getParentFunds().add(parentFund);
                        } catch (NumberFormatException e) {
                            System.out.println("Ignoring invalid line:" + line);
                        }
                    }
                }
        );
        return portfolio;
    }

    private static Fund getOrCreateFund(Portfolio portfolio, String fundName, BigDecimal marketValue) {
        Fund fund = portfolio.getFunds().putIfAbsent(fundName, new Fund(fundName, marketValue));
        if (fund == null) {
            fund = portfolio.getFunds().get(fundName);
        }
        return fund;
    }
}
