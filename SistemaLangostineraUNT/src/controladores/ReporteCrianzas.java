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
@WebServlet(name = "ReporteCrianzas", urlPatterns = {"/ReporteCrianzas"})
public class ReporteCrianzas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		System.out.println("entro al servlet");
        //Declaro e Instancio variables
		Connection conexion= null;
		//String tipo= request.getParameter("excel");
		//System.out.println("tipo: "+tipo);
		Map<String, Object> parametros= new HashMap<String, Object>();
		String annio= request.getParameter("annio");
		System.out.println("año: "+annio);
		parametros.put("annio", annio);
		String tipo= request.getParameter("tipo");
		System.out.println("tipo: "+tipo);
				
		
		try {
				
		ServletOutputStream out= response.getOutputStream();
        String pathReporte;
        
        conexion= (Connection) GestionaBaseDeDatos.conectar();
    	System.out.println("conectó Mysql");
    	pathReporte=getServletContext().getRealPath("/report_crianzas.jasper");
    	JasperReport reporte= (JasperReport) JRLoader.loadObjectFromFile(pathReporte);
       // JasperReport reporte= (JasperReport) JRLoader.loadObjectFromFile(getServletContext().getRealPath("WEB-INF/report_crianzas.jasper"));
        
    	JasperPrint jasperPrint= JasperFillManager.fillReport(reporte, parametros,conexion);
        
    	if(tipo.equals("pdf")){
    		System.out.println("entró pdf");
			
    		response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "inline;filename=\"Reporte_Crianzas.pdf\";");
			//response.setHeader("Content-Disposition", "attachment;filename=\"Reports.pdf\";");
			response.setHeader("Cache-Control","no-cache"); 
			response.setHeader("Pragma","no-cache");  
			response.setDateHeader ("Expires", 0);
    		
			JRPdfExporter exporter= new JRPdfExporter();
	    	System.out.println("exportando finalmente report");
	        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
	        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
	        SimplePdfExporterConfiguration configuration= new SimplePdfExporterConfiguration();
	        exporter.setConfiguration(configuration);
	        exporter.exportReport();
	    
	        System.out.println("hasta aquí llegó");
    		
    		
    	}else{
    		System.out.println("entró xls");
    		response.setContentType("application/xls");
			response.setHeader("Content-Disposition", "attachment;filename=\"Reporte_Crianzas.xls\";");
			
			JRXlsExporter exporter= new JRXlsExporter();
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
	        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
    		SimpleXlsReportConfiguration configuration= new SimpleXlsReportConfiguration();
    		configuration.setOnePagePerSheet(true);
    		configuration.setDetectCellType(true);
    		configuration.setCollapseRowSpan(false);
    		exporter.exportReport();
    		System.out.println("exportando xls");
    		}
    	  	        
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
