package model;

import java.util.List;

import entity.Task;

public class ProjectDetailsModel {

	private int idUser;
	   private String userName;
	   List<Task> taskEntities;

	    public ProjectDetailsModel() {
	    }

	    public ProjectDetailsModel(int idUser, String userName, List<Task> taskEntities) {
	        this.idUser = idUser;
	        this.userName = userName;
	        this.taskEntities = taskEntities;
	    }

	    public int getIdUser() {
	        return idUser;
	    }

	    public void setIdUser(int idUser) {
	        this.idUser = idUser;
	    }

	    public String getUserName() {
	        return userName;
	    }

	    public void setUserName(String userName) {
	        this.userName = userName;
	    }

	    public List<Task> getTaskEntities() {
	        return taskEntities;
	    }

	    public void setTaskEntities(List<Task> taskEntities) {
	        this.taskEntities = taskEntities;
	    }
	    
}
