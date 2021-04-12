package control;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jxl.*;
import jxl.write.*;
import jxl.write.Number;

public class CreateExcel implements Cloneable{
File f;
private CreateExcel(String path){
	// TODO Auto-generated constructor stub
	System.out.println(path);
	f = new File(path);
}

private static CreateExcel createexcel;

public static CreateExcel getcreateexcel(String path) {
	if(createexcel==null) {
		createexcel=new CreateExcel(path);
	}
	return createexcel.getClone();
}

public CreateExcel getClone() {
	try {
		return (CreateExcel)super.clone();
	}catch(Exception e) {
		e.printStackTrace();
		return null;
	}
}
public void getExcel(HttpServletRequest request) {
	// TODO Auto-generated method stub
	WritableWorkbook mybook = null;
	HttpSession session = request.getSession();
	try {
		  mybook = Workbook.createWorkbook(f);

            // create an Excel sheet
            WritableSheet excelSheet = mybook.createSheet("Sheet 1", 0);
            	
            // add something into the Excel sheet
            Enumeration<String> e=session.getAttributeNames();
			Label label = new Label(0, 0, "Item");
	        excelSheet.addCell(label);
	
	          
	
	          label = new Label(1, 0, "Quantity");
	          excelSheet.addCell(label);
	
	          label = new Label(2, 0, "Unit Price");
	          excelSheet.addCell(label);
	          label = new Label(3, 0, "Total Price	");
	          excelSheet.addCell(label);

            
            int i=1;
            float total=0;
			while(e.hasMoreElements()){
				String name=e.nextElement();
				
				
				
				if(! (name.equals("userid") || (name.equals("invoiceid")) || (name.contains("_")) )  ){
					String sprice = name+"_price";
					int qty=Integer.parseInt(session.getAttribute(name).toString()) ;
					if(qty!=0) {
						float price = Float.parseFloat(session.getAttribute(sprice).toString())*qty; 
						
						label = new Label(0,i,name);
		            	excelSheet.addCell(label);
		            	
		            	Number number =new Number(1,i,qty);
		            	excelSheet.addCell(number);
		            	number =new Number(2,i,Integer.parseInt(session.getAttribute(sprice).toString()));
		            	excelSheet.addCell(number);
		            	number =new Number(3,i,price);
		            	excelSheet.addCell(number);
		            	
		            	total+=price;
		            	System.out.println(total);
		            	i++;
						
					}
	
					}
				label = new Label(2,i,"Total");
	        	excelSheet.addCell(label);
//	        	float total = Float.parseFloat(session.getAttribute("total").toString());
				Number number =new Number(3,i,total);
	        	excelSheet.addCell(number);
			}
            
            
            mybook.write();
	}catch (IOException e) {
        e.printStackTrace();
    } catch (WriteException e) {
        e.printStackTrace();
    } finally {

        if (mybook != null) {
            try {
                mybook.close();
                System.out.println("Excel created");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
	
        }
    }
}
}