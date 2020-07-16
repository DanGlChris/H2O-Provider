/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AgentApp;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class Create_AgentController implements Initializable {
    private double xoffset, yoffset;
    @FXML
    private Label Bar;
    @FXML
    public Label Message;

    @FXML
    public JFXTextField Name;
     
    @FXML
    public JFXPasswordField Password;

    @FXML
    public TextField ID_Station;

    @FXML
    public JFXTextField Username;

    @FXML
    public JFXTextField Prenom;

    @FXML
    public JFXTextField Postnom;

    @FXML
    public JFXPasswordField Password2;

    @FXML
    public void Create(ActionEvent event) {
        if(!isEmpty(ID_Station) && !isEmpty(Name) && !isEmpty(Prenom) && !isEmpty(Postnom) &&
                !isEmpty(Username) && !isEmpty(Password) && !isEmpty(Password2)){
            System.out.println(Password.getText().equals(Password2.getText()));
            if(!Password.getText().equals(Password2.getText())){    //bizarre
                Message.setText("Ecrivez le même Mot de Passe"
                        + "\n sur le deux Champ");
                Password.clear();
                Password2.clear();
                
            }else{
                try {
                    if(Data.R.loginModel.isCreated(ID_Station.getText(), Name.getText(), 
                            Prenom.getText(), Postnom.getText(), Password.getText(), Username.getText())){

                    }else{

                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }else{
            Message.setText("Remplissez tous les Champs");
        }

    }

    @FXML
    public void Exit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    public void Return(ActionEvent event) {
        Data.M.Change_Stage(Data.R.stages.get(Data.S.Stge_Accueil), Data.R.stages.get(Data.S.Stge_Create_Agent), false);        
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
    
    public boolean isEmpty(TextField txfield){
        return txfield.getText().isEmpty();
    }
}
