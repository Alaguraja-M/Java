package com.pack.FarmToMarket;
 
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
 
import java.util.ArrayList;
import java.util.List;
 
import org.junit.Before;
import org.junit.Test;
//import org.junit.jupiter.api.Disabled;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
 
import com.pack.FarmToMarket.controller.FarmMarketController;
import com.pack.FarmToMarket.entity.Equipment;
import com.pack.FarmToMarket.entity.Item;
import com.pack.FarmToMarket.entity.ItemDetails;
import com.pack.FarmToMarket.entity.Login;
import com.pack.FarmToMarket.entity.User;
import com.pack.FarmToMarket.service.EquipmentService;
import com.pack.FarmToMarket.service.ItemService;
import com.pack.FarmToMarket.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
 
 
@RunWith(SpringRunner.class)
@WebMvcTest(value = FarmMarketController.class)
public class TestFarmToMarketController {
 
	private User user, user1, user2;
 
	@Autowired
	private MockMvc mockMvc; // test the controller
 
	@MockBean
	private UserService userService;
 
	@MockBean
	EquipmentService equipmentService;
 
	@MockBean
	ItemService itemService;
 
	@Autowired
	private ObjectMapper objectMapper;
 
	@Before
	public void setUp() {
		user=new User(1,"9847646122","Tamilnadu","Chennai","600116","farmer","Rajesh","abcd");
		user1=new User(2,"8847646122","Maharastra","Mumbai","220116","equipmentowner","Ramesh","abcd");
		user2=new User(3,"734566122","Tamilnadu","Coimbatore","640116","farmer","Rakesh","abcd");
	}
	@Test
	public void testLogin() throws Exception {
		Login login1=new Login(1,"abcd","farmer");
		given(userService.findUser(login1.getUserid())).willReturn(user);
		ResultActions response = mockMvc.perform(post("/farmmarket/login")
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(login1)));
 
		response.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.role", is(login1.getRole())));	
	}
 
	@Test
	public void testCreateUser() throws Exception {
		given(userService.saveUser(any(User.class))).willAnswer((invocation) -> invocation.getArgument(0));
 
		ResultActions response = mockMvc.perform(post("/farmmarket/newuser").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(user)));
 
		response.andDo(print()).andExpect(status().isCreated())
				.andExpect(jsonPath("$.mobileNumber", is(user.getMobileNumber())))
				.andExpect(jsonPath("$.state", is(user.getState()))).andExpect(jsonPath("$.city", is(user.getCity())))
				.andExpect(jsonPath("$.role", is(user.getRole())))
				.andExpect(jsonPath("$.username", is(user.getUsername())));
	}
 
	@Test
	public void testFarmerSearchEquipment() throws Exception {
		List<Equipment> list = new ArrayList<>();
		list.add(new Equipment(100,"Drone",20,30000.0,"Kerela","Cochin","Trivandrum","300021","John","8736514243",user1));
		list.add(new Equipment(101,"Tractor",25,25000.0,"Kerela","Cochin","Trivandrum","200021","Jack","7536514243",user1));
		list.add(new Equipment(102,"Sprayer",30,27000.0,"Kerela","Cochin","Trivandrum","600021","Jim","6736514243",user1));
		given(equipmentService.searchEquipment("Cochin")).willReturn(list);
 
		ResultActions response = mockMvc.perform(get("/farmmarket/search").param("city", "Cochin"));
 
		response.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.size()", is(list.size())))
				.andExpect(jsonPath("$[0].name").value("Drone")).andExpect(jsonPath("$[0].count").value(20))
				.andExpect(jsonPath("$[1].name").value("Tractor")).andExpect(jsonPath("$[1].count").value(25))
				.andExpect(jsonPath("$[2].name").value("Sprayer")).andExpect(jsonPath("$[2].count").value(30));
	}
 
	@Test
	public void testGetAllEquipment() throws Exception {
		List<Equipment> listOfEquipment = new ArrayList<>();
		listOfEquipment.add(new Equipment(100,"Drone",20,30000.0,"Kerela","Cochin","Trivandrum","300021","John","8736514243",user1));
		listOfEquipment.add(new Equipment(101,"Tractor",25,25000.0,"Kerela","Cochin","Trivandrum","200021","Jack","7536514243",user1));
		listOfEquipment.add(new Equipment(102,"Sprayer",30,27000.0,"Kerela","Cochin","Trivandrum","600021","Jim","6736514243",user1));
		given(equipmentService.getAllEquipment()).willReturn(listOfEquipment);
 
		ResultActions response = mockMvc.perform(get("/farmmarket/getAllEquipment"));
 
		response.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.size()", is(listOfEquipment.size())));
	}
 
	@Test
	public void testgetAllEquipmentById() throws Exception {
		Equipment e = new Equipment(100,"Drone",20,30000.0,"Kerela","Cochin","Trivandrum","300021","John","8736514243",user1);
		Equipment e1 = new Equipment(101,"Tractor",25,50000.0,"Kerela","Cochin","Trivandrum","300021","Jack","7736514243",user1);
		List<Equipment> list=new ArrayList<>();
		list.add(e);
		list.add(e1);
		given(equipmentService.getAllEquipmentById(user1.getId())).willReturn(list);
		ResultActions response = mockMvc.perform(get("/farmmarket/getAllEquipmentById/{id}", user1.getId()));
 
		response.andExpect(status().isOk()).andDo(print())
		        .andExpect(jsonPath("$[0].name").value("Drone"))
				.andExpect(jsonPath("$.size()", is(list.size())));
	}
	@Test
	public void testGetAllItemById() throws Exception {
		Item item1 = new Item(1000,"Potato","Vegetable",50,user);
		Item item2 = new Item(1001,"Tomato","Cherry Tomato",30,user);
		List<Item> itemList=new ArrayList<>();
		itemList.add(item1);
		itemList.add(item2);
        given(itemService.getAllItemById(user.getId())).willReturn(itemList);
		ResultActions response = mockMvc.perform(get("/farmmarket/getAllItemById/{id}", user.getId()));
		response.andExpect(status().isOk()).andDo(print())
		          .andExpect(jsonPath("$[0].itemName").value("Potato"))
		          .andExpect(jsonPath("$.size()", is(itemList.size())));
	}
	@Test
