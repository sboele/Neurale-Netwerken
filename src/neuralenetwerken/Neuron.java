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
    double value;
    
    public Neuron() {
        links = new ArrayList<Link>();
    }
    
    public void addLink(Neuron neuron, double weight) {
        Link link = new Link(neuron, weight);
        links.add(link);
    }
    
    public List<Link> getLinks() {
        return links;
    }
    
    public double getValue() {
        return value;
    }
    
    public void setValue(double value) {
        this.value = value;
    }
}
