package com.pack.FarmToMarket.service;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.pack.FarmToMarket.entity.Equipment;
import com.pack.FarmToMarket.entity.EquipmentDetails;
import com.pack.FarmToMarket.entity.EquipmentUser;
import com.pack.FarmToMarket.repository.EquipmentRepository;
import com.pack.FarmToMarket.repository.EquipmentUserRepository;
 
@Service
public class EquipmentService {
 
	@Autowired
	EquipmentRepository equipmentRepository;
	@Autowired
	EquipmentUserRepository equipmentUserRepository;
	public List<Equipment> searchEquipment(String city) {
		return equipmentRepository.findByCity(city);
	}
	public Equipment addEquipment(Equipment e)
	{
		/*
		 * return equipmentRepository.save(new Equipment(e.getName(), e.getCount(),
		 * e.getRentPerDay(), e.getState(), e.getCity(), e.getVillage(), e.getPincode(),
		 * e.getContactPerson(), e.getMobileNumber(), e.getUser()));
		 */
		return equipmentRepository.save(e);
	}
	public List<Equipment> getAllEquipment()
	{
		return equipmentRepository.findEquipmentByCount();
	}
	public List<Equipment> getAllEquipmentById(Integer id)
	{
		return equipmentRepository.findAllEquipment(id);
	}
	public Equipment getEquipmentById(Integer id)
	{
		return equipmentRepository.findById(id).get();
	}
	public void deleteEquipmentDetail(Integer equipId)
	{
		equipmentRepository.deleteById(equipId);
	}
public void deleteEquipmentUser(Integer bookingId) {
	equipmentUserRepository.deleteById(bookingId);
	}
	public List<EquipmentDetails> getHiredEquipment(Integer uid){
		return equipmentRepository.findHiredEquipment(uid);
	}
	public EquipmentUser addEquipmentUser(EquipmentUser euser) {
		/*
		 * return equipmentUserRepository.save(new
		 * EquipmentUser(euser.getUser(),euser.getEquipment(), euser.getQuantity()));
		 */
		return equipmentUserRepository.save(euser);
	}
}