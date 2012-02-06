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

    public void forwardPropogate()
    {
    }

    public void backPropogate()
    {
    }
}
