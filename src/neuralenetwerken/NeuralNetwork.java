package neuralenetwerken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Sander Boele
 *
 */
public class NeuralNetwork {

    List<Layer> layers = new ArrayList<Layer>();

    public NeuralNetwork(int hiddenLayerSize) {
        //Input layer
        Layer inputLayer = new Layer(null);
        List<Neuron> neuronsInputLayer = new ArrayList<Neuron>();
        for (int i = 0; i < 784; i++) {
            neuronsInputLayer.add(new Neuron());
        }
        inputLayer.setNeurons(neuronsInputLayer);
        layers.add(inputLayer);

        //Hidden layer
        Layer hiddenLayer = new Layer(inputLayer);
        List<Neuron> neuronsHiddenLayer = new ArrayList<Neuron>();
        for (int i = 0; i < hiddenLayerSize; i++) {
            neuronsHiddenLayer.add(new Neuron());
        }

        for (Neuron neuron : neuronsHiddenLayer) {
            for (Neuron neuronPreviousLayer : neuronsInputLayer) {
                neuron.addLink(neuronPreviousLayer, -0.05 + Math.random() * 0.05);
            }
        }
        hiddenLayer.setNeurons(neuronsHiddenLayer);
        layers.add(hiddenLayer);

        //Output layer
        Layer outputLayer = new Layer(hiddenLayer);
        List<Neuron> neuronsOutputLayer = new ArrayList<Neuron>();
        for (int i = 0; i < 10; i++) {
            neuronsOutputLayer.add(new Neuron());
        }

        for (Neuron neuron : neuronsOutputLayer) {
            for (Neuron neuronPreviousLayer : neuronsHiddenLayer) {
                neuron.addLink(neuronPreviousLayer, -0.05 + Math.random() * 0.05);
            }
        }
        outputLayer.setNeurons(neuronsOutputLayer);
        layers.add(outputLayer);
//        layers = new ArrayList<Layer>();
//        Layer layer;
//
//        int i, j;
//
//        // input layer
//        layer = new Layer();
//        layers.add(layer);
//        for (i = 0; i < 784; i++) {
//            layer.neurons.add(new Neuron());
//        }
//
//        // hidden layer
//        layer = new Layer(layer);
//        layers.add(layer);
//        for (i = 0; i < hiddenLayerSize; i++) {
//            layer.neurons.add(new Neuron());
//        }
//
//        // output layer
//        layer = new Layer(layer);
//        layers.add(layer);
//        for (i = 0; i < 10; i++) {
//            layer.neurons.add(new Neuron());
//        }
//
//        // create links
//        Layer layer1 = layers.get(0);
//        Layer layer2 = layers.get(1);
//        Layer layer3 = layers.get(2);
//
//        for (Neuron neuronHidden : layer2.neurons) {
//            for (Neuron neuronInput : layer1.neurons) {
//                neuronHidden.addLink(neuronInput, 0.5 - Math.random());
//            }
//        }
//
//        for (Neuron neuronOutput : layer3.neurons) {
//            for (Neuron neuronHidden : layer2.neurons) {
//                neuronOutput.addLink(neuronHidden, 0.5 - Math.random());
//            }
//        }
    }

    public double[] forwardPropogate(int[] input) {
        Layer layer1 = layers.get(0);
        int counter = 0;

        for (Neuron neuron : layer1.getNeurons()) {
            neuron.setValue((double) input[counter]);
            counter++;
        }

        for (Layer layer : layers.subList(1, layers.size())) {
            layer.forwardPropogate();
        }

        Layer lastLayer = layers.get(layers.size() - 1);
        double[] result = new double[lastLayer.getNeurons().size()];
        counter = 0;
        for (Neuron neuron : lastLayer.getNeurons()) {
            result[counter] = neuron.getValue();
            counter++;
        }

        return result;
    }

    public void backPropogate(double[] output, double[] wantedOutput, double eta) {
        List<Map<Neuron, Double>> layerDerivativesX = new ArrayList<Map<Neuron, Double>>();

        //Calculate derivatives for x, for last layer
        Map<Neuron, Double> derivativesX = new HashMap<Neuron, Double>();
        for (int i = 0; i < output.length; i++) {
            Neuron neuron = layers.get(layers.size() - 1).getNeurons().get(i);
            derivativesX.put(neuron, output[i] - wantedOutput[i]);
        }
        layerDerivativesX.add(derivativesX);

        //Iterate through all layers, except the first, starting from the last. 
        //  - Call backPropagate function for each layer
        //  - The backPropagete function returns the derivativesX for that layer, so add that to the derivatives list.
        for (int i = layers.size() - 1; i > 1; i--) {
            layerDerivativesX.add(0, layers.get(i).backPropogate(layerDerivativesX.get(0), eta));
        }
    }
}
