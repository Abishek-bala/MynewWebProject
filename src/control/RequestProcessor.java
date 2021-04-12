package control;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestProcessor {
public void process(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		try {
			Properties prop=(Properties)request.getServletContext().getAttribute("configProp");
		String classname = prop.getProperty(request.getParameter("formid"));
		System.out.println(classname + " " + request.getParameter("userid"));
		Action obj = (Action) Class.forName(classname).getConstructor().newInstance(); 
		

		String nextPage=prop.getProperty(obj.execute(request,response));
		

		RequestDispatcher rd=request.getRequestDispatcher(nextPage);
		
		rd.forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
