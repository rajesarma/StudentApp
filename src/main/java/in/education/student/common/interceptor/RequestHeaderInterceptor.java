package in.education.student.common.interceptor;

import in.education.student.common.exception.InvalidHeaderFieldException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@Component
public class RequestHeaderInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		if(request.getHeader("student-auth-key") == null) {
			throw new InvalidHeaderFieldException("Header Filed is not available");
		}

		return super.preHandle(request, response, handler);
	}
}
