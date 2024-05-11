package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.MySQLConfig;
import entity.Status;

public class StatusRepository {

	public List<Status> getAllStatus(){
		
		List<Status> listStatus = new ArrayList<Status>();
		
		try {
			
			String query = "SELECT * FROM status";
			
			Connection connection = MySQLConfig.getConnection();
			
			PreparedStatement statement = connection.prepareStatement(query);
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				
				Status status = new Status();
				
				status.setId(resultSet.getInt("id"));
				status.setName(resultSet.getString("name"));

				listStatus.add(status);
								
			}
			
		} catch (Exception e) {
			System.out.println("Loi lay thong tin status " + e.getLocalizedMessage());
		}
		
		return listStatus;
		
	}
	
}
