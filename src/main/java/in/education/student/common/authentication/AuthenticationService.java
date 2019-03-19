package in.education.student.common.authentication;

import in.education.student.common.exception.AuthenticationException;
import in.education.student.model.User;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuthenticationService {

	private AuthenticationRepository authenticationRepository;

	@Autowired
	AuthenticationService(final AuthenticationRepository authenticationRepository) {
		this.authenticationRepository = authenticationRepository;
	}

	boolean authenticateUser(String userName, String password,
			HttpServletRequest request) throws AuthenticationException {

		User user = authenticationRepository.authenticateUser(userName, password);

		HttpSession session = request.getSession(true);

		List<HashMap<String, String>> servicesList = authenticationRepository.getServices(userName, password);

		session.setAttribute("services", servicesList);
		session.setAttribute("user", user);
		session.setAttribute("user_desc", user.getUserDesc());

		List serviceUrls =  servicesList.stream()
				.map(serviceMap -> serviceMap.get("service_url"))
				.collect(Collectors.toList());

		session.setAttribute("serviceUrls", serviceUrls);

		List<HashMap<String, String>> servicesShowList = servicesList.stream()
				.filter(serviceMap -> "Y".equalsIgnoreCase(serviceMap.get("show_in_menu")))
				.collect(Collectors.toList());

		session.setAttribute("servicesMenu", new JSONArray(servicesShowList)); // Show
		// in menu only services which are allowed to show in menu

		if (user != null) {

			return true;
		}

		return false;
	}
}
