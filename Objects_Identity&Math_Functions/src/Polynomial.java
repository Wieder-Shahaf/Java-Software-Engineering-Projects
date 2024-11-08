/**
 * The Polynomial class represents a polynomial function.
 * It extends the Function class and provides methods to evaluate the polynomial, calculate its derivative,
 * and generate a string representation of the polynomial.
 */
public class Polynomial extends Function {
    private final double [] coefficients;

    /**
     * Constructs a polynomial with the given coefficients.
     *
     * @param coefficients the coefficients of the polynomial in ascending order of powers
     */
    public Polynomial(double...coefficients) {
            this.coefficients = coefficients;
        }

    /**
     * Evaluates the polynomial at the specified value of x.
     *
     * @param x the input value
     * @return the value of the polynomial at x
     */
    @Override
    public double valueAt(double x){
        double value = 0;
        for(int i = 0; i<this.coefficients.length; i++){
            value += this.coefficients[i] * Math.pow(x, i);
        }
        return value;
    }

    /**
     * Returns a string representation of the polynomial.
     *
     * @return the string representation of the polynomial
     */
    @Override
    public String toString() {
        String polyString = "(";
        int counter = 0;
        for(int i = 0; i<this.coefficients.length; i++) {
            if(this.coefficients[i]==0){
                counter++;
                continue;
            }
            if (i == 0) {
                if (checkInt(this.coefficients[i])){
                    polyString+=(int)this.coefficients[i];
                }
                else{
                polyString+=this.coefficients[i];
                }
            }
             else {
                     if (this.coefficients[i] > 0 && i!=counter) {
                         polyString += " + ";
                     } else if (this.coefficients[i] < 0) {
                         polyString += " - ";
                     }
                     else {polyString += "";}
                if (this.coefficients[i] != 1.0 && this.coefficients[i] != -1.0) {
                    if (i == 1) {
                        if (checkInt(this.coefficients[i])){
                        polyString += Math.abs((int) this.coefficients[i]) + "x";
                        }
                        else {
                            polyString+=Math.abs(this.coefficients[i])+ "x";
                        }
                    }
                    else {
                        if (checkInt(this.coefficients[i])){
                        polyString += Math.abs((int) this.coefficients[i]) + "x^" + i;
                        }
                        else {
                            polyString+=Math.abs(this.coefficients[i])+"x^" + i;
                        }
                    }
                }
                else {
                    if (i == 1) {
                        polyString += "x";
                    } else {
                        polyString += "x^" + i;
                    }
                }
            }
        }
        if(counter==this.coefficients.length){
            polyString+="0)";
        }
        else {
            polyString += ")";
        }
    return polyString;
    }

    /**
     * Checks if the given value is an integer.
     *
     * @param currentCoefficient the value to check
     * @return true if the value is an integer, false otherwise
     */
    public boolean checkInt (double currentCoefficient){
        double absValue = Math.abs(currentCoefficient);
        int intValue = (int) absValue;
        if (intValue == absValue) {
            return true;
        }
        else {
            return false;
        }
    }


    /**
     * Calculates and returns the derivative of the polynomial.
     *
     * @return the derivative of the polynomial as a new Function instance
     */
    @Override
    public Function derivative() {
        double[] derivativeCoefficients = new double[coefficients.length - 1];
        if(coefficients.length > 1) {
            for (int i = 1; i < coefficients.length; i++) {
                derivativeCoefficients[i - 1] = coefficients[i] * i;
            }
            return new Polynomial(derivativeCoefficients);
        }
        else{
            return new Constant(0);
        }

    }
}