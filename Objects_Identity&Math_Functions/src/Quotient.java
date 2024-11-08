/**
 * The Quotient class represents the quotient of two functions.
 * It extends the Function class and provides methods to evaluate the quotient,
 * generate a string representation of the quotient, and calculate its derivative using the quotient rule.
 */
public class Quotient extends Function{
    private final Function f1;
    private final Function f2;

    /**
     * Constructs a Quotient object with the specified numerator and denominator functions.
     *
     * @param f1 the numerator function
     * @param f2 the denominator function
     */
    public Quotient(Function f1, Function f2) {
        this.f1 = f1;
        this.f2 = f2;
    }

    /**
     * Evaluates the quotient of the two functions at the given input value of x.
     *
     * @param x the input value
     * @return the value of the quotient of the two functions at x
     */
    @Override
    public double valueAt(double x) {
        return (f1.valueAt(x) /f2.valueAt(x));
    }

    /**
     * Calculates and returns the derivative of the quotient of the two functions using the quotient rule.
     * The derivative of the quotient of two functions (f/g)' is given by:
     * (g * f' - f * g') / (g^2)
     *
     * @return a new Function representing the derivative of the quotient of the two functions
     */
    @Override
    public Function derivative() {
        // Using the quotient rule
        // (g(x) * f'(x) - f(x) * g'(x)) / (g(x))^2
        return new Quotient(new Difference(new Product(this.f1.derivative(),f2), new Product(this.f2.derivative(),this.f1 )),
                new Power(this.f2, 2));
    }

    /**
     * Returns a string representation of the quotient of the two functions.
     * The representation is in the form "(f1 / f2)".
     *
     * @return the string representation of the quotient of the two functions
     */
    @Override
    public String toString() {
        return "(" +this.f1.toString() + " / " +this.f2.toString() + ")";

    }
}
