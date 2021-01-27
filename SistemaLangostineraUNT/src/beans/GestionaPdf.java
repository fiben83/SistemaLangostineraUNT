/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

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
import com.itextpdf.text.pdf.PdfWriter;
import GestionDeDatos.GestionaBaseDeDatos;
import dao.ModelDaoImpl;
import helpers.HelperPdf;
import java.io.Serializable;
import static java.lang.System.out;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GestionaPdf implements Serializable{
    
    private Integer id,idPaciente,idHistoria;
    private String salida;
    
    public String getSalida(String operacion) {
        try {
            switch(operacion){
                case "Paciente":getPacienteForId();break;
                case "Historia":getHistoriaPorPaciente();break;
            }
            return salida;
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }
    
    void getPacienteForId() throws Exception{
        GestionaBaseDeDatos.conectar();
        ResultSet rs;
        String sql = "SELECT p.nombres,p.apellido_paterno,p.apellido_materno,pa.numero_historia,"
                            +"s.nombre,pa.numero_seguro,p.numero_documento"
                            +" FROM paciente pa "
                            +"INNER JOIN persona p on p.id = pa.id_persona "
                            +"INNER JOIN seguro s on s.id=pa.id_seguro "
                            +"WHERE pa.id='"+getIdPaciente()+"'";
        System.out.println(sql);
        rs = GestionaBaseDeDatos.consultar(sql);
        if(rs.next()){
            String data = rs.getString(4) +",";
                   data += rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(1)+",";
                   data += rs.getString(7)+",";
                   data += rs.getString(5)+",";
                   data += rs.getString(6);
            setSalida(data);
        }
        GestionaBaseDeDatos.desconectar();
    }
    
    void getHistoriaPorPaciente() throws Exception{
        GestionaBaseDeDatos.conectar();
        ResultSet rs;
        String sql = "SELECT p.nombres,p.apellido_paterno,p.apellido_materno,"
                            +"s.nombre,h.diagnostico,h.fecha"
                            +" FROM historia_clinica h "
                            +"INNER JOIN medico m on m.id=h.id_medico "
                            +"INNER JOIN usuario u on u.id=m.id_usuario "
                            +"INNER JOIN persona p on p.id = u.id_persona "
                            +"INNER JOIN servicio s on s.id=h.id_servicio "
                            +"WHERE h.id='"+getIdHistoria()+"'";
        rs = GestionaBaseDeDatos.consultar(sql);
        if(rs.next()){
            String data = rs.getString(6) +",";
                   data += rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(1)+",";
                   data += rs.getString(4)+",";
                   data += rs.getString(5);
            setSalida(data);
        }
        GestionaBaseDeDatos.desconectar();
    }                       
    
    public ArrayList<String> HistoriasPorPaciente() throws Exception{
        ArrayList<String> arreglo = new ArrayList<String>();
        GestionaBaseDeDatos.conectar();
        ResultSet rs;
        String sql = "SELECT p.nombres,p.apellido_paterno,p.apellido_materno,"
                            +"s.nombre,h.diagnostico,h.fecha"
                            +" FROM historia_clinica h "
                            +"INNER JOIN medico m on m.id=h.id_medico "
                            +"INNER JOIN usuario u on u.id=m.id_usuario "
                            +"INNER JOIN persona p on p.id = u.id_persona "
                            +"INNER JOIN servicio s on s.id=h.id_servicio "
                            +"WHERE h.id_paciente='"+getIdPaciente()+"' ORDER BY fecha DESC ";
        rs = GestionaBaseDeDatos.consultar(sql);
        while(rs.next()){
            String data = rs.getString(6) +",";
                   data += rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(1)+",";
                   data += rs.getString(4)+",";
                   data += rs.getString(5);
            arreglo.add(data);
        }
        GestionaBaseDeDatos.desconectar();
        return arreglo;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    

    public void setSalida(String salida) {
        this.salida = salida;
    }

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Integer getIdHistoria() {
        return idHistoria;
    }

    public void setIdHistoria(Integer idHistoria) {
        this.idHistoria = idHistoria;
    }
    
    
    
    
    
}
