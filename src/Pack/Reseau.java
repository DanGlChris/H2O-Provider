/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pack;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author HP
 */
public class Reseau extends AnchorPane{
    private SimpleStringProperty Id_Station = new SimpleStringProperty();
    private SimpleStringProperty nom = new SimpleStringProperty();
    public SimpleBooleanProperty status = new SimpleBooleanProperty();
    private Label label;
    private Circle signe;
    public Reseau(String Id_Station, String nom){
        this.Id_Station.set(Id_Station);
        this.nom.set(nom);
        this.setWidth(100);
        this.setHeight(12);
        
        label = new Label(nom);
        this.getChildren().add(label);
        AnchorPane.setTopAnchor(label, 0.0);
        AnchorPane.setBottomAnchor(label, 0.0);
        AnchorPane.setLeftAnchor(label, 20.0);
        AnchorPane.setRightAnchor(label, 30.0);
        
        signe = new Circle(6, Color.RED);
        signe.setStroke(Color.BLACK);
        signe.setStrokeWidth(1);
        this.getChildren().add(signe);
        AnchorPane.setTopAnchor(signe, 5.0);
        AnchorPane.setBottomAnchor(signe, 5.0);
        AnchorPane.setRightAnchor(signe, 5.0);

    }
    public void Active(){
        status.set(true);
        signe.setFill(Color.LIGHTGREEN);
    }
    public void Desactive(){
        status.set(false);
        signe.setFill(Color.RED);
    }

    public String getId_Station() {
        return Id_Station.get();
    }

    public String getNom() {
        return nom.get();
    }
}
