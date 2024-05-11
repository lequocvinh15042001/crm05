package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ProjectService;
import service.UserService;

@WebServlet(name = "projectController", urlPatterns = {"/groupwork", "/groupwork-add", "/groupwork-update", "/groupwork-details"})
public class ProjectController extends HttpServlet{
	
	ProjectService projectService = new ProjectService();
	UserService userService = new UserService();
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
        case "/groupwork":
            req.setAttribute("projects", projectService.getAllProject());
            req.getRequestDispatcher("/groupwork.jsp").forward(req,resp);
            break;
        case "/groupwork-add":
            req.getRequestDispatcher("/groupwork-add.jsp").forward(req, resp);
            break;
        case "/groupwork-update":
            int idUpdate = Integer.parseInt(req.getParameter("id"));
            req.setAttribute("project", projectService.getProjectById(idUpdate));
            req.getRequestDispatcher("/groupwork-update.jsp").forward(req, resp);
            break;
        case "/groupwork-details":
            int id = Integer.parseInt(req.getParameter("id"));
//            req.getSession().setAttribute("jobDetails", jobService.getDetailsByJobId(id));
            req.setAttribute("projectDetail", projectService.getProjectDetailsByProjectId(id));
            req.setAttribute("statusCount", projectService.getStatusCountByProjectId(id));
            req.getRequestDispatcher("/groupwork-details.jsp").forward(req, resp);
            break;
    }
		
	}
	
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action) {
            case "/groupwork-update":
                int id = Integer.parseInt(req.getParameter("id"));
//                req.getSession().setAttribute("project", projectService.getProjectById(id));
                req.setAttribute("project", projectService.getProjectById(id));
                req.getRequestDispatcher("/user-update.jsp").forward(req,resp);
                break;
        }
    }
}
