package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.TaskService;
import service.UserService;

@WebServlet(name = "homeController" , urlPatterns = {"/home", "/blank", "/error"})
public class DashboardController extends HttpServlet{

	UserService userService = new UserService();
	TaskService taskService = new TaskService();
	
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
		
		
		String action = req.getServletPath();
        switch (action) {
            case "/home":
            	req.setAttribute("statusCount", taskService.getAllStatusCountForDashBoard());
            	req.getRequestDispatcher("index.jsp").forward(req, resp);
                break;
            case "/blank":
                req.getRequestDispatcher("blank.html").forward(req,resp);
                break;
            case "/error":
                req.getRequestDispatcher("404.html").forward(req,resp);
                break;
        }
        
	}
}
