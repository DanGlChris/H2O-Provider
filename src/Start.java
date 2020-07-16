 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Start extends Application {
    /**
     * cette methode permt de generer le stage initial de 
     * l'application"
     * @param stage
     * @throws IOException -> le chargement des pages fxml pourait generer des 
     * erreur de non localisation des fichier de charger
     * une exception d'input output doit etre gerer
     */
    @Override
    public void start(Stage stage) throws IOException {        
        Parent root = (Parent) FXMLLoader.load(getClass().getResource("/AccueilApp/Accueil.fxml"));
            
        Scene scene = new Scene(root);
        Data.R.scenes.put(Data.S.Scne_Accueil, scene);
        Data.R.stages.put(Data.S.Stge_Accueil, stage);  //on ajoute le stage dans la liste des stages
        stage.setScene(scene);
        stage.setTitle("H20-Provider");
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
        Data.M.RecadrerStage(stage);   // mettre le stage au mileu de l'ecran        
        Load_Ressource();
        
    }

    public static void main(String[] args) {
        launch(args);
    }
    private void Load_Ressource()throws IOException{
        Load_Stage(Data.S.Stge_Connexion, Data.S.Scne_Connexion, "/ConnectApp/Connection.fxml");
        Load_Stage(Data.S.Stge_Create_Agent, Data.S.Scne_Create_Agent, "/AgentApp/Create_Agent.fxml");
        Load_Stage(Data.S.Stge_Dashboard, Data.S.Scne_Dashboard, "/DashboardApp/Dashboard.fxml");
        
    }
    
    /**
     * cette methode permet de charger un fichier fxml, la met dans une scene
     * puis l'assigne a un stage
     * le stage est referé grace au @StageId
     * @param Fxml_Source source du fichier fxml
     * @param SceneId id de la scene
     * @param StageId id du stage
     */
    public void Load_Stage(String StageId, String SceneId, String Fxml_Source) throws IOException{
        Load_Scene(SceneId, Fxml_Source);   // chargerment du fxml dans la Scene de Id SceneID    
        Stage stage = new Stage();
        stage.setTitle("H20-Provider");
        stage.setScene(Data.R.scenes.get(SceneId)); //recuperation de la Scene de Id: SceneId
        stage.initModality(Modality.APPLICATION_MODAL);   //rend les autres fenetres de l'app disable
        stage.initStyle(StageStyle.TRANSPARENT);
        Data.R.stages.put(StageId, stage);  //add this stage in Ressource file with StageId ID
        Data.M.RecadrerStage(stage);   // mettre le stage au mileu de l'ecran
    }
    /**
     * cette methode permet de charger un fichier fxml puis le me dans 
     * une scene enreigistré dans un le Ressource
     * @param SceneId
     * @param Fxml_Source
     * @throws IOException 
     */
    public void Load_Scene(String SceneId, String Fxml_Source)throws IOException{   //cette methode n'est pas dans M car un interface n'a pas la methode getClass
        AnchorPane root = FXMLLoader.load(getClass().getResource(Fxml_Source));
        Scene scene = new Scene(root, root.getPrefWidth(), root.getPrefHeight());
        Data.R.scenes.put(SceneId, scene);
    }

    
}