//	@Disabled("ignore test")
	public void testBookEquipment() throws Exception {
		int ecount=2;
		Equipment e = new Equipment(100,"Drone",20,30000.0,"Kerela","Cochin","Trivandrum","300021","John","8736514243",user1);
		given(equipmentService.getEquipmentById(e.getId())).willReturn(e);
		e.setCount(e.getCount() - ecount);
		//e.setStatus("Booked");
		given(equipmentService.addEquipment(any(Equipment.class)))
				.willAnswer((invocation) -> invocation.getArgument(0));
		ResultActions response = mockMvc.perform(put("/farmmarket/book/{equipId}/{userid}/{quantitycount}", e.getId(),e.getUser().getId(),ecount)
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(e)));
 
		response.andExpect(status().isOk()).andDo(print())
		        .andExpect(jsonPath("$.name", is(e.getName())))
				.andExpect(jsonPath("$.count", is(e.getCount())));
	}
 
	@Test
	public void testAddNewItem() throws Exception {
		Item item = new Item(1000,"Potato","Vegetable",50,user);
		given(itemService.addNewItem(any(Item.class))).willAnswer((invocation) -> invocation.getArgument(0));
 
		ResultActions response = mockMvc.perform(post("/farmmarket/addNewItem/{id}",user.getId())
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(item)));
 
		response.andDo(print()).andExpect(status().isCreated())
				.andExpect(jsonPath("$.itemName", is(item.getItemName())))
				.andExpect(jsonPath("$.itemQuantity", is(item.getItemQuantity())));
	}
 
	@Test
	public void testGetAllItems() throws Exception {
		List<ItemDetails> itemList = new ArrayList<>();
		Item item1 = new Item(1000,"Potato","Vegetable",50,user);
		Item item2 = new Item(1001,"Tomato","Cherry Tomato",30,user);
		itemList.add(
				new ItemDetails(item1.getItemId(),item1.getItemName(),item1.getItemQuantity(),item1.getUser().getUsername(),item1.getUser().getMobileNumber(),item1.getUser().getId()));
		itemList.add(
				new ItemDetails(item2.getItemId(),item2.getItemName(),item2.getItemQuantity(),item2.getUser().getUsername(),item2.getUser().getMobileNumber(),item2.getUser().getId()));
 
		given(itemService.getAllItems()).willReturn(itemList);
 
		ResultActions response = mockMvc.perform(get("/farmmarket/getAllItems"));
 
		response.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.size()", is(itemList.size())));
	}
 
