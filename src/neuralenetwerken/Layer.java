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
public class Layer {

    List<Neuron> neurons = new ArrayList<Neuron>();
    Layer prevLayer;

    public Layer() {
    }

    public Layer(Layer prevLayer) {
        this.prevLayer = prevLayer;
    }

    public List<Neuron> getNeurons() {
        return neurons;
    }

    public Layer getPrevLayer() {
        return prevLayer;
    }
    
    public void setNeurons(List<Neuron> neurons) {
        this.neurons = neurons;
    }

    public void forwardPropogate() {
        if (prevLayer != null) {
            for (Neuron neuron : neurons) {
                double total = 0.0;
                for (Link link : neuron.getLinks()) {
                    total += link.getWeight() * link.getNeuron().getValue();
                    //System.out.println("New total: "+ total + " -- "+ link.getNeuron().getValue());
                }
                neuron.setValue(HyperbolicTangent.activation(total));
                //System.out.println("Value: "+neuron.getValue());
            }
        }
    }

    public Map<Neuron, Double> backPropogate(Map<Neuron, Double> derivativesX, double eta) {
        Map<Neuron, Double> derivativesY = new HashMap<Neuron, Double>();
        Map<Double, Double> derivativesW = new HashMap<Double, Double>();
        Map<Neuron, Double> derivativesXPreviousLayer = new HashMap<Neuron, Double>();

        //Calculate derivatives for y		
        for (Neuron neuron : neurons) {
            double output = neuron.getValue();
            derivativesY.put(neuron, HyperbolicTangent.derivative(output) * derivativesX.get(neuron));
        }

        //Calculate derivatives for w
        for (Neuron neuron : neurons) {
            for (Link link : neuron.getLinks()) {
                double output = link.getNeuron().getValue();
                derivativesW.put(link.getWeight(), output * derivativesY.get(neuron));
            }
        }

        //Calculate derivatives for x, for the previous layer
        for (Neuron neuron : neurons) {
            for (Link link : neuron.getLinks()) {
                derivativesXPreviousLayer.put(link.getNeuron(),
                        derivativesY.get(neuron) * derivativesW.get(link.getWeight()));
            }
        }

        //Update the weights in this layer
        for (Neuron neuron : neurons) {
            for (Link link : neuron.getLinks()) {
                double oldValue = link.getWeight();
                double newValue = oldValue - eta * derivativesW.get(link.getWeight());
                link.setWeight(newValue);
            }
        }

        return derivativesXPreviousLayer;
    }
}
