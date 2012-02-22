package neuralenetwerken;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sander Boele
 *
 */
public class Layer
{

    List<Neuron> neurons = new ArrayList<Neuron>();;
    Layer prevLayer;

    public Layer() {
    }
    
    public Layer(Layer prevLayer)
    {
        this.prevLayer = prevLayer;
    }

    public List<Neuron> getNeurons() {
        return neurons;
    }
    
    public Layer getPrevLayer() {
        return prevLayer;
    }
    
    public void forwardPropogate()
    {
        for (Neuron neuron : neurons) {
            double total = 0.0;
            for( Link link : neuron.getLinks()) {
                total += link.getWeight() * link.getNeuron().getValue();
            }
            neuron.setValue(HyperbolicTangent.activation(total));
        }
    }

    public void backPropogate()
    {
    }
}
