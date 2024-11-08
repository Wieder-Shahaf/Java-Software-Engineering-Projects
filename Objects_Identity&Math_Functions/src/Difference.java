/**
 * The Difference class represents the difference between two functions.
 * It extends the Function class and provides methods to evaluate the difference of the functions,
 * generate a string representation of the difference, and calculate its derivative.
 */
public class Difference extends Function {
    private final Function f1;
    private final Function f2;

    /**
     * Constructs a Difference object with the given functions to be subtracted.
     *
     * @param f1 the first function
     * @param f2 the second function
     */
    public Difference(Function f1, Function f2) {
        this.f1 =f1;
        this.f2 =f2;
    }

    /**
     * Evaluates the difference between the two functions at the specified value of x.
     *
     * @param x the input value
     * @return the value of the difference between the two functions at x
     */
    @Override
    public double valueAt(double x){
       return (this.f1.valueAt(x)-this.f2.valueAt(x));
    }

    /**
     * Returns a string representation of the difference between the two functions.
     *
     * @return the string representation of the difference
     */
    @Override
    public String toString(){
        return "("+this.f1.toString()+" - "+this.f2.toString()+")";
    }

    /**
     * Calculates and returns the derivative of the difference between the two functions.
     *
     * @return the derivative of the difference as a new Function instance
     */
    @Override
    public Function derivative(){
       return new Difference(this.f1.derivative(),this.f2.derivative());
    }
}

