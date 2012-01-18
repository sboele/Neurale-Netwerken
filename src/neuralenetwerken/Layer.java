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

    List<Neuron> neurons;
    Layer prevLayer;

    public Layer()
    {
        neurons = new ArrayList<Neuron>();
    }

    public void forwardPropogate()
    {
    }

    public void backPropogate()
    {
    }
}
