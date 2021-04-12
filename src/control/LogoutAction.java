package control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.LoginService;
import service.LoginServiceImpl;

public class LogoutAction extends Action {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		LoginService service = LoginServiceImpl.getLoginService();
		HttpSession session = request.getSession();
		String uid = session.getAttribute("userid").toString();
		session.setAttribute("userid",null);
		System.out.println(uid + "logged out");
		service.UpdateFlag(uid, 0);
		
		return "logout.success";
		
	}
}
