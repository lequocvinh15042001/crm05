package service;

import java.util.List;

import entity.Project;
import entity.Task;
import entity.User;
import model.StatusCountModel;
import model.TaskModel;
import repository.TaskRepository;
import utils.DateHelper;

public class TaskService {
	
	TaskRepository taskRepository = new TaskRepository();
	DateHelper dateHelper = new DateHelper();
	
	public List<TaskModel> getAllTask(){
		
		List<TaskModel> tasks = taskRepository.getAllTask();
		
		for(TaskModel taskModel : tasks) {
			taskModel.setStartDate(dateHelper.changeFormatDate(taskModel.getStartDate(), "/"));
			taskModel.setEndDate(dateHelper.changeFormatDate(taskModel.getEndDate(), "/"));
		}
		
		return tasks;
	}
	
	public boolean addTask(Task taskEntity) {
		
		try {
			taskEntity.setStartDate(dateHelper.changeFormatDate(taskEntity.getStartDate(), "-"));
			taskEntity.setEndDate(dateHelper.changeFormatDate(taskEntity.getEndDate(), "-"));
			int count = taskRepository.save(taskEntity);

	        return count > 0;
	    } catch (Exception e){
            System.out.println("Error " + e.getMessage());
	        return false;
	    }
	}
	
	public Task getTaskById(int id) {
		Task taskEntity = new Task();
		taskEntity = taskRepository.getTaskById(id);
		taskEntity.setStartDate(dateHelper.changeFormatDate(taskEntity.getStartDate(), "/"));
		taskEntity.setEndDate(dateHelper.changeFormatDate(taskEntity.getEndDate(), "/"));
		return taskEntity;
	}
	
	public boolean updateTaskById(Task taskEntity) {
		
		try {
			taskEntity.setStartDate(dateHelper.changeFormatDate(taskEntity.getStartDate(), "-"));
			taskEntity.setEndDate(dateHelper.changeFormatDate(taskEntity.getEndDate(), "-"));
			int count = taskRepository.updateTaskById(taskEntity);

	        return count > 0;
	    } catch (Exception e){
            System.out.println("Error " + e.getMessage());
	        return false;
	    }
	}
	
	public boolean deleteTaskById(int id) {
		try {
			int count = taskRepository.deleteTaskById(id);
	        return count > 0;
	    } catch (Exception e){
            System.out.println("Error " + e.getMessage());
	        return false;
	    }
	}
	
	public List<TaskModel> getTaskByUserId(int id) {
		
		List<TaskModel> tasks = taskRepository.getTaskByUserId(id);
		
		for(TaskModel taskModel : tasks) {
			taskModel.setStartDate(dateHelper.changeFormatDate(taskModel.getStartDate(), "/"));
			taskModel.setEndDate(dateHelper.changeFormatDate(taskModel.getEndDate(), "/"));
			
		}
		
		return tasks;
		
	}
	
	public List<StatusCountModel> getAllStatusCountForDashBoard(){
		return taskRepository.getListStatusCountAllForDashBoard();
	}

}
