/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Utilities;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author HP
 */
public class PDFfile {
    private static Document document;
    private static Font[] fonts = {
                new Font(Font.FontFamily.UNDEFINED, 10, Font.NORMAL),
                new Font(Font.FontFamily.UNDEFINED, 11, Font.BOLD),
                new Font(Font.FontFamily.UNDEFINED, 8, Font.NORMAL)};
    private static PdfPTable data_table, Justif_table;
    public static boolean Create_Rapport_Pdf(String path, Rapport rapport){
        document = new Document();
        document.setPageSize(PageSize.A4);
        try{
            PdfWriter.getInstance(document, new FileOutputStream("src/Rapports/" + rapport.N_Rapport.get() + ".pdf"));
            
            document.open();
            Image img = Image.getInstance("src/Images/Regideso.jpg");
            img.scaleToFit(Utilities.millimetersToPoints(50), Utilities.millimetersToPoints(50));
            img.setAbsolutePosition(0, 0);            
            document.add(img);
            
            
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(81.5f);
            table.setWidths(new int[]{1, 2});
            Paragraph p = new Paragraph("Rapport - H20Provider ", new Font(Font.FontFamily.UNDEFINED, 14, Font.BOLD));  
            PdfPCell cell1 = new PdfPCell(p);
            cell1.setBorder(Rectangle.NO_BORDER);
            cell1.setPaddingLeft(-48);
            table.addCell(cell1);
            
                
            Image img_water = Image.getInstance("src/Images/Water.png");
            img_water.scaleToFit(Utilities.millimetersToPoints(7), Utilities.millimetersToPoints(7));       
            PdfPCell cell2 = new PdfPCell(img_water, false);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setPaddingLeft(-40);
            table.addCell(cell2);
            document.add(table);
            
            Paragraph N_Rapport = new Paragraph(String.format("N°%s  Date : %s", rapport.getN_Rapport(), rapport.getDate()), fonts[0]);
            LineSeparator ls = new LineSeparator();
            ls.setAlignment(Element.ALIGN_TOP);
            N_Rapport.add(Chunk.NEWLINE);
            N_Rapport.add(ls);
            N_Rapport.add(Chunk.NEWLINE);
            N_Rapport.add(Chunk.NEWLINE);
            N_Rapport.add(Chunk.NEWLINE);
            N_Rapport.add(new Paragraph("Réception et distribution", fonts[1]));
            N_Rapport.add(Chunk.NEWLINE);            
            
            document.add(N_Rapport);
            
            
            float[] columnWidths = {0.08f, 0.18f, 0.18f, 0.12f, 0.12f, 0.12f, 0.12f, 0.12f, 0.2f};
            data_table = new PdfPTable(columnWidths);
            data_table.setWidthPercentage(100f);
            data_table.setHorizontalAlignment(Element.ALIGN_LEFT);
            insertCell(data_table, "N°", Element.ALIGN_CENTER, 1, fonts[2]);
            insertCell(data_table, "Press. Arr.", Element.ALIGN_CENTER, 1, fonts[2]);
            insertCell(data_table, "Press. Drr.", Element.ALIGN_CENTER, 1, fonts[2]);
            insertCell(data_table, "GMP1", Element.ALIGN_CENTER, 1, fonts[2]);
            insertCell(data_table, "GMP2", Element.ALIGN_CENTER, 1, fonts[2]);
            insertCell(data_table, "GMP3", Element.ALIGN_CENTER, 1, fonts[2]);
            insertCell(data_table, "GMP4", Element.ALIGN_CENTER, 1, fonts[2]);
            insertCell(data_table, "GMP5", Element.ALIGN_CENTER, 1, fonts[2]);
            insertCell(data_table, "Heure", Element.ALIGN_CENTER, 1, fonts[2]);
            data_table.setHeaderRows(1);
            
            add_data_rapport(rapport);
                        
            document.add(data_table);
            document.add(Chunk.NEWLINE);
            
            Paragraph moyenne = new Paragraph("Moyenne Générale", fonts[1]);
            moyenne.add(Chunk.NEWLINE);
            moyenne.add(Chunk.NEWLINE);
            document.add(moyenne);
            
            float[] columnWidths2 = {0.18f, 0.18f, 0.18f, 0.18f, 0.18f,0.18f, 0.18f, 0.18f, 0.2f};
            PdfPTable moyenne_table = new PdfPTable(columnWidths2);
            moyenne_table.setWidthPercentage(100f);
            moyenne_table.setHorizontalAlignment(Element.ALIGN_LEFT);
            insertCell(moyenne_table, "Press. Arr. Max.", Element.ALIGN_CENTER, 1, fonts[2]);
            insertCell(moyenne_table, "Press. Arr. Min", Element.ALIGN_CENTER, 1, fonts[2]);
            insertCell(moyenne_table, "Press. Cum. Arr.", Element.ALIGN_CENTER, 1, fonts[2]);
            insertCell(moyenne_table, "Press. Moy. Arr.", Element.ALIGN_CENTER, 1, fonts[2]);
            insertCell(moyenne_table, "Press. Dist. Max.", Element.ALIGN_CENTER, 1, fonts[2]);
            insertCell(moyenne_table, "Press. Dist. Min", Element.ALIGN_CENTER, 1, fonts[2]);
            insertCell(moyenne_table, "Press. Cum. Dist.", Element.ALIGN_CENTER, 1, fonts[2]);
            insertCell(moyenne_table, "Press. Moy. Dist.", Element.ALIGN_CENTER, 1, fonts[2]);
            insertCell(moyenne_table, "Livraison", Element.ALIGN_CENTER, 1, fonts[2]);
            moyenne_table.setHeaderRows(1);
            
            insertCell(moyenne_table, String.valueOf(rapport.getData_moyenne().get(0).getPress_Ar_Max()), Element.ALIGN_CENTER, 1, fonts[2]);
            insertCell(moyenne_table, String.valueOf(rapport.getData_moyenne().get(0).getPress_Ar_Min()), Element.ALIGN_CENTER, 1, fonts[2]);
            insertCell(moyenne_table, String.valueOf(rapport.getData_moyenne().get(0).getPress_Cum_Ar()), Element.ALIGN_CENTER, 1, fonts[2]);
            insertCell(moyenne_table, String.valueOf(rapport.getData_moyenne().get(0).getPress_Moy_Ar()), Element.ALIGN_CENTER, 1, fonts[2]);
            insertCell(moyenne_table, String.valueOf(rapport.getData_moyenne().get(0).getPress_Dr_Max()), Element.ALIGN_CENTER, 1, fonts[2]);
            insertCell(moyenne_table, String.valueOf(rapport.getData_moyenne().get(0).getPress_Dr_Min()), Element.ALIGN_CENTER, 1, fonts[2]);
            insertCell(moyenne_table, String.valueOf(rapport.getData_moyenne().get(0).getPress_Cum_Dr()), Element.ALIGN_CENTER, 1, fonts[2]);
            insertCell(moyenne_table, String.valueOf(rapport.getData_moyenne().get(0).getPress_Moy_Dr()), Element.ALIGN_CENTER, 1, fonts[2]);
            insertCell(moyenne_table, String.valueOf(rapport.getData_moyenne().get(0).getLivraison()), Element.ALIGN_CENTER, 1, fonts[2]);
            document.add(moyenne_table);            
            
            document.add(Chunk.NEWLINE);
            
            Paragraph Justification = new Paragraph("Marche et Livraison", fonts[1]);
            Justification.add(Chunk.NEWLINE);
            Justification.add(Chunk.NEWLINE);
            document.add(Justification);
            
            float[] columnWidths3 = {0.08f, 0.12f, 0.34f, 0.1f, 0.1f,0.1f};
            Justif_table = new PdfPTable(columnWidths3);
            Justif_table.setWidthPercentage(100f);
            Justif_table.setHorizontalAlignment(Element.ALIGN_LEFT);;
            Justif_table.setHeaderRows(1);
            add_justif_rapport(rapport)
                    ;
            document.add(Justif_table);
            
            document.close();
            
            File file  = new File("src/Rapports/" + rapport.N_Rapport.get() + ".pdf");
            Desktop.getDesktop().open(file);
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    private static void insertCell(PdfPTable table, String text, int align, int colspan, Font font){
        PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
        cell.setHorizontalAlignment(align);
        cell.setColspan(colspan);
        
        if(text.trim().equalsIgnoreCase("")){
         cell.setMinimumHeight(10f);
        }
        table.addCell(cell);

    }
    private static void add_data_rapport(Rapport rapport){     
        rapport.getData().forEach(x->{
            insertCell(data_table, String.valueOf(x.getN()), Element.ALIGN_CENTER, 1, fonts[2]);
            insertCell(data_table, String.valueOf(x.getPression_Arrivé()), Element.ALIGN_CENTER, 1, fonts[2]);
            insertCell(data_table, String.valueOf(x.getPression_Distr()), Element.ALIGN_CENTER, 1, fonts[2]);
            insertCell(data_table, String.valueOf(x.getGMP_1()), Element.ALIGN_CENTER, 1, fonts[2]);
            insertCell(data_table, String.valueOf(x.getGMP_2()), Element.ALIGN_CENTER, 1, fonts[2]);
            insertCell(data_table, String.valueOf(x.getGMP_3()), Element.ALIGN_CENTER, 1, fonts[2]);
            insertCell(data_table, String.valueOf(x.getGMP_4()), Element.ALIGN_CENTER, 1, fonts[2]);
            insertCell(data_table, String.valueOf(x.getGMP_5()), Element.ALIGN_CENTER, 1, fonts[2]);
            insertCell(data_table, String.valueOf(x.getHeure()), Element.ALIGN_CENTER, 1, fonts[2]);
            
        });
    }
    private static List<Justification> tempo = new ArrayList<>();
    private static void add_justif_rapport(Rapport rapport){
        tempo.clear();
        tempo.addAll(rapport.getData_justification());
        Collections.sort(tempo);
        tempo.forEach(e->{            
            insertCell(Justif_table, String.valueOf(e.getN()), Element.ALIGN_CENTER, 1, fonts[2]);
            insertCell(Justif_table, e.getGMP(), Element.ALIGN_CENTER, 1, fonts[2]);
            insertCell(Justif_table, e.getRaison(), Element.ALIGN_CENTER, 1, fonts[2]);
            insertCell(Justif_table, e.getDebut(), Element.ALIGN_CENTER, 1, fonts[2]);
            insertCell(Justif_table, e.getFin(), Element.ALIGN_CENTER, 1, fonts[2]);
            insertCell(Justif_table, e.getTotal(), Element.ALIGN_CENTER, 1, fonts[2]);
        });
    }
}
