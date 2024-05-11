package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import config.MySQLConfig;
import entity.Task;
import model.StatusCountModel;
import model.TaskModel;

public class TaskRepository {

	public List<TaskModel> getAllTask() {
		
		List<TaskModel> listTask = new ArrayList<TaskModel>();
		
		try {
			
			String query = "SELECT \n"
					+ "    task.id AS id,\n"
					+ "    task.name AS task_name,\n"
					+ "    task.start_date AS start_date,\n"
					+ "    task.end_date AS end_date,\n"
					+ "    project.name AS project_name,\n"
					+ "    CONCAT(users.first_name, ' ', users.last_name) AS user_name,\n"
					+ "    status.name AS status_name\n"
					+ "FROM \n"
					+ "    task\n"
					+ "INNER JOIN \n"
					+ "    project ON task.id_project = project.id\n"
					+ "INNER JOIN \n"
					+ "    status ON task.id_status = status.id\n"
					+ "INNER JOIN \n"
					+ "    assigntask ON task.id = assigntask.id_task\n"
					+ "INNER JOIN \n"
					+ "    users ON assigntask.id_user = users.id;";
			Connection connection = MySQLConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				TaskModel task = new TaskModel();
                task.setId(resultSet.getInt("id"));
                task.setTaskName(resultSet.getString("task_name"));
                task.setStartDate(resultSet.getString("start_date"));
                task.setEndDate(resultSet.getString("end_date"));
                task.setProjectName(resultSet.getString("project_name"));
                task.setUserName(resultSet.getString("user_name"));
                task.setStatusTask(resultSet.getString("status_name"));
				
                listTask.add(task);
				
			}
		
		} catch (Exception e) {
			System.out.println("Loi truy cap task " + e.getLocalizedMessage());
		}
		
