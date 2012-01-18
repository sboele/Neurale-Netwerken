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
        
        MnistReader imageReader = new MnistReader(trainingsDataImages, trainingsDataLabels, testDataImages, testDataLabels);
        
        imageReader.getTrainingsImages();
        
        NeuralNetwork network = new NeuralNetwork();
    }
}
