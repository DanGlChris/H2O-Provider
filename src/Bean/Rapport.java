/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author HP
 */
public class Rapport {
    public SimpleIntegerProperty N_Rap = new SimpleIntegerProperty();
    public SimpleStringProperty N_Rapport = new SimpleStringProperty();  
    public SimpleStringProperty Reseau_Act = new SimpleStringProperty();
    public SimpleStringProperty Date = new SimpleStringProperty();
    public SimpleStringProperty ID_Station = new SimpleStringProperty();
    private ObservableList<Water_reseau> data = FXCollections.observableArrayList();
    private ObservableList<Water_moyenne> data_moyenne = FXCollections.observableArrayList();
    private ObservableList<Justification> data_justification = FXCollections.observableArrayList();
    public Rapport(String N_Rapport, int N_Rap, String Reseau_Act, String Date, String ID_Station){
        this.N_Rap.set(N_Rap);
        this.N_Rapport.set(N_Rapport);
        this.Reseau_Act.set(Reseau_Act);
        this.Date.set(Date);
        this.ID_Station.set(ID_Station);
    }    
    public Integer getN_Rap(){
        return N_Rap.get();
    }
    public String getN_Rapport(){
        return N_Rapport.get();
    }
    public String getReseau_Act(){
        return Reseau_Act.get();
    }
    public String getDate(){
        return Date.get();
    }
    public String getID_Station(){
        return ID_Station.get();
    }

    public ObservableList<Water_reseau> getData() {
        return data;
    }

    public ObservableList<Water_moyenne> getData_moyenne() {
        return data_moyenne;
    }

    public ObservableList<Justification> getData_justification() {
        return data_justification;
    }
    
}
