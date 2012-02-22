package neuralenetwerken;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sander Boele
 *
 */
public class NeuralNetwork {

    List<Layer> layers;

    public NeuralNetwork(int hiddenLayerSize) {
        layers = new ArrayList<Layer>();
        Layer layer;

        int i, j;

        // input layer
        layer = new Layer();
        layers.add(layer);
        for (i = 0; i < 784; i++) {
            layer.neurons.add(new Neuron());
        }

        // hidden layer
        layer = new Layer(layer);
        layers.add(layer);
        for (i = 0; i < hiddenLayerSize; i++) {
            layer.neurons.add(new Neuron());
        }

        // output layer
        layer = new Layer(layer);
        layers.add(layer);
        for (i = 0; i < 10; i++) {
            layer.neurons.add(new Neuron());
        }

        // create links
        Layer layer1 = layers.get(0);
        Layer layer2 = layers.get(1);
        Layer layer3 = layers.get(2);

        for (Neuron neuronInput : layer1.neurons) {
            for (Neuron neuronHidden : layer2.neurons) {
                neuronInput.addLink(neuronHidden, 0.5 - Math.random());
            }
        }

        for (Neuron neuronHidden : layer2.neurons) {
            for (Neuron neuronOutput : layer3.neurons) {
                neuronHidden.addLink(neuronOutput, 0.5 - Math.random());
            }
        }
    }

    public double[] forwardPropogate(int[] input) {
        Layer layer1 = layers.get(0);
        int counter = 0;

        for (Neuron neuron : layer1.getNeurons()) {
            neuron.setValue((double) input[counter]);
            counter++;
        }

        for (int i = 1; i < layers.size(); i++) {
            Layer layer = layers.get(i);
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

    public void backPropogate() {
    }
}
