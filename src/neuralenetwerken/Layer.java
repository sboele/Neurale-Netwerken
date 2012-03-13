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
        for (int i = 0; i < neurons.size(); i++) {
            Neuron neuron = neurons.get(i);
            double total = 0.0;
            for (Link link : neuron.getLinks()) {
                total += link.getWeight() * link.getNeuron().getValue();
                //System.out.println("New total: "+ total + " -- "+ link.getNeuron().getValue());
            }
            neuron.setValue(HyperbolicTangent.activation(total));
            //System.out.println("Value: "+neuron.getValue());
        }
    }

    public HashMap<Neuron, Double> backPropogate(HashMap<Neuron, Double> derivativesX, double eta) {
        HashMap<Neuron, Double> derivativesY = new HashMap<Neuron, Double>();
        HashMap<Double, Double> derivativesW = new HashMap<Double, Double>();
        HashMap<Neuron, Double> derivativesXPreviousLayer = new HashMap<Neuron, Double>();

        // equation 3
        for (int i = 0; i < neurons.size(); i++) {
            Neuron neuron = neurons.get(i);
            double output = neuron.getValue();
            derivativesY.put(neuron, HyperbolicTangent.derivative(output) * derivativesX.get(neuron));
        }

        // equation 4
        for (int i = 0; i < neurons.size(); i++) {
            Neuron neuron = neurons.get(i);
            for (int j = 0; j < neuron.getLinks().size(); j++) {
                Link link = neuron.getLinks().get(j);
                double output = link.getNeuron().getValue();
                derivativesW.put(link.getWeight(), output * derivativesY.get(neuron));
            }
        }
        
        // equation 5
        for (int i = 0; i < neurons.size(); i++) {
            Neuron neuron = neurons.get(i);
            for (int j = 0; j < neuron.getLinks().size(); j++) {
                Link link = neuron.getLinks().get(j);
                derivativesXPreviousLayer.put(link.getNeuron(), derivativesY.get(neuron) * derivativesW.get(link.getWeight()));
            }
        }

        // update weights
        for (int i = 0; i < neurons.size(); i++) {
            Neuron neuron = neurons.get(i);
            for (int j = 0; j < neuron.getLinks().size(); j++) {
                Link link = neuron.getLinks().get(j);
                double oldValue = link.getWeight();
                double newValue = oldValue - eta * derivativesW.get(link.getWeight());
                link.setWeight(newValue);
            }
        }

        return derivativesXPreviousLayer;
    }
}
