package neuralenetwerken;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sander Boele
 *
 */
public class NeuralNetwork
{
    List<Layer> layers;
    
    public NeuralNetwork(int hiddenLayerSize) {
        layers = new ArrayList<Layer>();
        Layer layer;
        
        int i;
        
        // input layer
        layer = new Layer();
        layers.add(layer);
        for(i = 0 ; i < 784 ; i++) {
            layer.neurons.add(new Neuron());
        }
        
        // hidden layer
        layer = new Layer(layer);
        layers.add(layer);
        for(i = 0 ; i < hiddenLayerSize ; i++) {
            layer.neurons.add(new Neuron());
        }
        
        // output layer
        layer = new Layer(layer);
        layers.add(layer);
        for(i = 0 ; i < 10 ; i++) {
            layer.neurons.add(new Neuron());
        }
    }
    
    public void forwardPropogate() {
        
    }
    
    public void backPropogate() {
        
    }
}
