package in.education.student.common.notUsing;

import in.education.student.common.security.WebSecurityConfig;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.stereotype.Component;

//@Component
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

	/*public SecurityWebApplicationInitializer() {
		super(WebSecurityConfig.class);
	}*/

	public SecurityWebApplicationInitializer() {
		super(WebSecurityConfig.class);
	}
}
