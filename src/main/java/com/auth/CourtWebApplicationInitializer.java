package com.auth;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.DispatcherServlet;
//to enable Async Processing, you have to tell all your filters
//and servlets to behave asynchronously. To do this, you can call the setAsyncSupported() method when
//registering a filter or servlet.

/*Luckily, Spring helps you with this, and when using the AbstractAnnotationConfigDispatcherServlet
Initializer as a superclass, this property is enabled by default for the registered DispatcherServlet and
filters. To change it, override isAsyncSupported() and implement the logic to determine whether it should
be on or off.
*/
public class CourtWebApplicationInitializer implements WebApplicationInitializer {
	public void onStartup(ServletContext ctx) {
		DispatcherServlet servlet = new DispatcherServlet();
		ServletRegistration.Dynamic registration = ctx.addServlet("dispatcher", servlet);
		registration.setAsyncSupported(true);
	}
}