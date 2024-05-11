package service;

import java.util.List;

import entity.Role;
import entity.User;
import repository.RoleRepository;

public class RoleService {
	
	private RoleRepository roleRepository = new RoleRepository();
	
	public boolean addRole(String name, String description) {
		
		return roleRepository.insertRole(name, description) > 0;
		
	}
	
	public boolean deleteRole(int id) {
		
		return roleRepository.deleteRole(id) > 0;
		
	}
	
	public List<Role> getAllRole() {
		
		return roleRepository.getAll();
		
	}
	
	public Role getRoleById(int id){
		Role role = roleRepository.getRoleById(id);
		return role;
	}

	public boolean updateRoleById(Role roleEntity) {
		
		return roleRepository.updateRoleById(roleEntity) > 0;
		
	}
	
}
