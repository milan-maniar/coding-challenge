package com.jpmc.portfolio;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;

public class FundWeightCalculatorTest {

    private FundWeightCalculator fundWeightCalculator = new FundWeightCalculator();

    @Test
    public void canCalculateFundWeightForSampleInputInProblemStatement() throws IOException {
        String fundWeights = fundWeightCalculator.calculate(Paths.get("src/test/resources/sampleInput.txt"));
        Assert.assertThat(fundWeights, CoreMatchers.is("A,D,0.167\n" +
                "A,E,0.083\n" +
                "A,F,0.083\n" +
                "A,G,0.333\n" +
                "A,H,0.333\n" +
                "B,D,0.500\n" +
                "B,E,0.250\n" +
                "B,F,0.250\n" +
                "C,G,0.500\n" +
                "C,H,0.500"));
    }

    @Test
    public void canCalculateFundWeightForSampleInputInRandomOrder() throws IOException {
        String fundWeights = fundWeightCalculator.calculate(Paths.get("src/test/resources/sampleInputInRandomOrder.txt"));
        Assert.assertThat(fundWeights, CoreMatchers.is("A,H,0.333\n" +
                "A,G,0.333\n" +
                "A,E,0.083\n" +
                "A,F,0.083\n" +
                "A,D,0.167\n" +
                "B,E,0.250\n" +
                "B,F,0.250\n" +
                "B,D,0.500\n" +
                "C,H,0.500\n" +
                "C,G,0.500"));
    }

    @Test
    public void canCalculateFundWeightForMultipleParentWithSameChild() throws IOException {
        String fundWeights = fundWeightCalculator.calculate(Paths.get("src/test/resources/multipleParentWithSameChild.txt"));
        Assert.assertThat(fundWeights, CoreMatchers.is("A,D,0.167\n" +
                "A,E,0.083\n" +
                "A,F,0.083\n" +
                "A,G,0.333\n" +
                "A,H,0.333\n" +
                "B,D,0.500\n" +
                "B,E,0.250\n" +
                "B,F,0.250\n" +
                "C,G,0.500\n" +
                "C,H,0.500\n" +
                "I,D,0.500\n" +
                "I,E,0.250\n" +
                "I,F,0.250\n" +
                "J,D,0.500\n" +
                "J,E,0.250\n" +
                "J,F,0.250"));
    }
}