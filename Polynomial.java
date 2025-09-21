/*
i. It has one field representing the coefficients of the polynomial using an array of
double. A polynomial is assumed to have the form 𝑎 ! + 𝑎" 𝑥 " + ⋯ + 𝑎 #$" 𝑥 #$" .
For example, the polynomial 6 − 2𝑥 + 5𝑥 % would be represented using the
array [6, -2, 0, 5]

ii. It has a no-argument constructor that sets the polynomial to zero (i.e. the
corresponding array would be [0])

iii. It has a constructor that takes an array of double as an argument and sets the
coefficients accordingly

iv. It has a method named add that takes one argument of type Polynomial and
returns the polynomial resulting from adding the calling object and the argument

v. It has a method named evaluate that takes one argument of type double
representing a value of x and evaluates the polynomial accordingly. For example,
if the polynomial is 6 − 2𝑥 + 5𝑥 % and evaluate(-1) is invoked, the result should
be 3.

vi. It has a method named hasRoot that takes one argument of type double and
determines whether this value is a root of the polynomial or not. Note that a root
is a value of x for which the polynomial evaluates to zero.
*/

public class Polynomial{
    //i
    private double[] coefficients;

    //constructors
    //ii
    public Polynomial() {
        coefficients = new double[]{};
    }

    //iii
    public Polynomial(double[] coefficients) {
        this.coefficients = coefficients;
    }

    //iv
    public Polynomial add(Polynomial other) {

        int maxLength = Math.max(this.coefficients.length, other.coefficients.length);
        double[] resultCoefficients = new double[maxLength];
        
        for (int i = 0; i < maxLength; i++) {
            double a = (i < this.coefficients.length) ? this.coefficients[i] : 0;
            double b = (i < other.coefficients.length) ? other.coefficients[i] : 0;
            resultCoefficients[i] = a + b;
        }
        
        return new Polynomial(resultCoefficients);
    }

    //v
    public double evaluate(double x) {
        double count = 0;
        for (int index = 0; index < coefficients.length; index++) {
            count += coefficients[index] * Math.pow(x, index);
        }
        return count;
    }

    //vi
    public boolean hasRoot (double x) {
        return evaluate(x) == 0;
    }


}