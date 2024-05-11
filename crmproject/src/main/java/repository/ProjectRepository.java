package repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import config.MySQLConfig;
import entity.Project;
import entity.Task;
import entity.User;
import model.StatusCountModel;

public class ProjectRepository {

	public List<Project> getAllProject() {
		
		List<Project> projects = new ArrayList<Project>();
		
		try {
			String query = "SELECT * FROM project";
			
			Connection connection = MySQLConfig.getConnection();
			
			PreparedStatement statement = connection.prepareStatement(query);
			
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			System.out.println(resultSet.getInt("id"));
			System.out.println(resultSet.getString("name"));
			
			while(resultSet.next()) {
				Project project = new Project();
				project.setId(resultSet.getInt("id"));
				project.setName(resultSet.getString("name"));
				project.setStartDate(resultSet.getString("start_date"));
				project.setEndDate(resultSet.getString("end_date"));
				
				projects.add(project);
			}
			
			connection.close();
			
		} catch (Exception e) {
			System.out.println("Loi get danh sach Project " + e.getLocalizedMessage());
		}
		
		return projects;
	}
	
	public Project getProjectById(int id) {
		
		Project project = new Project();
		
		try {
			String query = "SELECT * FROM project WHERE id = ?";
			
			Connection connection = MySQLConfig.getConnection();
			
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setInt(1, id);
			
			ResultSet resultSet = statement.executeQuery();
			
			//System.out.println(resultSet);
			
			while(resultSet.next()) {
				project.setId(resultSet.getInt("id"));
				project.setName(resultSet.getString("name"));
				project.setStartDate(resultSet.getString("start_date"));
				project.setEndDate(resultSet.getString("end_date"));
			}
			
			connection.close();
			
		} catch (Exception e) {
			System.out.println("Loi lay thong tin project " + e.getLocalizedMessage());
		}
		
		return project;
	}

	public int addProject(Project projectEntity) {
		
		int count = 0;
		
        try {
            String query = "INSERT INTO project (name, start_date, end_date) VALUES (?, ?, ?)";
            Connection connection = MySQLConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, projectEntity.getName());
            statement.setString(2, projectEntity.getStartDate());
            statement.setString(3, projectEntity.getEndDate());
            
            count = statement.executeUpdate();
            
            connection.close();
        } catch (Exception e) {
        	System.out.println("Loi them du an " + e.getLocalizedMessage());
        }
        return count;
		
	}
	
	public int updateProject(Project projectEntity) {
		int count = 0;
		
        try {
            String query = "UPDATE project SET name=?, start_date=?, end_date=? WHERE id = ?";
            Connection connection = MySQLConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, projectEntity.getName());
            statement.setString(2, projectEntity.getStartDate());
            statement.setString(3, projectEntity.getEndDate());
            statement.setInt(4, projectEntity.getId());
            
            count = statement.executeUpdate();
            
            connection.close();
        } catch (Exception e) {
        	System.out.println("Loi cap nhat du an " + e.getLocalizedMessage());
        }
        return count;
	}
	
	public int deleteProjectById(int id) {
		int count = 0;
		String query = "DELETE FROM project p WHERE p.id = " + id;
		
		try {
			
			Connection connection = MySQLConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			count = statement.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("Loi xoa project " + e.getLocalizedMessage());
		}

		return count;
	}
	
	public List<User> getUserByProjectId(int projectId){
		
		List<User> users = new ArrayList<User>();
		
		try {
			
			String query = "SELECT DISTINCT\n"
					+ "    users.id AS id_user,\n"
					+ "    users.first_name AS first_name,\n"
					+ "    users.last_name AS last_name,\n"
					+ "    CONCAT(users.first_name, ' ', users.last_name) AS user_name\n"
					+ "FROM \n"
					+ "    task\n"
					+ "INNER JOIN \n"
					+ "    project ON task.id_project = project.id\n"
					+ "INNER JOIN \n"
					+ "    status ON task.id_status = status.id\n"
					+ "INNER JOIN \n"
					+ "    assigntask ON task.id = assigntask.id_task\n"
					+ "INNER JOIN \n"
					+ "    users ON assigntask.id_user = users.id\n"
					+ "WHERE project.id =" + projectId;
			
			Connection connection = MySQLConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id_user"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                users.add(user);
            }
			
		} catch (Exception e) {
			System.out.println("Loi get danh sach user theo project id " + e.getLocalizedMessage());
		}
		
		return users;
	}
	
	public List<Task> getTaskByProjectIdAndUserId(int projectId, int userId){
		
		List<Task> tasks = new ArrayList<Task>();
		
		try {
			
			String query = "SELECT *\n"
					+ "FROM \n"
					+ "    task\n"
					+ "INNER JOIN \n"
					+ "    project ON task.id_project = project.id\n"
					+ "INNER JOIN \n"
					+ "    status ON task.id_status = status.id\n"
					+ "INNER JOIN \n"
					+ "    assigntask ON task.id = assigntask.id_task\n"
					+ "INNER JOIN \n"
					+ "    users ON assigntask.id_user = users.id\n"
					+ "WHERE id_user = ? and project.id = ?;";
			
			Connection connection = MySQLConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, userId);
			statement.setInt(2, projectId);
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
                Task taskEntity = new Task();
                taskEntity.setId(resultSet.getInt("id"));
                taskEntity.setTaskName(resultSet.getString("name"));
                taskEntity.setStartDate(resultSet.getString("start_date"));
                taskEntity.setEndDate(resultSet.getString("end_date"));
                taskEntity.setStatusId(resultSet.getInt("id_status"));
                taskEntity.setUserId(resultSet.getInt("id_user"));
                tasks.add(taskEntity);
            }
			
		} catch (Exception e) {
			System.out.println("Loi get danh sach task theo project id va user id " + e.getLocalizedMessage());
		}
		
		return tasks;
	}
	
	public List<StatusCountModel> getListStatusCountByProjectId(int id){
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
					+ "    task.id_project = ?\n"
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
			System.out.println("Loi lay list count status theo project id " + e.getLocalizedMessage());
		}
		
		return list;
	}
	
}
