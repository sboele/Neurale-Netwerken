package neuralenetwerken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mnist.tools.MnistImageFile;
import mnist.tools.MnistManager;

/**
 *
 * @author Sander Boele
 *
 */
public class MnistReader {

    MnistManager trainingsData;
    MnistManager testData;

    public MnistReader(String pathTrainingImages, String pathTrainingLabels, String pathTestImages, String pathTestLabels) {
        try {
            trainingsData = new MnistManager(pathTrainingImages, pathTrainingLabels);
            testData = new MnistManager(pathTestImages, pathTestLabels);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public List<int[][]> getTrainingsImages() {
        List<int[][]> result = new ArrayList<int[][]>();
        try {
            MnistImageFile images = trainingsData.getImages();
            for (int i = 1; i <= 100; i++) {
                images.setCurrentIndex(i);
                int[][] image = images.readImage();
                result.add(image);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return result;
    }

    public int[] getTrainingImage(int index) {
        trainingsData.setCurrent(index);
        int[][] image = new int[28][28];
        int[] newImage = new int[784];

        try {
            image = trainingsData.readImage();
            for (int i = 0; i < image.length; i++) {
                for (int j = 0; j < image[i].length; j++) {
                    newImage[j + i * image.length] = image[i][j];
                }
            }
            return newImage;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getTrainingLabel(int index) {
        trainingsData.setCurrent(index);
        int result = 0;
        try {
            result = trainingsData.readLabel();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public int getTrainingLength() {
        return trainingsData.getImages().getCount();
    }

    public int[] getTestImage(int index) {
        testData.setCurrent(index);
        int[][] image = new int[28][28];
        int[] newImage = new int[784];

        try {
            image = testData.readImage();
            for (int i = 0; i < image.length; i++) {
                for (int j = 0; j < image[i].length; j++) {
                    newImage[j + i * image.length] = image[i][j];
                }
            }
            return newImage;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getTestLabel(int index) {
        testData.setCurrent(index);
        int result = 0;
        try {
            result = testData.readLabel();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public int getTestLength() {
        return testData.getImages().getCount();
    }
}
