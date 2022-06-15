package net.codeJava.atozMain.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.codeJava.atozMain.service.JasperReportService;
import net.sf.jasperreports.engine.JRException;

@Controller
public class JasperReportController {
	
	@Autowired
	private JasperReportService jasperReportService;
	
	@GetMapping("/report/html")
	public String generateReport(@PathVariable String format) throws FileNotFoundException, JRException {
		
		jasperReportService.exportReport(format);
		return ("redirect:"+"/bank_select");
	}
	
	@GetMapping("/reportPDF")
	public String generateReport1() throws FileNotFoundException, JRException {
		jasperReportService.generatePdf();
		return ("redirect:"+"/bank_select");
	}
	
	@RequestMapping(value = "viewReport", method = RequestMethod.GET)
	public void viewReportPDF(HttpServletResponse response) throws JRException, IOException {
		response.setContentType("text/html");
		jasperReportService.viewPDF(response);
//		return ("redirect:"+"/bank_select");
	}

}
