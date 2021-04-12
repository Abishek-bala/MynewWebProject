package com;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.Action;
import control.RequestProcessor;

/**
 * Servlet implementation class ActionServlet
 */
public class ActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RequestProcessor rp;
	@Override
	public void init(ServletConfig config) throws ServletException {
		try {
		rp=new RequestProcessor();
		ServletContext application=config.getServletContext();
		String configfile=config.getInitParameter("config");
		String dbconfigfile=config.getInitParameter("dbconfig");
		String configpath=application.getRealPath(configfile);
		String dbconfigpath=application.getRealPath(dbconfigfile);
		
		Properties configProp=new Properties();
		
		configProp.load(new FileInputStream(configpath));
		
		Properties dbConfigProp=new Properties();
		dbConfigProp.load(new FileInputStream(dbconfigpath));
		
		application.setAttribute("configProp", configProp);
		application.setAttribute("dbConfigProp", dbConfigProp);
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			rp.process(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	

}
