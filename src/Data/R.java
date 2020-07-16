/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Bean.LoginModel;
import Bean.Rapport;
import Bean.Station;
import Pack.Reseau;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 *
 * @author HP
 */
public interface R {
    LoginModel loginModel = new LoginModel();    
    
    Map<String, Stage> stages = new HashMap<>();
    Map<String, Scene> scenes = new HashMap<>();
    Map<String, Station> stations = new HashMap<>();
    Map<String, SimpleStringProperty> string_properties = new HashMap<>();
    
    ObservableMap<String, Rapport> List_Rapport = FXCollections.observableHashMap();
    
    List<Reseau> Reseaux = new ArrayList<>();
    ObservableList<Reseau> Current_Reseau = FXCollections.observableArrayList();
    
    
}
