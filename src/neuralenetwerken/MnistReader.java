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
public class MnistReader {

    MnistManager trainingsData;
    MnistManager testData;
    private List<Image> trainingsImages = new ArrayList<Image>();
    private List<Image> testImages = new ArrayList<Image>();

    public MnistReader(String pathTrainingImages, String pathTrainingLabels, String pathTestImages, String pathTestLabels) {
        try {
            trainingsData = new MnistManager(pathTrainingImages, pathTrainingLabels);
            testData = new MnistManager(pathTestImages, pathTestLabels);
            
            for (int i = 1; i < getTrainingLength(); i++) {
                Image image = new Image(getTrainingLabel(i), getTrainingImage(i));
                trainingsImages.add(image);
            }
            
            for (int i = 1; i < getTestLength(); i++) {
                Image image = new Image(getTestLabel(i), getTestImage(i));
                testImages.add(image);
            }
            
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public double[] getTrainingImage(int index) {
        trainingsData.setCurrent(index);
        int[][] image = new int[28][28];
        double[] imageD = new double[784];
        ArrayList<Double> temp = new ArrayList<Double>();

        try {
            image = trainingsData.readImage();
            for (int i = 0; i < image.length; i++) {
                for (int j = 0; j < image[i].length; j++) {
                    temp.add((double) image[i][j]);
                }
            }
            for(int i = 0 ; i < temp.size() ; i++) {
                imageD[i] = (double) temp.get(i) / 128.0;
            }
            return imageD;
        } catch (IOException ioe) {
            ioe.printStackTrace();
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

    public double[] getTestImage(int index) {
        testData.setCurrent(index);
        int[][] image = new int[28][28];
        double[] imageD = new double[784];
        ArrayList<Double> temp = new ArrayList<Double>();
        
        try {
            image = testData.readImage();
            for (int i = 0; i < image.length; i++) {
                for (int j = 0; j < image[i].length; j++) {
                    temp.add((double) image[i][j]);
                }
            }
            for(int i = 0 ; i < temp.size() ; i++) {
                imageD[i] = (double) temp.get(i) / 128.0;
            }
            return imageD;
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return null;
    }

    public int getTestLabel(int index) {
        testData.setCurrent(index);
        int result = 0;
        try {
            result = testData.readLabel();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return result;
    }

    public int getTestLength() {
        return testData.getImages().getCount();
    }

    /**
     * @return the trainingsImages
     */
    public List<Image> getTrainingsImages() {
        return trainingsImages;
    }

    /**
     * @param trainingsImages the trainingsImages to set
     */
    public void setTrainingsImages(List<Image> trainingsImages) {
        this.trainingsImages = trainingsImages;
    }

    /**
     * @return the testImages
     */
    public List<Image> getTestImages() {
        return testImages;
    }

    /**
     * @param testImages the testImages to set
     */
    public void setTestImages(List<Image> testImages) {
        this.testImages = testImages;
    }
}
