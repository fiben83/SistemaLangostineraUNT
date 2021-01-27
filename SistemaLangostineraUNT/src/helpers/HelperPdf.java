/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eca
 */
public class HelperPdf {
    
    public static void title(Document doc,String cadena) throws DocumentException{
        Paragraph parrafo = new Paragraph();
        Font fontTitle = new Font(Font.FontFamily.HELVETICA,16,Font.BOLD,BaseColor.DARK_GRAY);
        parrafo.add(new Phrase(cadena,fontTitle));
        parrafo.setAlignment(Element.ALIGN_CENTER);
        parrafo.add(new Phrase(Chunk.NEWLINE));
        parrafo.setAlignment(Element.ALIGN_CENTER);
        parrafo.add(new Phrase(Chunk.NEWLINE));
        doc.add(parrafo);
    }
    
    public static void texto(Document doc,String cadena) throws DocumentException{
        Paragraph parrafo = new Paragraph();
        Font fontTitle = new Font(Font.FontFamily.HELVETICA,12);
        parrafo.add(new Phrase(cadena,fontTitle));
        parrafo.setAlignment(Element.ALIGN_LEFT);
        parrafo.add(new Phrase(Chunk.NEWLINE));
        doc.add(parrafo);
    }
    
    public static void columns(Document doc,String cabeceras) throws DocumentException{
        String[] columnas = cabeceras.split(",");
        PdfPTable tabla = new PdfPTable(columnas.length);
        for(String columna:columnas){
            PdfPCell celda = new PdfPCell(new Paragraph(columna,FontFactory.getFont("Arial", 12,Font.BOLD,BaseColor.WHITE)));
            celda.setBackgroundColor(BaseColor.DARK_GRAY);
            tabla.addCell(celda);
        }
        doc.add(tabla);
    }
    public static void row(Document doc,String camposFila) throws DocumentException{
        String[] campos = camposFila.split(",");
        PdfPTable tabla = new PdfPTable(campos.length);
        for(String campo:campos){
            tabla.addCell(campo);
        }
        doc.add(tabla);
    }
    public static void jump(Document doc) throws DocumentException{
        Paragraph parrafo = new Paragraph();
        parrafo.add(new Phrase(Chunk.NEWLINE));
        doc.add(parrafo);
    }
    public static void subtitle(Document doc,String cadena) throws DocumentException{
        Paragraph parrafo = new Paragraph();
        Font fontTitle = new Font(Font.FontFamily.HELVETICA,14,Font.BOLD);
        parrafo.add(new Phrase(cadena,fontTitle));
        parrafo.setAlignment(Element.ALIGN_CENTER);
        parrafo.add(new Phrase(Chunk.NEWLINE));
        doc.add(parrafo);
    }
    
    public static void logo(Document doc){
        try {
            Image imagen = Image.getInstance("D:\\desarrollo\\cursos\\tecnologiaWeb\\jsp_clinica_web\\Clinica\\web\\dist\\imagenes\\logo0.jpg");
            imagen.setAlignment(Element.ALIGN_LEFT);
            imagen.scaleToFit(100,100);
            doc.add(imagen);
        } catch (BadElementException ex) {
            Logger.getLogger(HelperPdf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HelperPdf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(HelperPdf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static void processRow(Document doc,String cadena,Integer size) throws DocumentException{
        
        Paragraph parrafo = new Paragraph();
        Font fontTitle = new Font(Font.FontFamily.HELVETICA,size,Font.BOLD);
        parrafo.add(new Phrase(cadena,fontTitle));
        parrafo.setAlignment(Element.ALIGN_CENTER);
        parrafo.add(new Phrase(Chunk.NEWLINE));
        doc.add(parrafo);
    }
}
