package com.pack.FarmToMarket.controller;
 
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
 
import com.pack.FarmToMarket.entity.User;
import com.pack.FarmToMarket.entity.Equipment;
import com.pack.FarmToMarket.entity.EquipmentDetails;
import com.pack.FarmToMarket.entity.EquipmentUser;
import com.pack.FarmToMarket.entity.Item;
import com.pack.FarmToMarket.entity.ItemDetails;
import com.pack.FarmToMarket.entity.Login;
import com.pack.FarmToMarket.service.EquipmentService;
import com.pack.FarmToMarket.service.ItemService;
import com.pack.FarmToMarket.service.UserService;
 
@RestController
@RequestMapping("/farmmarket")
public class FarmMarketController {
 
	@Autowired
	UserService userService;
 
	@Autowired 
	ItemService itemService;
	 @Autowired 
	 EquipmentService equipmentService;
 
	@PostMapping("/newuser")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		try {
			User saveUser1 = userService.saveUser(user);
			return new ResponseEntity<>(saveUser1, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
 
	@PostMapping("/login")
	public ResponseEntity<Object> checkLogin(@RequestBody Login login) {
		try {
			if (login.getRole().equals("trader")) {
				User user = userService.findUser(login.getUserid());
				String Upwd = user.getPassword();
				String Lpwd = login.getPassword();
				if (Lpwd.equals(Upwd)) {
					return new ResponseEntity<>(user, HttpStatus.OK);
				} else {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
			} else if (login.getRole().equals("farmer")) {
				User user = userService.findUser(login.getUserid());
				String Upwd = user.getPassword();
				String Lpwd = login.getPassword();
				if (Lpwd.equals(Upwd)) {
					return new ResponseEntity<>(user, HttpStatus.OK);
				} else {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
			} else if (login.getRole().equals("equipmentowner")) {
				User user = userService.findUser(login.getUserid());
				String Upwd = user.getPassword();
				String Lpwd = login.getPassword();
				if (Lpwd.equals(Upwd)) {
					return new ResponseEntity<>(user, HttpStatus.OK);
				} else {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
 
	@GetMapping("/search")
	public ResponseEntity<List<Equipment>> farmerSearchEquipment(@RequestParam("city") String city) {
		List<Equipment> list = equipmentService.searchEquipment(city);
		if (list.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	@PostMapping("/addNewEquipment/{id}")
	public ResponseEntity<Equipment> addNewEquipment(@RequestBody Equipment equipment, @PathVariable("id") Integer userId)
	{
		try {
			User user = userService.findUser(userId);
			if(user==null)
			{
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			else
			{
				equipment.setUser(user);
			}
			Equipment equipment1 = equipmentService.addEquipment(equipment);
			return new ResponseEntity<>(equipment1, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/getAllEquipment")
	public ResponseEntity<List<Equipment>> getAllEquipment()
	{
		try {
			List<Equipment> equipmentList= new ArrayList<>();
			equipmentService.getAllEquipment().forEach(equipmentList::add);
			if(equipmentList.isEmpty())
			{
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(equipmentList,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/getAllEquipments")
	public ResponseEntity<List<Equipment>> getAllEquipments() {
		try {
			List<Equipment> equipmentList= new ArrayList<>();
			equipmentService.getAllEquipment().forEach(equipmentList::add);
			if(equipmentList.isEmpty())
			{
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(equipmentList,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/getAllEquipmentById/{id}")
	public ResponseEntity<List<Equipment>> getAllEquipmentById(@PathVariable("id") Integer id)
	{
		try {
			//User user = userService.findUser(id);
		List<Equipment> equipmentList= equipmentService.getAllEquipmentById(id);
		if(equipmentList.isEmpty())
		{
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(equipmentList,HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	//change
	@PutMapping("/book/{equipId}/{userid}/{quantitycount}")
	public ResponseEntity<Equipment> bookEquipment(@PathVariable("equipId") Integer id,@PathVariable("quantitycount") Integer ecount,@PathVariable("userid")Integer uid) {
		try {
			Equipment equipment = equipmentService.getEquipmentById(id);
			User user = userService.findUser(uid);
			if(equipment.getId()!=null)
			{
				EquipmentUser eUser = new EquipmentUser(user,equipment,ecount);
				EquipmentUser equipmentUser = equipmentService.addEquipmentUser(eUser);
				equipment.setCount(equipment.getCount() - ecount);
				Equipment equipment1 = equipmentService.addEquipment(equipment);
				return new ResponseEntity<>(equipment1, HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@DeleteMapping("/deleteEquipmentDetails/{equipId}")
	public ResponseEntity<HttpStatus> deleteEquipmentDetails(@PathVariable("equipId") Integer equipId) {
		 try {
			equipmentService.deleteEquipmentDetail(equipId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} 
		 catch (NoSuchElementException e) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		 catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
 
//change
	@PutMapping("/equipmentDetails/{uid}")
	public ResponseEntity<Equipment> updateEquipmentDetails(
			@PathVariable("uid") Integer userId, @RequestBody Equipment equipment) {
	     try {
	    	 User user = userService.findUser(equipment.getUser().getId());
				/*
				 * if(user==null) { return new ResponseEntity<>(HttpStatus.NOT_FOUND); } else {
				 * equipment.setUser(user); }
				 */
		      Equipment equipment1 = equipmentService.getEquipmentById(equipment.getId());
		      if(equipment1.getUser().getId() == user.getId())
		      {	
		    	  equipment1.setCity(equipment.getCity());
		    	  equipment1.setContactPerson(equipment.getContactPerson());
		    	  equipment1.setCount(equipment.getCount());
		    	  equipment1.setMobileNumber(equipment.getMobileNumber());
		    	  equipment1.setName(equipment.getName());
		    	  equipment1.setPincode(equipment.getPincode());
		    	  equipment1.setRentPerDay(equipment.getRentPerDay());
		    	  equipment1.setState(equipment.getState());
		    	  equipment1.setVillage(equipment.getVillage());
		    	  Equipment updatedEquipment = equipmentService.addEquipment(equipment1);
		    	  return new ResponseEntity<>(updatedEquipment,HttpStatus.OK);
		      }
		      else
		      {
		    	  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		      }
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/getFarmer/{id}")
	public ResponseEntity<User> getFarmerDetails(@PathVariable("id") Integer id) {
        User user = userService.findUser(id);
        if(user!=null)
        {
        	return new ResponseEntity<>(user,HttpStatus.OK);
        }
        else
        {
        	return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	@PostMapping("/addNewItem/{id}")
	public ResponseEntity<Item> addNewItem(@RequestBody Item item, @PathVariable("id") Integer userId) {
		try {
			User user = userService.findUser(userId); 
//			if(user!=null)
//			{
//				item.setUser(user);
//			}
//			else
//			{
//				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//			}
			item.setUser(user);
		     Item item1 = itemService.addNewItem(item);
		     return new ResponseEntity<>(item1,HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/getAllItems")
	public ResponseEntity<List<ItemDetails>> getAllItems() {
		 try {
			List<ItemDetails> itemList = new ArrayList<>();
			itemService.getAllItems().forEach(itemList::add);
			if(itemList.isEmpty())
			{
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(itemList,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/getAllItemById/{id}")
	public ResponseEntity<List<Item>> getAllItemById(@PathVariable("id")Integer id) {
		try {
			//User user = userService.findUser(id);
			List<Item> itemList = new ArrayList<>();
			itemService.getAllItemById(id).forEach(itemList::add);
			if(itemList.isEmpty())
			{
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(itemList,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/itemDetails/{uid}")
	public ResponseEntity<Item> updateItemDetails(@PathVariable("uid") Integer userId,
			@RequestBody Item item) {
		try {
	    	 User user = userService.findUser(userId);
		      Item item1 = itemService.getItemById(item.getItemId());
		      if(item1.getUser().getId() == user.getId())
		      {	
		    	  item1.setItemName(item.getItemName());
		    	  item1.setItemDescription(item.getItemDescription());
		    	  item1.setItemQuantity(item.getItemQuantity());
		    	  Item updatedItem = itemService.addNewItem(item1);
		    	  return new ResponseEntity<>(updatedItem,HttpStatus.OK);
		      }
		      else
		      {
		    	  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		      }
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@DeleteMapping("/deleteItemDetails/{itemId}")
	public ResponseEntity<HttpStatus> deleteItemDetails(@PathVariable("itemId") Integer itemId) {
		 try {
			itemService.deleteItemDetails(itemId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} 
		 catch (NoSuchElementException e) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		 catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/searchItem")
	public ResponseEntity<List<ItemDetails>> traderSearchItem(@RequestParam("city") String city) {
		try {
			List<ItemDetails> itemList = new ArrayList<>();
			itemService.searchItem(city).forEach(itemList::add);
			if(itemList.isEmpty())
			{
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(itemList,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/orderItem/{itemId}")
	public ResponseEntity<ItemDetails> orderItem(@PathVariable("itemId") Integer id) {
	    try {
	    	Item item = itemService.getItemById(id);
			if(item.getItemId()!=null)
			{
				item.setItemQuantity(item.getItemQuantity() - 1);
				Item item1 = itemService.addNewItem(item);
				ItemDetails ID = new ItemDetails(item1.getItemId(),item1.getItemName(),item1.getItemQuantity(),item1.getUser().getUsername(),item1.getUser().getMobileNumber(),item1.getUser().getId());
				return new ResponseEntity<>(ID, HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	//change
	@PutMapping("/returnEquipment")
	public ResponseEntity<Equipment> returnEquipment(@RequestBody EquipmentDetails edetails) {
	      try {
			Equipment equipment = equipmentService.getEquipmentById(edetails.getEquipmentId());
			if(equipment.getId()!=null)
			{
				equipment.setCount(equipment.getCount() + edetails.getQuantityCount());
				equipmentService.deleteEquipmentUser(edetails.getBookingId());
				Equipment equipment1 = equipmentService.addEquipment(equipment);
				return new ResponseEntity<>(equipment1, HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/getHiredEquipment/{id}")
	public ResponseEntity<List<EquipmentDetails>> getHiredEquipment(@PathVariable("id")Integer id) {
		try {
//			Equipment equipment = equipmentService.getEquipmentById(id);
			List<EquipmentDetails> equipmentList = new ArrayList<>();
			equipmentService.getHiredEquipment(id).forEach(equipmentList::add);
			if(equipmentList.isEmpty())
			{
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(equipmentList,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}