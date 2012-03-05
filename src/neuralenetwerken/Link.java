package neuralenetwerken;

/**
 *
 * @author Sander Boele
 *
 */
public class Link
{
    Neuron neuron;
    double weight;
    
    public Link(Neuron neuron, double weight) {
        this.neuron = neuron;
        this.weight = weight;
    }
    
    public Neuron getNeuron() {
        return neuron;
    }
    
    public double getWeight() {
        return weight;
    }
    
    public void setWeight(double weight) {
        this.weight = weight;
    }
}
