package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName = "errorFilter", urlPatterns = {"/*"})
public class ErrorFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // Kiểm tra xem request có lỗi không
        if (req.getAttribute("javax.servlet.error.status_code") != null) {
            // Nếu có lỗi, chuyển hướng đến trang 404.html
            res.sendRedirect(req.getContextPath() + "/404.html");
        } else {
            // Nếu không có lỗi, tiếp tục xử lý request
            chain.doFilter(request, response);
        }
    }

    public void init(FilterConfig fConfig) throws ServletException {
        // Không cần thực hiện gì trong phương thức này
    }

    public void destroy() {
        // Không cần thực hiện gì trong phương thức này
    }

}
