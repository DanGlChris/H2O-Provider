/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

/**
 *
 * @author HP
 */
public interface S {
    //StringPropretie
    String Str_Prop_Agent_Name = "Agent_Name";
    String Str_Prop_Current_Id_Station = "Current_ID_Station";
    
    //scene
    String Scne_Accueil = "Accueil";
    String Scne_Connexion = "Connexion";
    String Scne_Create_Agent = "Scne_Create_Agent";
    String Scne_Dashboard = "Scne_Dashboard";
    
    //stages
    String Stge_Accueil =  "Accueil";
    String Stge_Connexion =  "Connexion";
    String Stge_Create_Agent = "Scne_Create_Agent";
    String Stge_Dashboard = "Stge_Dashboard";
    
    /**
     * Direct Using
     */
    String Str_Message_error_reseau = "Ce Réseau a déja été enreigistré!";
    String Str_Message_new_reseau = "Le Réseau a été correctement enreigistré!";
}
