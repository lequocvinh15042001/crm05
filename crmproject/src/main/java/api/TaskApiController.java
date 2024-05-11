package api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import entity.Task;
import response.BaseResponse;
import service.TaskService;

@WebServlet(name = "TaskApiController", urlPatterns = {"/api/add-task", "/api/update-task", "/api/delete-task"})
public class TaskApiController extends HttpServlet{

	Gson gson = new Gson();
	TaskService taskService = new TaskService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
		PrintWriter printWriter = resp.getWriter();
		
		BaseResponse baseResponse = new BaseResponse();
		
		switch (path) {
		case "/api/delete-task":
			
			int idDelete = Integer.parseInt(req.getParameter("id"));
			boolean isSuccessDelete = taskService.deleteTaskById(idDelete);

	        baseResponse.setStatusCode(200);
	        baseResponse.setMessage(isSuccessDelete ? "Xoa task thanh cong" : "Xoa task that bai");
	        baseResponse.setData(isSuccessDelete);
	        
	        String jsonDelete = gson.toJson(baseResponse);
	        printWriter.write(jsonDelete);
	        printWriter.close();
		        
			break;

		default:
			break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
		PrintWriter printWriter = resp.getWriter();
		
		BaseResponse baseResponse = new BaseResponse();
		String path = req.getServletPath();
		
		switch (path) {
		case "/api/add-task":
			int projectIdAdd = Integer.parseInt(req.getParameter("projectId"));
			String taskNameAdd = req.getParameter("taskName");
			int userIdAdd = Integer.parseInt(req.getParameter("userId"));
			String startDateAdd = req.getParameter("startDate");
			String endDateAdd = req.getParameter("endDate");

			Task taskEntity = new Task(taskNameAdd, startDateAdd, endDateAdd, userIdAdd, projectIdAdd);
	        boolean isSuccess = taskService.addTask(taskEntity);

	        baseResponse.setStatusCode(200);
	        baseResponse.setMessage(isSuccess ? "Them moi task thanh cong" : "Them moi task that bai");
	        baseResponse.setData(isSuccess);
	        
	        String json = gson.toJson(baseResponse);
	        printWriter.write(json);
	        printWriter.close();
	        
			break;
		case "/api/update-task":
			int idUpdate = Integer.parseInt(req.getParameter("id"));
			int projectIdUpdate = Integer.parseInt(req.getParameter("projectId"));
			String taskNameUpdate = req.getParameter("taskName");
			int userIdUpdate = Integer.parseInt(req.getParameter("userId"));
			String startDateUpdate = req.getParameter("startDate");
			String endDateUpdate = req.getParameter("endDate");
			int statusIdUpdate = Integer.parseInt(req.getParameter("statusId"));

			Task taskUpdate = new Task(idUpdate, taskNameUpdate,  startDateUpdate, endDateUpdate, userIdUpdate, projectIdUpdate, statusIdUpdate);
	        boolean isSuccessUpdate = taskService.updateTaskById(taskUpdate);

	        baseResponse.setStatusCode(200);
	        baseResponse.setMessage(isSuccessUpdate ? "Cap nhat thong tin task thanh cong" : "Cap nhat thong tin task that bai");
	        baseResponse.setData(isSuccessUpdate);
	        
	        String jsonUpdate = gson.toJson(baseResponse);
	        printWriter.write(jsonUpdate);
	        printWriter.close();
	        
			break;

		default:
			break;
		}
		
	}
	
}
