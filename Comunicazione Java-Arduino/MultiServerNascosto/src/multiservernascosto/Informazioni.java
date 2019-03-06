package multiservernascosto;

import java.util.Observable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author boschini_federico
 */
public class Informazioni extends Observable {
    String info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
        setChanged();
        notifyObservers();
    }
    
    
    
}
