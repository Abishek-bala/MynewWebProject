package control;

import java.security.SecureRandom;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Enumeration;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import InvoiceMaster.InvoiceMasterDAO;
import InvoiceMaster.InvoiceMasterDAOImpl;
import InvoiceMaster.InvoiceMasterDTO;
import Transaction.InvoiceTransDAO;
import Transaction.InvoiceTransDAOImpl;

public class ShowInvoice extends TagSupport{
	private HttpSession session;
	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}
	

	
	@Override
	public int doEndTag() throws JspException {
		// TODO Auto-generated method stub
		try {
			SecureRandom random = new SecureRandom();
			session.setAttribute("invoiceid", random.nextInt(100000));
			JspWriter out=pageContext.getOut();
			Enumeration<String> e=session.getAttributeNames();
			Float total=0.0f;
			while(e.hasMoreElements()){
				String name=e.nextElement();
				if(! (name.equals("userid") || (name.equals("invoiceid")) || (name.contains("_")) )  ){
					String sprice = name+"_price";
					System.out.println("Name = "+name);
					int value=Integer.parseInt(session.getAttribute(name).toString()) ;
					float price = Float.parseFloat(session.getAttribute(sprice).toString())*value; 
					if(value!=0) {
						out.println("<tr>"
								+ "	<td>"+name+"</td>"
								+ "<td>"+value+"</td>"
								+ "<td>" + price + "</td>"
								+ "	</tr>");
					total+=price;
					}
					
				}
			}
			session.setAttribute("grand_total", total);
			out.println("</table><h2>Total = "+ total +" </h2>");

		}catch(Exception e) {
			e.printStackTrace();
		}
				
		InvoiceMasterDAO invoicedao = InvoiceMasterDAOImpl.getInvoiceMasterDaoImpl();
		
		InvoiceTransDAO transactiondao = InvoiceTransDAOImpl.getInvoiceTransDaoImpl();
		
		LocalDate today= LocalDate.now();
		InvoiceMasterDTO invoicedto = InvoiceMasterDTO.getInvoiceMasterDTO();
		invoicedto.setInvoiceid(Integer.parseInt(session.getAttribute("invoiceid").toString()));
		invoicedto.setCustomerid(session.getAttribute("userid").toString());
		invoicedto.setInvdate(Date.valueOf(today));
		invoicedao.insertInvoiceMaster(invoicedto);
		return super.doEndTag();
	}
}
