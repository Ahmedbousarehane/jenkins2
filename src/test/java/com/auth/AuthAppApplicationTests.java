
package com.auth;

import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.NoSuchElementException;
import java.util.stream.Stream;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.auth.model.User;
import com.auth.repo.UserRepo;
import com.auth.service.IUserService;

@RunWith(SpringRunner.class)
@SpringBootTest 
 public class AuthAppApplicationTests {

	@Autowired
	private   IUserService userService;
    @Mock
	private UserRepo userRepo;
    @Before
    public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);}
	@Test
	public void test_getLogin_with_correct_username() {
		String email = "achraf@gmail.com";
		ReflectionTestUtils.setField(userService, "userRepo", userRepo);
		when(userRepo.getUserbyMail(email)).thenReturn(new User("achraf@gmail.com", "123456"));
		assertThat(userService.getLogin(email).getEmail(), is(email));}
 	@Test
	public void test_login_with_false_username() {
		User user = new User("omar@gmail.com", "123456");
		ReflectionTestUtils.setField(userService, "userRepo", userRepo);
		when(userRepo.getUserbyMail(user.getEmail())).thenReturn(null);
		assertThat(userService.isCorrectLogin(user), is(false));}
 	@Test
	public void test_login_with_correcte_username() {
		User user = new User("achraf@gmail.com", "123456");
		ReflectionTestUtils.setField(userService, "userRepo", userRepo);
		when(userRepo.getUserbyMail(user.getEmail())).thenReturn(new User("achraf@gmail.com", "123456"));
		assertThat(userService.isCorrectLogin(user), is(true));}
 	
	@Test
	public void test_login_with_false_password() {
		User user = new User("ayoub@gmail.com", "1234567");
		ReflectionTestUtils.setField(userService, "userRepo", userRepo);
		when(userRepo.getUserbyMail(user.getEmail())).thenReturn(new User("ayoub@gmail.com", "123456"));
		assertThat(userService.isCorrectLoginPassword(user), is(false));}
	@Test
	public void test_login_with_Correcte_usernameAndpassword() {
		User user = new User("achraf@gmail.com", "123456");
		ReflectionTestUtils.setField(userService, "userRepo", userRepo);
		when(userRepo.getUserbyMail(user.getEmail())).thenReturn(new User("achraf@gmail.com", "123456"));
		assertThat(userService.isCorrectLoginPassword(user), is(true));}

}
