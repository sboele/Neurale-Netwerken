/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralenetwerken;

/**
 *
 * @author Sander
 */
public class Image {
    private int label;
    private double[] image;

    public Image(int label, double[] image) {
        this.label = label;
        this.image = image;
    }
    /**
     * @return the label
     */
    public int getLabel() {
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(int label) {
        this.label = label;
    }

    /**
     * @return the image
     */
    public double[] getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(double[] image) {
        this.image = image;
    }
    
}
