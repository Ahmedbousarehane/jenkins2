package com.auth;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
 import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import javax.servlet.http.HttpSession;
import javax.xml.xpath.XPathExpressionException;

import com.auth.controller.UserController;
import com.auth.model.User;
import com.auth.service.IUserService;
 
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

	/*
	 * InjectMocks creates an instance of the class and injects the mocks that
	 * are created with the @Mock (or @Spy) annotations into this instance
	 */
	@InjectMocks
	private UserController userController;

	@Mock
	private IUserService userService;

	private MockMvc mockMvc;

	@Mock
	HttpSession httpSession;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = standaloneSetup(userController).build();
	}

	@Test
	public void test_index() throws Exception {
		when(httpSession.getAttribute("connectedUser")).thenReturn(new User("nasser@gmail.com","123456"));
		mockMvc.perform(get("/home"))
		.andExpect(status().isOk());
  	}

	@Test
	public void login_test() throws Exception {
		User user = new User("nasser@gmail.com","123456");
		when(userService.isCorrectLoginPassword(user)).thenReturn(true);
		MockHttpServletRequestBuilder createLogin = post("/login")
				.param("email",user.getEmail() )
				.param("motDepasse",user.getMotDepasse());
		System.out.println("dddd "+userService.isCorrectLoginPassword(user));
		mockMvc.perform(createLogin)
		       .andExpect(status().is3xxRedirection())
		       .andExpect(redirectedUrl("/hello"));}
	
	@Test
	public void test_form() throws XPathExpressionException, Exception{
		mockMvc.perform(get("/home/form"))
        .andExpect(xpath("//input[@name='email']").exists())
        .andExpect(xpath("//input[@name='motDepasse']").exists());}

}
