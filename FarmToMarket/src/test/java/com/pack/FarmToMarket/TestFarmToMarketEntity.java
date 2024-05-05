package com.pack.FarmToMarket;
 
import static org.junit.Assert.assertEquals;
 
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
 
import com.pack.FarmToMarket.entity.Equipment;
import com.pack.FarmToMarket.entity.EquipmentUser;
import com.pack.FarmToMarket.entity.Item;
import com.pack.FarmToMarket.entity.User;
 
 
@RunWith(SpringRunner.class)
@DataJpaTest
public class TestFarmToMarketEntity {
 
	private User user;
	private User user1;
	private Item item;
	private Equipment equipment;
	private EquipmentUser equipmentUser;
 
	private static final String MOBILENUMBER = "9876543210";
	private static final String STATE = "Tamilnadu";
	private static final String CITY = "Chennai";
	private static final String PINCODE = "600116";
	private static final String ROLE = "farmer";
	private static final String EROLE = "equipmentowner";
	private static final String USERNAME = "Ramesh";
	private static final String PASSWORD = "abcd";
 
	private static final String ITEMNAME = "Potato";
	private static final String ITEMDESCRIPTION = "Vegetable";
	private static final Integer QUANTITY = 20;
 
	private static final String NAME = "Drone";
	private static final Integer COUNT = 20;
	private static final Double RENTPERDAY = 20000.0;
	private static final String ESTATE = "Tamilnadu";
	private static final String ECITY = "Chennai";
	private static final String VILLAGE = "Tiruvallur";
	private static final String EPINCODE = "600113";
	private static final String CONTACTPERSON = "Suresh";
	private static final String EMOBILENUMBER = "9826535155";
	private static final String STATUS = "Available";
 
	@Before
	public void setUp() {
		user = new User(MOBILENUMBER,STATE,CITY,PINCODE,ROLE,USERNAME,PASSWORD);
		user1 = new User(MOBILENUMBER,STATE,CITY,PINCODE,EROLE,USERNAME,PASSWORD);
 
		item = new Item(ITEMNAME,ITEMDESCRIPTION,QUANTITY,user);
 
		equipment = new Equipment(NAME,COUNT,RENTPERDAY,ESTATE,ECITY,VILLAGE,EPINCODE,CONTACTPERSON,EMOBILENUMBER,user1);
		equipmentUser=new EquipmentUser(user,equipment,QUANTITY);
	}
 
	@Test
	public void testUser() {
		assertEquals(MOBILENUMBER, user.getMobileNumber());
		assertEquals(STATE, user.getState());
		assertEquals(CITY, user.getCity());
		assertEquals(ROLE, user.getRole());
		assertEquals(USERNAME, user.getUsername());
	}
 
	@Test
	public void testItem() {
		assertEquals(ITEMNAME, item.getItemName());
		assertEquals(USERNAME, item.getUser().getUsername());
	}
 
	@Test
	public void testEquipment() {
        assertEquals(NAME,equipment.getName());
        assertEquals(EROLE,equipment.getUser().getRole());
	}
	@Test
	public void testEquipmentUser() {
		assertEquals(user.getUsername(),equipmentUser.getUser().getUsername());
		assertEquals(equipment.getName(),equipmentUser.getEquipment().getName());
	}
}