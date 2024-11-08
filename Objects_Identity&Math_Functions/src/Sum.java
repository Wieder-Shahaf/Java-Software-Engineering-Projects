/**
 * The Sum class represents the sum of two functions.
 * It extends the Function class and provides methods to evaluate the sum of the functions,
 * generate a string representation of the sum, and calculate its derivative.
 */
public class Sum extends Function {
    private final Function f1;
    private final Function  f2;

    /**
     * Constructs a Sum object with the given functions to be summed.
     *
     * @param f1 the first function
     * @param f2 the second function
     */
    public Sum(Function f1, Function f2) {
        this.f1 = f1;
        this.f2 = f2;
    }


    /**
     * Evaluates the sum of the two functions at the specified value of x.
     *
     * @param x the input value
     * @return the value of the sum of the two functions at x
     */
    @Override
    public double valueAt(double x){
        return (this.f1.valueAt(x)+this.f2.valueAt(x));
    }

    /**
     * Returns a string representation of the sum of the two functions.
     *
     * @return the string representation of the sum
     */
    @Override
    public String toString(){
        return "("+this.f1.toString()+" + "+this.f2.toString()+")";
    }

    /**
     * Calculates and returns the derivative of the sum of the two functions.
     *
     * @return the derivative of the sum as a new Function instance
     */
    @Override
    public Function derivative(){
       return new Sum(this.f1.derivative(),this.f2.derivative());
    }
}
