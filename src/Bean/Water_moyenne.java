/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author HP
 */
public class Water_moyenne {
    public SimpleDoubleProperty Press_Ar_Max = new SimpleDoubleProperty();
    public SimpleDoubleProperty Press_Ar_Min = new SimpleDoubleProperty();
    public SimpleDoubleProperty Press_Dr_Max = new SimpleDoubleProperty();
    public SimpleDoubleProperty Press_Dr_Min = new SimpleDoubleProperty();
    public SimpleDoubleProperty Press_Moy_Dr = new SimpleDoubleProperty();
    public SimpleDoubleProperty Press_Moy_Ar = new SimpleDoubleProperty();
    public SimpleDoubleProperty Press_Cum_Dr = new SimpleDoubleProperty();
    public SimpleDoubleProperty Press_Cum_Ar = new SimpleDoubleProperty();
    public SimpleStringProperty Livraison = new SimpleStringProperty();

    public Double getPress_Ar_Max() {
        return Press_Ar_Max.get();
    }

    public Double getPress_Ar_Min() {
        return Press_Ar_Min.get();
    }

    public Double getPress_Dr_Max() {
        return Press_Dr_Max.get();
    }

    public Double getPress_Dr_Min() {
        return Press_Dr_Min.get();
    }

    public Double getPress_Moy_Dr() {
        return Press_Moy_Dr.get();
    }
    
    public Double getPress_Moy_Ar() {
        return Press_Moy_Ar.get();
    }
    
    public Double getPress_Cum_Dr() {
        return Press_Cum_Dr.get();
    }
    
    public Double getPress_Cum_Ar() {
        return Press_Cum_Ar.get();
    }

    public String getLivraison() {
        return Livraison.get();
    }
}
