package neuralenetwerken;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sander Boele
 *
 */
public class Neuron
{
    List<Link> links;
    
    public Neuron() {
        links = new ArrayList<Link>();
    }
    
    public void addLink(int neuronIndex, double weight) {
        Link link = new Link(neuronIndex, weight);
        links.add(link);
    }
}
