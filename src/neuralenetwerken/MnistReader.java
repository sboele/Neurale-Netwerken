package neuralenetwerken;

import java.io.IOException;
import mnist.tools.MnistManager;

/**
 *
 * @author Sander Boele
 *
 */
public class MnistReader
{

    MnistManager trainingsData;
    MnistManager testData;

    public MnistReader(String pathTrainingImages, String pathTrainingLabels, String pathTestImages, String pathTestLabels)
    {
        try
        {
            trainingsData = new MnistManager(pathTrainingImages, pathTrainingLabels);
            testData = new MnistManager(pathTestImages, pathTestLabels);
        } catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
    }
}
