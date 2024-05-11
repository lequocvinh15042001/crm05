package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Role;
import entity.User;
import service.RoleService;
import service.UserService;

@WebServlet(name = "roleController", urlPatterns = {"/role-add", "/role", "/role-update"})
public class RoleController extends HttpServlet{

	private RoleService roleService = new RoleService();
	private UserService userService = new UserService();
	
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
		        
		String path = req.getServletPath();
		
		switch (path) {
        case "/role-add":
			req.getRequestDispatcher("role-add.jsp").forward(req, resp);
            break;
        case "/role":
        	List<Role> listRole = roleService.getAllRole();
			req.setAttribute("listRole", listRole);
			req.getRequestDispatcher("role-table.jsp").forward(req, resp);
            break;
        case "/role-update":
            int idGetRole = Integer.parseInt(req.getParameter("id"));
            req.setAttribute("role", roleService.getRoleById(idGetRole));
            req.getRequestDispatcher("role-update.jsp").forward(req, resp);
            break;
		
		}
		
	}
}
