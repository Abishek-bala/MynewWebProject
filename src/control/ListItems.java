package control;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import Item.ItemDAO;
import Item.*;

public class ListItems extends TagSupport{
	private HttpSession session;
	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}
	@Override
	public int doEndTag() throws JspException {
		ItemDAO itemdao = ItemDAOImpl.getItemDaoImpl();
		JspWriter out=pageContext.getOut();
		List<ItemDTO> items = itemdao.findAll();
		
		for(ItemDTO item:items) {
			int id = item.getItemid();
			String name = item.getItem_name();
			session.removeAttribute(name);
			session.setAttribute(name+"_price", item.getItem_unit());
			System.out.println(session.getAttribute(name+"_price"));
			try{
				
				out.println(name + "<input type=\"number\" name=\""+name+"\"  min=\"0\" step=\"1\" value=\"0\" /><br/>");	
//				session.setAttribute(name, item);
			}catch(Exception e) {e.printStackTrace();}
			
		}
		
		return super.doEndTag();
	}
}
