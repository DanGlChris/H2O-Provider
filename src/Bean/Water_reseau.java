/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author HP
 */
public class Water_reseau {
    public  SimpleIntegerProperty N = new SimpleIntegerProperty();    
    public SimpleDoubleProperty Pression_Arrivé = new SimpleDoubleProperty();
    public SimpleDoubleProperty Pression_Distr = new SimpleDoubleProperty();
    public SimpleDoubleProperty GMP_1 = new SimpleDoubleProperty();
    public SimpleDoubleProperty GMP_2 = new SimpleDoubleProperty();
    public SimpleDoubleProperty GMP_3 = new SimpleDoubleProperty();
    public SimpleDoubleProperty GMP_4 = new SimpleDoubleProperty();
    public SimpleDoubleProperty GMP_5 = new SimpleDoubleProperty();
    public SimpleStringProperty Heure = new SimpleStringProperty();

    public Integer getN() {
        return N.get();
    }

    public Double getPression_Arrivé() {
        return Pression_Arrivé.get();
    }

    public Double getPression_Distr() {
        return Pression_Distr.get();
    }

    public Double getGMP_1() {
        return GMP_1.get();
    }

    public Double getGMP_2() {
        return GMP_2.get();
    }

    public Double getGMP_3() {
        return GMP_3.get();
    }

    public Double getGMP_4() {
        return GMP_4.get();
    }

    public Double getGMP_5() {
        return GMP_5.get();
    }

    public String getHeure() {
        return Heure.get();
    }
    
    
}
