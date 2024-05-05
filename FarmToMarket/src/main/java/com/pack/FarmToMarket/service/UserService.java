package com.pack.FarmToMarket.service;
 
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.pack.FarmToMarket.entity.User;
import com.pack.FarmToMarket.repository.UserRepository;
 
@Service
public class UserService {
 
	@Autowired
	UserRepository userRepository;
	public User saveUser(User user)
	{
		return userRepository.save(user);
	}
	public User findUser(Integer userId) {
		return userRepository.findById(userId).get();
	}
}