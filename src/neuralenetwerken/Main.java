package neuralenetwerken;

/**
 *
 * @author Sander Boele
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

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
        
        //for (int i = 1; i < 25000 ; i++) {
        for (int i = 1; i < imageReader.getTrainingLength(); i++) {
            //int[] image = imageReader.getTrainingImage(i);
            double[] image = imageReader.getTrainingImage(i);
            int label = imageReader.getTrainingLabel(i);

            double[] output = network.forwardPropogate(image);
            network.backPropogate(output, Helper.getTargetOutput(label), eta);
            //System.out.println("Training I: "+i);
        }

        int numberOfRightAnswers = 0;
        for (int i = 1; i <= imageReader.getTestLength(); i++) {
            //int[] image = imageReader.getTestImage(i);
            double[] image = imageReader.getTestImage(i);
            int label = imageReader.getTestLabel(i);
            double[] actualOutput = network.forwardPropogate(image);

            double bestOutput = -100.0;
            int bestIndex = 0;
            for (int j = 0; j < actualOutput.length; j++) {
                if (actualOutput[j] > bestOutput) {
                    bestOutput = actualOutput[j];
                    bestIndex = j;
                }
            }
            //System.out.println("Output: "+ bestIndex + " -- Expected: " +label );
            if (label == bestIndex) {
                numberOfRightAnswers++;
            }
            //System.out.println("Testing I: "+i);
        }
        System.out.println("Right answers: " + numberOfRightAnswers + "/" + imageReader.getTestLength() + " == " + (double) numberOfRightAnswers / (double) imageReader.getTestLength()*100.0 + "%");
    }
}
