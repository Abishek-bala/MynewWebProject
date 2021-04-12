package control;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


public class CreatePdf implements Cloneable{
	File f;

	private CreatePdf(String path){
		// TODO Auto-generated constructor stub
		System.out.println(path);
		f = new File(path);
	}

	private static CreatePdf createPdf;

	public static CreatePdf getcreatePdf(String path) {
		if(createPdf==null) {
			createPdf=new CreatePdf(path);
		}
		return createPdf.getClone();
	}

	public CreatePdf getClone() {
		try {
			return (CreatePdf)super.clone();
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void createPDF(HttpServletRequest request){
		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream(f));
		
		document.open();
		
		 Paragraph preface;// = new Paragraph();
	     document.add(new Paragraph());
	        // Lets write a big header
	     Font titleFont = new Font(FontFamily.TIMES_ROMAN,20);
	    
		
		PdfPTable table = new PdfPTable(4);
		PdfPCell c1 = new PdfPCell(new Phrase("Item"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Quantity"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("UnitPrice"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("total Price"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        
        table.addCell(c1);
        table.setHeaderRows(1);
        
        HttpSession session = request.getSession();
        
        Enumeration<String> e=session.getAttributeNames();
		float total=0;
		while(e.hasMoreElements()){
			String name=e.nextElement();
			if(! (name.equals("userid") || (name.equals("invoiceid")) || (name.contains("_")) )  ){
				
				String sprice = name+"_price";
				Integer qty=Integer.parseInt(session.getAttribute(name).toString()) ;
				Float price = Float.parseFloat(session.getAttribute(sprice).toString())*qty; 
				
				if(qty!=0) {
					c1 = new PdfPCell(new Phrase(name));
			        table.addCell(c1);
			        
			        table.addCell(c1);
			        c1 = new PdfPCell(new Phrase(session.getAttribute(sprice).toString()));
			        table.addCell(c1);
			        c1 = new PdfPCell(new Phrase(price.toString()));
			        table.addCell(c1);
				total+=price;
				}
				
			}
		}
        System.out.println(table);
        document.add(table);
        
        Paragraph summary = new Paragraph();
        Paragraph sub = new Paragraph("Total = "+total);
        sub.setAlignment(Element.ALIGN_RIGHT);
        summary.add(sub);
        summary.setAlignment(Element.ALIGN_RIGHT);
        
        document.add(summary);
        System.out.println("pdf almost done");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		finally {
			document.close();
			System.out.println("pdf done");
		}
        
}
		
}
