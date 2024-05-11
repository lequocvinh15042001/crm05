package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.User;
import model.TaskModel;
import service.ProjectService;
import service.StatusService;
import service.TaskService;
import service.UserService;

@WebServlet(name = "taskController", urlPatterns = {"/task", "/task-add", "/task-update"})
public class TaskController extends HttpServlet{
	
	TaskService taskService = new TaskService();
	ProjectService projectService = new ProjectService();
	UserService userService = new UserService();
	StatusService statusService = new StatusService();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// Lay thong tin profile
				Cookie[] cookies = req.getCookies();
				String userId = null;

				if(cookies != null) {
					for(Cookie cookie : cookies) {
						if(cookie.getName().equals("id")) {
							userId = cookie.getValue();
						}
					}
				}
				
				int id_user = Integer.parseInt(userId);
		        req.setAttribute("userProfile", userService.getUserById(id_user));
		        // Het lay thong tin profile
		
		String servletPath = req.getServletPath();// Duong dan
		
		switch (servletPath) {
        case "/task-add":
        	req.setAttribute("userList", userService.getAllUser());
        	req.setAttribute("projectList", projectService.getAllProject());
			req.getRequestDispatcher("task-add.jsp").forward(req, resp);
            break;
        case "/task":
        	List<TaskModel> listTask = taskService.getAllTask();
			req.setAttribute("listTask", listTask);
			req.getRequestDispatcher("task.jsp").forward(req, resp);
            break;
        case "/task-update":
            int idUpdate = Integer.parseInt(req.getParameter("id"));
            req.setAttribute("task", taskService.getTaskById(idUpdate));
            req.setAttribute("userList", userService.getAllUser());
            req.setAttribute("statusList", statusService.getAllStatus());
            req.setAttribute("projectList", projectService.getAllProject());
            req.getRequestDispatcher("task-update.jsp").forward(req, resp);
            break;
		
		}
	}
}
