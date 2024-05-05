package com.pack.FarmToMarket;
 
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
 
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
 
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
 
import com.pack.FarmToMarket.entity.Equipment;
import com.pack.FarmToMarket.entity.EquipmentDetails;
import com.pack.FarmToMarket.entity.EquipmentUser;
import com.pack.FarmToMarket.entity.Item;
import com.pack.FarmToMarket.entity.ItemDetails;
import com.pack.FarmToMarket.entity.User;
import com.pack.FarmToMarket.repository.EquipmentRepository;
import com.pack.FarmToMarket.repository.EquipmentUserRepository;
import com.pack.FarmToMarket.repository.ItemRepository;
import com.pack.FarmToMarket.repository.UserRepository;
 
 
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestFarmToMarketRepository {
	private User user,user1,user2;
	private Item item;
 
	@Autowired
	UserRepository userRepository;
	@Autowired
	ItemRepository itemRepository;
	@Autowired
	EquipmentRepository equipmentRepository;
	@Autowired
	EquipmentUserRepository equipmentUserRepository;
	@Before
	public void setUp() {
		user = new User("9847646122","Tamilnadu","Chennai","600116","farmer","Rajesh","abcd");
		user1 = new User("8847646122","Maharastra","Mumbai","220116","equipmentowner","Ramesh","abcd");
		user2 = new User("734566122","Tamilnadu","Coimbatore","640116","trader","Rakesh","abcd");
	     item=new Item("Potato","Vegetable",25,user);
	}
	@Test
	public void testAddUser() {
		User savedInDb = userRepository.save(user);
		Optional<User> data = userRepository.findById(savedInDb.getId());
        User getFromDb = (User)data.get();
        assertEquals(savedInDb.getId(),getFromDb.getId());
	}
	@Test
	public void testFindUser() {
		User savedInDb = userRepository.save(user);
		Optional<User> data = userRepository.findById(savedInDb.getId());
        User getFromDb = (User)data.get();
        assertThat(getFromDb.getId()).isEqualTo(savedInDb.getId());       
	}
	@Test
	public void testAddItem() {
		User savedInDb = userRepository.save(user);	
		Item savedItemDb=itemRepository.save(item);
		assertThat(savedItemDb.getItemName()).isEqualTo("Potato");     
	}
	@Test 
	public void testGetAllItems() {
		List<ItemDetails> itemList=itemRepository.findAllItems();
		assertThat(itemList.size()).isEqualTo(1);
	}
	@Test
	public void testGetItemById() {
		User savedInDb = userRepository.save(user);
		Item savedItemDb=itemRepository.save(item);
		Item item=itemRepository.findById(savedItemDb.getItemId()).get();
		assertThat(item.getItemId()).isEqualTo(item.getItemId());
	}
	@Test
	public void testGetAllItemsById() {
		User savedInDb = userRepository.save(user);	
		Item savedItemDb=itemRepository.save(item);
		List<Item> list=itemRepository.findAllItem(savedInDb.getId());
		assertThat(list.size()).isEqualTo(1);
	}
    @Test
    public void testItemCitySearch() {
    	List<ItemDetails> list=itemRepository.getItemDetailsBasedCity("Chennai");
        List<ItemDetails> iList = new ArrayList<>();
		System.out.println(list.size());
		for (ItemDetails item : list) {
			iList.add(item);
		}
		assertThat(iList.size()).isEqualTo(list.size());
    }
	@Test
	public void testItemDeleteItemDetails() {
		User savedInDb = userRepository.save(user);
		Item savedItemDb=itemRepository.save(item);
		Item item=itemRepository.findById(savedItemDb.getItemId()).get();
		itemRepository.deleteById(item.getItemId());
		List<Item> itemList=itemRepository.findAll();
		assertThat(itemList.size()).isEqualTo(3);
	}
	@Test
	public void testAddEquipment() {
		User savedInDb = userRepository.save(user1);
		Equipment e = new Equipment(100,"Drone",20,30000.0,"Kerela","Cochin","Trivandrum","300021","John","8736514243",user1);
		Equipment equipment=equipmentRepository.save(e);
		assertEquals(e.getName(),equipment.getName());
		assertEquals(e.getUser().getUsername(),equipment.getUser().getUsername());
	}
	@Test
	public void testSearchEquipment() {
		List<Equipment> list=equipmentRepository.findByCity("Cochin");
		assertEquals("Drone",list.get(0).getName());
	}
	@Test
	public void testGetAllEquipment() {
		List<Equipment> list=equipmentRepository.findEquipmentByCount();
		List<Equipment> elist=new ArrayList<>();
		for(Equipment e:list) {
			elist.add(e);
		}
		assertEquals(elist.size(),list.size());
	}
	@Test
	public void testGetAllEquipmentById() {
		User savedInDb = userRepository.save(user1);
		Equipment e = new Equipment(100,"Drone",20,30000.0,"Kerela","Cochin","Trivandrum","300021","John","8736514243",user1);
		Equipment e1 = new Equipment(101,"Sprayer",20,30000.0,"Kerela","Cochin","Trivandrum","300021","John","8736514243",user1);
		List<Equipment> elist=new ArrayList<>();
		elist.add(e);
		elist.add(e1);
		equipmentRepository.saveAll(elist);
		List<Equipment> list=equipmentRepository.findAllEquipment(savedInDb.getId());
		assertEquals(list.size(),2);
	}
	@Test
	public void testAddEquipmentUser() {
		User savedInDb = userRepository.save(user1);
		Equipment e = new Equipment("Drone",20,30000.0,"Kerela","Cochin","Trivandrum","300021","John","8736514243",user1);
		equipmentRepository.save(e);
		EquipmentUser euser=new EquipmentUser(savedInDb,e,20);
		equipmentUserRepository.save(euser);
		assertEquals(euser.getEquipment().getName(),"Drone");
	}
	@Test
	public void testGetHiredEquipment() {
		User savedInDb = userRepository.save(user1);
		Equipment e = new Equipment("Drone",20,30000.0,"Kerela","Cochin","Trivandrum","300021","John","8736514243",user1);
		equipmentRepository.save(e);
		EquipmentUser euser=new EquipmentUser(savedInDb,e,20);
		equipmentUserRepository.save(euser);
		List<EquipmentDetails> elist=equipmentRepository.findHiredEquipment(savedInDb.getId());
		assertEquals(elist.get(0).getQuantityCount(),euser.getQuantity());				
	}
	@Test
	public void testDeleteEquipmentUser() {
		User savedInDb = userRepository.save(user1);
		Equipment e = new Equipment("Drone",20,30000.0,"Kerela","Cochin","Trivandrum","300021","John","8736514243",user1);
		equipmentRepository.save(e);
		EquipmentUser euser=new EquipmentUser(savedInDb,e,20);
		equipmentUserRepository.save(euser);
		equipmentUserRepository.deleteById(euser.getId());
		Optional eusr=equipmentUserRepository.findById(euser.getId());
		assertTrue(eusr.isEmpty());
	}
}