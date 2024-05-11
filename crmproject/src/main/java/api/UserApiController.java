package api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import entity.Project;
import entity.User;
import response.BaseResponse;
import service.UserService;

@WebServlet(name = "userApiController", urlPatterns = {"/api/user/add", "/api/user/delete", "/api/user/update"})
public class UserApiController extends HttpServlet{
	
	/*
	 * {
	 * 		statusCode: 200,
	 * 		message:"",
	 * 		data: Object
	 * }
	 */
	
	private Gson gson = new Gson();
	private UserService userService = new UserService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int id = Integer.parseInt(req.getParameter("id"));
		
		boolean isSuccess = userService.deleteUserById(id);
		
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(200);
		baseResponse.setMessage(isSuccess ? "Xoa thanh cong" : "Xoa that bai");
		baseResponse.setData(isSuccess);
		
		String json = gson.toJson(baseResponse);
		
		resp.setHeader("Content-Type", "application/json");
		
		PrintWriter printWriter = resp.getWriter();
		printWriter.write(json);
		printWriter.close();
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
		PrintWriter printWriter = resp.getWriter();
		
		BaseResponse baseResponse = new BaseResponse();
		String path = req.getServletPath();
		
		switch (path) {
		case "/api/user/add":
			String fullnameAdd = req.getParameter("fullname");
			String emailAdd = req.getParameter("email");
			String phoneAdd = req.getParameter("phone");
			String passwordAdd = req.getParameter("password");
			int roleIdAdd = Integer.parseInt(req.getParameter("roleId"));

			User user = new User(fullnameAdd, emailAdd, phoneAdd, passwordAdd, roleIdAdd);
	        boolean isSuccess = userService.insertUser(user);

	        baseResponse.setStatusCode(200);
	        baseResponse.setMessage(isSuccess ? "Them moi du an thanh cong" : "Them moi du an that bai");
	        baseResponse.setData(isSuccess);
	        
	        String json = gson.toJson(baseResponse);
	        printWriter.write(json);
	        printWriter.close();
	        
			break;
		case "/api/user/update":
			int id = Integer.parseInt(req.getParameter("id"));
			String fullnameStrUpdate = req.getParameter("fullname");
			String phoneStrUpdate = req.getParameter("phone");
			String emailStrUpdate = req.getParameter("email");
			String passwordStrUpdate = req.getParameter("password");
			int roleIdUpdate = Integer.parseInt(req.getParameter("roleId"));

			User userUpdate = new User(id, fullnameStrUpdate, emailStrUpdate, passwordStrUpdate, phoneStrUpdate, roleIdUpdate);
	        boolean isSuccessUpdate = userService.updateUserById(userUpdate);

	        baseResponse.setStatusCode(200);
	        baseResponse.setMessage(isSuccessUpdate ? "Cap nhat thong tin user thanh cong" : "Cap nhat thong tin user that bai");
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
