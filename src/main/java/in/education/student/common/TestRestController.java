package in.education.student.common;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestController {

	@GetMapping("/testInterceptor")
	public String testInterceptor(@RequestHeader("student-auth-key") String authorization) {

		if(authorization != null) {
			return "Tested OK";
		}

		return "Empty Data";
	}
}
