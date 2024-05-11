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
import response.BaseResponse;
import service.ProjectService;

@WebServlet(name = "projectApiController", urlPatterns = {"/api/add-project", "/api/delete-project","/api/update-project"})
public class ProjectApiController extends HttpServlet{

	private Gson gson = new Gson();
	private ProjectService projectService = new ProjectService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		
		boolean isSuccess = projectService.deleteProjectById(id);
		
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
		case "/api/add-project":
			String name = req.getParameter("name");
			String startDateStr = req.getParameter("startDate");
			String endDateStr = req.getParameter("endDate");

			Project projectEntity = new Project(name, startDateStr, endDateStr);
	        boolean isSuccess = projectService.addProject(projectEntity);

	        baseResponse.setStatusCode(200);
	        baseResponse.setMessage(isSuccess ? "Them moi du an thanh cong" : "Them moi du an that bai");
	        baseResponse.setData(isSuccess);
	        
	        String json = gson.toJson(baseResponse);
	        printWriter.write(json);
	        printWriter.close();
	        
			break;
		case "/api/update-project":
			int id = Integer.parseInt(req.getParameter("id"));
			String nameSrtUpdate = req.getParameter("name");
			String startDateStrUpdate = req.getParameter("startDate");
			String endDateStrUpdate = req.getParameter("endDate");

			Project projectEntityUpdate = new Project(id, nameSrtUpdate, startDateStrUpdate, endDateStrUpdate);
	        boolean isSuccessUpdate = projectService.updateProject(projectEntityUpdate);

	        baseResponse.setStatusCode(200);
	        baseResponse.setMessage(isSuccessUpdate ? "Cap nhat du an thanh cong" : "Cap nhat du an that bai");
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
