/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Ese
 */
@ManagedBean(name="dimage", eager=true)
@RequestScoped
public class DisplayImage {
    
    String image="students.png";

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    

    /**
     * Creates a new instance of DisplayImage
     */
    public DisplayImage() {
    }
    
}
