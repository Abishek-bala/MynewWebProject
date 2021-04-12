package control;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import InvoiceMaster.InvoiceMasterDAO;
import InvoiceMaster.InvoiceMasterDAOImpl;
import InvoiceMaster.InvoiceMasterDTO;

public class GenerateInvoice extends Action{
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		CreateExcel excel = CreateExcel.getcreateexcel("E://MynewWebProject.xls");
		excel.getExcel(request);
		
		CreatePdf pdf = CreatePdf.getcreatePdf("E://MynewWebProject.pdf");
		pdf.createPDF(request);
		
		SendEmail email = SendEmail.getSendEmail("javaemailpack@gmail.com");
		email.sendMyMail(request);
		return "invoice.success";
	}
	
}
