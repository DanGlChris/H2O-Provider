/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccueilApp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AccueilController implements Initializable {
    private double xoffset, yoffset;
    @FXML
    private Label Bar;
   
    @FXML
    public void Connection(ActionEvent event) {
        Data.M.Change_Stage(Data.R.stages.get(Data.S.Stge_Accueil), Data.R.stages.get(Data.S.Stge_Connexion), true);
    }

    @FXML
    public void Nouvel_Agent(ActionEvent event) {
        Data.M.Change_Stage(Data.R.stages.get(Data.S.Stge_Accueil), Data.R.stages.get(Data.S.Stge_Create_Agent), true);

    }
    @FXML
    public void Exit(ActionEvent event){
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Bar.setOnMousePressed(e-> {
            xoffset = e.getSceneX();
            yoffset = e.getSceneY();
        });
        Bar.setOnMouseDragged(e->{
            Bar.getScene().getWindow().setX(e.getScreenX()-xoffset);
            Bar.getScene().getWindow().setY(e.getScreenY()-yoffset);
        });
    }    
    
}
