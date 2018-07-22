package com.jpmc.portfolio;

import com.jpmc.portfolio.domain.Fund;
import com.jpmc.portfolio.domain.Portfolio;

import java.io.IOException;
import java.math.RoundingMode;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FundWeightCalculator {

    public static void main(String[] args) {

        if(args.length == 0)
            System.out.println("Usage: mvn exec:java -Dexec.mainClass=com.jpmc.portfolio.FundWeightCalculator -Dexec.args=\"path/to/file\"");

        FundWeightCalculator fundWeightCalculator = new FundWeightCalculator();
        String results;
        try {
            results = fundWeightCalculator.calculate(Paths.get(args[0].trim()));
        } catch (IOException e) {
            results = e.toString();
        }
        System.out.println(results);
    }

    public String calculate(Path portfolioInputFilePath) throws IOException {
        Portfolio portfolio = PortfolioFileParser.parse(portfolioInputFilePath);
        String result = portfolio.getFunds().values().stream()
                .map(fund -> getResult(fund, fund.getChildFunds()))
                .collect(Collectors.joining());
        return result.trim();
    }

    private String getResult(Fund fund, List<Fund> childFunds) {
        StringBuilder result = new StringBuilder();
        for (Fund childFund : childFunds) {
            if (!childFund.getChildFunds().isEmpty()) {
                result.append(getResult(fund, childFund.getChildFunds()));
            } else {
                result.append(fund.getName());
                result.append(",");
                result.append(childFund.getName());
                result.append(",");
                result.append(childFund.getMarketValue().divide(fund.getMarketValue(), 3, RoundingMode.HALF_UP).toString());
                result.append("\n");
            }
        }
        return result.toString();
    }
}
