package com.pack.FarmToMarket.service;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.pack.FarmToMarket.entity.Item;
import com.pack.FarmToMarket.entity.ItemDetails;
import com.pack.FarmToMarket.repository.ItemRepository;
 
@Service
public class ItemService {
 
	@Autowired
	ItemRepository itemRepository;
	public Item addNewItem(Item item)
	{
		/*
		 * return itemRepository.save(new
		 * Item(item.getItemName(),item.getItemDescription(),
		 * item.getItemQuantity(),item.getUser()));
		 */
		return itemRepository.save(item);
	}
	public List<ItemDetails> getAllItems() {
		return itemRepository.findAllItems();
	}
	public List<Item> getAllItemById(Integer id) {
		   return itemRepository.findAllItem(id);
		}
	public Item getItemById(Integer itemId) {
		return itemRepository.findById(itemId).get();
	}
	public void deleteItemDetails(Integer itemId)
	{
		itemRepository.deleteById(itemId);
	}
	public List<ItemDetails> searchItem(String city) {
		return itemRepository.getItemDetailsBasedCity(city);
	}
}