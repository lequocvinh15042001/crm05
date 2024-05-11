package api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import entity.Role;
import entity.User;
import response.BaseResponse;
import service.RoleService;

@WebServlet(name = "roleApiController", urlPatterns = {"/api/add-role", "/api/delete-role", "/api/update-role"})
public class RoleApiController extends HttpServlet{

	private Gson gson = new Gson();
	private RoleService roleService = new RoleService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
		PrintWriter printWriter = resp.getWriter();
		BaseResponse baseResponse = new BaseResponse();
		String path = req.getServletPath();
		
		switch (path) {
		case "/api/delete-role":
		
			int id = Integer.parseInt(req.getParameter("id"));
	        boolean isSuccessDelete = roleService.deleteRole(id);
	
	        baseResponse.setStatusCode(200);
	        baseResponse.setMessage(isSuccessDelete ? "Xoa quyen thanh cong" : "Xoa quyen that bai");
	        baseResponse.setData(isSuccessDelete);
	        
	        String jsonUpdate = gson.toJson(baseResponse);
	        printWriter.write(jsonUpdate);
	        printWriter.close();
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
		case "/api/add-role":
			String name = req.getParameter("name");
			String description = req.getParameter("description");
			
			boolean isSuccess = roleService.addRole(name, description);
			
			baseResponse.setStatusCode(200);
			baseResponse.setMessage(isSuccess ? "Them quyen thanh cong!" : "Them quyen that bai!");
			baseResponse.setData(isSuccess);
			
			String json = gson.toJson(baseResponse);
			resp.setHeader("Content-Type", "application/json");
			printWriter.write(json);
			printWriter.close();
	        
			break;
			
		case "/api/update-role":
			int id = Integer.parseInt(req.getParameter("id"));
			String nameStrUpdate = req.getParameter("name");
			String descriptionStrUpdate = req.getParameter("description");

			Role roleUpdate = new Role(id, nameStrUpdate, descriptionStrUpdate);
	        boolean isSuccessUpdate = roleService.updateRoleById(roleUpdate);

	        baseResponse.setStatusCode(200);
	        baseResponse.setMessage(isSuccessUpdate ? "Cap nhat thong tin quyen thanh cong" : "Cap nhat thong tin quyen that bai");
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
