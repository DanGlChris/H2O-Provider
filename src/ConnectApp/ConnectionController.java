/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectApp;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ConnectionController implements Initializable {
    private double xoffset, yoffset;
    private Timeline time ;
    private SimpleStringProperty Agent_name = new SimpleStringProperty("Agent 47");
    @FXML
    public Label Message;
    @FXML
    public JFXTextField Username;

    @FXML
    public JFXPasswordField password;
    
    @FXML
    private Label Bar;

    private String id_station;
    @FXML
    public void Connection(ActionEvent event) {
        try {
            if(Data.R.loginModel.isLogin(this.Username.getText(), 
                    this.password.getText())){
                id_station = Data.R.loginModel.get_Id_Station(this.Username.getText(), this.password.getText());
                Data.R.string_properties.get(Data.S.Str_Prop_Current_Id_Station).set(id_station);
                Data.R.Current_Reseau.clear();
                // recover all reseau
                Data.R.Reseaux.forEach(x->{
                    if(x.getId_Station().equals(id_station)){
                        Data.R.Current_Reseau.add(x);
                    }
                });
                Data.R.List_Rapport.clear();
                Data.R.loginModel.getRapport(id_station).forEach((t, u) -> {
                    Data.R.List_Rapport.put(t, u);
                });
                Data.R.string_properties.get(Data.S.Str_Prop_Agent_Name).set(this.Username.getText());
                
                Username.clear();
                password.clear();
                this.Message.setText("");
                Data.M.Change_Stage(Data.R.stages.get(Data.S.Stge_Connexion), Data.R.stages.get(Data.S.Stge_Dashboard), true);
            }else{
                this.Message.setText("Pseudo ou Mot de Passe incorrect\n" +
                                "Réessayez s'il vous plaît!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    @FXML
    public void Return(ActionEvent event){
        Username.clear();
        password.clear();
        Data.M.Change_Stage(Data.R.stages.get(Data.S.Stge_Accueil), Data.R.stages.get(Data.S.Stge_Connexion), false);
    }
    @FXML
    public void Exit(ActionEvent event){
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(Data.R.loginModel.IsDatabaseConnected()){
            System.out.println("Connected To DataBase");
            Data.R.loginModel.Get_Station();
            Data.R.loginModel.Get_Reseaux();
        }else{
            System.out.println("Not Connected to DataBase");
        }
        Bar.setOnMousePressed(e-> {
            xoffset = e.getSceneX();
            yoffset = e.getSceneY();
        });
        Bar.setOnMouseDragged(e->{
            Bar.getScene().getWindow().setX(e.getScreenX()-xoffset);
            Bar.getScene().getWindow().setY(e.getScreenY()-yoffset);
        });
        
        /**
         * Utilities
         */
        SimpleStringProperty Current_Id_Station = new SimpleStringProperty();
        Data.R.string_properties.put(Data.S.Str_Prop_Current_Id_Station, Current_Id_Station);
    }    
    
}
