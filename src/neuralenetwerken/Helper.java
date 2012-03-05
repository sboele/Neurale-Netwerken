/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralenetwerken;

/**
 *
 * @author Sander
 */
public class Helper {
    public static double[] getTargetOutput(int label) {
		double[] targetOutput = new double[10];
		for (int i = 0; i < targetOutput.length; i++) {
			targetOutput[i] = -1.0;
		}
		targetOutput[label] = 1.0;
		return targetOutput;
	}

}
