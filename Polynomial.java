/* 
a. Replace the array representing the coefficients by two arrays: one representing the nonzero coefficients (of type double) and another one representing the corresponding
exponents(of type int). For example, the polynomial 6 − 2𝑥 + 5𝑥! would be represented
using the arrays [6, -2, 5] and [0, 1, 3]

b. Update the existing methods accordingly

c. Add a method named multiply that takes one argument of type Polynomial and returns
the polynomial resulting from multiplying the calling object and the argument. The
resulting polynomial should not contain redundant exponents.

d. Add a constructor that takes one argument of type File and initializes the polynomial
based on the contents of the file. You can assume that the file contains one line with no
whitespaces representing a valid polynomial. 

For example: the line 5-3x2+7x8
corresponds to the polynomial 5 − 3𝑥" + 7𝑥#
Hint: you might want to use the following methods: split of the String class, parseInt of
the Integer class, and parseDouble of the Double class

e. Add a method named saveToFile that takes one argument of type String representing a
file name and saves the polynomial in textual format in the corresponding file (similar to
the format used in part d)

f. You can add any supplementary classes/methods you might find useful 
*/

import java.io.*;
import java.util.*;


public class Polynomial{
    //i
    private double[] coefficients;
    private int[] exponents;

    //constructors
    //ii
    public Polynomial() {
        coefficients = new double[]{};
        exponents = new int[]{};
    }

    //iii
    public Polynomial(double[] coefficients, int[] exponents) {
        this.coefficients = coefficients;
        this.exponents = exponents;
    }

    //iv
    public Polynomial add(Polynomial other) {

        int MaxTerms = this.coefficients.length + other.coefficients.length;
        double[] tempCoefficients = new double[MaxTerms];
        int[] tempExponents = new int[MaxTerms];
        int count = 0;
        
        for (int i = 0; i < this.coefficients.length; i++) {
            tempCoefficients[count] = this.coefficients[i];
            tempExponents[count] = this.exponents[i];
            count++;
        }

        for (int i = 0; i < other.coefficients.length; i++) {
            boolean exponentExists = false;
        
            for (int j = 0; j < count; j++) {
                if (tempExponents[j] == other.exponents[i]) {
                    tempCoefficients[j] += other.coefficients[i];
                    exponentExists = true;
                    break;
                    }
                }
        
            if (!exponentExists) {
                tempCoefficients[count] = other.coefficients[i];
                tempExponents[count] = other.exponents[i];
                count++;
                }   
            }
        
        int nonZeros = 0;
            for (int i = 0; i < count; i++) {
                if (Math.abs(tempCoefficients[i]) > 0) {
                nonZeros++;
            }
        }

        double[] resultCoefficients = new double[nonZeros];
        int[] resultExponents = new int[nonZeros];
        int resultIndex = 0;
    
        for (int i = 0; i < count; i++) {
            if (Math.abs(tempCoefficients[i]) > 0) {
                resultCoefficients[resultIndex] = tempCoefficients[i];
                resultExponents[resultIndex] = tempExponents[i];
                resultIndex++;
            }
        }
        return new Polynomial(resultCoefficients, resultExponents);
    }

    //v
    public double evaluate(double x) {
        double count = 0;
        for (int index = 0; index < coefficients.length; index++) {
            count += coefficients[index] * Math.pow(x, exponents[index]);
        }
        return count;
    }

    //vi
    public boolean hasRoot (double x) {
        return Math.abs(evaluate(x)) < 1e-9;
    }

    //c
    public Polynomial multiply (Polynomial other) {

        Polynomial result = new Polynomial();

        for (int i = 0; i < other.coefficients.length; i++) {
            double[] temp_coeff = new double[this.coefficients.length];
            int[] temp_exp = new int[this.coefficients.length];

            for (int j = 0; j < this.coefficients.length; j++) {
                temp_coeff[j] = other.coefficients[i] * this.coefficients[j];
                temp_exp[j] = other.exponents[i] + this.exponents[j];
            }

            Polynomial temp = new Polynomial(temp_coeff, temp_exp);

            if (i == 0) {result = temp;}

            else {result = result.add(temp);}
        }
        return result;
    }

    //d
    public Polynomial(File file) throws IOException {
        Scanner scanner = new Scanner(file);
        String line = scanner.nextLine();
        scanner.close();
    
        int terms = 1;
        for (int i = 1; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '+' || c == '-') { terms++; }
        }
    
        coefficients = new double[terms];
        exponents = new int[terms];
    
        int currIndex = 0;
        int start = 0;
    
        for (int i = 1; i <= line.length(); i++) {

            if (i == line.length() || (line.charAt(i) == '+' || line.charAt(i) == '-')) {
                String term = line.substring(start, i);
                start = i; 
            
                if (term.contains("x")) {
                    int Pos_x = term.indexOf('x');
                    String coeffPart = term.substring(0, Pos_x);
                    String expPart = term.substring(Pos_x + 1);
                
                    if (coeffPart.equals("") || coeffPart.equals("+")) {coefficients[currIndex] = 1.0;} 
                    
                    else if (coeffPart.equals("-")) {coefficients[currIndex] = -1.0;}

                    else {coefficients[currIndex] = Double.parseDouble(coeffPart);}
                    
                    if (expPart.equals("")) {exponents[currIndex] = 1;}

                    else {exponents[currIndex] = Integer.parseInt(expPart);}
                }

                else {
                    coefficients[currIndex] = Double.parseDouble(term);
                    exponents[currIndex] = 0;
                }
                currIndex++;
           }
        }
    }

    //e
    public void saveToFile(String filename) throws IOException {
        PrintWriter writer = new PrintWriter(filename);
    
        for (int i = 0; i < coefficients.length; i++) {
            double curr_coeff = coefficients[i];
            int curr_exp = exponents[i];
        
            if (i > 0 && curr_coeff > 0) {writer.print("+");}
        
            if (curr_exp == 0) {writer.print(curr_coeff);} 
            else {
                if (curr_coeff == 1.0) {writer.print("x");}

                else if (curr_coeff == -1.0) {writer.print("-x");} 
            
                else {writer.print(curr_coeff + "x");}
            
                if (curr_exp != 1) {writer.print(curr_exp);}
                }
            }
        writer.close();
    }
}

