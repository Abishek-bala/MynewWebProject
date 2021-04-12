package control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import User.UserDTO;
import customer.CustomerDAOImpl;
import customer.CustomerDTO;
import service.LoginServiceImpl;

public class SignupAction extends Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String customername = request.getParameter("Customer name");
		String customerid = request.getParameter("Customerid");
		String customeraddress = request.getParameter("Customer address");
		String customer_no = request.getParameter("customerphone").toString();
		System.out.println(customer_no);
		int customerphone = Integer.parseInt(customer_no) ;
		String customeremail = request.getParameter("Customeremail");
		int customergstnumber = Integer.parseInt(request.getParameter("Customer GST number").toString()) ;
		
		LoginServiceImpl service=LoginServiceImpl.getLoginService();
		service.setCustomerDAO(CustomerDAOImpl.getCustomerDaoImpl());
		
		CustomerDTO customer = CustomerDTO.getCustomerDTO();
		
		if(service.CheckCustomer(customerid)) {
			return "signup.existing";
		}
		
		else {
			customer.setCustomerId(customerid);
			customer.setCustomerName(customername);
			customer.setCustomerAccountDetails(customeremail);
			customer.setCustomerAddress(customeraddress);
			customer.setCustomerPhonenumber(customerphone);
			customer.setCustomergstnumber(customergstnumber);
			customer.setFlag(0);
			System.out.println(customer);
			service.InsertUser(customer);
		}
		
		
		return "signup.success";
	}

}
