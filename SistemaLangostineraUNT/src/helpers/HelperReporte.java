/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import beans.GestionaPdf;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

/**
 *
 * @author Eca
 */
public class HelperReporte {
    
    public static void genReportBasic(Document doc,String[] datosPaciente) throws DocumentException{
        HelperPdf.logo(doc); //LOGO
        HelperPdf.title(doc, "REPORTE DE HISTORIA CLINICA");//Titulo

        HelperPdf.columns(doc,"N° Historia,Paciente,N° Documento");
        HelperPdf.row(doc,datosPaciente[0]+","+datosPaciente[1]+","+datosPaciente[2]);
        HelperPdf.jump(doc);HelperPdf.jump(doc);
        HelperPdf.columns(doc,"Seguro,N° Seguro");
        HelperPdf.row(doc,datosPaciente[3]+","+datosPaciente[4]);

        HelperPdf.jump(doc);HelperPdf.jump(doc);//2 Saltos de Liena
        HelperPdf.subtitle(doc, "Historia Clinica");//SubTitulo
        HelperPdf.jump(doc);
    }
    
}
