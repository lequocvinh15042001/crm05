package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import entity.User;

@WebServlet(name = "loginController", urlPatterns = "/login")
public class LoginController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//Neu da bam luu thong tin dang nhap thi quay lai manf hinh login van tu dong dien email, password
		
		// Lay toan cookie ma client truyen len
		Cookie[] cookies = req.getCookies();
		String savedEmail = null;
		String savedPassword = null;
		
		// Duyet qua danh sach cookie thi cai name cookie nao bang email, password thi no se gan cho
		// savedEmail va savedPassword
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals("email")) {
					savedEmail = cookie.getValue(); // lay gia tri cua cookie email gan cho savedEmail de luu vao value cua form login
				}
				if(cookie.getName().equals("password")) {
					savedPassword = cookie.getValue(); // lay gia tri cua cookie password gan cho savedPassword de luu vao value cua form login
				}
			}
		}
		
		//Tra ra gia tri cho login.jsp
		req.setAttribute("savedEmail", savedEmail);
		req.setAttribute("savedPassword", savedPassword);
		
		req.getRequestDispatcher("login.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// Xu ly dang nhap, cookies
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		boolean remember = req.getParameter("remember") != null && req.getParameter("remember").equals("on");
		
		// Kiem tra email va mat khau duoc luu chua
		if(remember) {
			// Tao 2 cookie de luu tru (ten, gia_tri) cua cookie do
			Cookie emailCookie = new Cookie("email", email);
			Cookie passwordCookie = new Cookie("password", password);

			// Set thoi gian song cho cookie
			emailCookie.setMaxAge(7*24*60*60);// 7 ngay --> giay
			passwordCookie.setMaxAge(7*24*60*60);

			resp.addCookie(emailCookie);
			resp.addCookie(passwordCookie);

		}
		// Ket thuc cookie
		
		
		// Chuan bi cau truy van kiem tra xem email va password co ton tai trong csdl khong
		String query = "SELECT *\n"
				+ "FROM users u \n"
				+ "WHERE u.email = '"+ email +"' AND u.password = '"+ password + "'";
		
		// Mo ket noi CSDL
		Connection connection = MySQLConfig.getConnection();
		
		// Truyen cau query da chuan bi cho CSDL da ket noi
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			
			/*
			 * excuteQuery : chi su dung khi cau truy van la cau SELECT
			 * excuteUpdate : chi su dung khi cau truy van la cau CREATE, UPDATE, DELETE...
			 */
			
			// ResultSet dai dien cho du lieu truy van duoc (la cai KQ sau khi truy van trong MySQL)
			ResultSet resultSet = statement.executeQuery();
			
			// Tao lits user chua co du lieu  
			List<User> listUser = new ArrayList<User>();
			
			// Khi nao resultSet con next duoc thi tao du lieu va luu vao list user
			while(resultSet.next()) {
				
				User user = new User();
				// Lay gia tri cua cot id va gan vao thuoc tinh id cua doi tuong User
				user.setId(resultSet.getInt("id"));
				user.setEmail(resultSet.getString("email"));
				user.setFirstName(resultSet.getString("first_name"));
				user.setLastName(resultSet.getString("last_name"));
				
				Role role = new Role();
				role.setId(resultSet.getInt("id_role"));
				user.setRole(role);
				
				// Them doi tuong user da gan gia tri vao trong list user
				listUser.add(user);
			}
			
			if(listUser.size() > 0) {
				System.out.println("Dang nhap thanh cong!");
				String contextPath = req.getContextPath();
				
				//Tao cookie roi luu thong tin role user
				// List user lay thang dau tien la lay get(0).getIdRole cua no la ra id role
				Cookie ckRole = new Cookie("role", String.valueOf(listUser.get(0).getRole().getId()));
				ckRole.setMaxAge(7*24*60*60);
				resp.addCookie(ckRole);// add role Id vao cookie co ten la "role"
				
				Cookie ckId = new Cookie("id", String.valueOf(listUser.get(0).getId()));
				ckId.setMaxAge(7*24*60*60);
				resp.addCookie(ckId);
				
				resp.sendRedirect(contextPath + "/home");
				
			} else {
				System.out.println("Dang nhap that bai!");
				req.setAttribute("loginFailed", true);
//				String contextPath = req.getContextPath();
//				resp.sendRedirect(contextPath + "/login");
				req.getRequestDispatcher("login.jsp").forward(req, resp);
			}
			
			/* Bai tap ve nha:
			 * - Khi dang nhap thanh cong --> chuyen qua trang dashboard
			 * - Khi dang nhap that bai --> xuat thong bao dang nhap that bai o man hinh login
			 */
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
