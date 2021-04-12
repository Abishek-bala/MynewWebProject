package customer;

import java.util.List;

public interface CustomerDAO {
	public CustomerDTO findByID(String customerid);
	public List<CustomerDTO> findAll();
	public int insertCustomer(CustomerDTO customerdto);
	public int updateCustomer(CustomerDTO customerdto);
	public int deleteCustomerByID(String customerid);
	public int deleteCustomerByDTO(CustomerDTO customerdto);
}
