package com.pack.FarmToMarket.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;
 
import com.pack.FarmToMarket.entity.User;
 
public interface UserRepository extends JpaRepository<User, Integer> {
 
}