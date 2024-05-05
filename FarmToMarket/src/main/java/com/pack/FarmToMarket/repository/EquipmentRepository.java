package com.pack.FarmToMarket.repository;
 
import java.util.List;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
 
import com.pack.FarmToMarket.entity.Equipment;
import com.pack.FarmToMarket.entity.EquipmentDetails;
 
public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {
	@Query(value = "SELECT * FROM EQUIPMENT where city = :city", nativeQuery = true)
	List<Equipment> findByCity(@Param("city")String code);
	 @Query(value = "SELECT * FROM EQUIPMENT e join USER200 u on e.id = u.user_id where u.user_id = ?1", nativeQuery=true)
	List<Equipment> findAllEquipment(Integer id);
	 @Query(value = "select * from equipment where count > 0", nativeQuery=true)
	 List<Equipment> findEquipmentByCount();
	 //change
	 @Query("select new com.pack.FarmToMarket.entity.EquipmentDetails(eu.id,e.id,e.name,e.contactPerson,e.mobileNumber,e.count) from Equipment e,User u,EquipmentUser eu where e.id=eu.equipment.id and u.id = :uid")
//	 @Query(value="select e.id,i.equipment_Id,i.name,i.contact_Person,i.mobile_Number,i.count from Equipment i join Equipment_User e on i.id = e.user_id join user200 u on u.user_id = e.user_id\r\n"
//	 		+ " where u.user_id = 1", nativeQuery=true)
	 List<EquipmentDetails> findHiredEquipment(@Param("uid")Integer id);
}