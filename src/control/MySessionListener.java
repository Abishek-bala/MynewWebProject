package control;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import service.LoginServiceImpl;

public class MySessionListener implements HttpSessionListener{
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("session created");
	}
	
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("session destroyed...");
    	HttpSession session=se.getSession();
    	Object name=session.getAttribute("userid");
    	if(name!=null) {
    		String userid=name.toString();
    		LoginServiceImpl login=LoginServiceImpl.getLoginService();
    		login.UpdateFlag(userid, 0);
    	}
	}
}