		return listTask;
		
	}
	
	public int save(Task taskEntity) {
		
		int result = 0;
	    PreparedStatement assignmentStatement = null;
	    
	    try {
	        Connection connection = MySQLConfig.getConnection();
	        String query = "INSERT INTO task (name, start_date, end_date, id_project, id_status)\n" +
	                "VALUES(?, ?, ?, ?, 2)";
	        PreparedStatement taskStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	        taskStatement.setString(1, taskEntity.getTaskName());
	        taskStatement.setString(2, taskEntity.getStartDate());
	        taskStatement.setString(3, taskEntity.getEndDate());
	        taskStatement.setInt(4, taskEntity.getProjectId());
	        result = taskStatement.executeUpdate();

	        if (result == 1) {
	            // Lấy ID của nhiệm vụ vừa được thêm
	            ResultSet generatedKeys = taskStatement.getGeneratedKeys();
	            if (generatedKeys.next()) {
	                int taskId = generatedKeys.getInt(1);
	               
	                // Thêm bản ghi vào bảng assigntask
	                String assignmentQuery = "INSERT INTO assigntask(id_user, id_task, id_status)\n"
	                		+ "VALUES(?, ?, ?)";
	                assignmentStatement = connection.prepareStatement(assignmentQuery);
	                assignmentStatement.setInt(1, taskEntity.getUserId());
	                assignmentStatement.setInt(2, taskId);
	                assignmentStatement.setInt(3, 2);

	                result = assignmentStatement.executeUpdate();
	            } else {
	                throw new Exception("Creating task failed, no ID obtained.");
	            }
	        }

	        connection.close();
	    } catch (Exception e) {
	        System.out.println("Error insert task " + e.getMessage());
	    }
	    return result;
	}
	
	public Task getTaskById(int id) {
		
		Task taskEntity = new Task();
		
		try {
			
			String query = "SELECT \n"
					+ "    task.id AS id,\n"
					+ "    task.name AS task_name,\n"
					+ "    task.start_date AS start_date,\n"
					+ "    task.end_date AS end_date,\n"
					+ "    project.name AS project_name,\n"
					+ "    project.id AS project_id,\n"
					+ "   	assigntask.id_user AS user_id,\n"
					+ "   	status.id AS status_id,\n"
					+ "    CONCAT(users.first_name, ' ', users.last_name) AS user_name,\n"
					+ "    status.name AS status_name\n"
					+ "    \n"
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
					+ "WHERE task.id = " + id;
			Connection connection = MySQLConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				taskEntity.setId(resultSet.getInt("id"));
				taskEntity.setTaskName(resultSet.getString("task_name"));
				taskEntity.setUserId(resultSet.getInt("user_id"));
				taskEntity.setProjectId(resultSet.getInt("project_id"));
				taskEntity.setStatusId(resultSet.getInt("status_id"));
				taskEntity.setStartDate(resultSet.getString("start_date"));
				taskEntity.setEndDate(resultSet.getString("end_date"));
			}
		
		} catch (Exception e) {
			System.out.println("Loi lay thong tin task theo id " + e.getLocalizedMessage());
		}
		
		return taskEntity;
		
	}
	
	public int updateTaskById(Task taskEntity) {
		int count = 0;
		try {
			
			String query = "UPDATE task SET name = ?, start_date = ?, end_date = ?, id_project = ?, id_status = ? WHERE id = ?";
			Connection connection = MySQLConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setString(1, taskEntity.getTaskName());
			statement.setString(2, taskEntity.getStartDate());
			statement.setString(3, taskEntity.getEndDate());
			statement.setInt(4, taskEntity.getProjectId());
			statement.setInt(5, taskEntity.getStatusId());
	        statement.setInt(6, taskEntity.getId());
	        
	        int rowsAffectedTask = statement.executeUpdate();

	        String assignmentQuery = "UPDATE assigntask SET id_user = ? WHERE id_task = ?";
	        PreparedStatement assignmentStatement = connection.prepareStatement(assignmentQuery);
	        assignmentStatement.setInt(1, taskEntity.getUserId());
	        assignmentStatement.setInt(2, taskEntity.getId());

	        int rowsAffectedAssignTask = assignmentStatement.executeUpdate();

	        count = rowsAffectedTask + rowsAffectedAssignTask;
	      
	        connection.close();
		
		} catch (Exception e) {
			System.out.println("Loi update task theo id " + e.getLocalizedMessage());
		}
		
		return count;
		
	}
	
	public int deleteTaskById(int id) {
		int count = 0;
		
		try {
	        Connection connection = MySQLConfig.getConnection();
	        
	        String deleteAssignTaskQuery = "DELETE FROM assigntask WHERE id_task = ?";
	        PreparedStatement deleteAssignTaskStatement = connection.prepareStatement(deleteAssignTaskQuery);
	        deleteAssignTaskStatement.setInt(1, id);
	        deleteAssignTaskStatement.executeUpdate();
	        
	        String query = "DELETE FROM task WHERE id = ?";
	        PreparedStatement statement = connection.prepareStatement(query);
	        statement.setInt(1, id);
	        count = statement.executeUpdate();

		} catch (Exception e) {
			System.out.println("Loi xoa task theo id " + e.getLocalizedMessage());
		}
		
		return count;
	}
	
	public List<TaskModel> getTaskByUserId(int id) {
		
		List<TaskModel> listTask = new ArrayList<TaskModel>();
		try {
			
			String query = "SELECT \n"
					+ "    task.id AS id,\n"
					+ "    task.name AS task_name,\n"
					+ "    task.start_date AS start_date,\n"
					+ "    task.end_date AS end_date,\n"
					+ "    project.name AS project_name,\n"
					+ "    CONCAT(users.first_name, ' ', users.last_name) AS user_name,\n"
					+ "    status.name AS status_name\n"
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
					+ "WHERE id_user = " + id;
			
			Connection connection = MySQLConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
                
                TaskModel task = new TaskModel();
                task.setId(resultSet.getInt("id"));
                task.setTaskName(resultSet.getString("task_name"));
                task.setStartDate(resultSet.getString("start_date"));
                task.setEndDate(resultSet.getString("end_date"));
                task.setProjectName(resultSet.getString("project_name"));
                task.setUserName(resultSet.getString("user_name"));
                task.setStatusTask(resultSet.getString("status_name"));
				
                listTask.add(task);
			}
		
		} catch (Exception e) {
			System.out.println("Loi lay thong tin task theo user id " + e.getLocalizedMessage());
		}
		
		return listTask;
		
	}
	
	public List<StatusCountModel> getListStatusCountAllForDashBoard(){
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
					+ "GROUP BY\n"
					+ "    status.name;";
			
			Connection connection = MySQLConfig.getConnection();
			
			PreparedStatement statement = connection.prepareStatement(query);
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				
				StatusCountModel statusCountModel = new StatusCountModel();
				statusCountModel.setName(resultSet.getString("status_name"));
				statusCountModel.setCount(resultSet.getInt("count"));

				list.add(statusCountModel);
								
			}
		} catch (Exception e) {
			System.out.println("Loi lay all list count status " + e.getLocalizedMessage());
		}
		
		return list;
	}

}
