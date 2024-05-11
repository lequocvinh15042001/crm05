package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import config.MySQLConfig;
import entity.Role;
import entity.Task;
import entity.User;
import model.StatusCountModel;
import service.ProjectService;
import service.StatusService;
import service.TaskService;
import service.UserService;

@WebServlet(name = "userController", urlPatterns = {"/user-add", "/users", "/user-update", "/user-details", "/profile", "/profile-edit"})
public class UserController extends HttpServlet{
	
	private UserService userService = new UserService();
	private TaskService taskService = new TaskService();	
	private ProjectService projectService = new ProjectService();	
	private StatusService statusService = new StatusService();
	
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
        case "/user-add":
        	req.setAttribute("listRole", userService.getAllRole());
			req.getRequestDispatcher("user-add.jsp").forward(req, resp);
            break;
        case "/users":
        	List<User> list = userService.getAllUser();
			req.setAttribute("listUser", list);
			req.getRequestDispatcher("user-table.jsp").forward(req, resp);
            break;
        case "/user-update":
            int idUpdate = Integer.parseInt(req.getParameter("id"));
            req.setAttribute("user", userService.getUserById(idUpdate));
            req.setAttribute("listRole", userService.getAllRole());
            req.getRequestDispatcher("user-update.jsp").forward(req, resp);
            break;
        case "/user-details":
            int idUser = Integer.parseInt(req.getParameter("id"));
            req.setAttribute("user", userService.getUserById(idUser));
            req.setAttribute("tasks", taskService.getTaskByUserId(idUser));
            req.setAttribute("statusCount", userService.getAllListStatusCount(idUser));
            req.getRequestDispatcher("user-details.jsp").forward(req, resp);
            break;
         
        case "/profile":
            req.setAttribute("user", userService.getUserById(id_user));
            req.setAttribute("tasks", taskService.getTaskByUserId(id_user));
            req.setAttribute("statusCount", userService.getAllListStatusCount(id_user));
            req.getRequestDispatcher("profile.jsp").forward(req, resp);    		
            break;
        case "/profile-edit":
          int idUpdateTask = Integer.parseInt(req.getParameter("id"));
          req.setAttribute("user", userService.getUserById(id_user));
          req.setAttribute("task", taskService.getTaskById(idUpdateTask));
          req.setAttribute("statusList", statusService.getAllStatus());
          req.setAttribute("projectList", projectService.getAllProject());
          req.setAttribute("userList", userService.getAllUser());
          req.getRequestDispatcher("profile-edit.jsp").forward(req, resp);
          break;
            
        }
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String action = req.getServletPath();
        switch (action) {
            case "/user-update":
                int id = Integer.parseInt(req.getParameter("id"));
//                req.getSession().setAttribute("project", projectService.getProjectById(id));
                req.setAttribute("user", userService.getUserById(id));
                req.getRequestDispatcher("/user-update.jsp").forward(req,resp);
                break;
        }
        
	}
	
}
