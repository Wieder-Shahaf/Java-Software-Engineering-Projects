/**
 * The Constant class represents a constant function.
 * It extends the Function class and provides methods to evaluate the constant value,
 * generate a string representation of the constant, and calculate its derivative.
 */
public class Constant extends Function{
    private  final double constant;

    /**
     * Constructs a Constant object with the specified constant value.
     *
     * @param constant the constant value
     */
    public  Constant(double constant){
        this.constant = constant;
    }

    /**
     * Evaluates the constant function at any input value of x.
     *
     * @param x the input value (ignored)
     * @return the constant value
     */
    @Override
    public double valueAt (double x){
        return this.constant;
    }

    /**
     * Returns a string representation of the constant value.
     * If the constant value is an integer, it is represented without decimal places.
     *
     * @return the string representation of the constant
     */
    @Override
    public String toString() {
        if (checkInt(this.constant)){
            return "("+(int)this.constant +")";
        }
        else{
            return "("+this.constant +")";
        }
    }

    /**
     * Calculates and returns the derivative of the constant function, which is always zero.
     *
     * @return a new Constant function representing the derivative (always zero)
     */
    @Override
    public Function derivative() {
    return new Constant(0);
    }
    public boolean checkInt (double current_value){
        double absValue = Math.abs(current_value);
        int intValue = (int) absValue;
        if (absValue == intValue) {
            return true;
        }
        else {
            return false;
        }
    }
}
