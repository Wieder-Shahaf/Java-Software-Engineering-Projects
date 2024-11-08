/**
 * The Product class represents a product of two functions.
 * It extends the Function class and provides methods to evaluate the product,
 * generate a string representation of the product, and calculate its derivative.
 */
public class Product extends Function{
    private final Function f1;
    private final Function f2;

    /**
     * Constructs a Product object with the specified functions.
     *
     * @param f1 the first function
     * @param f2 the second function
     */
    public Product(Function f1, Function f2)
    {
    this.f1=f1;
    this.f2=f2;
    }

    /**
     * Evaluates the product of the two functions at the given input value of x.
     *
     * @param x the input value
     * @return the value of the product of the two functions at x
     */
    @Override
    public double valueAt(double x){
        return this.f1.valueAt(x)*this.f2.valueAt(x);
    }

    /**
     * Returns a string representation of the product of the two functions.
     * The representation is in the form "(function1 * function2)".
     *
     * @return the string representation of the product of the two functions
     */

    @Override
    public String toString(){
        return "("+this.f1+" * "+this.f2+")";
    }

    /**
     * Calculates and returns the derivative of the product of the two functions using the product rule.
     * The product rule states that the derivative of a product of two functions (f * g) is given by:
     * (f * g)' = f' * g + f * g'.
     *
     * @return a new Function representing the derivative of the product of the two functions
     */
    @Override
    public Function derivative() {
        // Using the product rule (f*g)' = f'*g + f*g'
        return new Sum(new Product(this.f1.derivative(), f2), new Product(this.f2.derivative(),this.f1));
    }

}
