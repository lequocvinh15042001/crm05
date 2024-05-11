package service;
import java.util.ArrayList;
import java.util.List;

import entity.Project;
import entity.Task;
import entity.User;
import model.ProjectDetailsModel;
import model.StatusCountModel;
import model.TaskModel;
import repository.ProjectRepository;
import utils.DateHelper;

public class ProjectService {

	private ProjectRepository projectRepository = new ProjectRepository();
	private DateHelper dateHelper = new DateHelper();
	
	public List<Project> getAllProject(){
		
		List<Project> projects = projectRepository.getAllProject();
		
		for(Project project : projects) {
			project.setStartDate(dateHelper.changeFormatDate(project.getStartDate(), "/"));
			project.setEndDate(dateHelper.changeFormatDate(project.getEndDate(), "/"));
		}
		
		return projects;
	}
	
	public Project getProjectById(int id){
		
		Project project = projectRepository.getProjectById(id);
		
		project.setStartDate(dateHelper.changeFormatDate(project.getStartDate(), "/"));
		project.setEndDate(dateHelper.changeFormatDate(project.getEndDate(), "/"));
		return project;
	}
	
	public boolean addProject(Project projectEntity) {
		try {
			projectEntity.setStartDate(dateHelper.changeFormatDate(projectEntity.getStartDate(), "-"));
			projectEntity.setEndDate(dateHelper.changeFormatDate(projectEntity.getEndDate(), "-"));
	        int rowCount = projectRepository.addProject(projectEntity);

	        return rowCount > 0;
	    } catch (Exception e){
            System.out.println("Error " + e.getMessage());
	        return false;
	    }
		
	}
	
	public boolean updateProject(Project projectEntity) {
		try {
			projectEntity.setStartDate(dateHelper.changeFormatDate(projectEntity.getStartDate(), "-"));
			projectEntity.setEndDate(dateHelper.changeFormatDate(projectEntity.getEndDate(), "-"));
	        int rowCount = projectRepository.updateProject(projectEntity);

	        return rowCount > 0;
	    } catch (Exception e){
            System.out.println("Error " + e.getMessage());
	        return false;
	    }
	}
	
	public boolean deleteProjectById(int id) {
		
		return projectRepository.deleteProjectById(id) > 0;// lon hon 0 la xoa thanh cong, be hon 0 la xoa that bai
		
	}
	
	public List<ProjectDetailsModel> getProjectDetailsByProjectId(int id){
		
		List<ProjectDetailsModel> listProjectDetailsModels = new ArrayList<ProjectDetailsModel>();
		
		List<User> listUser = projectRepository.getUserByProjectId(id);
		
		for (User user: listUser) {
			ProjectDetailsModel projectDetailsModel = new ProjectDetailsModel();
			projectDetailsModel.setIdUser(user.getId());
			projectDetailsModel.setUserName(user.getFirstName() + " " + user.getLastName());
            List<Task> tasks = projectRepository.getTaskByProjectIdAndUserId(id, user.getId());
            
    		for(Task taskEntity : tasks) {
    			taskEntity.setStartDate(dateHelper.changeFormatDate(taskEntity.getStartDate(), "/"));
    			taskEntity.setEndDate(dateHelper.changeFormatDate(taskEntity.getEndDate(), "/"));
    		}
            
            projectDetailsModel.setTaskEntities(tasks);

            listProjectDetailsModels.add(projectDetailsModel);
        }
		
		
		return listProjectDetailsModels;
		
	}
	
	public List<StatusCountModel> getStatusCountByProjectId(int id){
		return projectRepository.getListStatusCountByProjectId(id);
	}
	
	
}
