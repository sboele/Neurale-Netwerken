package neuralenetwerken;

/**
 *
 * @author Sander Boele
 *
 */
public class Link
{
    int neuronIndex;
    double weight;
    
    public Link(int neuronIndex, double weight) {
        this.neuronIndex = neuronIndex;
        this.weight = weight;
    }
}
