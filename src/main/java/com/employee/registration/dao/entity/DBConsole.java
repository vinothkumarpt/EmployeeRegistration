package com.employee.registration.dao.entity;

import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/*Vinothkumar PT
 * This calss is used to create console for H2 DB Server*/

@Configuration
public class DBConsole {
	//Register a servlet for DB console with the path URI
	@Bean
	ServletRegistrationBean registerH2Servlet(){
		ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
		registrationBean.addUrlMappings("/db/console/*");
		return registrationBean;
	}
}
