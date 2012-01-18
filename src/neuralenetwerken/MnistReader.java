package neuralenetwerken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import mnist.tools.MnistImageFile;
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

    public List<int[][]> getTrainingsImages()
    {
        List<int[][]> result = new ArrayList<int[][]>();
        try
        {
            MnistImageFile images = trainingsData.getImages();
            for (int i = 1; i <= 100; i++)
            {
                images.setCurrentIndex(i);
                int[][] image = images.readImage();
                result.add(image);
            }
        } catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
        
//        for (int i = 0; i < result.get(0).length; i++)
//        {
//            for (int j = 0; j < result.get(0)[i].length; j++)
//            {
//                if (result.get(0)[i][j] == 0)
//                {
//                    System.out.print(" 00" + result.get(0)[i][j]);
//                } else if(result.get(0)[i][j] < 10) {
//                    System.out.print("   " + result.get(0)[i][j]);
//                }
//                else if(result.get(0)[i][j] < 100) {
//                    System.out.print("  " + result.get(0)[i][j]);
//                }
//                else
//                {
//                    System.out.print(" " + result.get(0)[i][j]);
//                }
//            }
//            System.out.println("");
//        }
        
        return result;
    }
}
