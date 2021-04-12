package control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

abstract public class Action {
	public abstract String execute(HttpServletRequest request, HttpServletResponse response);
}
