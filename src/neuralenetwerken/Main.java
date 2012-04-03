package neuralenetwerken;

import java.util.Collections;
import java.util.List;

/**
 *
 * @author Sander Boele
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //double avgPercentage = 0.0;
        //for (int g = 0; g < 10; g++) {
        String trainingsDataImages = "data/train-images.idx3-ubyte";
        String trainingsDataLabels = "data/train-labels.idx1-ubyte";
        String testDataImages = "data/t10k-images.idx3-ubyte";
        String testDataLabels = "data/t10k-labels.idx1-ubyte";

        double eta = 0.008;
        int sizeOfHiddenLayer = 50;

        MnistReader imageReader = new MnistReader(trainingsDataImages, trainingsDataLabels, testDataImages, testDataLabels);

        //imageReader.getTrainingsImages();
        //imageReader.getTrainingImage(15);

        NeuralNetwork network = new NeuralNetwork(sizeOfHiddenLayer);

        for (int j = 0; j < 10; j++) {
            List<Image> trainingsImages = imageReader.getTrainingsImages();
            Collections.shuffle(trainingsImages);

            for (int i = 0; i < trainingsImages.size(); i++) {
                double[] image = trainingsImages.get(i).getImage();
                int label = trainingsImages.get(i).getLabel();

                double[] output = network.forwardPropogate(image);
                network.backPropogate(output, getValue(label), eta);
                System.out.println("Training I: " + i);
            }
        }
        List<Image> testImages = imageReader.getTestImages();

        int numberOfRightAnswers = 0;
        for (int i = 0; i < testImages.size(); i++) {
            double[] image = testImages.get(i).getImage();
            int label = testImages.get(i).getLabel();
            double[] actualOutput = network.forwardPropogate(image);

            double bestOutput = -100.0;
            int bestIndex = 0;
            for (int j = 0; j < actualOutput.length; j++) {
                if (actualOutput[j] > bestOutput) {
                    bestOutput = actualOutput[j];
                    bestIndex = j;
                }
            }
            if (label == bestIndex) {
                numberOfRightAnswers++;
            }
            else {
                System.out.println("Output: " + bestIndex + " -- Expected: " + label);
            }
            System.out.println("Testing I: " + i);
        }
        //avgPercentage += (double) numberOfRightAnswers / (double) imageReader.getTestLength() * 100.0;
        System.out.println("Right answers: " + numberOfRightAnswers + "/" + testImages.size() + " == " + (double) numberOfRightAnswers / (double) testImages.size() * 100.0 + "%");
    }
    //System.out.println("Avg: " + avgPercentage / 10.0);
    //}

    public static double[] getValue(int label) {
        double[] value = new double[10];
        for (int i = 0; i < value.length; i++) {
            value[i] = -1.0;
        }
        value[label] = 1.0;
        return value;
    }
}
