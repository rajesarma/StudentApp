package in.education.student.common.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import in.education.student.common.authentication.AuthenticationService;
import in.education.student.model.Service;
import in.education.student.model.User;
import in.education.student.model.repository.ServiceRepository;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class AuthenticationSuccess extends SimpleUrlAuthenticationSuccessHandler {

	/*@Autowired
	AuthenticationService authenticationService;*/

	@Autowired
	private ServiceRepository serviceRepository;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//		 authenticationService.getServices(request, user);
		HttpSession session = request.getSession(true);
		HashMap<String, String> serviceMap;
		ObjectMapper oMapper = new ObjectMapper();

		// Get the Principal User object
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

//		 serviceRepository.findAll().forEach(System.out::print);

		/*List servicesUrls =
				StreamSupport.stream(serviceRepository.findAll().spliterator(), false)
						.filter(service -> service.getMenuDisplay() == 0 )
						.map(service -> service.getServiceUrl())
						.collect(Collectors.toList());*/

//		Iterable<Service> services = serviceRepository.findAll();

		Iterable<Service> services =
				serviceRepository.findByServiceName(user.getUserName());


		List serviceUrls =
				StreamSupport.stream(services.spliterator(), false)
						.filter(service -> service.getMenuDisplay() == 1 )
						.collect(Collectors.toList())
				.stream()
				.map(service -> oMapper.convertValue(service, Map.class))
				.collect(Collectors.toList());

		List<HashMap<String, String>> servicesShowList = new ArrayList<>();

		for(Object service: serviceUrls) {

			HashMap<String, String> sMap= (HashMap<String, String>)service;

			serviceMap = new HashMap<>();
			serviceMap.put("service_id", sMap.get("serviceId"));
			serviceMap.put("service_url", sMap.get("serviceUrl"));
			serviceMap.put("service_name", sMap.get("serviceName"));
			serviceMap.put("parent_id", sMap.get("parentId"));
			serviceMap.put("display_order", sMap.get("displayOrder"));
			serviceMap.put("menu_display", sMap.get("menuDisplay"));
			servicesShowList.add(serviceMap);
		}
		session.setAttribute("servicesMenu", new JSONArray(servicesShowList));

		response.sendRedirect("/home");
//		super.onAuthenticationSuccess(request, response, authentication);
	}
}
