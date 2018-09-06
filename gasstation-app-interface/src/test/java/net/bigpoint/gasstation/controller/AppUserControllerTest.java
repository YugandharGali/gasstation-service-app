package net.bigpoint.gasstation.controller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;

import java.time.ZonedDateTime;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import net.bigpoint.gasstation.datatransferobject.UserDTO;
import net.bigpoint.gasstation.domainobject.UserDO;
import net.bigpoint.gasstation.service.UserService;

public class AppUserControllerTest {

	private AppUserController appUserController;
	private UserService userService;
	UserDO userDO;
	UserDTO userDTO;

	@Before
	public void setUp() throws Exception {
		userService = Mockito.mock(UserService.class);
		userDTO = new UserDTO((long) 1, "yuga", "yuga123", "NTY4ZjM1NTYtYWVhNC00OD", ZonedDateTime.now(), false);
		userDO = new UserDO("yuga", "yuga123", "abcd", ZonedDateTime.now());
		appUserController = new AppUserController(userService);
	}

	@After
	public void tearDown() throws Exception {
		userDO = null;
		userDTO = null;
	}

	@Test
	public void testGetAccessToken() throws Exception {
		given(this.userService.getAccessToken("yuga", "yuga123"))
				.willReturn(new UserDO("yuga", "yuga123", "NTY4ZjM1NTYtYWVhNC00OD", ZonedDateTime.now()));
		UserDTO userDTO = appUserController.getAccessToken("yuga", "yuga123");
		assertNotNull(userDTO);
		Assert.assertEquals("yuga", userDTO.getUsername());
	}

	@Test
	public void testGenerateAccessToken() throws Exception {
		given(this.userService.generateAccessToken("yuga", "yuga123"))
				.willReturn(new UserDO("yuga", "yuga123", "NTY4ZjM1NTYtYWVhNC00OD", ZonedDateTime.now()));
		UserDTO userDTO = appUserController.generateAccessToken("yuga", "yuga123");
		assertNotNull(userDTO);
		Assert.assertEquals("yuga", userDTO.getUsername());

	}

}
