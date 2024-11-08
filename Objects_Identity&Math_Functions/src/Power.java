/**
 * The Power class represents a power function.
 * It extends the Function class and provides methods to evaluate the function,
 * generate a string representation of the function, and calculate its derivative.
 */
public class Power extends Function{
    private final Function f;
    private final int power;

    /**
     * Constructs a Power object with the specified base function and power.
     *
     * @param f    the base function
     * @param power the power
     */
    public Power(Function f, int power) {
        this.f = f;
        this.power=power;
        }

    /**
     * Evaluates the power function at the given input value of x by raising the base function to the specified power.
     *
     * @param x the input value
     * @return the value of the power function at x
     */
    @Override
    public double valueAt(double x){
        return Math.pow(f.valueAt(x),this.power);
    }

    /**
     * Returns a string representation of the power function.
     * If the power is greater than or equal to 1, it is represented as "(base^power)".
     * If the power is 0, it is represented as just the base function.
     *
     * @return the string representation of the power function
     */
    @Override
    public String toString() {
        if(this.power >=1) {
            return "(" + this.f.toString() + "^" + this.power+")";
        }
        else{
            return "(" + this.f.toString() + ")";
        }
    }

    /**
     * Calculates and returns the derivative of the power function using the power rule.
     * The derivative of a power function is given by the formula: (f^n)' = n * f^(n-1) * f'.
     *
     * @return a new Function representing the derivative of the power function
     */
    @Override
    public Function derivative() {
        // Using the power rule (f^n)' = n * f^(n-1) * f'
        if (power != 0) {
            return new MultiProduct(new Constant(this.power),new Power(this.f, this.power - 1), this.f.derivative());
        } else {
            return new Constant(0);
        }
    }
}
