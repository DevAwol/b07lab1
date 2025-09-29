import java.io.*;
import java.util.*;

public class Driver {
    public static void main(String[] args) {
        double[] coeff1 = {1, 6, 3};
        int[] exp1 = {0, 1, 2};
        Polynomial p1 = new Polynomial(coeff1, exp1);
        
        double[] coeff2 = {5, -8, 3};
        int[] exp2 = {1, 2, 3};
        Polynomial p2 = new Polynomial(coeff2, exp2);
        
        Polynomial result = p1.add(p2);
        System.out.println("p1 + p2 result evaluated at x=2: " + result.evaluate(2));
        
        try {
            p1.saveToFile("polynomial.txt");
            Polynomial fromFile = new Polynomial(new File("polynomial.txt"));
            System.out.println("File works");
        } catch (IOException e) {
            System.out.println("Something fucked up: " + e.getMessage());
        }
    }
}
