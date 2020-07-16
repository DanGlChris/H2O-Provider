/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DashboardApp;

import Bean.Justification;
import Bean.PDFfile;
import Bean.Rapport;
import Bean.Water_moyenne;
import Bean.Water_reseau;
import Pack.Reseau;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class DashboardController implements Initializable {
    
    private BooleanProperty visibility_of_new_rapport_property = new SimpleBooleanProperty(false);
    private ObservableList<Water_reseau> data;
    private ObservableList<Water_moyenne> data_moyenne;
    private ObservableList<Justification> data_justification, data_justificaion_tempo = FXCollections.observableArrayList();
    private ObservableList<Rapport> data_rapport = FXCollections.observableArrayList();
    private Water_moyenne water_moyenne;
    private Callback<TableColumn<Water_reseau, Double>, TableCell<Water_reseau, Double>> cellFactory ;    
    private double xoffset, yoffset;
    private int Num_rapport;
    private String Num_Rapport_String;
    private String Reseau_actif = "";
    private boolean block_reseau_set = false;
    
    @FXML
    public Label Agent_name;
    
    @FXML
    private JFXListView<Reseau> Paneau_reseaux;
    
    @FXML
    private TableView<Water_reseau> Pression_info_1;
    
    @FXML
    private TableView<Water_moyenne> Pression_moyenne;
    
    @FXML
    private TableView<Justification> Justification;

    @FXML
    private TableColumn<Water_reseau, Integer> N_1;

    @FXML
    private TableColumn<Water_reseau, Double> Pression_Ar;

    @FXML
    private TableColumn<Water_reseau, Double> Pression_Dis;

    @FXML
    private TableColumn<Water_reseau, String> Heure_Pression_1;
    
    @FXML
    private TableColumn<Water_reseau, Double> GMP_1;
    
    @FXML
    private TableColumn<Water_reseau, Double> GMP_2;
    
    @FXML
    private TableColumn<Water_reseau, Double> GMP_3;
    
    @FXML
    private TableColumn<Water_reseau, Double> GMP_4;
    
    @FXML
    private TableColumn<Water_reseau, Double> GMP_5;    
    
    @FXML
    private TableColumn<Water_moyenne, Double> Press_Ar_Max;

    @FXML
    private TableColumn<Water_moyenne, Double> Press_Ar_Min;

    @FXML
    private TableColumn<Water_moyenne, Double> Press_Dr_Max;
    

    @FXML
    private TableColumn<Water_moyenne, Double> Press_Dr_Min;

    @FXML
    private TableColumn<Water_moyenne, Double> Press_Cum_Dr;

    @FXML
    private TableColumn<Water_moyenne, Double> Press_Cum_Ar;
    
    @FXML
    private TableColumn<Water_moyenne, Double> Press_Moy_Dr;

    @FXML
    private TableColumn<Water_moyenne, Double> Press_Moy_Ar;

    @FXML
    private TableColumn<Water_moyenne, String> Livraison;
    
    @FXML
    private TableColumn<Justification, Integer> N_J;

    @FXML
    private TableColumn<Justification, String> GMP;

    @FXML
    private TableColumn<Justification, String> Raison; // problem : ici il faura mettre une list deroulante

    @FXML
    private TableColumn<Justification, String> Debut;

    @FXML
    private TableColumn<Justification, String> Fin;

    @FXML
    private TableColumn<Justification, String> Total;
    
    @FXML
    private TableView<Rapport> table_rapport;   
    
    @FXML
    private TableColumn<Rapport, Integer> N_Rap;

    @FXML
    private TableColumn<Rapport, String> N_Rapport;

    @FXML
    private TableColumn<Rapport, String> Reseau_activité;

    @FXML
    private TableColumn<Rapport, String> Date_Rap;

    @FXML
    private Label Num_dossier;

    @FXML
    private Label date;

    @FXML
    private Label nom_reseau;
    
    @FXML
    private VBox rapport;
    
    @FXML
    private AnchorPane Rapport_page;
    
    @FXML
    private AnchorPane Historique;
    
    @FXML
    private JFXButton btn_add_line;
    
    @FXML
    private JFXButton btn_add_justif;
    
    @FXML
    private JFXButton btn_ouvr_rap;

    @FXML
    private JFXButton btn_sup_rap;
    
    @FXML
    private Label Bar;    
    
    @FXML
    public void Add_new_line(ActionEvent event) {
        Water_reseau item = new Water_reseau();
        item.N.setValue(Pression_info_1.getItems().size()+1); // problem : Value a supprimer
        item.Pression_Arrivé.setValue(0);
        item.Pression_Distr.setValue(0);
        item.GMP_1.setValue(0);
        item.GMP_2.setValue(0);
        item.GMP_3.setValue(0);
        item.GMP_4.setValue(0);
        item.GMP_5.setValue(0);
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss"); 
        item.Heure.setValue(formatter.format(date));
        data.add(item);
    }
    @FXML
    public void Add_new_Justif(ActionEvent event){
        Justification item = new Justification();
        item.N.setValue(Justification.getItems().size()+1);
        data_justification.add(item);
    }
    @FXML
    public void Deconnexion(ActionEvent event) {
        Rapport_page.visibleProperty().set(false);
        Historique.visibleProperty().set(false);
        Data.M.Change_Stage(Data.R.stages.get(Data.S.Stge_Dashboard), Data.R.stages.get(Data.S.Scne_Accueil), true);
    }

    @FXML
    public void Historique(ActionEvent event) {
        Rapport_page.visibleProperty().set(false);
        Historique.visibleProperty().set(true);
    }

    @FXML
    public void Nouveau_rapport(ActionEvent event) {
        if(!Data.R.List_Rapport.values().isEmpty()){
            Num_rapport = Integer.valueOf(Data.R.List_Rapport.get(Data.R.List_Rapport.keySet().toArray()[Data.R.List_Rapport.keySet().size()-1]).getN_Rap())+1;
        }else Num_rapport = 1;
        
        Num_Rapport_String = String.format("O%s%05d", Data.R.Current_Reseau.get(0).getId_Station(), Num_rapport);
        while(Data.R.List_Rapport.keySet().contains(Num_Rapport_String)){
            Num_Rapport_String = String.format("OO%s%05d", Data.R.Current_Reseau.get(0).getId_Station(), Integer.valueOf(Num_Rapport_String.substring(1))+1);            
        }
        Num_dossier.setText("N° " + Num_Rapport_String);
        visibility_of_new_rapport_property.setValue(true);
        date.textProperty().set(new SimpleDateFormat("EEEEEEEE dd MMMMMMMMM yyyy '\t'HH'h'mm ",
            Locale.FRANCE).format(new Date()));
        data.clear();
        data_justification.clear();
    }
    @FXML
    public void Affiche_Rapport(ActionEvent event) {
        Rapport_page.visibleProperty().set(true);
        Historique.visibleProperty().set(false);
        close_rapport(event);
        
    }
    
    @FXML
    public void Enreigistrer(ActionEvent event) {
        //problem: Message de confirmation
        System.out.println("enreigistrement de l'element "+ Num_Rapport_String);
        if(!Data.R.List_Rapport.containsKey(Num_Rapport_String)){
            Rapport rap = new Rapport(Num_Rapport_String, Num_rapport, Reseau_actif, date.getText(), Data.R.Current_Reseau.get(0).getId_Station());
            rap.getData().addAll(data);
            rap.getData_justification().addAll(data_justification);
            rap.getData_moyenne().addAll(data_moyenne);
            data_rapport.add(Num_rapport-1, rap);
            Data.R.List_Rapport.put(Num_Rapport_String, rap);           
            Data.R.loginModel.AddRapport(rap);
        }else{
            date.textProperty().set(new SimpleDateFormat("EEEEEEEE dd MMMMMMMMM yyyy '\t'HH'h'mm ",
                Locale.FRANCE).format(new Date()));
            Data.R.List_Rapport.get(Num_Rapport_String).Date.set(date.getText());
            Data.R.List_Rapport.get(Num_Rapport_String).Reseau_Act.set(Reseau_actif);
            Data.R.List_Rapport.get(Num_Rapport_String).getData().clear();
            Data.R.List_Rapport.get(Num_Rapport_String).getData().addAll(data);
            Data.R.List_Rapport.get(Num_Rapport_String).getData_justification().clear();
            Data.R.List_Rapport.get(Num_Rapport_String).getData_justification().addAll(data_justification);
            Data.R.List_Rapport.get(Num_Rapport_String).getData_moyenne().clear();
            Data.R.List_Rapport.get(Num_Rapport_String).getData_moyenne().addAll(data_moyenne);           
            Data.R.loginModel.AddRapport(Data.R.List_Rapport.get(Num_Rapport_String));
            
        } 
        Rapport_page.visibleProperty().set(false);
        visibility_of_new_rapport_property.setValue(false);
        Historique.visibleProperty().set(true);   
    }
    
    @FXML
    public void Imprimer(ActionEvent event) {
        PDFfile.Create_Rapport_Pdf("",  Data.R.List_Rapport.get(Num_Rapport_String));
    }
    /**
     * cette methode permet de supprimmer un rapport enreigistrer
     * @param event 
     */

    @FXML
    public void Supprimer_rapport(ActionEvent event) {
        //problem: supprimer dans la base de donnée
        Rapport rap = data_rapport.get(table_rapport.getSelectionModel().getSelectedIndex());
        data_rapport.remove(rap);
        Data.R.List_Rapport.remove(rap.getN_Rapport());
        Data.R.loginModel.RemoveAllRapport();
        data_rapport.forEach(x->{
            x.N_Rap.set(data_rapport.indexOf(x)+1);
            Data.R.loginModel.AddRapport(x);
        });
    }
    /**
     * cette methode permet d'ouvrir un rapport enreigistrer
     * @param event 
     */
    @FXML
    public void Ouvrir_rapport(ActionEvent event) {
        Rapport rap = table_rapport.getItems().get(table_rapport.getSelectionModel().getSelectedIndex());
        data.clear(); data.addAll(rap.getData());
        data_justification.clear(); data_justification.addAll(rap.getData_justification());
        data_moyenne.clear(); data_moyenne.addAll(rap.getData_moyenne());
        Num_rapport = data_rapport.indexOf(rap);
        Num_Rapport_String = rap.getN_Rapport();
        Num_dossier.setText("N° " + Num_Rapport_String);
        Reseau_actif = rap.getReseau_Act(); //problem: modifier la valeur du reseau
        date.textProperty().set(rap.getDate());
        nom_reseau.setText(Reseau_actif);        
        visibility_of_new_rapport_property.setValue(true);
        Rapport_page.visibleProperty().set(true);
        Historique.visibleProperty().set(false);  
        
    }
    
    @FXML
    public void Nouveau_reseau(ActionEvent event) {

    }
    @FXML
    public void close_rapport(ActionEvent event){ 
        visibility_of_new_rapport_property.setValue(false);
        data.clear();
        data_justification.clear();
    }

    @FXML
    public void infos(ActionEvent event) {

    }
    @FXML
    public void modifier_reseau(ActionEvent event) {

    }
    @FXML
    public void reglages(ActionEvent event) {

    }
    @FXML
    public void supprimer_reseau(ActionEvent event) {

    }
    @FXML
    public void Exit(ActionEvent event) {
        System.exit(0);

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SimpleStringProperty Agent_Name = new SimpleStringProperty();
        Agent_name.textProperty().bind(Agent_Name);
        Data.R.string_properties.put(Data.S.Str_Prop_Agent_Name, Agent_Name);    //permet de pouvoir identifier le nom de l'agent exterieurement
        
        Rapport_page.visibleProperty().set(true);
        Historique.visibleProperty().set(false);
        Num_dossier.visibleProperty().bind(visibility_of_new_rapport_property);
        date.visibleProperty().bind(visibility_of_new_rapport_property);
        nom_reseau.visibleProperty().bind(visibility_of_new_rapport_property);
        rapport.visibleProperty().bind(visibility_of_new_rapport_property);
        btn_add_line.disableProperty().bind(visibility_of_new_rapport_property.not());
        btn_add_justif.disableProperty().bind(btn_add_line.disabledProperty());
        btn_ouvr_rap.disableProperty().bindBidirectional(btn_sup_rap.disableProperty());
        btn_ouvr_rap.disableProperty().set(true);
        
        /**
         * 
         * cette partie permet de rafraichir la tableview apres avoir changer d'utilisateur
         */
        data_rapport.addAll(Data.R.List_Rapport.values());
        Data.R.List_Rapport.addListener(new  MapChangeListener<String, Rapport>() {
            @Override
            public void onChanged(MapChangeListener.Change<? extends String, ? extends Rapport> change) {
                data_rapport.clear();
                data_rapport.addAll(Data.R.List_Rapport.values());
                
            }
        });
        
        
        table_rapport.getSelectionModel().selectedItemProperty().addListener((o, oldValue, newValue) -> {
            if(newValue != null){
                btn_ouvr_rap.disableProperty().set(false);                
            }else btn_ouvr_rap.disableProperty().set(true);
        });
                
        Paneau_reseaux.setItems(Data.R.Current_Reseau); 
        Paneau_reseaux.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null){
                Paneau_reseaux.getItems().forEach(x-> {
                    x.Desactive();
                });
                newValue.Active();
                Reseau_actif = newValue.getNom();
                nom_reseau.setText(Reseau_actif);
            }
            else{
                Paneau_reseaux.getItems().forEach(x-> {
                    x.Desactive();
                });                
            }
        });
        Bar.setOnMousePressed(e-> {
            xoffset = e.getSceneX();
            yoffset = e.getSceneY();
        });
        Bar.setOnMouseDragged(e->{
            Bar.getScene().getWindow().setX(e.getScreenX()-xoffset);
            Bar.getScene().getWindow().setY(e.getScreenY()-yoffset);
        });
        
        data = FXCollections.observableArrayList();
        Pression_info_1.setItems(data);
        Pression_info_1.setEditable(true);
        cellFactory = new Callback<TableColumn<Water_reseau, Double>, TableCell<Water_reseau, Double>>() {
            public TableCell call(TableColumn<Water_reseau, Double> p) {
                return new EditingCell();
            }
        };
        N_1.setCellValueFactory(new PropertyValueFactory<Water_reseau, Integer>("N"));
        Pression_Ar.setCellValueFactory(new PropertyValueFactory<Water_reseau, Double>("Pression_Arrivé"));
        Pression_Dis.setCellValueFactory(new PropertyValueFactory<Water_reseau, Double>("Pression_Distr"));
        Heure_Pression_1.setCellValueFactory(new PropertyValueFactory<Water_reseau, String>("Heure"));
        GMP_1.setCellValueFactory(new PropertyValueFactory<Water_reseau, Double>("GMP_1"));
        GMP_2.setCellValueFactory(new PropertyValueFactory<Water_reseau, Double>("GMP_2"));
        GMP_3.setCellValueFactory(new PropertyValueFactory<Water_reseau, Double>("GMP_3"));
        GMP_4.setCellValueFactory(new PropertyValueFactory<Water_reseau, Double>("GMP_4"));
        GMP_5.setCellValueFactory(new PropertyValueFactory<Water_reseau, Double>("GMP_5"));
        Edit_Commit(Pression_Ar, 1);
        Edit_Commit(Pression_Dis, 2);
        Edit_Commit(GMP_1, 3);
        Edit_Commit(GMP_2, 4);
        Edit_Commit(GMP_3, 5);
        Edit_Commit(GMP_4, 6);
        Edit_Commit(GMP_5, 7);
        
        /*
        ces lines permetent de faire apparaitre un menu deroulant(context menu) et de normaliser les chiffre
        */
        Pression_info_1.setRowFactory(new Callback<TableView<Water_reseau>, TableRow<Water_reseau>>() {
            @Override
            public TableRow<Water_reseau> call(TableView<Water_reseau> param) {
                final TableRow<Water_reseau> row = new TableRow<>();  
                final ContextMenu contextMenu = new ContextMenu();  
                final MenuItem removeMenuItem = new MenuItem("Supprimer");  
                row.setTextAlignment(TextAlignment.CENTER);
                removeMenuItem.setOnAction(new EventHandler<ActionEvent>() {  
                    @Override  
                    public void handle(ActionEvent event) {  
                        Pression_info_1.getItems().remove(row.getItem());
                        data.forEach(x ->{
                            x.N.set(data.indexOf(x)+1);
                        });
                    }  
                }); 
                contextMenu.getItems().add(removeMenuItem);
                row.contextMenuProperty().bind(  
                        Bindings.when(row.emptyProperty())  
                        .then((ContextMenu)null)  
                        .otherwise(contextMenu)  
                );  
                return row; 
            }
        });        
        
        water_moyenne = new Water_moyenne();
        data_moyenne = FXCollections.observableArrayList();
        Pression_moyenne.setItems(data_moyenne);        
        Press_Ar_Max.setCellValueFactory(new PropertyValueFactory<Water_moyenne, Double>("Press_Ar_Max"));
        Press_Ar_Min.setCellValueFactory(new PropertyValueFactory<Water_moyenne, Double>("Press_Ar_Min"));
        Press_Dr_Max.setCellValueFactory(new PropertyValueFactory<Water_moyenne, Double>("Press_Dr_Max"));
        Press_Dr_Min.setCellValueFactory(new PropertyValueFactory<Water_moyenne, Double>("Press_Dr_Min"));
        Press_Cum_Dr.setCellValueFactory(new PropertyValueFactory<Water_moyenne, Double>("Press_Cum_Dr"));
        Press_Cum_Ar.setCellValueFactory(new PropertyValueFactory<Water_moyenne, Double>("Press_Cum_Ar"));
        Press_Moy_Dr.setCellValueFactory(new PropertyValueFactory<Water_moyenne, Double>("Press_Moy_Dr"));
        Press_Moy_Ar.setCellValueFactory(new PropertyValueFactory<Water_moyenne, Double>("Press_Moy_Ar"));
        Livraison.setCellValueFactory(new PropertyValueFactory<Water_moyenne, String>("Livraison"));
        Pression_moyenne.setEditable(true);
        Livraison.setCellFactory(TextFieldTableCell.<Water_moyenne> forTableColumn());
        Livraison.setOnEditCommit(new EventHandler<CellEditEvent<Water_moyenne, String>>() {
            @Override
            public void handle(CellEditEvent<Water_moyenne, String> t) {
                Double value;
                try{
                    value = Double.valueOf(t.getNewValue());
                }catch (NumberFormatException e){
                    value = 0.0;
                }
                ((Water_moyenne)t.getTableView().getItems().get(t.getTablePosition().getRow())).Livraison.set(String.valueOf(value));
            }
        });
        
        data.addListener((Observable o) -> {
            actualiser_calcule();
        });
        
        data_justification = FXCollections.observableArrayList();
        Justification.setItems(data_justification);
        Justification.setEditable(true);
        N_J.setCellValueFactory(new PropertyValueFactory<Justification, Integer>("N"));
        GMP.setCellValueFactory(new PropertyValueFactory<Justification, String>("GMP"));
        Raison.setCellValueFactory(new PropertyValueFactory<Justification, String>("Raison"));
        Debut.setCellValueFactory(new PropertyValueFactory<Justification, String>("Debut"));
        Fin.setCellValueFactory(new PropertyValueFactory<Justification, String>("Fin"));
        Total.setCellValueFactory(new PropertyValueFactory<Justification, String>("Total"));
        Edit_Commit_Justification(GMP, 1);
        Edit_Commit_Justification(Raison, 2);
        Edit_Commit_Justification(Debut, 3);
        Edit_Commit_Justification(Fin, 4);
        
        Justification.setRowFactory(new Callback<TableView<Justification>, TableRow<Justification>>() {
            @Override
            public TableRow<Justification> call(TableView<Justification> param) {
                final TableRow<Justification> row = new TableRow<>();  
                final ContextMenu contextMenu = new ContextMenu();  
                final MenuItem removeMenuItem = new MenuItem("Supprimer");  
                row.setTextAlignment(TextAlignment.CENTER);
                removeMenuItem.setOnAction(new EventHandler<ActionEvent>() {  
                    @Override  
                    public void handle(ActionEvent event) {  
                        Justification.getItems().remove(row.getItem());
                        data_justification.forEach(x ->{
                            x.N.set(data_justification.indexOf(x)+1);
                        });
                    }  
                }); 
                contextMenu.getItems().add(removeMenuItem);
                row.contextMenuProperty().bind(  
                        Bindings.when(row.emptyProperty())  
                        .then((ContextMenu)null)  
                        .otherwise(contextMenu)  
                );  
                return row; 
            }
        });   
        
        table_rapport.setItems(data_rapport);
        N_Rap.setCellValueFactory(new PropertyValueFactory<Rapport, Integer>("N_Rap"));
        N_Rapport.setCellValueFactory(new PropertyValueFactory<Rapport, String>("N_Rapport"));
        Reseau_activité.setCellValueFactory(new PropertyValueFactory<Rapport, String>("Reseau_Act"));
        Date_Rap.setCellValueFactory(new PropertyValueFactory<Rapport, String>("Date"));
    }
    public void actualiser_calcule(){
        water_moyenne.Press_Ar_Max.set(getPress_Ar_Max());
        water_moyenne.Press_Ar_Min.set(getPress_Ar_Min());
        water_moyenne.Press_Cum_Ar.set(getPress_Cum_Ar());
        water_moyenne.Press_Moy_Ar.set(getPress_Moy_Ar());
        water_moyenne.Press_Dr_Max.set(getPress_Dr_Max());
        water_moyenne.Press_Dr_Min.set(getPress_Dr_Min());
        water_moyenne.Press_Cum_Dr.set(getPress_Cum_Dr());
        water_moyenne.Press_Moy_Dr.set(getPress_Moy_Dr());
        data_moyenne.clear();
        data_moyenne.add(water_moyenne);        
    }
    public void actualiser_justification(){
        data_justificaion_tempo.clear();
        data_justification.forEach(x->{
            data_justificaion_tempo.add(x);
        });
        data_justification.clear();
        data_justificaion_tempo.forEach(x->{
            data_justification.add(x);
        });
        data_justificaion_tempo.clear();
    }
    private double press_ar_max;
    public Double getPress_Ar_Max(){
        press_ar_max = 0;
        data.forEach(x->{
            if(press_ar_max<= x.getPression_Arrivé()){
                press_ar_max = x.getPression_Arrivé();
            }
        });
        return press_ar_max;
    }
    
    private double press_ar_min;
    public Double getPress_Ar_Min(){
        press_ar_min = 100;
        data.forEach(x->{
            if(x.getPression_Arrivé()!=0 && press_ar_min>= x.getPression_Arrivé()){
                press_ar_min = x.getPression_Arrivé();
            }
        });
        return press_ar_min;
    }
    
    private double press_cum_ar;
    public Double getPress_Cum_Ar(){
        press_cum_ar = 0;
        data.forEach(x->{
            press_cum_ar += x.getPression_Arrivé();
        });
        return press_cum_ar;
    }
    private double press_moy_ar = 0;
    public Double getPress_Moy_Ar(){
        press_moy_ar = 0;
        data.forEach(x->{
            press_moy_ar += x.getPression_Arrivé();
        });
        if(!data.isEmpty()) return press_moy_ar/data.size();
        else return 0.0;
    }
    private double press_dr_max;
    public Double getPress_Dr_Max(){
        press_dr_max = 0;
        data.forEach(x->{
            if(press_dr_max<= x.getPression_Distr()){
                press_dr_max = x.getPression_Distr();
            }
        });
        return press_dr_max;
    }    
    
    private double press_dr_min;
    public Double getPress_Dr_Min(){
        press_dr_min  = 100;
        data.forEach(x->{
            if(x.getPression_Distr()!=0 && press_dr_min>= x.getPression_Distr()){
                press_dr_min = x.getPression_Distr();
            }
        });
        return press_dr_min;
    }
    private double press_cum_dr;
    public Double getPress_Cum_Dr(){
        press_cum_dr = 0;
        data.forEach(x->{
            press_cum_dr += x.getPression_Distr();
        });
        return press_cum_dr;
    }
    private double press_moy_dr;
    public Double getPress_Moy_Dr(){
        press_moy_dr = 0;
        data.forEach(x->{
            press_moy_dr += x.getPression_Distr();
        });
        if(!data.isEmpty()) return press_moy_dr/data.size();
        else return 0.0;
    }
    public void Edit_Commit(TableColumn<Water_reseau, Double> column, int i){
        column.setCellFactory(cellFactory);//rendre editable
        column.setOnEditCommit(new EventHandler<CellEditEvent<Water_reseau, Double>>() {
            @Override
            public void handle(CellEditEvent<Water_reseau, Double> t) {                
                try {
                    switch(i){
                        case 1 : ((Water_reseau)t.getTableView().getItems().get(t.getTablePosition().getRow())).Pression_Arrivé.set(t.getNewValue()); break;
                        case 2 : ((Water_reseau)t.getTableView().getItems().get(t.getTablePosition().getRow())).Pression_Distr.set(t.getNewValue()); break;
                        case 3 : ((Water_reseau)t.getTableView().getItems().get(t.getTablePosition().getRow())).GMP_1.set(t.getNewValue()); break;
                        case 4 : ((Water_reseau)t.getTableView().getItems().get(t.getTablePosition().getRow())).GMP_3.set(t.getNewValue()); break;
                        case 6 : ((Water_reseau)t.getTableView().getItems().get(t.getTablePosition().getRow())).GMP_4.set(t.getNewValue()); break;
                        case 7 : ((Water_reseau)t.getTableView().getItems().get(t.getTablePosition().getRow())).GMP_5.set(t.getNewValue()); break;
                        default: break;
                    }
                } catch (NumberFormatException e) {
                    
                }
            }
        });
    }
    public void Edit_Commit_Justification(TableColumn<Justification, String> column, int i){
        column.setCellFactory(TextFieldTableCell.<Justification> forTableColumn());
        column.setOnEditCommit(new EventHandler<CellEditEvent<Justification, String>>() {
            @Override
            public void handle(CellEditEvent<Justification, String> t) {
                switch(i){
                    case 1 : ((Justification)t.getTableView().getItems().get(t.getTablePosition().getRow())).GMP.set(t.getNewValue().toUpperCase()); break;
                    case 2 : ((Justification)t.getTableView().getItems().get(t.getTablePosition().getRow())).Raison.set(t.getNewValue().length()>30? t.getNewValue().substring(0, 30):t.getNewValue()); break;
                    case 3 : ((Justification)t.getTableView().getItems().get(t.getTablePosition().getRow())).Debut.set(format_hour(t.getNewValue().length()>5? t.getNewValue().substring(0, 5):t.getNewValue())); break;
                    case 4 : ((Justification)t.getTableView().getItems().get(t.getTablePosition().getRow())).Fin.set(format_hour(t.getNewValue().length()>5? t.getNewValue().substring(0, 5):t.getNewValue())); break;
                    default: break;                    
                }
                actualiser_justification();
            }
        });
    }
    
    private String[] h_m;
    private String hh, mm;
    private int h, m;
    /**
     * cette methode permet de verifier le format de l'heure
     * entrée dans le textfield et de la normalisée
     * @param value
     * @return 
     */
    public String format_hour(String value){
        try{
            value.toLowerCase();
            h_m = value.split("h");
            if(h_m[0].length()==1){
                h = Integer.valueOf(h_m[0]);
                hh = "0" + String.valueOf(h); 
                System.out.println("1 ok");
            }else{
                h = Integer.valueOf(h_m[0]); 
                if(h<0 || h>23) throw new Exception();
                hh = h> 9? String.valueOf(h): ("0" + String.valueOf(h)) ;
                System.out.println("11 ok");
            }
            if(h_m[1].length()==1){
                m = Integer.valueOf(h_m[1]);
                mm = "0" + String.valueOf(m);
                System.out.println("2 ok"); 
            }else{
                m = Integer.valueOf(h_m[1]); 
                if(m<0 || h>59) throw new Exception();
                mm = m> 9? String.valueOf(m): ("0" + String.valueOf(m)) ;
                System.out.println("21 ok");
            }
            value = hh + "h" + mm;
            
        }catch (Exception e){
            value = "00h00";
        }
        return value;
    }
    
    class EditingCell extends TableCell<Water_reseau, Double>{
        private TextField textField;
        public EditingCell(){
            
        }
        @Override
        public void startEdit(){
            super.startEdit();
         
            if (textField == null) {
                createTextField();
            }

            setGraphic(textField);
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            textField.selectAll();
        }@Override
        public void cancelEdit() {
            super.cancelEdit();

            setText(String.valueOf(getItem()));
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        }
 
        @Override
        public void updateItem(Double item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (textField != null) {
                        textField.setText(getString());
                    }
                    setGraphic(textField);
                    setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                } else {
                    setText(getString());
                    setContentDisplay(ContentDisplay.TEXT_ONLY);
                }
            }
        }

        private void createTextField() {
            textField = new TextField(getString());
            textField.setMinWidth(this.getWidth() - this.getGraphicTextGap()*2);
            textField.setOnKeyPressed(new EventHandler<KeyEvent>() {

                @Override
                public void handle(KeyEvent t) {
                    if (t.getCode() == KeyCode.ENTER) {
                        try {
                          commitEdit(Double.parseDouble(textField.getText()));
                        } catch (NumberFormatException e) {
                          textField.setText("0");
                            }  
                    actualiser_calcule();
                    } else if (t.getCode() == KeyCode.ESCAPE) {
                        cancelEdit();
                    }
                }
            });
        }

        private String getString() {
            return getItem() == null ? "" : getItem().toString();
        }
    }
}
