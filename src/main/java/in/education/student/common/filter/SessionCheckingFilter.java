package in.education.student.common.filter;

import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class SessionCheckingFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		String path = httpRequest.getRequestURI();

		// Allow css, js, images
		if (path.equals("/") || path.contains("/css") || path.contains("/js") || path.contains("/images")
				|| path.contains("/favicon.ico")
		) {
			filterChain.doFilter(request, response);

		} else {

			int pathStartPosition = path.indexOf("/");
			String action = path.substring(pathStartPosition + 1);
//			String link = path.substring(pathStartPosition + 1, path.length());

			/*if ((action.equalsIgnoreCase("login")
				|| action.equalsIgnoreCase("logout")
				|| action.equalsIgnoreCase("home") ))
			*/

			String[] allowedUrls = {"login", "logout", "home"};
			List<String> allowedUrlsList = Arrays.asList(allowedUrls);

			if (allowedUrlsList.stream().anyMatch(action::equalsIgnoreCase)) {
				filterChain.doFilter(request, response);
			} else {

				RequestDispatcher rd = httpRequest.getRequestDispatcher("/expireSession");

				HttpSession session = httpRequest.getSession();

				if (session == null) {
					rd.forward(httpRequest, httpResponse);
				} else if (session.getAttribute("user") == null) {
					rd.forward(httpRequest, httpResponse);
				} else if (session.getAttribute("services") != null) {
					List services = (List) session.getAttribute("serviceUrls");

					if (services.contains(path.trim())) {
						filterChain.doFilter(request, response);
					} else {
						rd.forward(httpRequest, httpResponse);
					}
				} else {
					rd.forward(httpRequest, httpResponse);
				}
			}
		}
	}

		//		String resourcePath = new UrlPathHelper().getPathWithinApplication(httpRequest);

		/*
		String path = httpRequest.getRequestURI();
		int p= path.lastIndexOf("/");
		String action = path.substring(p+1,path.length()-3);
		link = path.substring(p+1,path.length());



		if (action!=null && (action.equalsIgnoreCase("login") || action.equalsIgnoreCase("Logout")
				|| action.equalsIgnoreCase("home")
				||action.equalsIgnoreCase("HomePage")
				||action.equalsIgnoreCase("changePassword")
		))
		{
			filterChain.doFilter(request, response);
		}
		else
		{
			HttpSession session=httpRequest.getSession(true);

			if (session!=null && session.getAttribute("userId")==null)
			{
				RequestDispatcher rd=httpRequest.getRequestDispatcher("sessionExpired.do");
				rd.forward(httpRequest, httpResponse);
			}
			else if (session!=null && session.getAttribute("services")!=null)
			{
				services = (ArrayList<HashMap<String,String>>)session.getAttribute("services");
				for (Iterator serviceMap = services.iterator(); serviceMap.hasNext();)
				{
					HashMap<String, String> service = (HashMap<String, String>) serviceMap.next();
					if(link.equals(service.get("service_url")))
					{
						flag = true;
						break;
					}
				}
				if (flag==true)
				{
					filterChain.doFilter(request, response);
				}
				else
				{
					RequestDispatcher rd = httpRequest.getRequestDispatcher
					("sessionExpired.do");
					rd.forward(httpRequest,httpResponse);
				}
			}
		}*/

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}
	public void destroy() {

	}

}
