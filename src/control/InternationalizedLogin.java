package control;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class InternationalizedLogin extends TagSupport{
	private HttpSession session;
	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		
		this.session = session;
	}
	@Override
	public int doEndTag() throws JspException {
		ResourceBundle rb=(ResourceBundle)session.getAttribute("rb");
		JspWriter out=pageContext.getOut();
		String userid = rb.getString("userid");
		String password = rb.getString("password"); 
		try {
			out.println(userid +":<input type = \"text\" name=\"userid\"/><br>");
		
			out.println( password+ ":<input type = \"password\" name=\"password\" placeholder = \"Enter your password\"/><br>");  	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return super.doEndTag();
	}
}