@Test
	public void testDeleteItemDetails() throws Exception {
		Item item = new Item(1000,"Potato","Vegetable",25,user);
		willDoNothing().given(itemService).deleteItemDetails(item.getItemId());
 
		ResultActions response = mockMvc.perform(delete("/farmmarket/deleteItemDetails/{itemId}", item.getItemId()));
		response.andExpect(status().isNoContent()).andDo(print());
	}
	@Test
	public void testUpdateItemDetails() throws Exception {
		Item item = new Item(1000,"Potato","Vegetable",50,user);
		Item updatedItem = new Item(1000,"Potato","Vegetable curry",20,user);
		given(userService.findUser(user.getId())).willReturn(user);
		given(itemService.getItemById(item.getItemId())).willReturn(item);
        given(itemService.addNewItem(any(Item.class)))
                .willAnswer((invocation)-> invocation.getArgument(0));
 
        ResultActions response = mockMvc.perform(put("/farmmarket/itemDetails/{uid}",user.getId())
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(updatedItem)));
 
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.itemName", is(updatedItem.getItemName())))
                .andExpect(jsonPath("$.itemDescription", is(updatedItem.getItemDescription())))
                .andExpect(jsonPath("$.itemQuantity", is(updatedItem.getItemQuantity())));
	}
	@Test
	public void testAddNewEquipment() throws Exception {
		Equipment e = new Equipment(100,"Drone",20,30000.0,"Kerela","Cochin","Trivandrum","300021","John","8736514243",user1);
		given(userService.findUser(user.getId())).willReturn(user);
		given(equipmentService.addEquipment(any(Equipment.class))).willAnswer((invocation) -> invocation.getArgument(0));
		ResultActions response = mockMvc.perform(post("/farmmarket/addNewEquipment/{id}",user.getId())
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(e)));
 
		response.andDo(print()).andExpect(status().isCreated())
				.andExpect(jsonPath("$.name", is(e.getName())))
				.andExpect(jsonPath("$.count", is(e.getCount())));
	}
	@Test
	public void testGetAllEquipments() throws Exception {
		List<Equipment> eList = new ArrayList<>();
		eList.add(new Equipment(100,"Drone",20,30000.0,"Kerela","Cochin","Trivandrum","300021","John","8736514243",user1));
		eList.add(new Equipment(101,"Tractor",25,25000.0,"Kerela","Cochin","Trivandrum","200021","Jack","7536514243",user1));
		eList.add(new Equipment(102,"Sprayer",30,27000.0,"Kerela","Cochin","Trivandrum","600021","Jim","6736514243",user1));
		given(equipmentService.getAllEquipment()).willReturn(eList);
 
		ResultActions response = mockMvc.perform(get("/farmmarket/getAllEquipments"));
 
		response.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.size()", is(eList.size())));
	}
	@Test
	public void testDeleteEquipmentDetails() throws Exception {
		Equipment e=new Equipment(100,"Drone",20,30000.0,"Kerela","Cochin","Trivandrum","300021","John","8736514243",user1);
		willDoNothing().given(equipmentService).deleteEquipmentDetail(e.getId());
 
		ResultActions response = mockMvc.perform(delete("/farmmarket/deleteEquipmentDetails/{equipId}", e.getId()));
		response.andExpect(status().isNoContent()).andDo(print());
	}
	@Test
	public void testUpdateEquipmentDetails() throws Exception {
		Equipment equipment = new Equipment(100,"Drone",20,30000.0,"Kerela","Cochin","Trivandrum","300021","John","8736514243",user1);
		Equipment updatedEquipment = new Equipment(100,"Drone",25,35000.0,"Kerela","Cochin","Trivandrum","300021","John","8736514243",user1);
		given(userService.findUser(user1.getId())).willReturn(user1);
		given(equipmentService.getEquipmentById(equipment.getId())).willReturn(equipment);
        given(equipmentService.addEquipment(any(Equipment.class)))
                .willAnswer((invocation)-> invocation.getArgument(0));
 
        ResultActions response = mockMvc.perform(put("/farmmarket/equipmentDetails/{uid}", equipment.getId(),user1.getId())
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(updatedEquipment)));
 
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.name", is(updatedEquipment.getName())))
                .andExpect(jsonPath("$.count", is(updatedEquipment.getCount())))
                .andExpect(jsonPath("$.rentPerDay", is(updatedEquipment.getRentPerDay())));
	}
	/*@Test
	public void testReturnEquipment() throws Exception {
		Equipment e = Equipment.builder().id(100).name("Drone").count(20).rentPerDay(30000.0).state("Kerela")
				.city("Cochin").village("Trivandrum").pinCode("300021").contactPerson("John").mobileNumber("8736514243")
				.status("Available").user(user1).build();
		EquipmentDetails edetails=EquipmentDetails.builder().bookingId(1).equipmentId(100).equipmentName("Drone").contactPerson("John").mobileNumber("8736514243").quantityCount(2).build();
		given(equipmentService.getEquipmentById(edetails.getEquipmentId())).willReturn(e);
		e.setCount(e.getCount() + edetails.getQuantityCount());
		equipmentService.deleteEquipmentDetail(edetails.getBookingId());		
		given(equipmentService.addEquipment(any(Equipment.class)))
				.willAnswer((invocation) -> invocation.getArgument(0));
		ResultActions response = mockMvc.perform(put("/farmmarket/returnEquipment")
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(e)));
 
		response.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.name", is(e.getName())))
				.andExpect(jsonPath("$.count", is(e.getCount())));
	}*/
	@Test
	public void testGetFarmerDetails() throws Exception {
		given(userService.findUser(user.getId())).willReturn(user);
		ResultActions response = mockMvc.perform(get("/farmmarket/getFarmer/{id}",user.getId()));
		response.andExpect(status().isOk())
		        .andDo(print())
		        .andExpect(jsonPath("$.username", is(user.getUsername())))
		        .andExpect(jsonPath("$.role", is(user.getRole())));
	}
	@Test
	public void testTraderSearchItem() throws Exception {
		List<ItemDetails> itemList = new ArrayList<>();
		Item item1 = new Item(1000,"Potato","Vegetable",50,user);
		Item item2 = new Item(1001,"Tomato","Cherry Tomato",30,user);
 
		itemList.add(
				new ItemDetails(item1.getItemId(),item1.getItemName(),item1.getItemQuantity(),item1.getUser().getUsername(),item1.getUser().getMobileNumber(),item1.getUser().getId()));
		itemList.add(
				new ItemDetails(item2.getItemId(),item2.getItemName(),item2.getItemQuantity(),item2.getUser().getUsername(),item2.getUser().getMobileNumber(),item2.getUser().getId()));
		given(itemService.searchItem("Chennai")).willReturn(itemList);
 
		ResultActions response = mockMvc.perform(get("/farmmarket/searchItem").param("city", "Chennai"));
 
		response.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.size()", is(itemList.size())));
 
	}
	@Test
	public void testOrderItem() throws Exception {
		Item item=new Item(1000,"Potato","Vegetable",25,user);
		given(itemService.getItemById(item.getItemId())).willReturn(item);
		item.setItemQuantity(item.getItemQuantity() - 1);
		given(itemService.addNewItem(any(Item.class)))
				.willAnswer((invocation) -> invocation.getArgument(0));
		ResultActions response = mockMvc.perform(put("/farmmarket/orderItem/{itemId}", item.getItemId())
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(item)));
 
		response.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.itemName", is(item.getItemName())))
				.andExpect(jsonPath("$.itemQuantity", is(item.getItemQuantity())));
	}

}