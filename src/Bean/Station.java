 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Pack.Reseau;
import java.util.HashSet;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author HP
 */
public class Station {    
    private SimpleStringProperty Id_Station = new SimpleStringProperty();
    private SimpleStringProperty Nom = new SimpleStringProperty();
    private SimpleStringProperty Address = new SimpleStringProperty();
    private HashSet<Reseau> Reseaux = new HashSet<>();
    public Station(String Id_Station, String Nom, String Address){
        this.Id_Station.set(Id_Station);
        this.Nom.set(Nom);
        this.Address.set(Address);
    }

    public String getId_Station() {
        return Id_Station.get();
    }

    public String getNom() {
        return Nom.get();
    }

    public String getAddress() {
        return Address.get();
    }

    public HashSet<Reseau> getReseaux() {
        return Reseaux;
    }
    
}
