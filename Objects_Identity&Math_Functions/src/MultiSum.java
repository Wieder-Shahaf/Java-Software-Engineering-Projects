/**
 * The MultiSum class represents a sum of multiple functions.
 * It extends the Function class and provides methods to evaluate the sum,
 * generate a string representation of the sum, and calculate its derivative.
 */
 class MultiSum extends Function {
    private final Function[] functions;

    /**
     * Constructs a MultiSum object with the specified functions.
     *
     * @param f1 the first function in the sum
     * @param f2 the second function in the sum
     * @param functions  additional functions to be included in the sum
     */
    public MultiSum(Function f1, Function f2, Function... functions) {
        this.functions = new Function[functions.length + 2];
        this.functions[0] = f1;
        this.functions[1] = f2;
        for (int i = 2; i < functions.length + 2; i++) {
            this.functions[i] = functions[i - 2];
        }
    }

    /**
     * Evaluates the sum of the functions at the given input value of x.
     *
     * @param x the input value
     * @return the value of the sum of the functions at x
     */
    @Override
    public double valueAt(double x) {
        double value = 0;
        for (int i = 0; i < this.functions.length; i++) {
            value += this.functions[i].valueAt(x);
        }
        return value;
    }

    /**
     * Returns a string representation of the sum of the functions.
     * The representation is in the form "(f1 + f2 + ... + fn)".
     *
     * @return the string representation of the sum of the functions
     */
    @Override
    public String toString() {
        String sumString = "(";
        for (int i = 0; i < this.functions.length - 1; i++) {
            sumString += this.functions[i].toString() + " + ";
        }
        sumString = sumString  + this.functions[functions.length - 1].toString() + ")";
        return sumString;
    }

    /**
     * Calculates and returns the derivative of the sum of the functions.
     *
     * @return a new Function representing the derivative of the sum of the functions
     */
    @Override
    public Function derivative() {
        Function[] derivativeFunctions = new Function[functions.length-2];
        Function f1=functions[0].derivative();
        Function f2=functions[1].derivative();
        for (int i = 0; i < functions.length-2; i++) {
            derivativeFunctions[i] = functions[i+2].derivative();
        }
        return new MultiSum(f1,f2,derivativeFunctions);
    }

    /**
     * Constructs a MultiSum object from the derivatives and functions arrays.
     *
     * @param derivatives an array of derivative functions
     * @param functions   an array of functions
     * @return a new MultiSum object representing the sum of the derivative functions and functions
     */
    static MultiSum getMultiSum(Function[] derivatives, Function[] functions) {
        Function derivativesFirst = derivatives[0];
        Function derivativesSecond = derivatives[1];
        Function[] derivativesOthers = new Function[functions.length - 2];
        for(int i = 0; i < functions.length - 2; i++) {
            derivativesOthers[i] = derivatives[i + 2];
        }
        return new MultiSum(derivativesFirst, derivativesSecond, derivativesOthers);
    }

}