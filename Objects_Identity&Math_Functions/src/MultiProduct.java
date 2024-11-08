/**
 * The MultiProduct class represents a product of multiple functions.
 * It extends the Function class and provides methods to evaluate the product,
 * generate a string representation of the product, and calculate its derivative.
 */
public class MultiProduct extends Function {
    private final Function[] functions;
    private Function f2;

    /**
     * Constructs a MultiProduct object with the specified functions.
     *
     * @param f1 the first function in the product
     * @param f2 the second function in the product
     * @param functions  additional functions to be included in the product
     */
    public MultiProduct(Function f1, Function f2, Function... functions) {
        this.functions=new Function[functions.length+2];
        this.functions[0]=f1;
        this.functions[1]=f2;
        for (int i=2;i<functions.length+2;i++){
            this.functions[i]=functions[i-2];
        }
    }

    /**
     * Helper constructor for the multiProduct derivative method, the constructor gets one of the functions derivative
     * and the rest of the function as they are in the multiProduct expression.
     * @param derivative the derivative of the function
     * @param otherFunctions the other functions in the product
     */
    private MultiProduct(Function derivative, Function[] otherFunctions) {
        Function[] factors = new Function[otherFunctions.length + 1];
        factors[0] = derivative;
        for(int i = 1; i < otherFunctions.length + 1; i++) {
            factors[i] = otherFunctions[i-1];
        }
        this.functions = factors;
    }

    @Override
    public double valueAt(double x) {
        double value = 1; // Initialize to 1 instead of 0
        for (Function function : this.functions) {
            value *= function.valueAt(x);
        }
        return value;
    }

    /**
     * Returns a string representation of the product of the functions.
     * The representation is in the form "(f1 * f2 * ... * fn)".
     *
     * @return the string representation of the product of the functions
     */
    @Override
    public String toString() {
        String multiPstring = "(";
        for (int i = 0; i < this.functions.length - 1; i++) {
            multiPstring +=  this.functions[i].toString() + " * ";
        }
        multiPstring = multiPstring  + this.functions[functions.length - 1].toString() + ")";
        return multiPstring;
    }

    /**
     * Calculates and returns the derivative of the product of the functions.
     *
     * @return a new Function representing the derivative of the product of the functions
     */
    @Override
    public Function derivative() {
        int numOfFunctions = functions.length;
        Function[] derivatives = new Function[numOfFunctions];
        for (int i = 0; i < numOfFunctions; i++) {
            int index = 0;
            Function[] otherFunctions = new Function[numOfFunctions - 1];
            for(int j = 0; j < numOfFunctions; j++) {
                if (i != j) {
                    otherFunctions[index++] = functions[j];
                }
            }
            derivatives[i] = new MultiProduct(functions[i].derivative(),otherFunctions);
        }

        return sumDerivative(derivatives);
    }

    /**
     * Constructs a MultiSum object from the derivatives and functions array.
     *
     * @param derivatives an array of derivative functions
     * @return a new MultiSum object representing the sum of the derivative functions and functions
     */
    public MultiSum sumDerivative(Function[] derivatives) {
        return MultiSum.getMultiSum(derivatives, functions);
    }
}
