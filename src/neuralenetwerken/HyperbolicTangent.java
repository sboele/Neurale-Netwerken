/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralenetwerken;

/**
 *
 * @author Sander
 */
public class HyperbolicTangent {

	public static double activation(double total) {
		double result = Math.sinh(total)/Math.cosh(total);
                return result;
	}

	public static double derivative(double total) {
		return 1-(total*total);
	}
}
