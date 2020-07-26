package Bean;

import Pack.Reseau;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeMap;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

/**
 *
 * @author DanGlChris
 */
public class LoginModel {
    private Connection connection;
    public LoginModel(){
        try {
            this.connection = dbUtil.dbConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(this.connection == null){
            System.exit(0);
        }
    }
    
    public boolean IsDatabaseConnected(){
        return this.connection!=null;
    }
    public boolean isLogin(String user, String pass)throws SQLException{
        PreparedStatement pr = null;
        ResultSet rs = null;
        
        String sql = "SELECT * FROM Agent_Station WHERE Username = ? and Password = ?";
        try { 
            pr = this.connection.prepareStatement(sql);
            pr.setString(1, user);    
            pr.setString(2, pass);   
            
            rs = pr.executeQuery();
            
            if(rs.next()){
                rs.close();
                pr.close();
                return true;
            }
            rs.close();
            pr.close();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            rs.close();
            pr.close();
            return false;
        }       
        
    }
    public String get_Id_Station(String user, String pass){
        PreparedStatement pr = null;
        ResultSet rs = null;
        
        String sql = "SELECT * FROM Agent_Station WHERE Username = ? and Password = ?";
        try {
            pr = this.connection.prepareStatement(sql);
            pr.setString(1, user);    
            pr.setString(2, pass);   
            
            rs = pr.executeQuery();
            
            if(rs.next()){
                String id_station = rs.getString("ID_Station");
                rs.close();
                pr.close();
                return id_station;
            }
            rs.close();
            pr.close();
            return "Unknown";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Unknown";
        }       
    }
    /**
     * cette methode permet de recuperer l'id de la station
     * dans laquelle l'agent travaille
     * @param user
     * @param pass
     * @return
     * @throws SQLException 
     */
    public void Get_Station(){
        PreparedStatement pr = null;
        ResultSet rs = null;
        
        String sql = "SELECT * FROM Station";
        try { 
            pr = this.connection.prepareStatement(sql);  
            
            rs = pr.executeQuery();
            
            while(rs.next()){
                Station station = new Station(rs.getString("ID_Station"), rs.getString("Nom"), rs.getString("Address"));
                Data.R.stations.put(station.getId_Station(), station);
            }
            rs.close();
            pr.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }     
        
    }
    
    /***
     * Managing Reseau
     */
    public void Get_Reseaux(){
        PreparedStatement pr = null;
        ResultSet rs = null;
        
        String sql = "SELECT * FROM Reseaux";
        try { 
            pr = this.connection.prepareStatement(sql);   
            
            rs = pr.executeQuery();
            
            while(rs.next()){
                Reseau reseau = new Reseau(rs.getString("ID_Station"), rs.getString("Nom_Reseau"));
                System.out.println(reseau.getId_Station() + " " + reseau.getNom());
                Data.R.Reseaux.add(reseau);
            }
            rs.close();
            pr.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }  
    }
    /**
     * cette methode permet d'ajouter un reseau dans une station
     * dans la base de données
     * @param reseau
     * @return 
     */
    public boolean addReseau(Reseau reseau){        
        PreparedStatement pr = null;
        String sql = "INSERT INTO Reseaux(ID_Station, Nom_Reseau) VALUES(?,?)";
        try { 
            pr = this.connection.prepareStatement(sql);
            pr.setString(1, reseau.getId_Station());    
            pr.setString(2, reseau.getNom());     
            
            pr.executeUpdate();
            pr.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } 
    }
    /**
     * cette methode permet d'ajouter un reseau dans une station
     * dans la base de données
     * @param reseau
     * @return 
     */
    public boolean Modify_Reseau(String old_Name, String new_Name){  
        PreparedStatement pr = null;

        String sql = "UPDATE Reseaux SET Nom_Reseau = ? WHERE Nom_Reseau = ?";
        try { 
            pr = this.connection.prepareStatement(sql);    
            pr.setString(1, old_Name);
            pr.setString(2, new_Name);

            pr.executeUpdate();
            pr.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } 
    }
    /**
     * cette methode permet d'elever un reseau dans une station
     * dans la base de données
     * @param reseau
     * @return 
     */
    public boolean Remove_Reseau(Reseau reseau){        
        PreparedStatement pr = null;
        String sql = "DELETE FROM Reseaux WHERE Nom_Reseau = ?";
        try {             
            pr = this.connection.prepareStatement(sql);
            pr.setString(1, reseau.getNom()); 
            pr.executeUpdate();            
            pr.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } 
    }
    
    
    public boolean isCreated(String ID_Station, String Nom, String Prenom, String Postnom, String Password, String Username){
        PreparedStatement pr = null;
        
        String sql = "INSERT INTO Agent_Station(ID_Station, Nom, Prenom, Postnom, Password, Username) VALUES(?,?,?,?,?,?)";
        try { 
            pr = this.connection.prepareStatement(sql);
            pr.setString(1, ID_Station);    
            pr.setString(2, Nom);   
            pr.setString(3, Prenom);   
            pr.setString(4, Postnom);   
            pr.setString(5, Password);   
            pr.setString(6, Username);   
            
            pr.executeUpdate();
            pr.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } 
    }
    public boolean RemoveRapport(Rapport rapport){
        PreparedStatement pr = null;  
        
        String sql = "DELETE FROM Rapport WHERE N_Rapport = ?";
        try{
            
            pr = this.connection.prepareStatement(sql);
            pr.setString(1, rapport.getN_Rapport()); 
            pr.executeUpdate();            
            pr.close();
            
            sql = "DELETE FROM Data WHERE N_Rapport = ?";
            pr = this.connection.prepareStatement(sql);
            pr.setString(1, rapport.getN_Rapport()); 
            pr.executeUpdate();    
            pr.close();
            
            sql = "DELETE FROM Data_Moy WHERE N_Rapport = ?";
            pr = this.connection.prepareStatement(sql);
            pr.setString(1, rapport.getN_Rapport()); 
            pr.executeUpdate();    
            pr.close();
            
            sql = "DELETE FROM Data_Just WHERE N_Rapport = ?";
            pr = this.connection.prepareStatement(sql);
            pr.setString(1, rapport.getN_Rapport());
            pr.executeUpdate();     
            pr.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } 
        
    }
    public boolean RemoveAllRapport(){        
        PreparedStatement pr = null;  
        
        try{
            
            String sql = "DELETE FROM Rapport";
            pr = this.connection.prepareStatement(sql);
            pr.executeUpdate();            
            pr.close();
            
            sql = "DELETE FROM Data ";
            pr = this.connection.prepareStatement(sql);
            pr.executeUpdate();    
            pr.close();
            
            sql = "DELETE FROM Data_Moy";
            pr = this.connection.prepareStatement(sql);
            pr.executeUpdate();    
            pr.close();
            
            sql = "DELETE FROM Data_Just";
            pr = this.connection.prepareStatement(sql);
            pr.executeUpdate();     
            pr.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } 
        
    }
    public boolean AddRapport(Rapport rapport){     
        PreparedStatement pr = null;
        ResultSet rs = null;
        RemoveRapport(rapport);
        
        try{
            String sql = "INSERT INTO Rapport(N_Rapport, N_Rap, Reseau, Date, ID_Station) VALUES(?,?,?,?,?)"; 

            pr = this.connection.prepareStatement(sql);
            pr.setString(1, rapport.getN_Rapport());    
            pr.setInt(2, rapport.getN_Rap());   
            pr.setString(3, rapport.getReseau_Act());   
            pr.setString(4, rapport.getDate());   
            pr.setString(4, rapport.getDate()); 
            pr.setString(5, rapport.getID_Station());      

            pr.executeUpdate();
            /*-------------------*/

            sql = "INSERT INTO Data(N_Rapport, N, Pression_Ar, Pression_D, GMP_1, GMP_2, GMP_3, GMP_4, GMP_5, Date) VALUES(?,?,?,?,?,?,?,?,?,?)"; 

            for(Water_reseau x: rapport.getData()){ 
                pr = this.connection.prepareStatement(sql);
                pr.setString(1, rapport.getN_Rapport()); 
                pr.setInt(2, x.getN());
                pr.setDouble(3, x.getPression_Arrivé());
                pr.setDouble(4, x.getPression_Distr());
                pr.setDouble(5, x.getGMP_1());
                pr.setDouble(6, x.getGMP_2());
                pr.setDouble(7, x.getGMP_3());
                pr.setDouble(8, x.getGMP_4());
                pr.setDouble(9, x.getGMP_5());
                pr.setString(10, x.getHeure());
                pr.executeUpdate();
            }               
            /*-------------------*/
            sql = "INSERT INTO Data_Moy(N_Rapport, Press_Arr_Max, Press_Arr_Min, Press_Cum_Arr, "
                    + "Press_Moy_Arr, Press_Dist_Max, Press_Dist_Min, Press_Cum_Dist, "
                    + "Press_Moy_Dist, Livraison) "
                    + "VALUES(?,?,?,?,?,?,?,?,?,?)"; 

            for(Water_moyenne x: rapport.getData_moyenne()){
                pr = this.connection.prepareStatement(sql);                
                pr.setString(1, rapport.getN_Rapport()); 
                pr.setDouble(2, x.getPress_Ar_Max());
                pr.setDouble(3, x.getPress_Ar_Min());
                pr.setDouble(4, x.getPress_Cum_Ar());
                pr.setDouble(5, x.getPress_Moy_Ar());
                pr.setDouble(6, x.getPress_Dr_Max());
                pr.setDouble(7, x.getPress_Dr_Min());
                pr.setDouble(8, x.getPress_Cum_Dr());
                pr.setDouble(9, x.getPress_Moy_Dr());
                pr.setString(10, x.getLivraison());
                pr.executeUpdate();
                pr.close();
            }               
            /*-------------------*/

            sql = "INSERT INTO Data_Just(N_Rapport, N, GMP, Raisons, Debut, Fin, Total) VALUES(?,?,?,?,?,?,?)"; 

            for(Justification x: rapport.getData_justification()){ 
                pr = this.connection.prepareStatement(sql);               
                pr.setString(1, rapport.getN_Rapport()); 
                pr.setInt(2, x.getN());
                pr.setString(3, x.getGMP());
                pr.setString(4, x.getRaison());
                pr.setString(5, x.getDebut());
                pr.setString(6, x.getFin());
                pr.setString(7, x.getTotal());
                pr.executeUpdate();
                pr.close();
            }    
            pr.close();
            return true;            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        
    }
    
    
    private ObservableMap<String, Rapport> List_Rapport = FXCollections.observableHashMap();
    /**
     * this method allow to get all rapport according this ID station
     * @param Id_statiton
     * @return 
     */
    public ObservableMap<String, Rapport>  getRapport(String Id_statiton){        
        PreparedStatement pr = null, pr_2 = null;
        ResultSet rs = null, rs_2 = null;
        List_Rapport.clear();
        String sql = "SELECT * FROM Rapport WHERE ID_Station = ?";
        try{    
            // Selection des rapport par num de rapport
            pr = this.connection.prepareStatement(sql);  
            pr.setString(1, Id_statiton);
            
            rs = pr.executeQuery();
            while(rs.next()){
                pr_2 = null;
                rs_2 = null;
                
                Rapport rapport = new Rapport(rs.getString(1), Integer.valueOf(rs.getString(2)), rs.getString(3), rs.getString(4), rs.getString(5));
                /**
                 * Pour chaque valeur de N_Rapport, on va recuperer tout les elements
                 * dans data, data_just et data_moy
                 */
                //recuperation des Valeur du reseau
                String sql_2 = "SELECT * FROM Data WHERE N_Rapport = ?";
                
                pr_2 = this.connection.prepareStatement(sql_2);
                pr_2.setString(1, rapport.getN_Rapport());
                
                rs_2 = pr_2.executeQuery();
                
                while(rs_2.next()){
                    Water_reseau water = new Water_reseau();
                    water.N.set(rs_2.getInt("N"));
                    water.Pression_Arrivé.set(rs_2.getDouble("Pression_Ar"));
                    water.Pression_Distr.set(rs_2.getDouble("Pression_D"));
                    water.GMP_1.set(rs_2.getDouble("GMP_1"));
                    water.GMP_2.set(rs_2.getDouble("GMP_2"));
                    water.GMP_3.set(rs_2.getDouble("GMP_3"));
                    water.GMP_4.set(rs_2.getDouble("GMP_4"));
                    water.GMP_5.set(rs_2.getDouble("GMP_5"));
                    water.Heure.set(rs_2.getString("Date"));
                    rapport.getData().add(water);
                }
                pr_2.close();
                rs_2.close();
                
                //recuperation des Moyenne
                sql_2 = "SELECT * FROM Data_Moy WHERE N_Rapport = ?";
                
                pr_2 = this.connection.prepareStatement(sql_2);
                pr_2.setString(1, rapport.getN_Rapport());
                rs_2 = pr_2.executeQuery();
                
                while(rs_2.next()){
                    Water_moyenne water = new Water_moyenne();
                    water.Press_Ar_Max.set(rs_2.getDouble("Press_Arr_Max"));
                    water.Press_Ar_Min.set(rs_2.getDouble("Press_Arr_Min"));
                    water.Press_Cum_Ar.set(rs_2.getDouble("Press_Cum_Arr"));
                    water.Press_Moy_Ar.set(rs_2.getDouble("Press_Moy_Arr"));
                    water.Press_Dr_Max.set(rs_2.getDouble("Press_Dist_Max"));
                    water.Press_Dr_Min.set(rs_2.getDouble("Press_Dist_Min"));
                    water.Press_Cum_Dr.set(rs_2.getDouble("Press_Cum_Dist"));
                    water.Press_Moy_Dr.set(rs_2.getDouble("Press_Moy_Dist"));
                    water.Livraison.set(rs_2.getString("Livraison"));
                    rapport.getData_moyenne().add(water);
                }
                pr_2.close();
                rs_2.close();
                
                //recuperation des justification
                sql_2 = "SELECT * FROM Data_just WHERE N_Rapport = ?";
                
                pr_2 = this.connection.prepareStatement(sql_2);
                pr_2.setString(1, rapport.getN_Rapport());
                rs_2 = pr_2.executeQuery();
                
                while(rs_2.next()){
                    Justification justif = new Justification();
                    justif.N.set(rs_2.getInt("N"));
                    justif.GMP.set(rs_2.getString("GMP"));
                    justif.Raison.set(rs_2.getString("Raisons"));
                    justif.Debut.set(rs_2.getString("Debut"));
                    justif.Fin.set(rs_2.getString("Fin"));
                    justif.Total.set(rs_2.getString("Total"));  
                    rapport.getData_justification().add(justif);
                }
                pr_2.close();
                rs_2.close();
                
                System.out.println("##### " + rapport.getN_Rapport() + " uploaded");
                List_Rapport.put(rapport.getN_Rapport(), rapport);
                
            }
            rs.close();
            pr.close();
            
            
            
        }catch (SQLException e){
            System.out.println("Il y a un probleme");
            e.printStackTrace();   
        }
        return List_Rapport;
    }
    
}
