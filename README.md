# coding-challenge
JPMC coding challenge

This project contains Java application to calculate fund weight within a portfolio. Flattened fund structure and market value is provided as input. The calculator will calculate fund weight for all base (leaf node) funds within the portfolio.

Main Application: com.jpmc.portfolio.FundWeightCalculator
Program Arguments: Input file with full path
Program Results: Fund Weight will be printed on console. If an IO error occurs, error details will be printed as output. 

Note: The program will ignore any invalid lines in the input file and try to process the valid lines if any.

Sample Input:
A,B,1000
A,C,2000
B,D,500
B,E,250
B,F,250
C,G,1000
C,H,1000

Expected Output:
A,E,0.083
A,F,0.083
A,G,0.333
A,H,0.333
B,D,0.500
B,E,0.250
B,F,0.250
C,G,0.500
C,H,0.500
