package handle;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterception implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String name = (String) request.getSession().getAttribute("name");

        if (!"zs".equals(name)){
            request.getRequestDispatcher("/tips.jsp").forward(request,response);
            return false;
        }
        return true;
    }
}
