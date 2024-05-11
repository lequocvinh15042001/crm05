package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
										// Cac duong dan se kich hoat filter
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import enumdata.RoleName;
@WebFilter(filterName = "authorFilter", urlPatterns = {"/user-add", "/user-update", "/groupwork-add", "/groupwork-update", "/role-add", "/role-update", "/task-add", "/task-update"})
public class AuthorFilter extends HttpServlet implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		// Cho phep di vao duong dan ma nguoi dung dang goi
//		chain.doFilter(request, response);
		
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String userRole = "";
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
            	if (cookie.getName().equals("role")) {

                    userRole = cookie.getValue();
                    break;
                }
            }
        }
        
        String reqPath = req.getServletPath();
        
        // Xử lý trường hợp URL là "/"
        if (reqPath.equals("/")) {
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }
        
        // Cach nay cua minh van dung!!!!!
        // Co sai enum
        
        if (userRole.equals(RoleName.ADMIN.getName()) 
        	    && (reqPath.equals("/user-add") || reqPath.equals("/user-delete") || reqPath.equals("/user-update")
        	        || reqPath.equals("/role-add") || reqPath.equals("/role-delete") || reqPath.equals("/role-update")
        	        || reqPath.equals("/groupwork-add") || reqPath.equals("/groupwork-delete") || reqPath.equals("/groupwork-update"))) {

        	    chain.doFilter(request, response);

        	} else if ((reqPath.equals("/task-add") || reqPath.equals("/task-delete") || reqPath.equals("/task-update"))
        	    && (userRole.equals(RoleName.ADMIN.getName()) || userRole.equals(RoleName.LEAD.getName()))) {

        	    chain.doFilter(request, response);

        	} else {

        	    resp.sendRedirect(req.getContextPath() + "/404.html");

        	}

        
        
        // Cach kiem trra bang switch-case cua thay Binh
        // Khong dung enum duoc
        
//        int index = Integer.parseInt(userRole);
        
        
//        switch (index) {
//        case 1: // Admin
//            if (reqPath.equals("/user-add") || reqPath.equals("/user-delete") || reqPath.equals("/user-update")
//                    || reqPath.equals("/role-add") || reqPath.equals("/role-delete") || reqPath.equals("/role-update")
//                    || reqPath.equals("/groupwork-add") || reqPath.equals("/groupwork-delete") || reqPath.equals("/groupwork-update")
//                    || reqPath.equals("/task-add") || reqPath.equals("/task-delete") || reqPath.equals("/task-update")) {
//                chain.doFilter(req, resp);
//            } else {
//                resp.sendRedirect(req.getContextPath() + "/404.html");
//            }
//            break;
//
//        case 2: // Lead
//            if (reqPath.equals("/task-add") || reqPath.equals("/task-delete") || reqPath.equals("/task-update")) {
//                chain.doFilter(req, resp);
//            } else {
//                resp.sendRedirect(req.getContextPath() + "/404.html");
//            }
//            break;
//
//        default: // User
//            resp.sendRedirect(req.getContextPath() + "/404.html");
//            break;
//        }
        
		
	}
	
}
