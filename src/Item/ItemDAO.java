package Item;

import java.util.List;


public interface ItemDAO {
	public ItemDTO findByID(Integer itemid);
	public List<ItemDTO> findAll();
	public int insertItem(ItemDTO itemDTO);
	public int updateItem(ItemDTO itemDTO);
	public int deleteItemByID(Integer itemid);
	public int deleteItemByDTO(ItemDTO itemDTO);
}

