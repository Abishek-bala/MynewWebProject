package control;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Item.ItemDAO;
import Item.ItemDAOImpl;
import Item.ItemDTO;

public class GoShoppingAction extends Action {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String userid =session.getAttribute("userid").toString() ;
		session.setAttribute("userid", userid);
		return "goshop.success";
		}
}
