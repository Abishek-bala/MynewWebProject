package service;

import customer.*;

public class LoginServiceImpl implements LoginService,Cloneable {
	private CustomerDAO customerDAO;
	private LoginServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	private static LoginServiceImpl loginService;
	synchronized public static LoginServiceImpl getLoginService() {
		if(loginService==null) {
			loginService=new LoginServiceImpl();
		}
		return loginService.getClone();
	}
	public final CustomerDAO getCustomerDAO() {
		return customerDAO;
	}
	public final void setCustomerDAO(CustomerDAO CustomerDAO) {
		this.customerDAO = CustomerDAO;
	}
	public LoginServiceImpl getClone() {
		try {
			return (LoginServiceImpl)super.clone();
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public LoginServiceImpl(CustomerDAO customerDAO) {
		this.customerDAO=customerDAO;
	}
	@Override
	public boolean checkFlag(String Customerid) {
		CustomerDAO udao = CustomerDAOImpl.getCustomerDaoImpl();
		CustomerDTO Customer = udao.findByID(Customerid);
		if(Customer.getFlag()!=0) {
			return true;
		}
		return false;
	}
	@Override
	public boolean CheckCustomer(String Customerid) {
		// TODO Auto-generated method stub
		CustomerDAO udao = CustomerDAOImpl.getCustomerDaoImpl();
		CustomerDTO Customer = udao.findByID(Customerid);
		if(Customer!=null) {
			System.out.println(Customer);
			return true;
		}
		return false;	
	}
	@Override
	public int UpdateFlag(String Customerid,int flag) {
		// TODO Auto-generated method stub
		CustomerDAO udao = CustomerDAOImpl.getCustomerDaoImpl();
		CustomerDTO Customer = udao.findByID(Customerid);
		if(Customer!=null) {
			Customer.setFlag(flag);
			
			return 1;
		}
		return 0;	
	
	}
	@Override
	public int InsertUser(CustomerDTO customer) {
		CustomerDAO udao = CustomerDAOImpl.getCustomerDaoImpl();
		int res =udao.insertCustomer(customer);
		System.out.println(res);
		return res;
	}
	
}
