/**
 * The Function class represents a mathematical function.
 * It provides methods to evaluate the function at a given point, calculate the derivative,
 * perform numerical methods such as bisection and Newton-Raphson, and generate Taylor polynomials.
 */
public abstract class Function {

    /**
     * Evaluates the function at the specified value of x.
     *
     * @param x the input value
     * @return the value of the function at x
     */
    public abstract double valueAt(double x);


    /**
     * Returns a string representation of the function.
     *
     * @return the string representation of the function
     */
    public abstract String toString();


    /**
     * Calculates and returns the derivative of the function.
     *
     * @return the derivative of the function as a new Function instance
     */
    public abstract Function derivative();


    /**
     * Performs the bisection method to find the root of the function within the specified interval.
     *
     * @param a       the left endpoint of the interval
     * @param b       the right endpoint of the interval
     * @param epsilon the desired precision of the root approximation
     * @return the approximate value of the root within the given interval
     */
    public double bisectionMethod(double a, double b, double epsilon) {
        double left = a;
        double right = b;
        double mid;
        while (right-left > epsilon) {
            mid = (left + right) / 2;
            if (valueAt(left) * valueAt(mid) > 0) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return (left + right) / 2;
    }


    /**
     * Overloaded version of the bisectionMethod that uses a default epsilon value.
     *
     * @param a the left endpoint of the interval
     * @param b the right endpoint of the interval
     * @return the approximate value of the root within the given interval
     */
    public double bisectionMethod(double a, double b) {
        return bisectionMethod(a, b, Math.pow(10,-5));
    }


    /**
     * Performs the Newton-Raphson method to find the root of the function starting from the specified initial guess.
     *
     * @param a       the initial guess for the root
     * @param epsilon the desired precision of the root approximation
     * @return the approximate value of the root
     */
    public double newtonRaphsonMethod(double a, double epsilon) {
        while (!(Math.abs(this.valueAt(a)) < Math.abs(epsilon))) {
            double functionValue = valueAt(a); // Calculate the value of the function at x0
            double derivativeValue = derivative().valueAt(a); // Calculate the value of the derivative at x0
            a -= (functionValue/derivativeValue); // Calculate the next approximation
        }
        return a;
    }


    /**
     * Overloaded version of the newtonRaphsonMethod that uses a default epsilon value.
     *
     * @param a the initial guess for the root
     * @return the approximate value of the root
     */
    public double newtonRaphsonMethod(double a) {
        return newtonRaphsonMethod(a, Math.pow(10,-5));
    }


    /**
     * Generates a Taylor polynomial of the specified degree for the function.
     *
     * @param n the degree of the Taylor polynomial
     * @return the Taylor polynomial as a new Function instance
     */
    public Function taylorPolynomial(int n) {
            // Array to store coefficients of the Taylor polynomial
            double[] coefficients = new double[n + 1];

            // Initialize the current function as the instance of the function the method is called on
            Function currentFunction = this;

            // Loop through from 0 to n to calculate each coefficient
            for (int i = 0; i <= n; i++) {
                // Calculate the i-th coefficient by evaluating the derivative at x = 0
                // and dividing by i factorial
                double coefficient = currentFunction.valueAt(0) / factorial(i);

                // Store the coefficient in the array
                coefficients[i] = coefficient;

                // Update currentFunction to the derivative for the next iteration
                currentFunction = currentFunction.derivative();
            }

            // Return a new Polynomial function with the calculated coefficients
            return new Polynomial(coefficients);
    }

    /**
     * Helper function to calculate the factorial of an integer.
     *
     * @param n the integer value
     * @return the factorial of n
     */
    public static double factorial(int n) {
        double result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
