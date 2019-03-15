package in.education.student.common.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

@Configuration
public class MainConfig implements WebMvcConfigurer {

	/* This can be used if not mentioned in properties file
	@Bean
	public ViewResolver getViewResolver(){
	    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
	    resolver.setPrefix("/WEB-INF/view/");
	    resolver.setSuffix(".jsp");
	    
	    // resolver.setPrefix("classpath:/static/");
		// resolver.setSuffix(".html");
	    
	    resolver.setViewClass(JstlView.class);
	    return resolver;
	}*/

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//WebMvcConfigurer.super.addResourceHandlers(registry);
		
		// Register resource handler for CSS and JS
		registry.addResourceHandler("/resources/**")
				.addResourceLocations("classpath:/static/")
				.setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());

		// Register resource handler for images
		registry.addResourceHandler("/images/**")
				.addResourceLocations("classpath:/static/images/")
				.setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());
	}



	/*@Bean
	public FilterRegistrationBean<RequestResponseLoggingFilter> loggingFilter(){
		FilterRegistrationBean<RequestResponseLoggingFilter> registrationBean
				= new FilterRegistrationBean<>();

		registrationBean.setFilter(new RequestResponseLoggingFilter());
		registrationBean.addUrlPatterns("/users/*");

		return registrationBean;
	}*/

}
