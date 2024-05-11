package service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import entity.Project;
import entity.Role;
import entity.User;
import model.StatusCountModel;
import repository.RoleRepository;
import repository.UserRepository;

public class UserService {
	
	private RoleRepository roleRepository = new RoleRepository();
	private UserRepository userRepository = new UserRepository();
	
	public List<Role> getAllRole(){
		return roleRepository.getAll();
	}
	
	public boolean insertUser(User user) {
		int count = userRepository.save(user);
		
		return count > 0; // count > 0 --> return true; count <= 0 --> return false
	}
	
	public List<User> getAllUser(){
		return userRepository.getAllUser();
	}
	
	public User getUserById(int id){
		User user = userRepository.getUserById(id);
		return user;
	}
	
	public boolean deleteUserById(int id) {
		
		return userRepository.deleteUserById(id) > 0;// lon hon 0 la xoa thanh cong, be hon 0 la xoa that bai
		
	}
	
	public boolean updateUserById(User userEntity) {
		try {
	        int rowCount = userRepository.updateUserById(userEntity);
	        return rowCount > 0;
	    } catch (Exception e){
            System.out.println("Error " + e.getMessage());
	        return false;
	    }
	}
	
	public List<StatusCountModel> getAllListStatusCount(int id){
		return userRepository.getListStatusCount(id);
	}
}
