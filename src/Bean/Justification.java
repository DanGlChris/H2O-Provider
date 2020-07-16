/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author HP
 */
public class Justification implements Comparable<Justification>{    
    public SimpleIntegerProperty N = new SimpleIntegerProperty();    
    public SimpleStringProperty GMP = new SimpleStringProperty("GMP1");
    public SimpleStringProperty Raison = new SimpleStringProperty("");
    public SimpleStringProperty Debut = new SimpleStringProperty("00h00");
    public SimpleStringProperty Fin = new SimpleStringProperty("00h00");
    public SimpleStringProperty Total = new SimpleStringProperty("00h00");
    private SimpleBooleanProperty actionner = new SimpleBooleanProperty(false);
    private int heure_1, heure_2, heure, minute_1, minute_2;
    private String value_total = "00h00", heure_total, minute_total;
    private String[] hh_mm_1 , hh_mm_2;
    public Justification(){
        Debut.addListener(e->{
            actionner.set(!actionner.get());
        });
        Fin.addListener(e->{
            actionner.set(!actionner.get());
        });
        actionner.addListener((ov, t, t1) -> {
            System.out.println("Je suis passé par les deux chiffre");
            try{
                hh_mm_1 = Debut.get().split("h");
                hh_mm_2 = Fin.get().split("h");
                heure_1 = Integer.valueOf(hh_mm_1[0]);
                heure_2 = Integer.valueOf(hh_mm_2[0]);  
                heure = (heure_2-heure_1);
                if(heure<0) heure+=24;
                
                minute_1 = Integer.valueOf(hh_mm_1[1]);
                minute_2 = Integer.valueOf(hh_mm_2[1]);
                if(minute_2-minute_1>=0){
                    minute_total = String.valueOf(minute_2-minute_1);
                }else{
                    minute_total = String.valueOf((minute_2-minute_1)+60);
                    heure--;
                }           
                heure_total = String.valueOf(heure);
                value_total = (heure_total.length()==1? "0" + heure_total: heure_total)  + "h" + (minute_total.length()==1? "0" + minute_total: minute_total);
                Total.set(value_total);
                System.out.println(value_total);
                System.out.println("Je suis passé par la");
            }catch (NumberFormatException e){                
                Total.set(value_total);
                System.out.println("Je suis passé par la bas");
            }catch (StringIndexOutOfBoundsException e){
                Total.set(value_total);
                System.out.println("Je suis passé par la bas");                
            }
        });
    }
    public Integer getN() {
        return N.get();
    }

    public String getGMP() {
        return GMP.get();
    }

    public String getRaison() {
        return Raison.get();
    }

    public String getDebut() {
        return Debut.get();
    }

    public String getFin() {
        return Fin.get();
    }

    public String getTotal() {
        return Total.get();
    }
    int a, b;
    @Override
    public int compareTo(Justification o) {
        try{
            a = Integer.valueOf(getGMP().charAt(getGMP().length()-1));
            b = Integer.valueOf(o.getGMP().charAt(o.getGMP().length()-1));
            if(a>b) return 1;
            else if(a==b) return 0;
            else return -1;      
            
        }catch (Exception e){
            return -1;
        }
    }
    
}
