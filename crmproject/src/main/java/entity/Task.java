package entity;

public class Task {
	
    private int id;
    private String taskName;
    private int userId;
    private int projectId;
    private int statusId;
    private String startDate;
    private String endDate;


    public Task(int id, String taskName, String startDate, String endDate, int userId, int projectId, int statusId) {
        this.id = id;
        this.taskName = taskName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userId = userId;
        this.projectId = projectId;
        this.statusId = statusId;
    }

    public Task(String taskName, String startDate, String endDate, int userId, int projectId) {
        this.taskName = taskName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userId = userId;
        this.projectId = projectId;
    }

    public Task() {
    	
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }
}
