package com.pack.FarmToMarket;
 
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
 
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
 
import org.junit.Test;
//import org.junit.jupiter.api.Disabled;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
 
import com.pack.FarmToMarket.entity.Equipment;
import com.pack.FarmToMarket.entity.Item;
import com.pack.FarmToMarket.entity.ItemDetails;
import com.pack.FarmToMarket.entity.User;
import com.pack.FarmToMarket.repository.EquipmentRepository;
import com.pack.FarmToMarket.repository.ItemRepository;
import com.pack.FarmToMarket.repository.UserRepository;
import com.pack.FarmToMarket.service.EquipmentService;
import com.pack.FarmToMarket.service.ItemService;
import com.pack.FarmToMarket.service.UserService;
 
 
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestFarmToMarketService {
 
	@Autowired
	EquipmentService equipmentService;
	@Autowired
	ItemService itemService;
	@Autowired
	UserService userService;
	@MockBean
	EquipmentRepository equipmentRepository;
	@MockBean
	UserRepository userRepository;
	@MockBean
	ItemRepository itemRepository;
	@Test
	public void testSaveUser(){
		User user=new User("9847572727","Tamilnadu","Chennai","600116","farmer","Senthil","abcd");
	    Mockito.when(userRepository.save(user)).thenReturn(user);    
	    assertThat(userService.saveUser(user)).isEqualTo(user);
	}
	@Test
	public void testFindUser() {
		User user=new User(1,"9847572727","Tamilnadu","Chennai","600116","farmer","Senthil","abcd");
		Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(user));
		userService.findUser(user.getId());
		assertThat(userService.findUser(user.getId())).isEqualTo(user);
	}
	@Test
	public void testGetFarmerDetails() {
		User user=new User(1,"9847572727","Tamilnadu","Chennai","600116","farmer","Senthil","abcd");
		Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(user));
		userService.findUser(user.getId());
		assertThat(userService.findUser(user.getId())).isEqualTo(user);
	}
	@Test
	public void testAddNewItem() {
		User user=new User(1,"9847572727","Tamilnadu","Chennai","600116","farmer","Senthil","abcd");
		Item item=new Item(10,"Cabbage","vegetable",20,user);
		Mockito.when(itemRepository.save(item)).thenReturn(item);    
	    assertThat(itemService.addNewItem(item)).isEqualTo(item);
	}
	@Test
	public void testGetAllItems(){
		List<ItemDetails> itemList=null;
		Mockito.when(itemRepository.findAllItems()).thenReturn(itemList);		
		assertThat(itemService.getAllItems()).isEqualTo(itemList);
	}
	@Test
	public void testDeleteItemDetails(){
		Item item=new Item(); 
		Mockito.when(itemRepository.findById(1)).thenReturn(Optional.of(item));
	    itemService.deleteItemDetails(item.getItemId());
	    assertFalse(itemRepository.existsById(item.getItemId()));
	}
	@Test
	public void testGetItemById() {
		User user=new User(1,"9847572727","Tamilnadu","Chennai","600116","farmer","Senthil","abcd");
		Item item=new Item(10,"Cabbage","vegetable",20,user);
		Mockito.when(itemRepository.findById(10)).thenReturn(Optional.of(item));
		itemService.getItemById(item.getItemId());
		assertThat(itemService.getItemById(item.getItemId())).isEqualTo(item);
	}
	@Test
	public void testSearchItem() {
		User user=new User(1,"9847572727","Tamilnadu","Chennai","600116","farmer","Senthil","abcd");
		ItemDetails item1=new ItemDetails(10,"Cabbage",20,user.getUsername(),user.getMobileNumber(),user.getId());
		ItemDetails item2=new ItemDetails(11,"Potato",25,user.getUsername(),user.getMobileNumber(),user.getId());
		List<ItemDetails> itemList=new ArrayList<>();
		itemList.add(item1);
		itemList.add(item2);
		Mockito.when(itemRepository.getItemDetailsBasedCity("Chennai")).thenReturn(itemList);
		itemService.searchItem("Chennai");
		assertThat(itemService.searchItem("Chennai")).isEqualTo(itemList);
	}
	@Test
//	@Disabled("ignore test")
	public void testAddEquipment() {
		User user=new User(2,"9873626111","Mumbai","Maharastra","600115","equipmentowner","Suresh","abcd");
		Equipment equipment=new Equipment(11,"Drone",10, 30000.0,"Tamilnadu","Chennai","Poru","600015","Rajesh","8187272711",user);
		Mockito.when(equipmentRepository.save(equipment)).thenReturn(equipment);    
	    assertThat(equipmentService.addEquipment(equipment)).isEqualTo(equipment);
	}
	@Test
//	@Disabled("ignore test")
	public void testSearchEquipment() {
		User user=new User(2,"9873626111","Mumbai","Maharastra","600115","equipmentowner","Suresh","abcd");
		Equipment equipment1=new Equipment(11,"Drone",10, 30000.0,"Tamilnadu","Chennai","Poru","600015","Rajesh","8187272711",user);
		Equipment equipment2=new Equipment(12,"Tractor",15, 35000.0,"Tamilnadu","Chennai","Tiru","300015","Ramesh","8344272711",user);
		List<Equipment> list=new ArrayList<>();
		list.add(equipment1);
		list.add(equipment2);
		Mockito.when(equipmentRepository.findByCity("Chennai")).thenReturn(list);
		assertThat(equipmentService.searchEquipment("Chennai")).isEqualTo(list);
	}
	@Test
	public void testGetAllEquipment(){
		List<Equipment> equipmentList=null;
		Mockito.when(equipmentRepository.findEquipmentByCount()).thenReturn(equipmentList);		
		assertThat(equipmentService.getAllEquipment()).isEqualTo(equipmentList);
	}

	@Test
	public void testDeleteEquipmentDetail(){
		Equipment equipment=new Equipment(); 
		Mockito.when(equipmentRepository.findById(1)).thenReturn(Optional.of(equipment));
	    equipmentService.deleteEquipmentDetail(equipment.getId());
	    assertFalse(equipmentRepository.existsById(equipment.getId()));
	}
	@Test
//	@Disabled("ignore test")
	public void testGetEquipmentById() {
		User user=new User(2,"9873626111","Mumbai","Maharastra","600115","equipmentowner","Suresh","abcd");
		Equipment equipment=new Equipment(11,"Drone",10, 30000.0,"Tamilnadu","Chennai","Poru","600015","Rajesh","8187272711",user);
		Mockito.when(equipmentRepository.findById(11)).thenReturn(Optional.of(equipment));
		equipmentService.getEquipmentById(equipment.getId());
		assertThat(equipmentService.getEquipmentById(equipment.getId())).isEqualTo(equipment);
	}

}