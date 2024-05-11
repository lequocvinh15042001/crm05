package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.MySQLConfig;
import entity.Role;
import entity.User;

// Quan ly tat ca cac cau truy van lien quan den bang role
public class RoleRepository {

	public List<Role> getAll() {
		
		List<Role> listRole = new ArrayList<Role>();
		
		try {
			
			String query = "SELECT * FROM roles";
			Connection connection = MySQLConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				Role role = new Role();
				role.setId(resultSet.getInt("id"));
				role.setName(resultSet.getString("name"));
				role.setDescription(resultSet.getString("description"));
				
				listRole.add(role);
				
			}
		
		} catch (Exception e) {
			System.out.println("Loi truy cap roles " + e.getLocalizedMessage());
		}
		
		return listRole;
		
	}
	
	public int insertRole(String name, String description) {
		
		int count = 0;
		
		try {
			
			String query = "INSERT INTO roles(name, description)\n"
					+ "VALUES('" + name +"', '" + description +"')";
			
			Connection connection = MySQLConfig.getConnection();
			
			PreparedStatement statement = connection.prepareStatement(query);
			
			count = statement.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("Loi them quyen " + e.getLocalizedMessage());
		}
		
		return count;
	}
	
	public int deleteRole(int id) {
		
		int count = 0;
		
		try {
			
			String query = "DELETE  FROM roles r WHERE r.id = "+ id +";";
			Connection connection = MySQLConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			count = statement.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("Loi xoa quyen " + e.getLocalizedMessage());
		}
		
		return count;
	}
	
	public int updateRoleById(Role roleEntity) {
		
		int count = 0;
		
		try {
			
			String query = "UPDATE roles SET name =?, description =? WHERE id = ?";
			Connection connection = MySQLConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, roleEntity.getName());
			statement.setString(2, roleEntity.getDescription());
			statement.setInt(3, roleEntity.getId());
			count = statement.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("Loi cap nhat quyen " + e.getLocalizedMessage());
		}
		
		return count;
	}
	
	public Role getRoleById(int id) {
		
		Role role = new Role();
		
		try {
			String query = "SELECT * FROM roles WHERE id = ?";
			Connection connection = MySQLConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();

			while(resultSet.next()) {
				role.setId(resultSet.getInt("id"));
				role.setName(resultSet.getString("name"));
				role.setDescription(resultSet.getString("description"));
			}
	
			connection.close();
			
		} catch (Exception e) {
			System.out.println("Loi lay thong tin role " + e.getLocalizedMessage());
		}
		
		return role;
	}
	
}
