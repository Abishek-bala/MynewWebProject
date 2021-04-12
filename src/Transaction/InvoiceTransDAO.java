package Transaction;

import java.util.List;

public interface InvoiceTransDAO {
	public InvoiceTransDTO findByID(Integer invoiceid);
	public List<InvoiceTransDTO> findAll();
	public int insertInvoiceTrans(InvoiceTransDTO InvoiceTransDTO);
	public int updateInvoiceTrans(InvoiceTransDTO InvoiceTransDTO);
	public int deleteInvoiceTransByID(Integer invoiceid);
	public int deleteInvoiceTransByDTO(InvoiceTransDTO InvoiceTransDTO);
}
