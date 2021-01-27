package controladores;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;

import beans.GestionaCrianza;
import GestionDeDatos.GestionaBaseDeDatos;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;

/**
 * Servlet implementation class ReporteCrianzas
 */
@WebServlet(name = "GraficaParametros", urlPatterns = {"/GraficaParametros"})
public class GraficaParametros extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		System.out.println("entro al reporte Diario");
        //Declaro e Instancio variables
		Connection conexion= null;
		Map<String, Object> parametros= new HashMap<String, Object>();
		
		String id= request.getParameter("idC");
		System.out.println("id: "+id);
		parametros.put("crianza", id);
		String param= request.getParameter("crud");
		System.out.println("param: "+param);
				
		
		try {
				
		ServletOutputStream out= response.getOutputStream();
        String pathReporte="";
        
        conexion= (Connection) GestionaBaseDeDatos.conectar();
    	System.out.println("conectó Mysql");
    	
    	if(param.equals("o")){
    		pathReporte=getServletContext().getRealPath("/grafica_oxigeno.jasper");
    	}else if(param.equals("c")){
    		pathReporte=getServletContext().getRealPath("/grafica_temperatura.jasper");
    	}else if(param.equals("ph")){
    		pathReporte=getServletContext().getRealPath("/grafica_ph.jasper");
    	}else if(param.equals("sal")) {
    		pathReporte=getServletContext().getRealPath("/grafica_salinidad.jasper");
    	}else if(param.equals("talla")) {
    		pathReporte=getServletContext().getRealPath("/grafica_talla.jasper");
    	}else if(param.equals("peso")) {
    		pathReporte=getServletContext().getRealPath("/grafica_peso.jasper");
    	}else if(param.equals("bio")) {
    		pathReporte=getServletContext().getRealPath("/grafica_biomasa.jasper");
    	}
    	
    	
    	JasperReport reporte= (JasperReport) JRLoader.loadObjectFromFile(pathReporte);
       // JasperReport reporte= (JasperReport) JRLoader.loadObjectFromFile(getServletContext().getRealPath("WEB-INF/report_crianzas.jasper"));
        JasperPrint jasperPrint= JasperFillManager.fillReport(reporte,parametros ,conexion);
        
    		System.out.println("entro pdf");
    		response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "inline;filename=\"Grafica_Parametros.pdf\";");
			//response.setHeader("Content-Disposition", "attachment;filename=\"Reporte_Med_diarias.pdf\";");
			response.setHeader("Cache-Control","no-cache"); 
			response.setHeader("Pragma","no-cache");  
			response.setDateHeader ("Expires", 0);
    		
			JRPdfExporter exporter= new JRPdfExporter();
	    	System.out.println("exportando finalmente...");
	        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
	        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
	        SimplePdfExporterConfiguration configuration= new SimplePdfExporterConfiguration();
	        exporter.setConfiguration(configuration);
	        exporter.exportReport();
	    
	        System.out.println("successfull");
    		
		}
		catch(Exception ex) {
			System.out.println("ERRORRRRRR...");
			ex.printStackTrace();
			
		}
             
    }
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

}
