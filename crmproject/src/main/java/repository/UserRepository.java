package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.MySQLConfig;
import entity.Project;
import entity.Role;
import entity.User;
import model.StatusCountModel;

public class UserRepository {

	// int cho excuteUpdate
	public int deleteUserById(int id) {
		int count = 0;
		String query = "DELETE FROM users u WHERE u.id = " + id;
		
		try {
			
			Connection connection = MySQLConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			count = statement.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("Loi Xoa user " + e.getLocalizedMessage());
		}
		
		
		
		return count;
	}
	
	//List cho excuteQuery
	public List<User> getAllUser(){
		
		List<User> listUser = new ArrayList<User>();
		try {
			
			String query = "SELECT u.id, u.first_name, u.last_name, u.email, r.name as role_name, u.fullname \n"
					+ "FROM users u \n"
					+ "JOIN roles r ON u.id_role = r.id;";
			
			Connection connection = MySQLConfig.getConnection();
			
			PreparedStatement statement = connection.prepareStatement(query);
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				
				User user = new User();
				user.setId(resultSet.getInt("id"));
				user.setEmail(resultSet.getString("email"));
				user.setFirstName(resultSet.getString("first_name"));
				user.setLastName(resultSet.getString("last_name"));
				user.setFullname(resultSet.getString("fullname"));
				
				Role role = new Role();
				role.setName(resultSet.getString("role_name"));
				
				user.setRole(role);
				
				listUser.add(user);
								
			}
			
		} catch (Exception e) {
			System.out.println("Loi select list user " + e.getLocalizedMessage());
		}
		
		return listUser;
		
	}
	
	public int save(User user) {
		
		int count = 0;
		
		try {
			System.out.println(user.getFullname() +"','"+ user.getEmail() +"','"+ user.getPassword() +"','"+ user.getPhone() +"',"+ user.getRole().getId());
			String query = "INSERT INTO users(fullname,email,password,phone, id_role) \n"
					+ "VALUES('"+ user.getFullname() +"','"+ user.getEmail() +"','"+ user.getPassword() +"','"+ user.getPhone() +"',"+ user.getRole().getId() + ")";
			
			Connection connection = MySQLConfig.getConnection();
			
			PreparedStatement statement = connection.prepareStatement(query);
			
			count = statement.executeUpdate();
			
			
		} catch (Exception e) {
			System.out.println("Loi insert user " + e.getLocalizedMessage());
		}
		
		return count;
	}
	
	public User getUserById(int id) {
		
		User user = new User();
		
		try {
			String query = "SELECT * FROM users WHERE id = ?";
			
			Connection connection = MySQLConfig.getConnection();
			
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setInt(1, id);
			
			ResultSet resultSet = statement.executeQuery();

			while(resultSet.next()) {
				user.setId(resultSet.getInt("id"));
				user.setFirstName(resultSet.getString("first_name"));
				user.setLastName(resultSet.getString("last_name"));
				user.setFullname(resultSet.getString("fullname"));
				user.setEmail(resultSet.getString("email"));
				user.setPhone(resultSet.getString("phone"));
				user.setPassword(resultSet.getString("password"));
				Role role = new Role();
				role.setId(resultSet.getInt("id_role"));
				user.setRole(role);
			}
			
			connection.close();
			
		} catch (Exception e) {
			System.out.println("Loi lay thong tin user " + e.getLocalizedMessage());
		}
		
		return user;
	}
	
	public int updateUserById(User userEntity) {
		int count = 0;
		
        try {
            String query = "UPDATE users SET fullname = ?, email = ?, password = ?, phone = ?, id_role = ? WHERE id = ?";
            Connection connection = MySQLConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, userEntity.getFullname());
            statement.setString(2, userEntity.getEmail());
            statement.setString(3, userEntity.getPassword());
            statement.setString(4, userEntity.getPhone());
            statement.setInt(5, userEntity.getRole().getId());
            statement.setInt(6, userEntity.getId());
            
            System.out.println("Kiem tra role "+ userEntity.getId());
            
            count = statement.executeUpdate();
            
            connection.close();
        } catch (Exception e) {
        	System.out.println("Loi cap nhat thong tin user " + e.getLocalizedMessage());
        }
        return count;
	}
	
	public List<StatusCountModel> getListStatusCount(int id){
		List<StatusCountModel> list = new ArrayList<StatusCountModel>();
		
		try {
			String query = "SELECT\n"
					+ "    status.name AS status_name,\n"
					+ "    COUNT(*) AS count\n"
					+ "FROM\n"
					+ "    task\n"
					+ "INNER JOIN\n"
					+ "    status ON task.id_status = status.id\n"
					+ "INNER JOIN\n"
					+ "    assigntask ON task.id = assigntask.id_task\n"
					+ "WHERE\n"
					+ "    assigntask.id_user = ?\n"
					+ "GROUP BY\n"
					+ "    status.name;";
			
			Connection connection = MySQLConfig.getConnection();
			
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				
				StatusCountModel statusCountModel = new StatusCountModel();
				statusCountModel.setName(resultSet.getString("status_name"));
				statusCountModel.setCount(resultSet.getInt("count"));

				list.add(statusCountModel);
								
			}
		} catch (Exception e) {
			System.out.println("Loi lay list count status " + e.getLocalizedMessage());
		}
		
		return list;
	}
	
}
