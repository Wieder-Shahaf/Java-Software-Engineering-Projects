/**
 * The Negation class represents the negation of a function.
 * It extends the Function class and provides methods to evaluate the negation,
 * generate a string representation of the negation, and calculate its derivative.
 */
public class Negation extends Function {
    private final Function f;

    /**
     * Constructs a Negation object with the specified function.
     *
     * @param function the function to be negated
     */
    public Negation(Function function) {
        this.f = function;
    }

    /**
     * Evaluates the negation of the function at the given input value of x.
     *
     * @param x the input value
     * @return the value of the negation of the function at x
     */
    @Override
    public double valueAt(double x) {
        return -f.valueAt(x);
    }

    /**
     * Calculates and returns the derivative of the negation of the function.
     * The derivative of the negation of a function is equal to the negation of the derivative of the function.
     *
     * @return a new Function representing the derivative of the negation of the function
     */
    @Override
    public Function derivative() {
        return new Negation(f.derivative());
    }

    /**
     * Returns a string representation of the negation of the function.
     * The representation is in the form "(-function)".
     *
     * @return the string representation of the negation of the function
     */
    @Override
    public String toString() {
        return "(-" + f.toString() + ")";
    }
}
