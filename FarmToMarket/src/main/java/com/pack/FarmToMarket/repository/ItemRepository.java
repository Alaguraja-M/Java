package com.pack.FarmToMarket.repository;
 
import java.util.List;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
 
import com.pack.FarmToMarket.entity.Item;
import com.pack.FarmToMarket.entity.ItemDetails;
 
public interface ItemRepository extends JpaRepository<Item, Integer> {
	@Query("select new com.pack.FarmToMarket.entity.ItemDetails(i.itemId,i.itemName,i.itemQuantity,u.username,u.mobileNumber,u.id) from Item i,User u where i.user.id=u.id and i.itemQuantity>0")
	List<ItemDetails> findAllItems();
	@Query(value="select * from item i join user200 u on i.id = u.user_id where u.user_id = :id", nativeQuery=true)
	List<Item> findAllItem(@Param("id")Integer id);
	@Query("select new com.pack.FarmToMarket.entity.ItemDetails(i.itemId,i.itemName,i.itemQuantity,u.username,u.mobileNumber,u.id) from Item i,User u where i.user.id=u.id and u.city= :code")
//	@Query(value="select i.item_id,i.item_name,i.item_quantity,u.username,u.mobile_number,u.user_id from item i join user200 u on i.id=u.user_id where u.city= :code", nativeQuery=true)
	List<ItemDetails> getItemDetailsBasedCity(@Param("code")String code);
}