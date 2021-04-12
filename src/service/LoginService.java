package service;

import customer.CustomerDTO;

public interface LoginService {
	public boolean CheckCustomer(String userid);
	public boolean checkFlag(String userid);
	public int UpdateFlag(String userid,int flag);
	public int InsertUser(CustomerDTO customer);
}
