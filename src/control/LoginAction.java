package control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import User.UserDAOImpl;
import customer.CustomerDAOImpl;
import service.LoginService;
import service.LoginServiceImpl;

public class LoginAction extends Action {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String userid = request.getParameter("userid");
		HttpSession session = request.getSession();
		LoginServiceImpl service=LoginServiceImpl.getLoginService();
		service.setCustomerDAO(CustomerDAOImpl.getCustomerDaoImpl());
		
		if(service.CheckCustomer(userid)) {
			System.out.println("user existance"+service.CheckCustomer(userid));
			if(!service.checkFlag(userid)) {
				service.UpdateFlag(userid,1);
				session.setAttribute("userid", userid);
				return "login.success";
			}
		}
		else {
			return "login.register";
		}
		
		return "login.register";
		
	}
}
