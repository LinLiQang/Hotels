/*

package good.controller;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object loginName = request.getSession().getAttribute("loginName");
        if (loginName==null) {
            response.sendRedirect(request.getContextPath()+"/pages/login.jsp");
            //拦截
            return false;
        }else {
            //放行
            return true;
        }
    }




*/
/*@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object loginName = request.getSession().getAttribute("loginName");
        if(loginName == null){
            response.sendRedirect(request.getContextPath()+"/pages/login.jsp");
            return false;
        }else {
            return true;
        }
    }*//*



    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
*/
