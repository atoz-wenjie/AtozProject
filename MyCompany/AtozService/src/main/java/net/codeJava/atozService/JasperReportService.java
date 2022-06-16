package net.codeJava.atozService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import net.codeJava.atozEntity.User;
import net.codeJava.atozRepository.UserRepository;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;

@Service
public class JasperReportService {

	@Autowired
	private UserRepository repository;
	public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
		
		String localPath = "C:\\Users\\admin\\Desktop\\Report";
		List<User> users = repository.findAll();
		//Load file and compile it
		File file = ResourceUtils.getFile("classpath:jasperReport/users.jrxml");
		
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(users);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("createBy", "WenJie");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,map,dataSource);
		
		if(reportFormat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint,localPath+"\\users.html");
		}
		if(reportFormat.equalsIgnoreCase("pdf")) {
			JasperExportManager.exportReportToPdfFile(jasperPrint,localPath+"\\users.pdf");
		}

		return "Report successfully generated in path: "+localPath;
	}
	
	public String generatePdf() throws FileNotFoundException, JRException {
		
		String localPath = "C:\\Users\\admin\\Desktop\\Report";
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(repository.findAll());
		JasperReport jasperReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/templates/jasperReport/Blank_A4.jrxml"));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("createBy", "Alibabababa");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,map,beanCollectionDataSource);
		JasperExportManager.exportReportToPdfFile(jasperPrint,localPath+"\\users.pdf");
		return "Report successfully generated in path: "+localPath;
	}
	
	public void viewPDF(HttpServletResponse response) throws JRException, IOException {
		
		String localPath = "C:\\Users\\admin\\Desktop\\Report";
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(repository.findAll());
		JasperReport jasperReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/templates/jasperReport/Blank_A4.jrxml"));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("createBy", "Alibabababa");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,map,beanCollectionDataSource);
		JasperExportManager.exportReportToPdfFile(jasperPrint,localPath+"\\users.pdf");
		
		HtmlExporter htmlExporter = new HtmlExporter(DefaultJasperReportsContext.getInstance());
		htmlExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		htmlExporter.setExporterOutput(new SimpleHtmlExporterOutput(response.getWriter()));
		htmlExporter.exportReport();
	}
}
